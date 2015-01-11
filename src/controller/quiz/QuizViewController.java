package controller.quiz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.TreeMap;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import controller.opdracht.OpdrachtController;
import model.*;
import model.catalogi.Catalogi;
import controller.opdracht.*;
import view.QuizView;

public class QuizViewController //extends Controller
{
	private QuizView quizView;
	
	private TreeMap<Integer, Opdracht> selectedOpdrachten = new TreeMap<Integer, Opdracht>();
	
	private Quiz quiz;
	private QuizzenViewController qvc;

	
	public QuizViewController(String titleQuizView) throws Exception
	{		
		quizView = new QuizView(titleQuizView);
		
		this.addListeners();
	}
	
	public QuizViewController(String titleQuizView, QuizzenViewController qvc) throws Exception
	{
		this.qvc = qvc;
		
		quizView = new QuizView(titleQuizView);
		
		this.addListeners();
	}

	public QuizViewController(Quiz quiz, QuizzenViewController qvc) throws Exception
	{
		this.qvc = qvc;
		
		this.quiz = quiz;
		selectedOpdrachten = quiz.getOpdrachten();
		quizView = new QuizView(quiz);
		
		this.addListeners();
	}
	
	private void addListeners()
	{
		this.quizView.addOpdrachtSelectedListener(new OpdrachtSelectionListener());
		this.quizView.addBtnVoegOpdrachtToeListener(new BtnVoegOpdrachtToeListener());
		this.quizView.addBtnVerwijderOpdrachtListener(new BtnVerwijderOpdrachtListener());
		this.quizView.addBtnOpdrachtUpListener(new BtnOpdrachtUpListener());
		this.quizView.addBtnRegistreerListener(new BtnRegistreerListener());
		this.quizView.addBtnNieuweOpdrachtListener(new BtnNieuweOpdrachtListener());
		this.quizView.addCbxCategorieListener(new CategorieSelectionListener());
		this.quizView.addCbxSorteringListener(new SorteringSelectionListener());
	}
	
	public void updateQuizView()
	{
		quizView.getAllOpdrachtenTable().setRowSelectionAllowed(false);
		quizView.getAllOpdrachtenTable().setRowSelectionAllowed(true);
	}
	
	class OpdrachtSelectionListener implements ListSelectionListener
	{

		@Override
		public void valueChanged(ListSelectionEvent event)
		{
			if(event.getValueIsAdjusting()) return;
			if(quizView.getAllOpdrachtenTable().getRowSelectionAllowed())
				quizView.setLbOpdrachtDetails(quizView.getSelectedOpdracht(quizView.getAllOpdrachtenTable()));
		}		
	}
	
	class BtnVoegOpdrachtToeListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event)
		{
			if(quizView.getSelectedOpdracht(quizView.getAllOpdrachtenTable()) == null)
				JOptionPane.showMessageDialog(null, "Selecteer Opdracht om toe te voegen", "alert", JOptionPane.ERROR_MESSAGE);	
			else if (!selectedOpdrachten.containsValue(quizView.getSelectedOpdracht(quizView.getAllOpdrachtenTable())))
			{
				selectedOpdrachten.put(selectedOpdrachten.size(), quizView.getSelectedOpdracht(quizView.getAllOpdrachtenTable()));
				quizView.addSelectedOpdrachtToPane(quizView.getSelectedOpdracht(quizView.getAllOpdrachtenTable()));
				quizView.setLbOpdrachtenAantal(selectedOpdrachten.size());
			} 
			else
			{
				JOptionPane.showMessageDialog(null, "Opdracht is al in Quiz", "alert", JOptionPane.ERROR_MESSAGE);	
			}
		}}
	
	class BtnVerwijderOpdrachtListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event)
		{
			if (quizView.getSelectedOpdracht(quizView.getSelectedOpdrachtenTable()) == null)
				JOptionPane.showMessageDialog(null, "Selecteer Opdracht om toe te verwijderen", "alert", JOptionPane.ERROR_MESSAGE);	
			else
			{
				for(Integer i : selectedOpdrachten.keySet())
				{
					if (selectedOpdrachten.get(i) == quizView.getSelectedOpdracht(quizView.getSelectedOpdrachtenTable()))
					{
						selectedOpdrachten.remove(i);
					}
				}
				quizView.removeSelectedOpdrachtVanPane(quizView.getSelectedOpdrachtenTable().getSelectedRow());
				quizView.setLbOpdrachtenAantal(selectedOpdrachten.size());
			}
		}}
	
	class BtnOpdrachtUpListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			if (quizView.getSelectedOpdracht(quizView.getSelectedOpdrachtenTable()) == null)
				JOptionPane.showMessageDialog(null, "Selecteer Opdracht om UP te verplaatsen", "alert", JOptionPane.ERROR_MESSAGE);	
			else
			{
				int previous = 0;
				for(Integer i : selectedOpdrachten.keySet())
				{
					if (selectedOpdrachten.get(i) == quizView.getSelectedOpdracht(quizView.getSelectedOpdrachtenTable()))
					{
						Opdracht o = selectedOpdrachten.get(i);
						Opdracht p = selectedOpdrachten.get(previous);
						selectedOpdrachten.replace(i, o, p);
						selectedOpdrachten.replace(previous, p, o);				
					}
					previous = i;
				}
				quizView.moveUp(quizView.getSelectedOpdrachtenTable().getSelectedRow());
			}
		}}

	class BtnRegistreerListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			try
			{
				int quizID = -1;
				if (quiz == null)
				{
					quiz = new Quiz(Catalogi.getQuizzen().getLastQuizID(), quizView.getOnderwerp(), quizView.getKlas(), false, false, quizView.getQuizStatus(), quizView.getAuteur());
					for(Opdracht opdracht : selectedOpdrachten.values())
					{
						QuizOpdracht.koppelOpdrachtAanQuiz(quiz, opdracht, 
								quizView.getMaxScorePerQuizOpdracht(opdracht));
					}
				}
				else
				{
					quizID = quiz.getQuizID();
				              
					Catalogi.getQuizzen().verwijderQuiz(quiz);
					quiz = new Quiz(quizID, quizView.getOnderwerp(), quizView.getKlas(), false, false, quizView.getQuizStatus(), quizView.getAuteur());
					for(Opdracht opdracht : selectedOpdrachten.values())
					{
						QuizOpdracht.koppelOpdrachtAanQuiz(quiz, opdracht, 
								quizView.getMaxScorePerQuizOpdracht(opdracht));
					}
				}
				Catalogi.getQuizzen().voegQuizToe(quizID, quiz);
				//TODO
				//Collections.sort(quizCatalogus.getQuizzen());
				qvc.updateQuizzenView();
				
			} catch (Exception e1)
			{
				JOptionPane.showMessageDialog(null, "Fout bij Quiz Registratie", "alert", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
		}}
	
	class BtnNieuweOpdrachtListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			try
			{
				//TODO: wat moet dat hier doen? Een nieuwe opdracht als leraar aanmaken???
				//OpdrachtController ovc = new OpdrachtController(null , QuizViewController.this);
			} catch (Exception e1)
			{
				e1.printStackTrace();
			}		
		}}
	
	class CategorieSelectionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			quizView.getAllOpdrachtenTable().setRowSelectionAllowed(false);
			TreeMap<Integer, Opdracht> opdrachten = new TreeMap<Integer, Opdracht>();
			for(Entry<Integer,Opdracht> opdracht : Catalogi.getOpdrachten())
			{
				if(opdracht.getValue().getCategorie() == quizView.getSelectedOpdrachCategorie() || quizView.getSelectedOpdrachCategorie() == null)
				{
					opdrachten.put(opdrachten.size(), opdracht.getValue());
				}
			}
			quizView.setGesoorteerdeOpdrachten(opdrachten);
			quizView.getAllOpdrachtenTable().setRowSelectionAllowed(true);
		}}
	
	class SorteringSelectionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			TreeMap<Integer, Opdracht> opdrachten = new TreeMap<Integer, Opdracht>();	
			ArrayList<Opdracht> sortedOpdrachten = quizView.getOpdrachtenInTable(quizView.getAllOpdrachtenTable());
			switch(quizView.getSelectedSorterigType())
			{
			case "geen":
				break;
			case "categorie":
				Collections.sort(sortedOpdrachten, new Comparator<Opdracht>(){

					@Override
					public int compare(Opdracht opdracht1, Opdracht opdracht2)
					{
						return opdracht1.compareCategorie(opdracht2);
					}});
				break;
			case "vraag":
				Collections.sort(sortedOpdrachten, new Comparator<Opdracht>(){

					@Override
					public int compare(Opdracht opdracht1, Opdracht opdracht2)
					{
						return opdracht1.compareVraag(opdracht2);
					}});
				break;
			default:
				break;
			}
			for(Opdracht opdracht : sortedOpdrachten)
			{
				opdrachten.put(opdrachten.size(), opdracht);
			}
			quizView.setGesoorteerdeOpdrachten(opdrachten);
		}}
	
	
	public static void main(String[] args) throws Exception
	{
		try
		{
			 new QuizViewController("test");
		} 
		catch 
		(InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}	
	}
}

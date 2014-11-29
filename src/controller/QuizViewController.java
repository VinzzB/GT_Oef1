package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.*;
import persistency.*;
import view.QuizView;

public class QuizViewController
{
	private QuizView quizView;
	private QuizCatalogus quizCatalogus;
	private OpdrachtCatalogus opdrachtCatalogus;
	private ArrayList<QuizOpdracht> quizOpdrachten;
	
	private TreeMap<Integer, Opdracht> selectedOpdrachten = new TreeMap<Integer, Opdracht>();
	
	private DatabaseHandler db;
	private Quiz quiz;
	private QuizzenViewController qvc;

	
	public QuizViewController(String titleQuizView) throws Exception
	{
		db  = new DatabaseHandler();
		db.vulCatalogus();
		
		quizCatalogus = Database.getQuizCatalogus();
		opdrachtCatalogus = Database.getOpdrachtCatalogus();
		quizOpdrachten = Database.getQuizOpdrachten();
		
		quizView = new QuizView(titleQuizView);
		
		this.quizView.addOpdrachtSelectedListener(new OpdrachtSelectionListener());
		this.quizView.addBtnVoegOpdrachtToeListener(new BtnVoegOpdrachtToeListener());
		this.quizView.addBtnVerwijderOpdrachtListener(new BtnVerwijderOpdrachtListener());
		this.quizView.addBtnOpdrachtUpListener(new BtnOpdrachtUpListener());
		this.quizView.addBtnRegistreerListener(new BtnRegistreerListener());
	}
	
	public QuizViewController(String titleQuizView, DatabaseHandler db, QuizzenViewController qvc) throws Exception
	{
		this.db  = db;
		this.qvc = qvc;
		
		quizCatalogus = Database.getQuizCatalogus();
		opdrachtCatalogus = Database.getOpdrachtCatalogus();
		quizOpdrachten = Database.getQuizOpdrachten();
		
		quizView = new QuizView(titleQuizView);
		
		this.quizView.addOpdrachtSelectedListener(new OpdrachtSelectionListener());
		this.quizView.addBtnVoegOpdrachtToeListener(new BtnVoegOpdrachtToeListener());
		this.quizView.addBtnVerwijderOpdrachtListener(new BtnVerwijderOpdrachtListener());
		this.quizView.addBtnOpdrachtUpListener(new BtnOpdrachtUpListener());
		this.quizView.addBtnRegistreerListener(new BtnRegistreerListener());
	}

	public QuizViewController(Quiz quiz, DatabaseHandler db, QuizzenViewController qvc) throws Exception
	{
		this.db = db;
		this.qvc = qvc;
		
		quizCatalogus = Database.getQuizCatalogus();
		opdrachtCatalogus = Database.getOpdrachtCatalogus();
		
		this.quiz = quiz;
		selectedOpdrachten = quiz.getOpdrachten();
		quizView = new QuizView(quiz);
		
		this.quizView.addOpdrachtSelectedListener(new OpdrachtSelectionListener());
		this.quizView.addBtnVoegOpdrachtToeListener(new BtnVoegOpdrachtToeListener());
		this.quizView.addBtnVerwijderOpdrachtListener(new BtnVerwijderOpdrachtListener());
		this.quizView.addBtnOpdrachtUpListener(new BtnOpdrachtUpListener());
		this.quizView.addBtnRegistreerListener(new BtnRegistreerListener());
		this.quizView.addBtnNieuweOpdracht(new BtnNieuweOpdrachtListener());
	}
	class OpdrachtSelectionListener implements ListSelectionListener
	{

		@Override
		public void valueChanged(ListSelectionEvent event)
		{
			if(event.getValueIsAdjusting()) return;
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
					quiz = new Quiz(quizCatalogus.setQuizID(), quizView.getOnderwerp(), quizView.getKlas(), false, false, quizView.getQuizStatus(), quizView.getAuteur());
					for(Opdracht opdracht : selectedOpdrachten.values())
					{
						QuizOpdracht.koppelOpdrachtAanQuiz(quiz, opdracht, 
								quizView.getMaxScorePerQuizOpdracht(opdracht));
					}
				}
				else
				{
					quizID = quiz.getQuizID();
				              
					quizCatalogus.verwijderQuiz(quiz);
					quiz = new Quiz(quizID, quizView.getOnderwerp(), quizView.getKlas(), false, false, quizView.getQuizStatus(), quizView.getAuteur());
					for(Opdracht opdracht : selectedOpdrachten.values())
					{
						QuizOpdracht.koppelOpdrachtAanQuiz(quiz, opdracht, 
								quizView.getMaxScorePerQuizOpdracht(opdracht));
					}
				}
				quizCatalogus.voegQuizToe(quizID, quiz);
				Collections.sort(quizCatalogus.getQuizzen());
				db.safeCatalogus();
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
			// TODO Auto-generated method stub
			
		}}
	
	public static void main(String[] args) throws Exception
	{
		try
		{
			QuizViewController qvc = new QuizViewController("test");
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

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.TreeMap;

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
	
	private TreeMap<Integer, Opdracht> selectedOpdrachten = new TreeMap<Integer, Opdracht>();
	private DatabaseHandler db;

	
	public QuizViewController(String titleQuizView) throws Exception
	{
		db  = new DatabaseHandler();
		db.vulCatalogus();
		
		quizCatalogus = Database.getQuizCatalogus();
		opdrachtCatalogus = Database.getOpdrachtCatalogus();
		
		quizView = new QuizView(titleQuizView);
		
		this.quizView.addOpdrachtSelectedListener(new OpdrachtSelectionListener());
		this.quizView.addBtnVoegOpdrachtToeListener(new BtnVoegOpdrachtToeListener());
		this.quizView.addBtnVerwijderOpdrachtListener(new BtnVerwijderOpdrachtListener());
		this.quizView.addBtnOpdrachtUpListener(new BtnOpdrachtUpListener());
		this.quizView.addBtnRegistreerLitener(new BtnRegistreerListener());
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
			for(Quiz q : quizCatalogus)
			{
				System.out.println(q);
			}
			try
			{
				Quiz q = new Quiz(quizCatalogus.setQuizID(), quizView.getOnderwerp(), quizView.getKlas(), false, false, quizView.getQuizStatus());
				for(Opdracht o : selectedOpdrachten.values())
				{
					QuizOpdracht.koppelOpdrachtAanQuiz(q, o, quizView.getMaxScorePerQuizOpdracht(o));
				}
				quizCatalogus.voegQuizToe(q);
				db.safeCatalogus();
			} catch (Exception e1)
			{
				JOptionPane.showMessageDialog(null, "Fout bij Quiz Registratie", "alert", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
			for(Quiz q : quizCatalogus)
			{
				System.out.println(q);
			}
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

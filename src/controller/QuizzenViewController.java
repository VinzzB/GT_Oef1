package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.*;
import persistency.Database;
import persistency.DatabaseHandler;
import view.QuizzenView;


public class QuizzenViewController extends Controller
{	
	private QuizzenView quizzenView;
	
	public QuizzenViewController(String titleQuizzenView) throws Exception
	{		
		quizzenView = new QuizzenView(titleQuizzenView);
		
		addListeners();
	}
	
	private void addListeners()
	{
		quizzenView.addQuizSelectedListener(new QuizSelectionListener());
		quizzenView.addBtnNieuweListener(new BtnNieuweListener());
		quizzenView.addBtnUpdateListener(new BtnUpdateListener());
		quizzenView.addBtnVerwijderListener(new BtnVerwijderListener());
		quizzenView.addWindowClosingListener(new WindowClosedListener());
	}
	
	public void updateQuizzenView()
	{
		quizzenView.updateViewQuizzen();
	}
	
	class QuizSelectionListener implements ListSelectionListener{

		@Override
		public void valueChanged(ListSelectionEvent e)
		{
			if(quizzenView.getSelectedQuiz() != null)
			{
				TreeMap<Integer, Opdracht> opdrachten = quizzenView.getSelectedQuiz().getOpdrachten();
				String[] s = new String[opdrachten.size()];
				int i = 0;
				for(Opdracht opdracht : opdrachten.values())
				{
					for(QuizOpdracht qo : quizzenView.getSelectedQuiz().getQuizOpdrachten())
						if(qo.getOpdracht() == opdracht)
						{
							s[i] = opdracht.toString() + " maxScore: " + qo.getMaxScore();
						}
					i++;
				}
				quizzenView.setOpdrachten(s);
				quizzenView.setLbAuteur(quizzenView.getSelectedQuiz().getAuteur().toString());
				quizzenView.setLbCreatieDatum(quizzenView.getSelectedQuiz().getDatumVanCreatie().toString());
			}
		}
	}
	
	class BtnNieuweListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			try
			{
				QuizViewController qvc = new QuizViewController("Maak nieuwe Quiz", QuizzenViewController.this);
			} catch (Exception e1)
			{
				JOptionPane.showMessageDialog(null, e1.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
		}}
	
	class BtnUpdateListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			try
			{
				if(quizzenView.getSelectedQuiz() != null)
				{
					QuizViewController qvc = new QuizViewController(quizzenView.getSelectedQuiz(), QuizzenViewController.this);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Selecteer een Quiz om up te daten", "alert", JOptionPane.ERROR_MESSAGE);
				}
			} catch (Exception e1)
			{
				JOptionPane.showMessageDialog(null, e1.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
			
		}}
	
	class BtnVerwijderListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			int i = quizzenView.getSelectedIndex();
			quizCatalogus.verwijderQuiz(quizzenView.getSelectedQuiz());
			quizzenView.removeQuiz(i);

		}}
	
	class WindowClosedListener extends WindowAdapter
	{
		@Override
		public void windowClosing(WindowEvent e)
		{
		}
	}

	public static void main(String[] args)
	{
		try
		{
			QuizzenViewController qvc = new QuizzenViewController("test");
		} catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
}

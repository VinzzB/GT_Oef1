package controller;

import java.lang.reflect.InvocationTargetException;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.OpdrachtCatalogus;
import model.QuizCatalogus;
import persistency.Database;
import persistency.DatabaseHandler;
import view.QuizView;

public class QuizViewController
{
	private QuizView quizView;
	private QuizCatalogus quizCatalogus = Database.getQuizCatalogus();
	private OpdrachtCatalogus opdrachtCatalogus = Database.getOpdrachtCatalogus();
	
	
	public QuizViewController(QuizView quizView)
	{
		this.quizView = quizView;
		
		this.quizView.addOpdrachtSelectedListener(new OpdrachtSelectionListener());
	}
	
	class OpdrachtSelectionListener implements ListSelectionListener
	{

		@Override
		public void valueChanged(ListSelectionEvent event)
		{
			if(event.getValueIsAdjusting()) return;
			quizView.setLbOpdrachtDetails(opdrachtCatalogus.getOpdracht(quizView.getTable().convertRowIndexToModel(quizView.getTable().getSelectedRow()) + 1));
			System.out.println(quizView.getTable().convertColumnIndexToModel(quizView.getTable().getSelectedRow()));
		}
		
	}

	public static void main(String[] args) throws Exception
	{
		DatabaseHandler db;
		try
		{
			db = new DatabaseHandler();
			db.vulCatalogus();
		} 
		catch 
		(InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		QuizViewController qvc = new QuizViewController(new QuizView("test"));
	}

}

package controller;

import model.Opdracht;
import model.OpdrachtCatalogus;
import model.Quiz;
import model.QuizCatalogus;
import model.QuizOpdracht;
import persistency.DatabaseHandler;

public class PersistencyController
{
	public static void main(String[] args)
	{
		try
		{
			OpdrachtCatalogus oc = new OpdrachtCatalogus();
			QuizCatalogus qc = new QuizCatalogus();
			DatabaseHandler db = new DatabaseHandler();
			Opdracht opdracht1 = new Opdracht("Wat is de hoofdstad van Franrijk?","Parijs");
			Opdracht opdracht2 = new Opdracht("Wat is de hoofdstad van Spanje?","Madrid");
			oc.voegOpdrachtToe(opdracht1);
			oc.voegOpdrachtToe(opdracht2);
			Quiz quiz = new Quiz("Hoofdsteden Europa");
			qc.voegQuizToe(quiz);
			QuizOpdracht.koppelOpdrachtAanQuiz(quiz, opdracht1, 2);
			QuizOpdracht.koppelOpdrachtAanQuiz(quiz, opdracht2, 2);
			db.getDb().setCatalogus(oc, qc);
			db.safeCatalogus();
			
			DatabaseHandler db2 = new DatabaseHandler();
			db2.vulCatalogus();
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		} 
	}

}

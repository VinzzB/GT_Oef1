package controller;

import java.util.ArrayList;

import model.OpdrachtCatalogus;
import model.QuizCatalogus;
import persistency.Catalogi;
import persistency.DatabaseHandler;
import model.QuizOpdracht;

public abstract class Controller
{
	protected DatabaseHandler db = OpstartController.getDatabaseStrategy();
	protected QuizCatalogus quizCatalogus = Catalogi.get().getQuizzen();
	protected OpdrachtCatalogus opdrachtCatalogus = Catalogi.get().getOpdrachten();
	
	/**
	 * @return the db
	 */
//	public DatabaseHandler getDb()
//	{
//		return db;
//	}

	/**
	 * @return the quizCatalogus
	 */
	public QuizCatalogus getQuizCatalogus()
	{
		return quizCatalogus;
	}
	
	/**
	 * @return the opdrachtCatalogus
	 */
	public OpdrachtCatalogus getOpdrachtCatalogus()
	{
		return opdrachtCatalogus;
	}
//	
//	/**
//	 * @return the quizOpdrachten
//	 */
//	public ArrayList<QuizOpdracht> getQuizOpdrachten()
//	{
//		return quizOpdrachten;
//	}
}

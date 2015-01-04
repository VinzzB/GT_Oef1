package controller;

import java.util.ArrayList;

import src.driver.Driver;
import src.model.opdracht.OpdrachtCatalogus;
import src.model.QuizCatalogus;
import src.persistency.DatabaseHandler;
import src.model.QuizOpdracht;

public abstract class Controller
{
	protected DatabaseHandler db = Driver.getDatabaseStrategy();
	protected QuizCatalogus quizCatalogus = Driver.getQuizCatalogus();
	protected OpdrachtCatalogus opdrachtCatalogus = Driver.getOpdrachtCatalogus();
	protected ArrayList<QuizOpdracht> quizOpdrachten = Driver.getQuizOpdrachten();
	
	/**
	 * @return the db
	 */
	public DatabaseHandler getDb()
	{
		return db;
	}

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
	
	/**
	 * @return the quizOpdrachten
	 */
	public ArrayList<QuizOpdracht> getQuizOpdrachten()
	{
		return quizOpdrachten;
	}
}

package driver;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import src.controller.QuizzenViewController;
import src.controller.StartViewController;
import src.model.opdracht.OpdrachtCatalogus;
import src.model.QuizCatalogus;
import src.model.QuizOpdracht;
import src.persistency.*;
import src.view.LeesDatabaseGui;

public class Driver
{
	private static DatabaseHandler db;	
	private static OpdrachtCatalogus opdrachten; 
	private static QuizCatalogus quizzen;
	private static ArrayList<QuizOpdracht> quizOpdrachten;
	
	public Driver() throws Exception
	{
		db  = new DatabaseHandler();
		this.setDatabaseStrategy();
		db.vulCatalogus();
		opdrachten = db.getDatabaseStrategy().getOpdrachtCatalogus();
		quizzen = db.getDatabaseStrategy().getQuizCatalogus();
		quizOpdrachten =db.getDatabaseStrategy().getQuizOpdrachten();
		
		StartViewController start = new StartViewController();
	}
	
	/**
	 * Roept een GUI om gebruiker te vragen over welke manier van
	 * van opslagen en lezen van objecten hij wilt gebruiken.
	 *  	
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws ClassNotFoundException
	 */
	private void setDatabaseStrategy() throws InstantiationException, IllegalAccessException, 
											  IllegalArgumentException, InvocationTargetException, 
											  ClassNotFoundException, NoSuchMethodException, SecurityException
	{
		LeesDatabaseGui ldb = new LeesDatabaseGui();
		Class<?> dataBase = Class.forName(ldb.kiesDatabaseStrategy());
		Constructor<?> ctor = dataBase.getConstructor();
		db.setDatabaseStrategy((IDatabaseStrategy)ctor.newInstance());
	}
	
	public static DatabaseHandler getDatabaseStrategy()
	{
		return db;
	}
	
	public static QuizCatalogus getQuizCatalogus()
	{
		return quizzen;
	}
	
	public static OpdrachtCatalogus getOpdrachtCatalogus()
	{
		return opdrachten;
	}
	
	public static ArrayList<QuizOpdracht> getQuizOpdrachten()
	{
		return quizOpdrachten;
	}

	public static void main(String[] args) throws Exception
	{
		Driver d = new Driver();
	}

}

package controller;
/**
 * Deze klasse bevat een main methode om project op te starten: 
 * Het menu wordt getoond en bij elke menukeuze wordt de overeenkomstige 
 * controllerklasse geactiveerd.
 * De opstartcontroller zal ook de OpdrachtCatalogus, de QuizCatalogus en 
 * de LeerlingContainer opvullen met objecten (afkomstig uit de databank) . 
 * De opstartcontroller zal tevens (bij het gebruik van design patterns) met 
 * behulp van Factory klassen de juiste strategieën inpluggen (vb rekenregel 
 * voor berekenen quiz score)
 *
 *@author Natalia Dyubankova
 */
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import persistency.*;
import utils.Constants;
import utils.LoadProperties;
import view.LeesDatabaseGui;

public class OpstartController
{
	private static DatabaseHandler db;
	private static LoadProperties properties;
	
	public OpstartController() throws Exception
	{
		db  = new DatabaseHandler();
		this.setDatabaseStrategy();
		db.vulCatalogus();
		properties = new LoadProperties(new File(Constants.SETTINGS_PATH + 
				Constants.SETTINGS_FILE));
		
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
	
	public static LoadProperties getProperties()
	{
		return properties;
	}

	public static void main(String[] args) throws Exception
	{
		OpstartController d = new OpstartController();
	}

}

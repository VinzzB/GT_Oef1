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

import java.lang.reflect.InvocationTargetException;
import persistency.*;
import view.LeesDatabaseGui;

public class OpstartController
{

	public OpstartController() throws Exception
	{
		getDatabaseStrategy(); //MogelijkeDatabasen.TXTbestand);
		Catalogi.LoadData();				
		new StartViewController();
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
	private void getDatabaseStrategy() throws InstantiationException, IllegalAccessException, 
											  IllegalArgumentException, InvocationTargetException, 
											  ClassNotFoundException, NoSuchMethodException, SecurityException
	{
		LeesDatabaseGui ldb = new LeesDatabaseGui();
	//	Class<?> dataBase = Class.forName(ldb.kiesDatabaseStrategy());
	//	Constructor<?> ctor = dataBase.getConstructor();
		DatabaseHandler.setDatabaseStrategy(ldb.kiesDatabaseStrategy());
		
	}	

	public static void main(String[] args) throws Exception
	{
		new OpstartController();
	}

}

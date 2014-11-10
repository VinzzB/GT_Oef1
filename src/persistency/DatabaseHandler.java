package persistency;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import view.LeesDatabaseGui;

/**
 * Facade klasse. Gebruik om manieren 
 * van opslagen en lezen van objecten te kiesen.
 * 
 * Bij toevoegen van een nieuwe manier hoeft niet verandered worden 
 * 
 * @author Natalia Dyubankova <fornnd@gmail.com>
 * @version     1.0                 
 * @since       2014-11-11 
 */
public class DatabaseHandler
{
	private IDatabaseStrategy db; 
	/**
	 * @return the db
	 */
	public IDatabaseStrategy getDb()
	{
		return db;
	}
		
	public DatabaseHandler() throws InstantiationException, IllegalAccessException, 
									IllegalArgumentException, InvocationTargetException, 
									NoSuchMethodException, SecurityException, 
									ClassNotFoundException 
	{
		setDatabaseStrategy();
	}

/**
 * Leest objecten van de files en voelt database in.
 * 	
 * @throws FileNotFoundException
 * @throws IOException
 */
	public void vulCatalogus() throws FileNotFoundException, IOException
	{
		db.leesOpdrachten();
		db.leesQuzen();
		db.kopelQuizOpdrachten();
	}
/**
 * Schrijft huidige objecten weg in de files	
 */
	public void safeCatalogus()
	{
		db.safeOpdrachten();
		db.safeQuizen();
		db.safeQuizOpdrachten();
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
	public void setDatabaseStrategy() throws InstantiationException, IllegalAccessException, 
											IllegalArgumentException, InvocationTargetException, 
											NoSuchMethodException, SecurityException, 
											ClassNotFoundException
	{
		LeesDatabaseGui ldb = new LeesDatabaseGui();
		Class<?> dataBase = Class.forName(ldb.kiesDatabaseStrategy());
		Constructor<?> ctor = dataBase.getConstructor();
		this.db = (IDatabaseStrategy)ctor.newInstance();
		
		System.out.print(db.getClass() + "\n");
	}
}

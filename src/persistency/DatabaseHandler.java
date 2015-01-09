package persistency;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

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
		
	public DatabaseHandler(){}

/**
 * Leest objecten van de files en voelt database in.
 * @throws Exception 
 * @throws NumberFormatException 
 */
	public void vulCatalogus() throws NumberFormatException, Exception
	{
		db.leesOpdrachten();
		db.leesQuzen();
		db.kopelQuizOpdrachten();
	}
/**
 * Schrijft huidige objecten weg in de files	
 * @throws SQLException 
 * @throws IOException 
 * @throws FileNotFoundException 
 */
	public void safeCatalogus() throws SQLException, FileNotFoundException, IOException
	{
		db.safeOpdrachten();
		db.safeQuizen();
		db.safeQuizOpdrachten();
	}
	
	/**
	 * @return the db
	 */
	public IDatabaseStrategy getDatabaseStrategy()
	{
		return db;
	}
	public void setDatabaseStrategy(IDatabaseStrategy db)
	{
		this.db =db;
	}
}

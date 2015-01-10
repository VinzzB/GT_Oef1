package persistency;

/**
 * Facade klasse. Gebruik om manieren 
 * van opslagen en lezen van objecten te kiesen.
 * 
 * Bij toevoegen van een nieuwe manier hoeft niet verandered worden 
 * 
 * @author Natalia Dyubankova <fornnd@gmail.com>
 * @version     1.0                 
 * @since       2014-11-11 
 * @Revisioned Vincent on 10/01/2015
 */
public class DatabaseHandler
{
	private static IDatabaseStrategy db;
			
	private DatabaseHandler(){}
	
	public static IDatabaseStrategy instance()
	{
		if (db == null)
			db = MogelijkeDatabasen.TXTbestand.getDbStrategy();
		return db;
	}	
	
	/**
	 * @return the db
	 */
	public IDatabaseStrategy getDatabaseStrategy()
	{
		return db;
	}
	public static void setDatabaseStrategy(IDatabaseStrategy newDb)
	{
		db =newDb;
	}
}

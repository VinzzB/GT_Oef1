package persistency;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import view.LeesDatabaseGui;

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
	
	public void vulCatalogus()
	{
		db.leesOpdrachten();
		db.leesQuzen();
		db.leesQuizOpdachten();
		db.kopelQuizOpdrachten();
	}
	
	public void safeCatalogus()
	{
		db.safeOpdrachten();
		db.safeQuizen();
		db.safeQuizOpdrachten();
	}
	
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

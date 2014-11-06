package persistency;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import view.LeesDatabaseGui;

public class DatabaseHandler
{
	private IDatabaseStrategy db; 
		
	public DatabaseHandler() throws InstantiationException, IllegalAccessException, 
									IllegalArgumentException, InvocationTargetException, 
									NoSuchMethodException, SecurityException, 
									ClassNotFoundException 
	{
		setDatabaseStrategy();
	}
	
	public void vulCatalogs()
	{
		db.leesOpdrachten();
		db.leesQuzen();
		db.leesQuizOpdrachten();
		db.kopelQuizOpdrachten();
	}
	
	public void safeCatalogs()
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
	}
}

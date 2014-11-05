package persistency;

public class DatabaseHandler
{
	private DatabaseStrategy db; 
	
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
	
	public void setDatabaseStrategy(DatabaseStrategy db)
	{
		this.db = db;
	}
}

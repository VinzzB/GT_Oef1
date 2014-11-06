package persistency;

public enum MogelijkeDatabasen
{	
	TXTbestand ("TXT-bestand", DatabaseTXT.class),
	MySQLbestand ("MySQL-bestand", DatabaseMySQL.class);
	
	private final String name;
	private final Class<? extends IDatabaseStrategy> dbStrategy;
	
	MogelijkeDatabasen(String name, Class<? extends IDatabaseStrategy> dbStrategy) 
	{ 
		this.name = name;
		this.dbStrategy = dbStrategy;
	}

	public String getName()
	{
		return name;
	}

	public Class<? extends IDatabaseStrategy> getDbStrategy()
	{
		return dbStrategy;
	}
		
	@Override
	public String toString()
	{
		return MogelijkeDatabasen.this.name();
	}
}




package persistency;

/**
 * Enumeration van mogelijke manieren van opslagen en lezen van objecten
 * 
 * On nieuwe manier toe to voegen maak eerst een nieuwe klasse
 * met lees/schrijf techniken en daarna voeg een nieuwe field in 
 * enumeration MogelijkeDatbasen
 * bv TXTbestand ("TXT-bestand", DatabaseTXT.class)
 * 
 * @author Natalia Dyubankova <fornnd@gmail.com>
 * @version     1.0                 
 * @since       2014-11-11 
 */
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




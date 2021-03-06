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
 * @revisioned Vincent on 10/01/2015
 */
public enum DbStores
{	
	TXTbestand ("TXT-bestand", DbPlainText.class),
	MySQLbestand ("MySQL-bestand", DbMySql.class);
	
	private final String name;
	private final IDatabaseStrategy dbStrategy;
	
	DbStores(String name, Class<? extends IDatabaseStrategy> dbStrategy)  
	{ 
		this.name = name;
		this.dbStrategy = CreateStrategyClass(dbStrategy);
	}
	
	private IDatabaseStrategy CreateStrategyClass(Class<? extends IDatabaseStrategy> dbStrategy)
	{
		try
		{ return dbStrategy.newInstance(); }
		catch (Exception ex){ }
		return null;
	}

	public String getName()
	{
		return name;
	}

	public IDatabaseStrategy getDbStrategy()
	{
		return dbStrategy;
	}
		
	@Override
	public String toString()
	{
		return DbStores.this.name();
	}
}




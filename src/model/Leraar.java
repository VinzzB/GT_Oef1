package model;

public enum Leraar 
{
	LERAAR1("AA", "aa"),
	LERAAR2("BB", "bb"),
	LERAAR3("CC", "cc"),
	LERAAR4("DD", "dd");
	
	private final String naam;
	private final String voornaam;
	
	Leraar(String naam, String voornaam) 
	{
		this.naam = naam;
		this.voornaam = voornaam;
	}
	
	public String getNaam()
	{
		return this.naam;
	}
	
	public String getVoornaam()
	{
		return this.voornaam;
	}
	
	public String toString()
	{
		return this.naam + " " + this.voornaam;
	}
}

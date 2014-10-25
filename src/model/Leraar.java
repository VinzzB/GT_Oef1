package model;

public enum Leraar 
{
	LEERAAR1("", ""),
	LEERAAR2("", ""),
	LEERAAR3("", ""),
	LEERAAR4("", "");
	
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
		return this.naam + "" + this.voornaam;
	}
}

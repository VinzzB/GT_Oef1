package model;

public enum Leraar 
{
	LERAAR1("", ""),
	LERAAR2("", ""),
	LERAAR3("", ""),
	LERAAR4("", "");
	
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

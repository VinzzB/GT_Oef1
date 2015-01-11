package model.opdrachten;

public enum OpdrachtTypen {
	VRAAG("Vraag / antwoord"), 
	MEERKEUZE("Meerkeuze / antwoord numeriek"), 
	OPSOMMING("Opsomming / antwoord (in volgorde)"),
	REPRODUCTIE("Reproductie");
	
	final String omschrijving;
	
	private OpdrachtTypen(String omschrijving) 
	{
		this.omschrijving = omschrijving;
	}
	
	public String getOmschrijving()
	{
		return omschrijving;
	}
	
	@Override
	public String toString() 
	{
		return getOmschrijving();
	}
}

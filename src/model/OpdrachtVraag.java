package model;

import persistency.framework.DbOpdrachtBase;
import model.Leraar;
import model.Opdracht;

public class OpdrachtVraag extends Opdracht 
{

	public OpdrachtVraag() 
	{
		super();
	}

	/**
	 * Creert een nieuwe opdracht van het type Vraag / antwoord.
	 * @param auteur
	 * @param categorie
	 * @param vraag
	 * @param juisteAntwoord
	 * @throws Exception 
	 */
	public OpdrachtVraag(Leraar auteur, OpdrachtCategorie categorie,
			String vraag, String antwoord)  
					throws Exception  
	{
		super(vraag,antwoord);
		super.setCategorie(categorie);
		super.setAuteur(auteur);		
	}
	
	/**
	 * Constructor om instantie van Meerkeuze opdracht te crieeren van TXTbestand
	 * + keuzen
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	public OpdrachtVraag(DbOpdrachtBase vanTXTBestand) 
			throws NumberFormatException, Exception 
	{
		super(vanTXTBestand);		
	}
	
	public OpdrachtVraag(OpdrachtVraag fromObj) throws Exception
	{
		super(fromObj);		
	}
	
	@Override
	public boolean isJuisteAntwoord(String antwoord) 
	{
		return super.isJuisteAntwoord(antwoord);	
	}

	@Override
	public String toBestand()
	{
		return super.toBestand();
	}


	@Override
	public OpdrachtTypen getType() 
	{
		return OpdrachtTypen.VRAAG;
	}

	@Override
	public Opdracht clone() throws CloneNotSupportedException
	{
		try
		{ return new OpdrachtVraag(); }
		catch (Exception e)
		{ return null; }
	}
}

package model;

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
			String vraag, String antwoord) throws Exception 
	{
		super(vraag, antwoord, categorie, auteur);
	}
	
	/**
	 * Constructor om instantie van Meerkeuze opdracht te crieeren van TXTbestand
	 * + keuzen
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	public OpdrachtVraag(String[] vanTXTBestand) 
			throws NumberFormatException, Exception 
	{
		super(vanTXTBestand);
		
	}
	
	@Override
	public boolean isJuisteAntwoord(String antwoord) 
	{
		return getAntwoord().equals(antwoord);	
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
}

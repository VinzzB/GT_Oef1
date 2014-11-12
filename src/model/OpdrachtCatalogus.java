package model;

import java.util.ArrayList;

/**
 * @author      Nathalie Mathieu <natmathieu@gmail.com>
 * @author      Natalia Dyubankova <fornnd@gmail.com>
 * @version     1.0                 
 * @since       2014-11-12  
 */
public class OpdrachtCatalogus
{
	private ArrayList<Opdracht> opdrachtCatalogus;
	
	public OpdrachtCatalogus()
	{
		opdrachtCatalogus = new ArrayList<Opdracht>();
	}
	
	public ArrayList<Opdracht> getOpdrachten()
	{
		return opdrachtCatalogus;
	}
	
	public void voegOpdrachtToe(Opdracht opdracht)
	{
		opdracht.setOpdrachtCatalogus(this);
		opdracht.setOpdrachtID(setOpdrachtID());
		opdrachtCatalogus.add(opdracht);
	}
	
	protected void verwijderOpdracht(Opdracht opdracht) throws Exception
	{
		//enkel mogelijk als nog niet gekoppeld aan quiz
		if (!opdracht.isGekoppeldAanQuiz())
		{
			this.opdrachtCatalogus.remove(opdracht);
		}
		else throw new Exception("Opdracht kan niet verwijderd worden, "
								+ "want is reeds gelinkt aan een quiz.");
	}
	 public int setOpdrachtID()
	 {
		 return this.opdrachtCatalogus.size() + 1;
	 }
	 
	public Opdracht getOpdracht(int opdrachtID)
	{
		for(Opdracht opdracht : opdrachtCatalogus)
		{
			if(opdracht.getOpdrachtID() == opdrachtID)
				return opdracht;
		}
		return null;
	}
}

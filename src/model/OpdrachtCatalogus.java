package model;

import java.util.ArrayList;

/**
 * @author      Nathalie Mathieu <natmathieu@gmail.com>
 * @author      Natalia Dyubankova <fornnd@gmail.com>
 * @version     1.0                 
 * @since       2014-11-12  
 */
public class OpdrachtCatalogus implements Comparable<OpdrachtCatalogus>, Cloneable
{
	private ArrayList<Opdracht> opdrachtCatalogus;
	
	public OpdrachtCatalogus()
	{
		opdrachtCatalogus = new ArrayList<Opdracht>();
	}
	
	public OpdrachtCatalogus(OpdrachtCatalogus opdrachtCatalogus)
	{
		this();
		
		for(Opdracht opdracht : opdrachtCatalogus.getOpdrachten())
		{
			this.opdrachtCatalogus.add(opdracht);
		}
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
		this.opdrachtCatalogus.remove(opdracht);
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

	@Override
	public int compareTo(OpdrachtCatalogus opdrachtCatalogus)
	{
		if (this.opdrachtCatalogus.size() == opdrachtCatalogus.getOpdrachten().size())
			return this.hashCode() - opdrachtCatalogus.hashCode();
		else return this.opdrachtCatalogus.size() - opdrachtCatalogus.getOpdrachten().size();
	}
	
	public OpdrachtCatalogus clone()
	{
		return new OpdrachtCatalogus(this);
	}
}

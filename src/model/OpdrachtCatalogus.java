package model;

import java.util.ArrayList;

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
	
	protected void verwijderOpdracht(Opdracht opdracht)
	{
		opdrachtCatalogus.remove(opdracht);
	}
	 public int setOpdrachtID()
	 {
		 return opdrachtCatalogus.size() + 1;
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

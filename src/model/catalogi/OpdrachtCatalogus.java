package model.catalogi;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import model.Opdracht;

/**
 * @author      Nathalie Mathieu
 * @author      Natalia Dyubankova <fornnd@gmail.com>
 * @version     1.0                 
 * @since       2014-11-12  
 */
public class OpdrachtCatalogus implements /*Comparable<OpdrachtCatalogus>*/ Iterable<Entry<Integer,Opdracht>>,Cloneable
{
	private HashMap<Integer,Opdracht> opdrachten;
	
	//constructor
	
	public OpdrachtCatalogus()
	{
		opdrachten = new HashMap<Integer,Opdracht>();
	}
	
	/*public OpdrachtCatalogus(OpdrachtCatalogus opdrachtCatalogus)
	{
		this();		
		for(Opdracht opdracht : opdrachtCatalogus.getOpdrachten())
		{
			this.opdrachtCatalogus.add(opdracht);
		}
	}*/
	
	//getters en setters
	
	public HashMap<Integer,Opdracht> getOpdrachten()
	{
		return this.opdrachten;
	}
	
	//methods
	
	public void voegOpdrachtToe(Opdracht o)
	{
		o.setOpdrachtID(this.getLastID()+1); //is in principe niet nodig om dit op te slaan in opdracht class...
		this.opdrachten.put(o.getOpdrachtID(), o);
	}
	public void voegOpdrachtToe(int index, Opdracht o)
	{
		o.setOpdrachtID(index); //is in principe niet nodig om dit op te slaan in opdracht class...
		this.opdrachten.put(index, o);
	}
	public void verwijderOpdracht(Opdracht o) throws Exception
	{
		//enkel mogelijk als nog niet gekoppeld aan test
		this.opdrachten.remove(o.getOpdrachtID()); 
	}
	
	public int getLastID()
	{
		int value = 0;
		for (int key : opdrachten.keySet()) {
			if (value < key) { value = key;}
		}
		return value;
	}
	
	public int Count()
	{ return opdrachten.size(); }
	 
	public Opdracht getOpdracht(int opdrachtID)
	{
		return opdrachten.get(opdrachtID);
	}
	
	/**
	 * zoekt het indexnummer van een opdracht object in deze catalogus.
	 * @param opdracht Het opdracht object waar op gezocht moet worden.
	 * @return Retourneert een integer value als index. -1 indien de opdracht niet gevonden werd.
	 */
	
	public int getIndex(Opdracht opdracht)
	{
		if (opdrachten.containsValue(opdracht))
		{
			for (Entry<Integer, Opdracht> item : opdrachten.entrySet()) {
				if (item.getValue().equals(opdracht))
				{ return item.getKey(); }
			}
		}
		return -1; //not found
	}

	/*@Override
	 * CompareTo overbodig? er is max één catalogus?
	public int compareTo(OpdrachtCatalogus opdrachtCatalogus)
	{
		if (this.opdrachtCatalogus.size() == opdrachtCatalogus.getOpdrachten().size())
			return this.hashCode() - opdrachtCatalogus.hashCode();
		else return this.opdrachtCatalogus.size() - opdrachtCatalogus.getOpdrachten().size();
	}*/
	
	@Override
	public Object clone()
	{
		OpdrachtCatalogus clone = new OpdrachtCatalogus();
		for(Opdracht o : this.opdrachten.values()) 
		{
			clone.voegOpdrachtToe(o);
		}
		return clone;		
	}
	
	@Override
	public Iterator<Entry<Integer,Opdracht>> iterator() 
	{
		return opdrachten.entrySet().iterator();
	}
	

}

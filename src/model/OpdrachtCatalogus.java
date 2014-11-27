package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.sun.javafx.collections.MappingChange.Map;

/**
 * @author      Nathalie Mathieu
 * @author      Natalia Dyubankova <fornnd@gmail.com>
 * @version     1.0                 
 * @since       2014-11-12  
 */
public class OpdrachtCatalogus implements /*Comparable<OpdrachtCatalogus>*/ Iterable<Opdracht>,Cloneable
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
	
	public void addOpdracht(Opdracht o)
	{
		o.setOpdrachtID(this.getLastID()+1);
		this.opdrachten.put(o.getOpdrachtID(), o);
	}
	
	public void removeOpdracht(Opdracht o) throws Exception
	{
		//enkel mogelijk als nog niet gekoppeld aan test
		this.opdrachten.remove(o.getOpdrachtID()); 
	}
	
	public int getLastID()
	{
		int id = 0;
		//get max value of keys in opdrachten
		for (Integer key : this.opdrachten.keySet()) 
		{
			id++;
			if (id == this.opdrachten.keySet().size()) 
			{
				id = key;
			}
		}
		return id;
	}
	 
	public Opdracht getOpdracht(int opdrachtID)
	{
		for(java.util.Map.Entry<Integer, Opdracht> entry : this.opdrachten.entrySet())
		{
			if(entry.getKey() == opdrachtID)
				return entry.getValue();
		}
		return null;
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
			clone.addOpdracht(o);
		}
		return clone;		
	}
	
	@Override
	public Iterator<Opdracht> iterator() 
	{
		return opdrachten.values().iterator();
	}
}

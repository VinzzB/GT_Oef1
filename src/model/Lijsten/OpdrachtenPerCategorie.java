package model.Lijsten;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import model.Opdracht;
import model.OpdrachtCatalogus;
import persistency.Catalogi;

/**
 * Maak een klasse die alle opdrachten uit de opdrachtendatabase leest 
 * en op de console een overzicht toont van alle categorieën van opdrachten 
 * met het aantal opdrachten per categorie. 
 * Los dit op door gebruik te maken van de klasse HashMap.
 * 
 * @author Natalia
 *
 */
public class OpdrachtenPerCategorie implements ILijst
{
	OpdrachtCatalogus opdrachten = Catalogi.getOpdrachten();
	HashMap <String,Integer> map = new HashMap<String,Integer>();
	String output = "";
	
	public OpdrachtenPerCategorie()
	{
		for(Entry<Integer,Opdracht> opdracht : opdrachten)
		{
			int aantal = 1;
			if (map.containsKey(opdracht.getValue().getCategorie()))
			{
				aantal = map.get(opdracht.getValue().getCategorie())+1;
			}
			map.put(opdracht.getValue().getCategorie().toString(), aantal);
		}
		
		Set <Map.Entry<String, Integer>> set = map.entrySet();
		for (Map.Entry<String,Integer> entry:set)
		{
			output += String.format("%20s %2d %n", entry.getKey(),entry.getValue());
		}
	}
	
	public String getLijst()
	{
		return output;
	}
	
}

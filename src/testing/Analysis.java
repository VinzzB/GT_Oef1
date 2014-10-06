package testing;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import utils.Datum2;

public class Analysis 
{
	String[] namen = new String[12];
	String[] daten = new String[12];
	Datum2[] ddaten = new Datum2[12];
	
	public void leesVanBestand(String url)
	{

		  int i = 0;
		  
		  try
		  {
			Scanner scanner = new Scanner(new File(url));
			while (scanner.hasNext())
			{
		      String lijn = scanner.nextLine();
			  String [] velden = lijn.split("\t");
			  namen[i] = velden[0];
			  daten[i] = velden[1];
			  i++;
			}
			if (scanner != null)
			{
			  scanner.close();
			}
		  }
		  catch(FileNotFoundException ex)
		  {
		    System.out.println("bestand niet gevonden");
		  }
		  catch(Exception ex)
		  {
		    System.out.println(ex.getMessage());
		  }		
	}
	
	public int oudste()
	{
		int oudste = 0;
		for (int i = 0; i < 12; i++) 
		{
			try
			{
				ddaten[i] = new Datum2(daten[i]);
				if (ddaten[oudste].kleinerDan(ddaten[i])) oudste = i;
			}
			catch(IllegalArgumentException e)
			{
				System.out.println(e);
			}
		}
		return oudste;
	}
	
	public void printBestand()
	{
		for (int i = 0; i < 12; i++) 
		{
			System.out.printf("%s\t%s\n", namen[i], daten[i]);
		}
	}
	
	public static void main(String[] args) 
	{
		Analysis a = new Analysis();
		a.leesVanBestand("personen.txt");
		System.out.print(a.oudste());
	}
}

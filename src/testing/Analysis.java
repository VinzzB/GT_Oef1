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
	
	String lijn;
	
	StringBuilder foutenBodschap = new StringBuilder();
	
	int oudste = 0;
	int jongste = 0;
	
	public void leesVanBestand(String url)
	{
		  Scanner scanner;
		  int i = 0;
		  try
		  {
			  scanner = new Scanner(new File(url));
			  while (scanner.hasNext())
			  {
				  try
				  {
					  lijn = scanner.nextLine();
					  String [] velden = lijn.split("\t");
					  namen[i] = velden[0];
					  daten[i] = velden[1];
					  ddaten[i] = new Datum2(daten[i]);
					  i++;
				  }
				  catch(Exception ex)
				  {
					  foutenBodschap.append(ex.getMessage() + " "+ lijn);
					  foutenBodschap.append(System.lineSeparator());
				  }
			}
			if (scanner != null)
			{
			  scanner.close();
			}
		  }
		  catch(FileNotFoundException ex)
		  {
			  foutenBodschap.append("bestand niet gevonden");
			  foutenBodschap.append(System.lineSeparator());
		  }
	}
	
	public void findOudste()
	{
		for (int i = 0; i < ddaten.length; i++) 
		{
			if (ddaten[i] != null)
			{
				if (ddaten[oudste].kleinerDan(ddaten[i])) oudste = i;
			}
		}
	}
	
	public void findJongste()
	{
		for (int i = 0; i < ddaten.length; i++) 
		{
			if(ddaten[i] != null)
			{
				if (ddaten[jongste].groterDan(ddaten[i])) jongste = i;
			}
		}
	}
	public void printBestand()
	{
		for (int i = 0; i < 12; i++) 
		{
			System.out.printf("%s\t%s\n", namen[i], daten[i]);
		}
	}
	
	public String printOudste()
	{
		findOudste();
		return "De oudste persoon is " + namen[oudste] + 
				" die geboren is op " + daten[oudste];
	}
	
	public String printJongste()
	{
		findJongste();
		return "De jongste persoon is " + namen[jongste] + 
				" die geboren is op " + daten[jongste];
	}
	
	public String printVerschill()
	{
		return String.format("%s is %d jaaren ouder dan %s", 
				namen[oudste],  ddaten[oudste].verschillInJaren(ddaten[jongste]), namen[jongste]);
	}
	
	public static void main(String[] args) 
	{
		Analysis a = new Analysis();
		a.leesVanBestand("bestanden/personen.txt");
		System.out.println(a.printOudste());
		System.out.println(a.printJongste());
		System.out.println(a.printVerschill());
		System.out.printf("%nOngeldige datums uit het inputbestand %n%s", a.foutenBodschap);
	}
}

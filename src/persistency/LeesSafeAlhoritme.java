package persistency;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

abstract class LeesSafeAlhoritme
{
	private String output ="";
	private File file = new File("bestanden\\werknemers.txt");
	  
	public void leesVanBestand()
	{
		  try
		  {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNext())
			{
		      String lijn = scanner.nextLine();
			  String [] velden = lijn.split(",");
			  String naam = velden[0];
			  double salaris = Double.parseDouble(velden[1]);
			  Werknemer werknemer = new Werknemer(naam, salaris);				
			  output += werknemer.toString()+"\n";
			}
			if (scanner!=null)
			{
			  scanner.close();
			}
			System.out.println(output);
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
	
	public void schrijfWerknemersNaarBestand()
	{

		try{
			PrintWriter writer = new PrintWriter(file);
			for (int i = 0;i <werknemerLijst.getAantalWerknemers();i++)
			{
				Werknemer werknemer = werknemerLijst.getWerknemer(i);
				String lijn = werknemer.getNaam()+","+werknemer.getSalaris();
				writer.println(lijn);
			}
			if (writer !=null)
				writer.close();
			}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
}

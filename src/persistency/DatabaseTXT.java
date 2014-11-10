package persistency;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;


public class DatabaseTXT extends Database
{
	public DatabaseTXT()
	{
		super();
		opdrachtenDB = new File("bestanden\\opdrachten.txt");
		quizzenDB = new File("bestanden\\quizzen.txt");
		quizOpdrachtDB = new File("bestanden\\quizopdrachten.txt");
	}

	@Override
	Object[] leesVanBestand(File file)
	{
		  Object[] objecten = new Object[(int)file.length()];
		  try
		  {
			Scanner scanner = new Scanner(file);
			int i = 0;
			while (scanner.hasNext())
			{
		      String lijn = scanner.nextLine();
		      String[] velden = lijn.split("\t");
		      objecten[i] = velden;
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
		  return objecten;
	}

	@Override
	void schrijfNaarBestand(String[] objecten, File file)
	{
		try
		{
			PrintWriter writer = new PrintWriter(file);
			for (String object : objecten)
			{
				writer.println(object);
			}
			if (writer != null)
			{
				writer.close();
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}

}

package persistency;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import utils.Arrays;
/**
 * Abstracte klasse om objecten naar bestanden te lezen en weg te schrijven
 * 
 * @author Natalia Dyubankova <fornnd@gmail.com>
 * @version     1.0                 
 * @since       2014-11-11 
 * @Revisioned Vincent on 10/01/2015: - Aanpassing in methods (DEZE TEMPLATE CLASSE IS ENKEL VOOR FILE DBs!)
 */
public abstract class DbFileHandler implements IDatabaseStrategy
{
	
	public String readFile(String filePath)
	{
		StringBuilder result = new StringBuilder();
		try (Scanner s = new Scanner(new File(filePath))) 
		{
			while (s.hasNext())
			{
				result.append(s.nextLine());
				result.append(System.lineSeparator());
			}			
		}
		catch(FileNotFoundException e)
		{ System.out.println("Kan het bestand niet vinden op de opgegeven locatie: " + filePath); }
		return result.toString();
	}
	
	public boolean WriteFile(String pFilePath, String data)
	{
		try (PrintWriter print = new PrintWriter(new File(pFilePath))) 
		{
			print.write(data);
			return true;
		}
		catch(FileNotFoundException e)
		{ System.out.println("Kan het bestand niet vinden op de opgegeven locatie: " + pFilePath); }
		return false;
	}
	
	
	protected List<String[]> readFileLines(String filePath, String columnDelimiter)
	{		
			List<String[]> data = new ArrayList<String[]>();	
			try (Scanner s = new Scanner(new File(filePath))) 
			{
				while (s.hasNext())
				{
					String currLine = s.nextLine();
					if (currLine != null && currLine != "")
					{
						String[] fields = currLine.split(columnDelimiter); 
						data.add(fields);								
					}
				}
			}
			catch(FileNotFoundException e)
			{ System.out.println("Kan het bestand niet vinden op de opgegeven locatie: " + filePath); }			
			return data;
	}
	
	protected boolean writeFileLines(String filePath, List<String[]> data, String columnDelimiter)
	{		
			try (PrintWriter print = new PrintWriter(new File(filePath))) 
			{				
				for (String[] row : data) {
					print.println(Arrays.Join(columnDelimiter, row));
				}
				return true;
			}
			catch(FileNotFoundException e)
			{ System.out.println("Kan het bestand niet vinden op de opgegeven locatie: " + filePath); }			
			return false;
	}
}

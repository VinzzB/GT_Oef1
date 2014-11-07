package testing;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
/**
 * 
 * @author bloemevi
 *
 */
public class FileAnalysis {

	private String filePath;
	private List<FileAnalysisPerson> persons;	
	private List<String> readErrors;

	/**
	 * Empty CTOR
	 */
	public FileAnalysis() { }
	/**
	 * CTOR with FilePath parameter. Starts reading immediately.
	 * @param pFilePath
	 * @throws FileNotFoundException
	 */
	public FileAnalysis(String pFilePath) throws FileNotFoundException 
	{	
		setFilePath(pFilePath);
		readFile();
	}	
	
	/**
	 * Gets the current path of the file to read from. 
	 * @return
	 */
	public String getFilePath()
	{
		return filePath;
	}
	
	/**
	 * Sets a path for the file to read the data from
	 * @param pFilePath
	 * @throws FileNotFoundException
	 */
	public void setFilePath(String pFilePath) throws FileNotFoundException
	{
		if (!fileExists(pFilePath)) 
		{ throw new FileNotFoundException("Kan het bestand niet vinden op de opgegeven locatie."); }
		filePath = pFilePath;
	}
	/**
	 * Determines if the given path is a file and exists. 
	 * @param pFilePath
	 * @return
	 */
	public static boolean fileExists(String pFilePath)
	{
		File f = new File(pFilePath);
		return f.exists() && !f.isDirectory(); //is a file and exists.
	}
	
	/**
	 * Reads the file given in setFilePath() or in the CTOR and fills the list with persons
	 */
	public void readFile()
	{		
		Scanner s = null;
		try
		{
			//init lists
			persons = new ArrayList<FileAnalysisPerson>();
			readErrors = new ArrayList<String>();
			//init scanner
			s = new Scanner(new File(filePath));
			//read each line
			while (s.hasNext())
			{
				String currLine = s.nextLine();
				String[] currFields = currLine.split("\t"); //takes a regex				
				AddPerson(currFields[0], currFields[1]);
			}
			//Order by birthday (with an anonymous comparator)
			persons.sort(new Comparator<FileAnalysisPerson>() {
				public int compare(FileAnalysisPerson o1, FileAnalysisPerson o2) {
					return o1.getDateOfBirth().compareTo(o2.getDateOfBirth());
				}				
			});	 
		}		
		catch(Exception ex) //catches all exceptions!
		{
			readErrors.add("FOUT: " + ex.getMessage());
		}
		finally
		{
			if (s != null)
				s.close();
		}
	}
	
	/**
	 * Internally creates and adds a new person object
	 * @param pCurrName The name of the person
	 * @param pCurrDateofBirth his birthdate as a string.
	 */
	private void AddPerson(String pCurrName, String pCurrDateofBirth)
	{
		try
		{				
			//Create person object				
			FileAnalysisPerson p = new FileAnalysisPerson(pCurrName, pCurrDateofBirth);
			persons.add(p);
		}
		catch(IllegalArgumentException ex)
		{
			String errText = "FOUT: " + pCurrName + " " + pCurrDateofBirth + " (" + ex.getMessage() + ")";				
			readErrors.add(errText);
		}						
	}	
	
	/**
	 * Generates a list with all the errors occurred during reading.
	 * @return a string with errors each separated by a new line
	 */
	public String printErrors()
	{
		String t = "";
		for (String s : readErrors) {
			t += s + "\r\n";
		}
		return t;
	}
	
	public List<String> getErrors()
	{
		return readErrors;
	}
	
	/**
	 * Retrieves all the persons
	 * @return
	 */
	public List<FileAnalysisPerson> getPersons()
	{
		return persons;
	}
	
	/**
	 * Gets the youngest person from the internal list.
	 * @return
	 */
	public FileAnalysisPerson getYoungest()
	{
		return persons == null ? null : persons.get(persons.size()-1);		
	}
	
	/**
	 * Gets the oldest person from the internal list
	 * @return
	 */
	public FileAnalysisPerson getOldest()
	{
		return persons == null ? null : persons.get(0);		
	}
	
	/**
	 * Static Driver Method
	 * @param args
	 */
	public static void main(String[] args) {
		try {		
			FileAnalysis fa = new FileAnalysis("./bin/testing/personen.txt");
			FileAnalysisPerson p1 = fa.getYoungest();
			FileAnalysisPerson p2 = fa.getOldest();
			System.out.println("Jongste persoon: " + p1);
			System.out.println("Oudste persoon: " + p2);
			System.out.println("Verschil in Maanden: " + p1.getDateOfBirth().verschilInMaanden(p2.getDateOfBirth()));
			System.out.println("Verschil in Jaren: " + p1.getDateOfBirth().verschilInJaren(p2.getDateOfBirth()));
			if (fa.readErrors.size() != 0) {
				System.out.println("------------------------------------------");
				System.out.println("Fouten (" + fa.getErrors().size() + ")");
				System.out.println("------------------------------------------");
				System.out.println(fa.printErrors());
			}
			
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}		
	}
}

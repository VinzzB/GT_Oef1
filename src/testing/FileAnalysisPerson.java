package testing;

import utils.date.normal.Datum;
/**
 * a class that can hold data for one person.
 * @author bloemevi
 *
 */
public class FileAnalysisPerson {

	private String name;
	private Datum dateOfBirth;
	
	public FileAnalysisPerson(String pName, Datum pDateOfBirth)
	{
		name = pName;
		dateOfBirth = pDateOfBirth;
	}

	public FileAnalysisPerson(String pName, String pDateOfBirth)
	{
		name = pName;
		dateOfBirth = new Datum(pDateOfBirth);
	}	
	
	public String getName() { return name; }
	public Datum getDateOfBirth() { return dateOfBirth; }
	
	@Override
	public String toString()
	{
		return name + " geboren op " + dateOfBirth;
	}
}

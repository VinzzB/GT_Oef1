package utils;
/***
 * 
 * @author bloemevi
 *
 */
public class Arrays
{
	public static String Join(String Delimiter, String ... strings)
	{
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < strings.length; i++) {
			b.append(strings[i]);
			if (i < strings.length-1)
			{ b.append(Delimiter); }
		}		
		return b.toString();
	}
	public static String Join(String Delimiter, int ... ints)
	{
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < ints.length; i++) {
			b.append(ints[i]);
			if (i < ints.length-1)
			{ b.append(Delimiter); }
		}		
		return b.toString();
	}	
	
	public static String FormatJoin(String format, String ... strings)
	{
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < strings.length; i++) {
			String line = String.format(format, i+1, strings[i]);
			b.append(line);			
		}		
		return b.toString();
	}	
}

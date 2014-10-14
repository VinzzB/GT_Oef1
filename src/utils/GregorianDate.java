package utils;

import java.util.Date;
import java.util.GregorianCalendar;

public class GregorianDate extends GregorianCalendar
{
	static final int[] DAGEN_PER_MAAND = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
//	private GregorianCalendar date;
	private int dag;
	private int maand;
	private int jaar;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		GregorianDate a = new GregorianDate(3, 1, 2009);
//		GregorianDate b  = new GregorianDate(3, 3, 2009);
		
		GregorianDate a = new GregorianDate(1, 3, 2007);
		GregorianDate b  = new GregorianDate(3, 1, 2009);
		
		
		System.out.println("objects created: ");
		System.out.println("a: " + a);
		System.out.println("b: " + b);
		
		System.out.print("American format: ");
		System.out.println(a.getAmericanFormat());
		
		System.out.print("European format: ");
		System.out.println(a.getEuropeanFormat());
		
		System.out.print("Is a kleiner dan b: ");
		System.out.println(a.kleinerDan(b));
		
		System.out.print("Verschil Jaren: ");
		System.out.println(a.verschilInJaren(b));
		
		System.out.print("Verschil Maanden: ");
		System.out.println(a.verschilInMaanden(b));
		
		System.out.print("Verschil Dagen: ");
		System.out.println(a.verschilInDagen(b));
		
//		System.out.println("a is leap year: " + IsLeapYear());
//		System.out.println("b is leap year: " + b.IsLeapYear());	

	}
	
	public int getDagenInMaand(int month, int year)
	{
		return (month == 2 && isLeapYear(year)) ? 29 : DAGEN_PER_MAAND[month-1];
	}	

	public GregorianDate()
	{	    
		setDatum(get(DAY_OF_MONTH), get(MONTH), get(YEAR));	    	    	
	}
	
	public GregorianDate(Date date)
	{
		setDatum(date.getDay(), date.getMonth(), date.getYear());					
	}
	
	public GregorianDate(int day, int month, int year)
	{
		setDatum(day, month, year);
	}
	
	public GregorianDate(String dateString) throws IllegalArgumentException
	{
		String[] dateArr = dateString.replace('\\','/').split("/");
		//Validate datum (kan evt in een method)
		if (dateArr.length < 2 || !(dateArr[0].length() == 2) || !(dateArr[1].length() == 2) || !(dateArr[2].length() == 4))
		{ throw new IllegalArgumentException("Er werd een ongeldig formaat opgegeven. Geldig formaat: DD/MM/YYYY");  }
		
		setDatum(Integer.parseInt(dateArr[0]),Integer.parseInt(dateArr[1]),Integer.parseInt(dateArr[2]));
	}
		
	public boolean setDatum(int day, int month, int year) throws IllegalArgumentException
	{
		try 
		{
			if (day < 1 || day > getDagenInMaand(month, year) || month < 1 || month > 12 || year < 0)
			{ throw new IllegalArgumentException("Foutieve invoer"); }			
		} catch (ArrayIndexOutOfBoundsException e) 
		{ throw new IllegalArgumentException("Foutieve invoer"); }
		
		this.clear();
		this.set(year, month, day);
		dag = day;
		maand = month;
		jaar = year;
		return true;	
	}
	
	public boolean kleinerDan(GregorianDate date)
	{
		return before(date);
	}
	
	public String getAmericanFormat()
	{return "";}
	
	public String getEuropeanFormat()
	{return "";}
	
	public int verschilInDagen(GregorianDate date)
	{
		GregorianDate firstDate = (GregorianDate)(this.before(date) ? this.clone() : date.clone());
		GregorianDate lastDate = this.before(date) ? date : this;		
		int i;
		for (i = 1; firstDate.before(lastDate); i++) 
		{ firstDate.add(DAY_OF_MONTH, 1); } //Count only
		return i+2;
	}
	
	public int verschilInMaanden(GregorianDate date)
	{		
		int totMonths = 0;
		GregorianDate minDate = (GregorianDate)(this.before(date) ? this.clone() : date.clone());
		GregorianDate maxDate = this.before(date) ? date : this;
		if (minDate.get(YEAR) != maxDate.get(YEAR))
		{
			for (int i = minDate.get(YEAR); i <= maxDate.get(YEAR); i++)
			{
				if (i == maxDate.get(YEAR))
				{ totMonths += maxDate.get(MONTH); }
				else if (i == minDate.get(YEAR))
				{ totMonths += 12 - (minDate.get(MONTH)); }
				else
				{ totMonths += 12; }				
			}
		}
		else
		{
			totMonths += maxDate.get(MONTH) - minDate.get(MONTH) - (minDate.get(DAY_OF_MONTH) > maxDate.get(DAY_OF_MONTH)?1:0);
		}
		return totMonths;
	}

	public int verschilInJaren(GregorianDate date)
	{return verschilInMaanden(date)/12;}
}

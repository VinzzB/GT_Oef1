package utils;
import java.util.Date;
/* Created By: Vinzz */
public class NormalDate implements Comparable<NormalDate>
{
	private static final int[] DAGEN_PER_MAAND = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	private static final String MAANDEN[] = {"januari", "februari", "maart", "april", "mei","juni","juli",
											 "augustus","september","obtober","november","december"};
	private int day = 0;
	private int month = 0;
	private int year = 0;			
	
	public static int getDagenInMaand(int month, int year)
	{ return (month == 2 && isLeapYear(year)) ? 29 : DAGEN_PER_MAAND[month-1]; }
	
	public static int getDagenInJaar(int year)
	{ return isLeapYear(year) ? 366 : 365; }
	
	//Schrikkeljaar = deelbaar door 4 en (niet in een eeuwjaar OF (wel in een eeuwjaar EN deelbaar door 400))	
	public static boolean isLeapYear(int year)
	{ return year % 4 == 0 && (year % 100 != 0 || (year % 400 == 0)); }
	
	public static void main(String[] Args)
	{		
		try
		{		
			NormalDate a = new NormalDate("03/01/2009");
			NormalDate b = new NormalDate(1,1,2009);
			
			System.out.println(new Date());
			
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
			
			System.out.println("a is leap year: " + a.IsLeapYear());
			System.out.println("b is leap year: " + b.IsLeapYear());			
			
			for (int x = 1; x < 365; x++)
			{
				System.out.print("Verhoog a met "+x+" dagen: ");
				System.out.println(a.veranderDatum(x) + " check verschil: " + a.verschilInDagen(a.veranderDatum(x)));
			}								
			
			for (int x = -1; x > -365; x--)
			{					
				System.out.print("Verlaag a met "+x+" dag: ");
				System.out.println(a.veranderDatum(x) + " check verschil: " + a.verschilInDagen(a.veranderDatum(x)));		
			}			
		}
		catch (Exception ex)
		{
			System.out.println("ERROR: " + ex.getMessage());
		}			
	}
		
	//CTOR: Day / month / year
	public NormalDate(int dag, int maand, int jaar)
	{ setDatum(dag,maand,jaar); }
	
	//CTOR: date object
	@SuppressWarnings("deprecation")
	public NormalDate(Date fullDate)
	{ setDatum(fullDate.getDate(), fullDate.getMonth(), fullDate.getYear()); }
	
	//CTOR: date today
	public NormalDate() 
	{ this(new Date()); }
		
	//CTOR: String that contains the European date format. Format: DD/MM/YYYY
	public NormalDate(String datum) throws IllegalArgumentException
	{
		//split string and validate
		String[] dateArr = datum.split("/");
		if (dateArr.length < 2 || !(dateArr[0].length() == 2) || !(dateArr[1].length() == 2) || !(dateArr[2].length() == 4))
		{ throw new IllegalArgumentException("Er werd een ongeldig formaat opgegeven. Geldig formaat: DD/MM/YYYY");  }
		//set internal date
		setDatum(Integer.parseInt(dateArr[0]), 
				 Integer.parseInt(dateArr[1]), 
				 Integer.parseInt(dateArr[2]));		
	}
	
	//GETTERS
	public int getDay() { return day; }
	public int getMonth() { return month; }	
	public int getYear() { return year; }	
	
	//SETTERS
	private void setDay(int day) throws IllegalArgumentException 
	{ 
		int dim = getDagenInMaand(month, year);
		if (day < 1 || day > dim) 
		{ throw new IllegalArgumentException("De opgegeven dag ligt buiten de geldige range. (1-" + dim + ")"); }			
		this.day = day; 
	}
	
	private void setMonth(int month) throws IllegalArgumentException 
	{ 
		if (month < 1 || month > 12) 
		{ throw new IllegalArgumentException("De opgegeven maand ligt buiten de geldige range. (1-12)"); }
		this.month = month; 
	}
	
	private void setYear(int year) throws IllegalArgumentException 
	{ 
		if (year < 0) 
		{ throw new IllegalArgumentException("Het opgegeven jaar ligt buiten de geldige range. (>=0)"); }
		this.year = year; 
	}
	
	public boolean setDatum(int dag, int maand, int jaar) 		
	{
			setYear(jaar);	
			setMonth(maand);
			setDay(dag);
			return true;
	}
	
	public String getAmericanFormat()
	{ return String.format("%04d/%02d/%02d", year,month,day); }
	
	public String getEuropeanFormat()
	{ return String.format("%02d/%02d/%04d", day,month,year);}

	@Override
	public String toString()
	{ return String.format("%02d %s %04d",day, MAANDEN[month-1], year); }

	@Override
	public int compareTo(NormalDate normalDate) {
		// Check Year
		if (year > normalDate.year) { return 1; }
		if (year < normalDate.year) { return -1; }
		//Check Month
		if (month > normalDate.month) { return 1; }
		if (month < normalDate.month) { return -1; }
		//check Day
		if (day > normalDate.day) { return 1; }	
		if (day < normalDate.day) { return -1; }
		return 0; //equal
	}
	
	@Override
	public boolean equals(Object obj) {
		//Same object? = Always true
		if (this == obj) return true;
		//Same Type?
		if (obj == null || !(obj instanceof NormalDate)) return false;
		//CompareDate and return true if equal.
		return compareTo((NormalDate)obj)==0;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 37;
		int result = 1;
		result = prime * result + day;
		result = prime * result + month;
		result = prime * result + year;
		return result;
	}

	public boolean kleinerDan(NormalDate date)
	{ return compareTo(date)==-1; }
	
	public int verschilInDagen(NormalDate date)
	{ 					
		return new DateDiff(this, date).getDays();
	}
	
	public int verschilInMaanden(NormalDate date)
	{
		return new DateDiff(this, date).getMonths();
	}
	
	public int verschilInJaren(NormalDate date)
	{
		return new DateDiff(this, date).getYears();
	}

	public NormalDate veranderDatum(int aantalDagen)
	{
		int d = day;
		int m = month;
		int y = year;
		if (aantalDagen > 0)
		{									
			do {
				if (aantalDagen+d <= getDagenInMaand(m,y))
				{ break; }
				aantalDagen -= getDagenInMaand(m, y)-d+1; //verminder met dagen in maand	
				y += (m == 12 ? 1 : 0); //verhoog jaar?
				m = (m == 12 ? 1 : m+1); //verhoog maand?
				d = 1; //Nieuwe maand												
			} while (true);			
		}
		else // negative value, go back in time!
		{			
			do {
				//Break do wanneer de restwaarde van aantalDagen kleiner is dan de dag
				if (-d < aantalDagen) 
				{ break; }
				//aantalDagen groter dan de dag? => month (/ year) -1 
				if (aantalDagen <= -d) 
				{ 
					aantalDagen += d;
					y -= (m == 1 ? 1 : 0); //verminder jaartal?
					m = (m ==1 ? 13 : m) -1; //verminder maand?
					d = getDagenInMaand(m, y);  //zet las laatste dag van vorige maand
				}								
			} while (true);			
		}
		return new NormalDate(d+aantalDagen, m, y);
	}
	
	public boolean IsLeapYear()
	{ return isLeapYear(this.year); }	
	
}

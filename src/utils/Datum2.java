package utils;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Datum2 implements Comparable<Datum2>
{
	private GregorianCalendar calendar;
	
	private static final int[] DAGEN_PER_MAAND = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	private static final String[] MAANDEN = {"January", "February", "March", "April", "May",
		"June", "July", "August", "September", "October", "November", "December"};
	
	public Datum2()
	{
		calendar = new GregorianCalendar();
	}
	
	public Datum2(Datum2 datum)
	{
		this();
		this.calendar.set(datum.getJaar(), datum.getMaand(), datum.getDag());
		
	}
	
	public Datum2(int dag, int maand, int jaar)
	{
		this();
		this.setDatum(dag, maand, jaar);
	}
	
	public Datum2(String datum)
	{
		this();
		
		this.setDatum(Integer.parseInt(datum.split("/")[0]),
					Integer.parseInt(datum.split("/")[1]),
					Integer.parseInt(datum.split("/")[2])); 

	}
	private int setDag(int dag) throws IllegalArgumentException
	{
		if (dag > 0 && dag <= DAGEN_PER_MAAND[this.getMaand()])	
			{
			return dag;
			}
		else if (dag == 29 && this.getMaand() == 2 && this.calendar.isLeapYear(this.getJaar())) 
			{
			return dag;
			}
		else throw new IllegalArgumentException ("fout dag");
	}

	private int setMaand(int maand) throws IllegalArgumentException 
	{
		if (maand <= 0 || maand > 12) throw new IllegalArgumentException ("fout maand");
		return maand;
	}

	private int setJaar(int jaar) throws IllegalArgumentException
	{
		if (jaar < 1900) throw new IllegalArgumentException ("fout jaar");
		return jaar;
	}
	/**Een methode om een Datumobject een geldige waarde te geven 
	(indien ongeldige dag of maand Exception werpen) */
	public boolean setDatum(int dag, int maand, int jaar)
	{
		this.calendar.set(Calendar.YEAR, setJaar(jaar));
		this.calendar.set(Calendar.MONTH, setMaand(maand));
		this.calendar.set(Calendar.DAY_OF_MONTH, setDag(dag));
		return true;
	}
	public int getJaar()
	{
		return this.calendar.get(Calendar.YEAR);
	}
	
	public int getMaand()
	{
		return this.calendar.get(Calendar.MONTH) + 1;
	}
	
	public int getDag()
	{
		return this.calendar.get(Calendar.DAY_OF_MONTH);
	}
	
	public GregorianCalendar getCalendar()
	{
		return this.calendar;
	}
	
	/** geeft een datum in Amerikaans formaat terug (vb 2009/2/4)*/
	public String getDatumInAmerikaansFormaat()
	{
		return String.format("%d/%d/%d", this.getJaar(), this.getMaand(), this.getDag());
	}
	
	/**geeft een datum in Europees formaat terug   (vb 4/2/2009)*/
	public String getDatumInEuropeesFormaat()
	{
		return String.format("%d/%d/%d", this.getDag(), this.getMaand(), this.getJaar());
	}
	
	/**geeft datum object terug als volgt: 4 februari 2009*/
	@Override
	public String toString()
	{
		return String.format("%d %s % d", this.getDag(), MAANDEN[this.getMaand()], this.getJaar());
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((calendar == null) ? 0 : calendar.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj) 
		{
			return true;
		}
		if (obj == null) 
		{
			return false;
		}
		if (!(obj instanceof Datum2)) 
		{
			return false;
		}
		Datum2 other = (Datum2) obj;
		if (calendar == null) {
			if (other.getCalendar() != null) {
				return false;
			}
		} else if (!calendar.equals(other.getCalendar())) {
			return false;
		}
		return true;
	}
	
	public int compareTo(Datum2 datum) 
	{
		return this.calendar.compareTo(datum.getCalendar());
	}
	
	/**bepaalt of een datum d kleiner is dan huidig datumobject*/
	public boolean kleinerDan(Datum2 datum)
	{
		return this.calendar.after(datum.getCalendar());
	}
	
	/**bepaalt of een datum d groter is dan huidig datumobject*/
	public boolean groterDan(Datum2 datum)
	{
		return this.calendar.before(datum.getCalendar());
	}
	
	/**bepaalt het verschil in volledige jaren tussen datum d en huidig datumobject  
	 * (vb 01032007 en 03012009 -> 1 jaar)*/
	public int verschillInJaren(Datum2 datum)
	{
		return this.verschillCalender(datum).get(Calendar.YEAR) - 1970;
	}
	
	/**bepaalt het verschil in volledige maanden tussen datum d en huidig datumobject 
	 * (vb 01032007 en 03012009 -> 22 maanden)*/
	public int verschillInMaanden(Datum2 datum)
	{
		return (this.verschillCalender(datum).get(Calendar.YEAR) - 1970)*12 
			    + this.verschillCalender(datum).get(Calendar.MONTH);	
	}
	
	/**bepaalt het verschil in dagen tussen datum d en huidig datumobject*/
	public int verschillInDagen(Datum2 datum)
	{
		long l = this.verschillInMillis(datum)/(24*60*60*1000);
		int i = (int)l;
		return i;
	}
	
	/**bepaalt het verschil in milliseconds tussen datum d en huidig datumobject*/
	public long verschillInMillis(Datum2 datum)
	{
		return Math.abs(this.calendar.getTime().getTime() 
				- datum.calendar.getTime().getTime());
	}
	
	/**returns calender calculated from het verschil in milliseconds
	 *  tussen datum d en huidig datumobject*/
	public Calendar verschillCalender(Datum2 datum)
	{
		Calendar c = new GregorianCalendar();
		c.setTimeInMillis(this.verschillInMillis(datum));
		return c;
	}
	
	/** verhoogt of verlaagt de datum met een aantal dagen */
	public void veranderDatum(int aantalDagen)
	{
		this.calendar.add(Calendar.DAY_OF_YEAR, aantalDagen);
	}
	
	/**	geeft een nieuw Datum object terug dat 
	 * gelijk is aan het originele datum object verhoogt of verlaagt met een aantal dagen. */
	public Datum2 veranderedDatum(int aantalDagen)
	{
		this.veranderDatum(aantalDagen);
		return this;
	}
	
	public static void main(String[] args) 
	{
		try
		{
			Datum2 c = new Datum2();
			System.out.println(c);
			Datum2 d = new Datum2("29/2/1964");
			System.out.println(d.getMaand());
			System.out.println(d);
			Datum2 b = new Datum2(31, 12, 2014);
			System.out.println(b);
			System.out.println(b.getMaand());
			Datum2 a = new Datum2(b);
			System.out.println(a.veranderedDatum(3));
			System.out.println(b.kleinerDan(d));
			System.out.println();

		}
		catch (IllegalArgumentException e)
		{
			System.out.print(e);
		}
	}

}

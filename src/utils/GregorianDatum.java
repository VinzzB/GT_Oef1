package utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author      Nathalie Mathieu <natmathieu@gmail.com>
 * @author      Natalia Dyubankova <fornnd@gmail.com>
 * @version     1.0                 
 * @since       2014-10-08          
 */
public class GregorianDatum implements Comparable<GregorianDatum>
{
	/**
	 * Object van klasse GregorianCalendar
	 */
	private GregorianCalendar calendar;
		
	/**
	 * Een constructor zonder parameters, object datum gelijk aan de systeemdatum
	 */
	public GregorianDatum()
	{
		calendar = new GregorianCalendar();
	}
	
	/**
	 * Een constructor met een GregorianDatum object als parameter 
	 *  
	 *  @param datum GregorianDatum
	 */
	public GregorianDatum(GregorianDatum datum)
	{
		this();
		this.calendar.set(datum.getJaar(), datum.getMaand(), datum.getDag());
		
	}
	
	/**
	 * Een constructor met parameters dag, maand en jaar ( 3 gehele getallen) 
	 *  
	 *  @param dag int zet dag van calendar 
	 *  @param maand int zet maand van calendar 
	 *  @param jaar int zet jaar van calendar 
	 */
	public GregorianDatum(int dag, int maand, int jaar)
	{
		this();
		this.setDatum(dag, maand, jaar);
	}
	
	/**
	 * Een constructor met een String als parameter  
	 *  
	 * @param datum  String in DDMMJJJJ formaat maar tussen de dag, 
	 *  					maand en jaar staat een scheidingsteken 
	 *  					(Vb 12/05/2009)
	 */
	public GregorianDatum(String datum)
	{
		this();
		
		this.setDatum(Integer.parseInt(datum.split("/")[0]),
					Integer.parseInt(datum.split("/")[1]),
					Integer.parseInt(datum.split("/")[2])); 
	}
	
	/**
	 * Sets the calendar day with the given day.
	 * Controls if the parameter is allowed according to the length of different
	 * months of different years
	 * 
	 * @param dag
	 * @return dag van de maand
	 * @throws IllegalArgumentException
	 */
	private int setDag(int dag) throws IllegalArgumentException
	{
		if(dag >= this.calendar.getActualMinimum(GregorianCalendar.DAY_OF_MONTH) && 
				dag <= this.calendar.getActualMaximum(GregorianCalendar.DAY_OF_MONTH))
			return dag;
		else throw new IllegalArgumentException ("fout dag");
	}

	/**
	 * Sets the calendar month with the given month extracted 1 
	 * to adopt GregorianCalendar format.
	 * Controls if the parameter is between 1 and 12
	 * 
	 * @param maand
	 * @return maand van de jaar
	 * @throws IllegalArgumentException
	 */
	private int setMaand(int maand) throws IllegalArgumentException 
	{
		if (maand <= 0 || maand > 12) throw new IllegalArgumentException ("fout maand");
		return maand - 1;
	}
	
	/**
	 * Sets the calendar year with the given year.
	 * Controls if the parameter is higher then 1900
	 * 
	 * @param jaar
	 * @return jaar
	 * @throws IllegalArgumentException
	 */
	private int setJaar(int jaar) throws IllegalArgumentException
	{
		if (jaar < 0) throw new IllegalArgumentException ("fout jaar");
		return jaar;
	}
	/**Een methode om een GregorianDatum object een geldige waarde te geven
	 * 
	 * @param dag
	 * @param maand
	 * @param jaar
	 * @return frue (gelukt) / false (niet gelukt)
	 */
	public boolean setDatum(int dag, int maand, int jaar)
	{
		this.calendar.set(Calendar.YEAR, setJaar(jaar));
		this.calendar.set(Calendar.MONTH, setMaand(maand));
		this.calendar.set(Calendar.DAY_OF_MONTH, setDag(dag));
		return true;
	}
	
	/**
	 * @return jaar van calendar
	 */
	public int getJaar()
	{
		return this.calendar.get(Calendar.YEAR);
	}
	
	/**
	 * @return maand van calendar
	 */
	public int getMaand()
	{
		return this.calendar.get(Calendar.MONTH);
	}
	
	/**
	 * @return dag van calendar
	 */
	public int getDag()
	{
		return this.calendar.get(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * @return calendar
	 */
	public GregorianCalendar getCalendar()
	{
		return this.calendar;
	}
	
	/** geeft een datum in Amerikaans formaat terug (vb 2009/2/4) 
	 * 
	 * @return String in Amerikaans formaat
	 */
	public String getDatumInAmerikaansFormaat()
	{
		return String.format("%d/%d/%d", this.getJaar(), this.getMaand() + 1, this.getDag());
	}
	
	/**geeft een datum in Europees formaat terug (vb 4/2/2009)
	 * 
	 * @return String in Europees formaat
	 */
	public String getDatumInEuropeesFormaat()
	{
		return String.format("%d/%d/%d", this.getDag(), this.getMaand() + 1, this.getJaar());
	}
	
	/**geeft datum object terug als volgt: 4 februari 2009
	 * 
	 * @return String in d MMMM yyyy format
	 */
	@Override
	public String toString()
	{
		SimpleDateFormat string = new SimpleDateFormat("d MMMM yyyy");
		return string.format(this.calendar.getTime()).toString();
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
		if (!(obj instanceof GregorianDatum)) 
		{
			return false;
		}
		GregorianDatum other = (GregorianDatum) obj;
		
		/* Not Javadoc
		 * GregorianCalendar may have different milliseconds value for the same date value. 
		 * Difficult to compare.
		 */
		this.calendar.clear(GregorianCalendar.MILLISECOND);
		other.calendar.clear(GregorianCalendar.MILLISECOND);
		if (calendar == null) {
			if (other.getCalendar() != null) {
				return false;
			}
		} else if (!calendar.equals(other.getCalendar())) {
			return false;
		}
		return true;
	}
	
	public int compareTo(GregorianDatum datum) 
	{
		return this.calendar.compareTo(datum.getCalendar());
	}
	
	/**
	 * bepaalt of een datum d kleiner is dan huidig datumobject
	 * 
	 * @param datum
	 * @return true / false
	 */
	public boolean kleinerDan(GregorianDatum datum)
	{
		return this.calendar.before(datum.getCalendar());
	}
	
	/**
	 * bepaalt of een datum d groter is dan huidig datumobject
	 * 
	 * @param datum
	 * @return true / false
	 */
	public boolean groterDan(GregorianDatum datum)
	{
		return this.calendar.after(datum.getCalendar());
	}
	
	/**
	 * bepaalt het verschil in volledige jaren tussen datum d en huidig datumobject  
	 * (vb 01032007 en 03012009 -> 1 jaar)
	 * @param datum
	 * @return int jaren
	 */ 
	public int verschillInJaren(GregorianDatum datum)
	{
		return this.verschillCalender(datum).get(Calendar.YEAR) - 1970;
	}
	
	/**
	 * bepaalt het verschil in volledige maanden tussen datum d en huidig datumobject 
	 * (vb 01032007 en 03012009 -> 22 maanden)
	 * @param datum
	 * @return int maanden
	 */
	public int verschillInMaanden(GregorianDatum datum)
	{
		return (this.verschillCalender(datum).get(Calendar.YEAR) - 1970)*12 
			    + this.verschillCalender(datum).get(Calendar.MONTH);	
	}
	
	/**
	 * bepaalt het verschil in dagen tussen datum d en huidig datumobject
	 * 
	 * @param datum
	 * @return int dagen
	 */
	public int verschillInDagen(GregorianDatum datum)
	{
		int dagen = 0;
		
		while (!this.equals(datum))
		{
			this.veranderDatum(this.compareTo(datum)*(-1));
			dagen++;
		}
		return dagen;
	}
	
	/**
	 * bepaalt het verschil in milliseconds tussen datum d en huidig datumobject
	 * 
	 * @param datum
	 * @return int milliseconds
	 */
	public long verschillInMillis(GregorianDatum datum)
	{
		return Math.abs(this.calendar.getTime().getTime() 
				- datum.calendar.getTime().getTime());
	}
	
	/**
	 * returns calender calculated from het verschil in milliseconds
	 *  tussen datum d en huidig datumobject
	 * @param datum
	 * @return GregorianDatum
	 */ 
	public Calendar verschillCalender(GregorianDatum datum)
	{
		Calendar c = new GregorianCalendar();
		c.setTimeInMillis(this.verschillInMillis(datum));
		return c;
	}
	
	/** 
	 * verhoogt of verlaagt de datum met een aantal dagen
	 * 
	 * @param aantalDagen
	 */
	public void veranderDatum(int aantalDagen)
	{
		this.calendar.add(Calendar.DAY_OF_YEAR, aantalDagen);
	}
	
	/**	
	 * geeft een nieuw GregorianDatum object terug dat 
	 * gelijk is aan het originele datum object verhoogt of verlaagt 
	 * met een aantal dagen.
	 * 
	 * @param aantalDagen
	 * @return GregorianDatum
	 */
	public GregorianDatum veranderedDatum(int aantalDagen)
	{
		this.veranderDatum(aantalDagen);
		return this;
	}
	
	/**
	 * Test static klasse
	 * Prints op concole output can methoden om te testen
	 * Moet verwijderd worden bij gebruik van de klasse
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		try
		{
			GregorianDatum c = new GregorianDatum();
			System.out.println(c);
			GregorianDatum d = new GregorianDatum("28/2/1963");
			System.out.println(d.getMaand());
			System.out.println(d);
			System.out.println(d.getDatumInAmerikaansFormaat());
			System.out.println(d.getDatumInEuropeesFormaat());
			System.out.println(d.calendar.getActualMaximum(GregorianCalendar.DAY_OF_MONTH));
			System.out.println(d.calendar.getMinimum(GregorianCalendar.DAY_OF_MONTH));
			GregorianDatum b = new GregorianDatum(31, 12, 2014);
			System.out.println(b);
			System.out.println(b.getMaand());
			GregorianDatum a = new GregorianDatum(b);
			System.out.println(a.equals(b));
			System.out.println(a.veranderedDatum(3));
			System.out.println(a.verschillInDagen(b));
			
			GregorianDatum e = new GregorianDatum(1, 3, 2007);
			GregorianDatum f  = new GregorianDatum(3, 1, 2009);
			
			
			System.out.println("objects created: ");
			System.out.println("e: " + e);
			System.out.println("f: " + f);
			
			System.out.print("American format: ");
			System.out.println(e.getDatumInAmerikaansFormaat());
			
			System.out.print("European format: ");
			System.out.println(f.getDatumInAmerikaansFormaat());
			
			System.out.print("Is f kleiner dan e: ");
			System.out.println(e.kleinerDan(f));
			
			System.out.print("Verschil Jaren: ");
			System.out.println(e.verschillInJaren(f));
			
			System.out.print("Verschil Maanden: ");
			System.out.println(e.verschillInMaanden(f));
			
			System.out.print("Verschil Dagen: ");
			System.out.println(e.verschillInDagen(f));




		}
		catch (IllegalArgumentException e)
		{
			System.out.print(e);
		}
	}

}

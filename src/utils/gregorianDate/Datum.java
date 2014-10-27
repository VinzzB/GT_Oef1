package utils.gregorianDate;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * @author      Nathalie Mathieu <natmathieu@gmail.com>
 * @author      Natalia Dyubankova <fornnd@gmail.com>
 * @version     1.0                 
 * @since       2014-10-08          
 */
public class Datum implements Comparable<Datum>
{
	/**
	 * Object van klasse GregorianCalendar
	 */
	private GregorianCalendar calendar;
		
	/**
	 * Een constructor zonder parameters, object datum gelijk aan de systeemdatum
	 */
	public Datum()
	{
		calendar = new GregorianCalendar();
	}
	
	/**
	 * Een constructor met een GregorianDatum object als parameter 
	 *  
	 *  @param datum GregorianDatum
	 */
	public Datum(Datum datum)
	{
		this();
		this.calendar.set(datum.getYear(), datum.getMonth(), datum.getDay());
		
	}
	
	/**
	 * Een constructor met parameters dag, maand en jaar ( 3 gehele getallen) 
	 *  
	 *  @param dag int zet dag van calendar 
	 *  @param maand int zet maand van calendar 
	 *  @param jaar int zet jaar van calendar 
	 */
	public Datum(int dag, int maand, int jaar)
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
	public Datum(String datum)
	{
		this();

		this.setDatum(Integer.parseInt(datum.split("\\W")[0]),
					Integer.parseInt(datum.split("\\W")[1]),
					Integer.parseInt(datum.split("\\W")[2])); 
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
	public int getYear()
	{
		return this.calendar.get(Calendar.YEAR);
	}
	
	/**
	 * @return maand van calendar
	 */
	public int getMonth()
	{
		return this.calendar.get(Calendar.MONTH);
	}
	
	/**
	 * @return dag van calendar
	 */
	public int getDay()
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
	public String getAmericanFormat()
	{
		return String.format("%d/%d/%d", this.getYear(), this.getMonth() + 1, this.getDay());
	}
	
	/**geeft een datum in Europees formaat terug (vb 4/2/2009)
	 * 
	 * @return String in Europees formaat
	 */
	public String getEuropeanFormat()
	{
		return String.format("%d/%d/%d", this.getDay(), this.getMonth() + 1, this.getYear());
	}
	
	/**geeft datum object terug als volgt: 4 februari 2009
	 * 
	 * @return String in d MMMM yyyy format
	 */
	@Override
	public String toString()
	{
		SimpleDateFormat string = new SimpleDateFormat("d MMMM yyyy", new Locale("nl"));
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
		if (!(obj instanceof Datum)) 
		{
			return false;
		}
		Datum other = (Datum) obj;
		
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
	
	public int compareTo(Datum datum) 
	{
		return this.calendar.compareTo(datum.getCalendar());
	}
	
	/**
	 * bepaalt of een datum d kleiner is dan huidig datumobject
	 * 
	 * @param datum
	 * @return true / false
	 */
	public boolean kleinerDan(Datum datum)
	{
		return this.calendar.before(datum.getCalendar());
	}
	
	/**
	 * bepaalt of een datum d groter is dan huidig datumobject
	 * 
	 * @param datum
	 * @return true / false
	 */
	public boolean groterDan(Datum datum)
	{
		return this.calendar.after(datum.getCalendar());
	}
	
	/**
	 * bepaalt het verschil in volledige jaren tussen datum d en huidig datumobject  
	 * (vb 01032007 en 03012009 -> 1 jaar)
	 * @param datum
	 * @return int jaren
	 */ 
	public int verschilInJaren(Datum datum)
	{
		int jaren = 0;
		Datum kleinere = new Datum(this.kleinerDan(datum) ? this : datum);
		Datum grotere = new Datum(this.groterDan(datum) ? this : datum);
		while(kleinere.kleinerDan(grotere))
		{
			kleinere.calendar.add(GregorianCalendar.YEAR, 1);
			if(kleinere.kleinerDan(grotere) || kleinere.equals(grotere)) jaren++;
		}
		return jaren;
	}
	
	/**
	 * bepaalt het verschil in volledige maanden tussen datum d en huidig datumobject 
	 * (vb 01032007 en 03012009 -> 22 maanden)
	 * @param datum
	 * @return int maanden
	 */
	public int verschilInMaanden(Datum datum)
	{
		int maanden = 0;
		Datum kleinere = new Datum(this.kleinerDan(datum) ? this : datum);
		Datum grotere = new Datum(this.groterDan(datum) ? this : datum);
		while(kleinere.kleinerDan(grotere))
		{
			kleinere.calendar.add(GregorianCalendar.MONTH, 1);
			if(kleinere.kleinerDan(grotere) || kleinere.equals(grotere)) maanden++;
		}
		return maanden;	
	}
	
	/**
	 * bepaalt het verschil in dagen tussen datum d en huidig datumobject
	 * 
	 * @param datum
	 * @return int dagen
	 */
	public int verschilInDagen(Datum datum)
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
	public long verschillInMillis(Datum datum)
	{
		return Math.abs(this.calendar.getTime().getTime() 
				- datum.calendar.getTime().getTime());
	}
		
	/** 
	 * verhoogt of verlaagt de datum met een aantal dagen
	 * 
	 * @param aantalDagen
	 */
	private void veranderThisDatum(int aantalDagen)
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
	public Datum veranderDatum(int aantalDagen)
	{
		this.veranderThisDatum(aantalDagen);
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
			Datum c = new Datum();
			System.out.println(c);
			Datum d = new Datum("3/5/2007");
			System.out.println(d.getMonth());
			System.out.println(d);
			System.out.println(d.getAmericanFormat());
			System.out.println(d.getEuropeanFormat());
			System.out.println(d.calendar.getActualMaximum(GregorianCalendar.DAY_OF_MONTH));
			System.out.println(d.calendar.getMinimum(GregorianCalendar.DAY_OF_MONTH));
			Datum b = new Datum(31, 12, 2014);
			System.out.println(b);
			System.out.println(b.getMonth());
			Datum a = new Datum(b);
			System.out.println(a.equals(b));
			System.out.println(a.veranderDatum(3));
			System.out.println(a.verschilInDagen(b));
		
			Datum e = new Datum(1, 3, 2007);
			Datum f  = new Datum(3, 1, 2009);
			Datum zero = new Datum(1, 1, 1);
			
			
			System.out.println("objects created: ");
			System.out.println("e: " + e);
			System.out.println("f: " + f);
			System.out.println("zero: " + zero);
			
			System.out.print("American format: ");
			System.out.println(e.getAmericanFormat());
			
			System.out.print("European format: ");
			System.out.println(f.getAmericanFormat());
			
			System.out.print("Is f kleiner dan e: ");
			System.out.println(e.kleinerDan(f));
			
			System.out.print("Verschil Jaren: ");
			System.out.println(e.verschilInJaren(f));
			
			System.out.print("Verschil Maanden: ");
			System.out.println(e.verschilInMaanden(f));
			
			System.out.print("Verschil Dagen: ");
			System.out.println(e.verschilInDagen(f));
		}
		catch (IllegalArgumentException e)
		{
			System.out.print(e);
		}
	}

}

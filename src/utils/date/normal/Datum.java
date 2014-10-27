package utils.date.normal;

import java.util.Date;

/**
 * Een Datum classe die 'From Scratch' is opgebouwd.
 * @author Isaak, Silvia, Vinzz
 *
 */
public class Datum implements Comparable<Datum>, Cloneable
{
	private int day = 0;
	private int month = 0;
	private int year = 0;

	/**
	 * CTOR: Day / month / year
	 * 
	 * @param dag
	 * @param maand
	 * @param jaar
	 */
	public Datum(int dag, int maand, int jaar)
	{
		setDatum(dag, maand, jaar);
	}

	/**
	 * CTOR: date object
	 * 
	 * @param fullDate
	 */
	@SuppressWarnings("deprecation")
	public Datum(Date fullDate)
	{
		setDatum(fullDate.getDate(), fullDate.getMonth(), fullDate.getYear());
	}

	/**
	 * Copy CTOR (private) -> public equivalent = Clone()
	 * 
	 * @param dateObj
	 */
	private Datum(Datum dateObj)
	{
		setDatum(dateObj.getDay(), dateObj.getMonth(), dateObj.getYear());
	}

	/**
	 * CTOR: date today
	 */
	public Datum()
	{
		this(new Date());
	}

	/**
	 * CTOR: String that contains the European date format. Format: DD/MM/YYYY
	 * 
	 * @param datum
	 * @throws IllegalArgumentException
	 */
	public Datum(String datum) throws IllegalArgumentException
	{
		// split string and validate
		String[] dateArr = datum.split("/");
		if (dateArr.length < 2 || !(dateArr[0].length() >= 1)
							   || !(dateArr[1].length() == 2) 
							   || !(dateArr[2].length() == 4))
		{
			throw new IllegalArgumentException(
					"Er werd een ongeldig formaat opgegeven. Geldig formaat: (D)D/MM/YYYY");
		}
		// set internal date
		setDatum(Integer.parseInt(dateArr[0]), 
				Integer.parseInt(dateArr[1]),
				Integer.parseInt(dateArr[2]));
	}

	// GETTERS
	/**
	 * dag van datum
	 * @return
	 */
	public int getDay()
	{
		return day;
	}
	/**
	 * maand van datum
	 * @return
	 */
	public int getMonth()
	{
		return month;
	}
	/**
	 * jaar van datum
	 * @return
	 */
	public int getYear()
	{
		return year;
	}

	// SETTERS
	private void setDay(int day) throws IllegalArgumentException
	{
		int dim = Maanden.get(month).GetLength(year); 
		if (day < 1 || day > dim)
		{
			throw new IllegalArgumentException(
					"De opgegeven dag ligt buiten de geldige range. (1-" + dim + ")");
		}
		this.day = day;
	}

	private void setMonth(int month) throws IllegalArgumentException
	{
		if (month < 1 || month > 12)
		{
			throw new IllegalArgumentException(
					"De opgegeven maand ligt buiten de geldige range. (1-12)");
		}
		this.month = month;
	}

	private void setYear(int year) throws IllegalArgumentException
	{
		if (year < 0)
		{
			throw new IllegalArgumentException(
					"Het opgegeven jaar ligt buiten de geldige range. (>=0)");
		}
		this.year = year;
	}

	/**
	 * zet de datum naar de opgegeven waarden.
	 * @param dag
	 * @param maand
	 * @param jaar
	 * @return true als succesvol.
	 */
	public boolean setDatum(int dag, int maand, int jaar)
	{
		setYear(jaar);
		setMonth(maand);
		setDay(dag);
		return true;
	}

	/**
	 * Datum in Amerikaans formaat (YYYY/MM/DD)
	 * @return
	 */
	public String getAmericanFormat()
	{
		return String.format("%04d/%02d/%02d", year, month, day);
	}

	/**
	 * Datum in Europees formaat (DD/MM/YYYY)
	 * @return
	 */
	public String getEuropeanFormat()
	{
		return String.format("%02d/%02d/%04d", day, month, year);
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return String.format("%d %s %04d", day, Maanden.get(month), year);
	}

	/**
	 * @see java.lang. java.lang.Comparable#compareTo(T o)
	 */
	@Override
	public int compareTo(Datum normalDate)
	{
		// Check Year
		if (year > normalDate.year) { return 1; }
		if (year < normalDate.year) { return -1; }
		// Check Month
		if (month > normalDate.month) { return 1; }
		if (month < normalDate.month) { return -1; }
		// check Day
		if (day > normalDate.day) { return 1; }
		if (day < normalDate.day) { return -1; }
		return 0; // equal
	}

	/**
	 * 
	 * @see java.lang.Object#equals()
	 */
	@Override
	public boolean equals(Object obj)
	{	
		// Same object? = Always true
		if (this == obj)
			return true;
		// Same Type?
		if (obj == null || !(obj instanceof Datum))
			return false;
		// CompareDate and return true if equal.
		return compareTo((Datum) obj) == 0;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 37;
		int result = 1;
		result = prime * result + day;
		result = prime * result + month;
		result = prime * result + year;
		return result;
	}

	/**
	 * Is huidig Datum object kleiner dan opgegeven Datum object
	 * @param date
	 * @return
	 */
	public boolean kleinerDan(Datum date)
	{
		return compareTo(date) < 0;
	}

	/**
	 * Is huidig Datum object groter dan opgegeven Datum object
	 * @param date
	 * @return
	 */
	public boolean groterDan(Datum date)
	{
		return compareTo(date) > 0;
	}

	/**
	 * Telt het verschil in dagen tussen het huidig Datum object en het opgegeven Datum object
	 * @param date
	 * @return
	 */
	public int verschilInDagen(Datum date)
	{
		return new DateDiff(this, date).getDays();
	}

	/**
	 * Telt het verschil in Maanden tussen het huidig Datum object en het opgegeven Datum object
	 * @param date
	 * @return
	 */
	public int verschilInMaanden(Datum date)
	{
		return new DateDiff(this, date).getMonths();
	}

	/**
	 * Telt het verschil in jaren tussen het huidig Datum object en het opgegeven Datum object
	 * @param date
	 * @return
	 */
	public int verschilInJaren(Datum date)
	{
		return new DateDiff(this, date).getYears();
	}
	/**
	 * Verandert de huidige datum(!) met X aantal dagen en geeft een nieuw(!) Datum object terug. 
	 * @param aantalDagen
	 * @return Een nieuw Datum object
	 */
	public Datum veranderDatum(int aantalDagen)
	{
		int d = day;
		int m = month;
		int y = year;
		if (aantalDagen > 0)
		{
			while (aantalDagen + d > Maanden.get(m).GetLength(y))
			{
				// verminder met dagen in maand
				aantalDagen -= Maanden.get(m).GetLength(y) - d + 1; 
				y += (m == 12 ? 1 : 0); // verhoog jaar?
				m = (m == 12 ? 1 : m + 1); // verhoog maand?
				d = 1; // Nieuwe maand
			}
		}
		else
		// negative value, go back in time!
		{
			while (-d >= aantalDagen)
			{
				// verminder met aantal dagen in huidige maand.
				aantalDagen += d; 
				y -= (m == 1 ? 1 : 0); // verminder jaartal?
				m = (m == 1 ? 12 : m - 1); // verminder maand?
				// zet als laatste dag van (vorige) maand
				d = Maanden.get(m).GetLength(y); 													 
			}
		}
		setDatum(d+ aantalDagen, m, y); //add rest
		return (Datum)clone(); 
	}

	/**
	 * 
	 * @see java.lang.Object#clone()
	 */
	@Override
	protected Object clone()
	{
		return new Datum(this);
	}
}
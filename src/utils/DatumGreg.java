package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;


public class DatumGreg {

	private GregorianCalendar gregCal; 
	//private GregorianCalendar currentGregCall = new GregorianCalendar();

	private int dag;
	private int maand;
	private int jaar;
	private String sDate;
	private Date dDatum;
	 
	
//Constructor----------------------------------------------------
	
	public DatumGreg()
	{		
		
	}
	
	public DatumGreg(Date date)
	{
		//gregCal-----------
		this.gregCal = new GregorianCalendar();
		this.gregCal.setGregorianChange(date);
		//Date--------------
		this.dDatum = date;		
	}
	
	public DatumGreg(int day, int month, int year) throws ParseException
	{
		//gregCal-----------
		gregCal = new GregorianCalendar(day, month, year);
		//Date--------------
		setDatum(day,month,year);//boolean setDatum?
	}
	
	public DatumGreg(String sDate) throws ParseException
	{
		//controle op string DDMMJJJJ via exception? format in error message in main specifiëren?
		//Date
		setDateFromString(sDate); 
	}
	
//------------------------------------------------------------------------------
	
//Getters en setters-------------------------------------------------------------
	
	//Set en Get datum---------------
	
	public boolean setDatum(int dag, int maand, int jaar) throws ParseException
	{//boolean??
		//gregCal-----------??
		this.gregCal = new GregorianCalendar(dag, maand, jaar);
		//Date--------------
		setDag(dag);
		setMaand(maand);
		setJaar(jaar);		
		return true;
	}
	
	public Date getDate()
	{
		return this.dDatum;
	}
	
	public String getDate(int dag, int maand, int jaar)
	{
		sDate = String.format("%02d/%02d/%4d", dag, maand, jaar);
		return sDate;
	}
	
	public void setDag(int dag)throws IllegalArgumentException
	{
		if (dag < 1 || dag > 31)throw new IllegalArgumentException("Dag verkeerd!");
		this.dag = dag;	
	}
	
	public int getDag()
	{
		return this.dag;
	}
	
	public void setMaand(int maand)throws IllegalArgumentException
	{
		if (maand < 1 || maand > 12)throw new IllegalArgumentException("Maand verkeerd!");
		this.maand = maand;
	}
	
	public int getMaand()
	{
		return this.maand;
	}
	
	public void setJaar(int jaar)throws IllegalArgumentException
	{
		if (jaar < 0 || jaar > getCurrentYear())throw new IllegalArgumentException("Jaar verkeerd!");
		this.jaar = jaar;
	}
	
	public int getJaar()
	{
		return this.jaar;
	}
	
	//--------------------------------------

	//Get Date format-----------------------

	public String getDatumInAmerikaansFormaat()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/M/d");
		String date = sdf.format(this.dDatum);
		return date;
	}
	
	public String getDatumInEuropeesFormaat()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("d/M/yyyy");
		String date = sdf.format(this.dDatum);
		return date;
	}
	
	//--------------------------------------
	
//Override methods--------------------------------------------------------------
	
		@ Override
		public String toString()
		{
			SimpleDateFormat sdf = new SimpleDateFormat("d MMMM yyyy");
			String sDateTekst = sdf.format(getDate());
			return sDateTekst;
		}
		
	

//--------------------------------------
		
//methods------------------------------------------------------------------

	public void setDateFromString(String sDate) throws ParseException
	{
		//Date
		this.dDatum = new SimpleDateFormat("dd/MM/yyyy").parse(sDate);		
	}	
	
	public String getSDate(GregorianCalendar gc)
	{
		int day = gc.get(GregorianCalendar.DAY_OF_MONTH);
		int month = gc.get(GregorianCalendar.MONTH);
		int year = gc.get(GregorianCalendar.YEAR);
		String date = String.format("%02d/%02d/%4d", day, month, year);
		return date;
	}
	
	public int getCurrentYear()
	{
		Date d = new Date();
		GregorianCalendar gc = this.setGregC(d);
		int currentYear = gc.get(GregorianCalendar.YEAR);
		return currentYear; 
	}
	
	public GregorianCalendar setGregC(Date d)
	{
		GregorianCalendar gc = new GregorianCalendar();
		gc.setGregorianChange(d);
		this.gregCal = gc;
		return gc;
	}
	
	public GregorianCalendar setGregC(GregorianCalendar gc)
	{
		this.gregCal = gc;
		return gc;
	}
	
	public GregorianCalendar getGregC()
	{
		return this.gregCal;
	}
	
	public int verschilInDagen (Date d1, Date d2)
	{
		return (int)((d2.getTime()-d1.getTime())/(1000*60*60*24));
	}
	
	public boolean kleinerDan(Date d2)
	{
		Boolean check = this.getDate().after(d2);
		return check;
	}
	
	public int verschilInMaanden (GregorianCalendar gc1, GregorianCalendar gc2)
	{
		int count = 0;
		Date d1 = gc1.getTime(); Date d2 = gc2.getTime();
		for(count = 0; d1.getTime() < d2.getTime(); count++)
				{
					gc1.add(GregorianCalendar.MONTH,1);	
					d1 = gc1.getTime();
				}
		return count;
	}
	
	public int verschilInJaren (GregorianCalendar gc1, GregorianCalendar gc2)
	{
		int count = 0;
		Date d1 = gc1.getTime(); Date d2 = gc2.getTime();
		for(count = 0; d1.getTime() < d2.getTime(); count++)
				{
					gc1.add(GregorianCalendar.YEAR,1);		
					d1 = gc1.getTime();
				}
		return count;
	}
	
	public void veranderDatum (int aantalDagen)
	{
		this.getGregC().add(GregorianCalendar.DAY_OF_MONTH, aantalDagen);
	}
	
	public DatumGreg veranderDatum1 (int aantalDagen) throws ParseException
	{
		GregorianCalendar gc = this.getGregC();
		gc.add(GregorianCalendar.DAY_OF_MONTH, aantalDagen);
		this.setGregC(gc);
		DatumGreg d = new DatumGreg(this.getDag(), this.getMaand(), this.getJaar());
		return d;
	}
}
	
	

	
//-------------------------------------------------------------------------

	

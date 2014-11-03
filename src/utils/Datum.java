package utils;

import java.util.Date;

/**
 * 
 * @author Isaak, Silvia, Vincent
 */
public class Datum implements Comparable<Datum>
{
	private int dag;
	private int maand;
	private int jaar;
	

	/**
	 * Constructor zonder parameters
	 */
	@SuppressWarnings("deprecation")
	public Datum()
	{
		Date datumNu = new Date();
		this.setJaar(datumNu.getYear());
		this.setMaand(datumNu.getMonth());
		this.setDag(datumNu.getDay());
	}

	/**
	 * Constructor met Datum object parameter
	 * @param d
	 */
	public Datum(Datum d)
	{
		this.setJaar(d.jaar);
		this.setMaand(d.maand);
		this.setDag(d.dag);
	}

	/**
	 * Constructor met datum parameters: 3 gehele getallen
	 * @param dag
	 * @param maand
	 * @param jaar
	 */
	public Datum(int dag, int maand, int jaar)
	{
		this.setJaar(jaar);
		this.setMaand(maand);
		this.setDag(dag);
	}

	/**
	 * constructor met String in de vorm van 15/05/2014
	 * @param datumTekst
	 */ 
	public Datum(String datumTekst)
	{
		if (datumTekst.length() == 9 || datumTekst.length() == 10) {
			// Nu kijken we of de datum in formaat (x)x/xx/xxxx is gegeven
			if (datumTekst.indexOf("/") != -1) {
				String datumDelen[] = datumTekst.split("/");
				
				this.setDag(Integer.parseInt(datumDelen[0]));
				this.setMaand(Integer.parseInt(datumDelen[1]));
				this.setJaar(Integer.parseInt(datumDelen[2]));
				return;
			}
		}
		
		// Enkel als het niet lukte wordt de exception gethrowt
		throw new IllegalArgumentException("Foutieve datum gegeven");
	}

	/**
	 * 
	 * @return
	 */
	public int getDag()
	{
		return dag;
	}

	/**
	 * 
	 * @param dag
	 */
	private void setDag(int dag)
	{
		if (dag >= 1 && dag <= 31) {
			switch (maand) {
				case 2:
					if (this.isSchrikkeljaar(jaar)) {
						if (dag <= 29) {
							this.dag = dag;
						}
						else {
							throw new IllegalArgumentException("Dag moet tussen 1 en 29 liggen voor " + Maand.values()[maand - 1] + " in een schrikkeljaar");
						}
					}
					else {
						if (dag <= 28) {
							this.dag = dag;
						}
						else {
							throw new IllegalArgumentException("Dag moet tussen 1 en 28 liggen voor " + Maand.values()[maand - 1] + " in een niet-schrikkeljaar");
						}
					}
					break;
					
				case 4:
				case 6:
				case 9:
				case 11:
					if (dag <= 30) {
						this.dag = dag;
					} else {
						throw new IllegalArgumentException("Dag voor " + Maand.values()[maand - 1] + " maand moet tussen 1 en 30 liggen");
					}
					break;
					
				default:
					this.dag = dag;
					break;
			}
		}
		else { // dag groter dan 31
			throw new IllegalArgumentException("Dag moet tussen 1 en 31 liggen");
		}

	}
	
	/**
	 * Is het gegeven jaar een schrikkeljaar? Schrikkeljaren zijn deelbaar tot 4, tenzij het eeuwjaren zijn,
	 * dan moeten ze deelbaar zijn door 400
	 * 
	 * @return boolean true of false
	 */
	private boolean isSchrikkeljaar(int jaar)
	{
		return (jaar % 100 == 0 && jaar % 400 == 0) || jaar % 4 == 0;
	}

	/**
	 * 
	 * @return
	 */
	public int getMaand()
	{
		return maand;
	}

	/**
	 * Set maand methode
	 * @param maand
	 */
	private void setMaand(int maand)
	{
		// test of maand tussen 1 en 12 ligt
		if (maand >= 1 && maand <= 12) {
			this.maand = maand;
		} else {
			throw new IllegalArgumentException("maand moet tussen 1 en 12 liggen");
		}

	}

	/**
	 * 
	 * @return
	 */
	public int getJaar()
	{
		return jaar;
	}

	/**
	 * 
	 * @param jaar
	 */
	private void setJaar(int jaar)
	{
		// test of jaar groter of gelijk aan 0 is
		if (jaar >= 0) {
			this.jaar = jaar;
		} else {
			throw new IllegalArgumentException("ongeldige waarde voor jaar");
		}
	}

	/**
	 * 
	 * @return
	 */
	public String getDatumInAmerikaansFormaat()
	{
		return this.jaar + "/" + this.maand + "/" + this.dag;
	}

	/**
	 * 
	 * @return
	 */
	public String getDatumInEuropeesFormaat()
	{
		return this.dag + "/" + this.maand + "/" + this.jaar;
	}

	/**
	 * 
	 */
	@Override
	public String toString()
	{
		return this.dag + " " + Maand.values()[maand - 1] + " " + this.jaar;
	}

	/**
	 * 
	 */
	@Override
	public int hashCode()
	{
		int hash = 5;
		hash = 23 * hash + this.dag;
		hash = 23 * hash + this.maand;
		hash = 23 * hash + this.jaar;
		return hash;
	}

	/**
	 * 
	 */
	@Override
	public boolean equals(Object obj)
	{
		final Datum other = (Datum) obj;
		
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		
		if (this.dag != other.dag || this.maand != other.maand || this.jaar != other.jaar) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 */
	@Override
	public int compareTo(Datum d)
	{
		if (this.hashCode() > d.hashCode()) {
			return 1;
		}
		if (this.hashCode() < d.hashCode()) {
			return -1;
		}
		return 0;
	}

	/**
	 * 
	 * @param d
	 * @return
	 */
	public boolean kleinerDan(Datum d)
	{
		return this.compareTo(d) == 1;
	}

	/**
	 * 
	 * @param d
	 * @return
	 */
	public int verschilInJaren(Datum d)
	{
		int verschil = 0;
		verschil = verschilInMaanden(d) / 12;
		return Math.abs(verschil);
	}

	/**
	 * 
	 * @param d
	 * @return
	 */
	public int verschilInMaanden(Datum d)
	{
		int maandenDatum1;
		int maandenDatum2;
		int verschilMaanden;
		
		maandenDatum1 = this.jaar * 12;
		maandenDatum1 += this.maand;
		maandenDatum2 = d.jaar * 12;
		maandenDatum2 += d.maand;
		
		if (kleinerDan(d) == true) {
			verschilMaanden = maandenDatum2 - maandenDatum1;

		} else {
			verschilMaanden = maandenDatum1 - maandenDatum2;
		}
		return Math.abs(verschilMaanden);
	}

	public int verschilInDagen(Datum d) 
	{		// public int verschilInDagen(Datum d) {
				NormalDate datum1 = new NormalDate(this.dag, this.maand, this.jaar);			//
				NormalDate datum2 = new NormalDate(d.dag, d.maand, d.jaar);			// return 0;
				return new DateDiff(datum1, datum2).getDays();			// }
			}		
				
			public void veranderDatum(int aantalDagen) {		
				Datum d = new Datum(veranderDate(aantalDagen));		
				this.jaar = d.jaar;		
				this.maand = d.maand;		
				this.dag = d.dag;		
			}		
				
			public Datum veranderDate(int aantalDagen) {		
				int day = dag;		
				int month = maand;		
				int year = jaar;		
				
				if (aantalDagen > 0) {		
					for (int i = 1; i <= aantalDagen; i++) {		
						if (day < NormalDate.getDagenInMaand(month, year)) {		
							day++;		
						} else {		
							if (day == NormalDate.getDagenInMaand(month, year)) {		
								day = 1;		
								if (month < 12) {		
									month++;		
								} else {		
									if (month == 12) {		
										month = 1;		
										year++;		
									}		
								}		
							}		
						}		
					}		
				}		
				
				if (aantalDagen < 0) {		
					int j = Math.abs(aantalDagen);		
					for (int i = j; i > 0; i--) {		
						if (day <= NormalDate.getDagenInMaand(month, year) && day != 1) {		
							day--;		
						} else {		
							if (day == 1) {		
								if (month > 1) {		
									month--;		
								} else {		
									if (month == 1) {		
										month = 12;		
										year--;		
									}		
								}		
								day = NormalDate.getDagenInMaand(month, year);		
							}		
						}		
					}		
				}		
				
				return new Datum(day, month, year);		
			}
}
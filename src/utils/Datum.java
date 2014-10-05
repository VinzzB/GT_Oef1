package utils;
// Test123
import java.util.Date;

public class Datum implements Comparable<Datum> {
	/**
	 *
	 * fields
	 */
	private int dag;
	private int maand;
	private int jaar;

	/**
	 *
	 * Constructors
	 */
	// constructor zonder parameters
	@SuppressWarnings("deprecation")
	public Datum() {
		Date datumNu = new Date();
		this.setJaar(datumNu.getYear());
		this.setMaand(datumNu.getMonth());
		this.setDag(datumNu.getDay());
	} // end constructor Datum

	// constructor met datum parameter
	public Datum(Datum d) {
		this.setJaar(d.jaar);
		this.setMaand(d.maand);
		this.setDag(d.dag);
	}

	// constructor met 3 gehele getallen
	public Datum(int dag, int maand, int jaar) {
		this.setJaar(jaar);
		this.setMaand(maand);
		this.setDag(dag);
	}

	// constructor met String
	public Datum(String datumTekst) {
		this.setJaar(Integer.parseInt(datumTekst.split("/")[2]));
		this.setMaand(Integer.parseInt(datumTekst.split("/")[1]));
		this.setDag(Integer.parseInt(datumTekst.split("/")[0]));
	}

	/**
	 *
	 * @return
	 */
	public int getDag() {
		return dag;
	}// end method getDag

	private void setDag(int dag) {
		if (dag >= 1 && dag <= 31) // test of dag tussen 1 en 31 ligt
		{ // begin if
			switch (maand) {
			case 2:
				if (dag > 29) // februari: maximum 29
				{
					throw new IllegalArgumentException("dag moet tussen 1 en 29 liggen");
				} else {
					if (dag == 29) // enkel mogelijk in schrikkeljaar
					{
						if (jaar % 4 == 0) // deelbaar door 4
						{
							if (jaar % 100 == 0) // eeuwjaren
							{
								if (jaar % 400 == 0) { // deelbaar door 400 dus schrikkeljaar
									this.dag = dag;
								} else { // niet deelbaar door 400 is geen schrikkeljaar
									throw new IllegalArgumentException("dag moet tussen 1 en 28 liggen");
								}

							} else { // schrikkeljaar
								this.dag = dag;
							}
						} else { // geen schrikkeljaar
							throw new IllegalArgumentException("dag moet tussen 1 en 28 liggen");
						}
					} else { // dag is kleiner dan 29 ==> ok
						this.dag = dag;
					}

				}
				break;
			case 4:
			case 6:
			case 9:
			case 11:
				if (dag > 30) // april, juni, september en november: max 30 dagen
				{
					throw new IllegalArgumentException("dag moet tussen 1 en 30 liggen");
				} else {
					this.dag = dag;
				}
				break;
			default:
				this.dag = dag;
				break;
			} // end switch
		} // end if
		else { // dag groter dan 31
			throw new IllegalArgumentException("dag moet tussen 1 en 31 liggen");
		} // end else

	}// end method setDag

	public int getMaand() {
		return maand;
	}// end method getMaand

	private void setMaand(int maand) {
		// test of maand tussen 1 en 12 ligt
		if (maand >= 1 && maand <= 12) {
			this.maand = maand;
		} else {
			throw new IllegalArgumentException("maand moet tussen 1 en 12 liggen");
		}

	}// end method setMaand

	public int getJaar() {
		return jaar;
	}// end method getJaar

	private void setJaar(int jaar) {
		// test of jaar groter of gelijk aan 0 is
		if (jaar >= 0 && jaar <= 9999) {
			this.jaar = jaar;
		} else {
			throw new IllegalArgumentException("ongeldige waarde voor jaar");
		}
	}// end method setJaar

	public String getDatumInAmerikaansFormaat() {
		return this.jaar + "/" + this.maand + "/" + this.dag;
	}

	public String getDatumInEuropeesFormaat() {
		return this.dag + "/" + this.maand + "/" + this.jaar;
	}

	@Override
	public String toString() {
		String maandTekst = "";
		switch (this.maand) {
		case 1:
			maandTekst = "januari";
			break;
		case 2:
			maandTekst = "februari";
			break;
		case 3:
			maandTekst = "maart";
			break;
		case 4:
			maandTekst = "april";
			break;
		case 5:
			maandTekst = "mei";
			break;
		case 6:
			maandTekst = "juni";
			break;
		case 7:
			maandTekst = "juli";
			break;
		case 8:
			maandTekst = "augustus";
			break;
		case 9:
			maandTekst = "september";
			break;
		case 10:
			maandTekst = "oktober";
			break;
		case 11:
			maandTekst = "november";
			break;
		case 12:
			maandTekst = "december";
			break;
		}
		return "" + this.dag + " " + maandTekst + " " + this.jaar;
	} // end ToString

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 23 * hash + this.dag;
		hash = 23 * hash + this.maand;
		hash = 23 * hash + this.jaar;
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Datum other = (Datum) obj;
		if (this.dag != other.dag) {
			return false;
		}
		if (this.maand != other.maand) {
			return false;
		}
		if (this.jaar != other.jaar) {
			return false;
		}
		return true;
	} // end equals

	@Override
	public int compareTo(Datum d) {
		if (this.hashCode() > d.hashCode()) {
			return 1;
		}
		if (this.hashCode() < d.hashCode()) {
			return -1;
		}
		return 0;
	} // end compareTo

	public boolean kleinerDan(Datum d) {
		if (this.compareTo(d) == 1) {
			return true;
		}
		return false;
	} // end kleinerDan

	public int verschilInJaren(Datum d) {
		int verschil = 0;
		verschil = verschilInMaanden(d) / 12;
		return Math.abs(verschil);
	} // end verschilInJaren

	public int verschilInMaanden(Datum d) {
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
	} // end verschilInMaanden

	// public int verschilInDagen(Datum d) {
	//
	// return 0;
	// }
}// end class Datum

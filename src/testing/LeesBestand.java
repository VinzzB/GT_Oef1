package testing;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

<<<<<<< HEAD
import utils.date.gregorian.Datum;
=======
import utils.Datum;
>>>>>>> refs/remotes/origin/master

public class LeesBestand
{
	private String output;
	private String naamOudste;
	private String naamJongste = "";
	private Datum geboortedatumOudste;
	private Datum geboortedatumJongste;
	private ArrayList<String> fouteDatums;
	private int tellerFouteDatums;

	/**
	 *
	 */
	public LeesBestand()
	{
		this.output = "";
		this.naamOudste = "";
		this.naamJongste = "";
		this.geboortedatumOudste = null;
		this.geboortedatumJongste = null;
		this.fouteDatums = new ArrayList<String>();
		this.tellerFouteDatums = 0;
	}

	/**
	 * @param output
	 * @param naamOudste
	 * @param naamJongste
	 * @param geboortedatumOudste
	 * @param geboortedatumJongste
	 * @param fouteDatums
	 * @param tellerFouteDatums
	 */
	public LeesBestand(String output, String naamOudste, String naamJongste, Datum geboortedatumOudste,
			Datum geboortedatumJongste, ArrayList<String> fouteDatums, int tellerFouteDatums)
	{
		this.output = output;
		this.naamOudste = naamOudste;
		this.naamJongste = naamJongste;
		this.geboortedatumOudste = geboortedatumOudste;
		this.geboortedatumJongste = geboortedatumJongste;
		this.fouteDatums = fouteDatums;
		this.tellerFouteDatums = tellerFouteDatums;
	}

	public static void main(String[] args)
	{

		LeesBestand l = new LeesBestand();
		File file = new File("bestanden/personen.txt");
		try {
			l.leesFile(file);
			l.toonResultaat();
		} catch (FileNotFoundException ex) {
			System.out.println("bestand niet gevonden");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

	}

	/**
	 *
	 */
	public void toonResultaat()
	{
		setOutput(getOutput() + "oudste: " + getNaamOudste() + " " + getGeboortedatumOudste().toString() + "\n" + "jongste: "
				+ getNaamJongste() + " " + getGeboortedatumJongste().toString() + "\n" + "verschil in dagen: "
				+ getGeboortedatumJongste().verschilInDagen(getGeboortedatumOudste()) + "\n" + "verschil in jaren: "
				+ getGeboortedatumJongste().verschilInJaren(getGeboortedatumOudste()) + "\n" + "ongeldige datums:" + "\n");

		System.out.println(getOutput());
		for (String item : fouteDatums) {
			System.out.println(item);
		}
	}

	/**
	 * @param file
	 * @throws FileNotFoundException
	 */
	public void leesFile(File file) throws FileNotFoundException
	{
		Scanner scanner = new Scanner(file);
		while (scanner.hasNext()) {
			String lijn = scanner.nextLine();
			String[] velden = lijn.split("\t");
			String naam = velden[0];
			String datum = velden[1];
			try {
				Datum d = new Datum(datum);
				if (getGeboortedatumOudste() == null && getGeboortedatumJongste() == null) {
					setNaamOudste(setNaamJongste(naam));
					setGeboortedatumOudste(d);
					setGeboortedatumJongste(d);
				} else {
					if (d.compareTo(getGeboortedatumJongste()) < 0) {
						setGeboortedatumJongste(d);
						setNaamJongste(naam);
					}
					if (d.compareTo(getGeboortedatumOudste()) > 0) {
						setGeboortedatumOudste(d);
						setNaamOudste(naam);
					}
				}
			} catch (Exception ex) {
				this.fouteDatums.add(datum);
				setTellerFouteDatums(getTellerFouteDatums() + 1);
			}
		}
		if (scanner != null) {
			scanner.close();
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fouteDatums == null) ? 0 : fouteDatums.hashCode());
		result = prime * result + ((geboortedatumJongste == null) ? 0 : geboortedatumJongste.hashCode());
		result = prime * result + ((geboortedatumOudste == null) ? 0 : geboortedatumOudste.hashCode());
		result = prime * result + ((naamJongste == null) ? 0 : naamJongste.hashCode());
		result = prime * result + ((naamOudste == null) ? 0 : naamOudste.hashCode());
		result = prime * result + ((output == null) ? 0 : output.hashCode());
		result = prime * result + tellerFouteDatums;
		return result;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof LeesBestand)) {
			return false;
		}
		LeesBestand other = (LeesBestand) obj;
		if (fouteDatums == null) {
			if (other.fouteDatums != null) {
				return false;
			}
		} else if (!fouteDatums.equals(other.fouteDatums)) {
			return false;
		}
		if (geboortedatumJongste == null) {
			if (other.geboortedatumJongste != null) {
				return false;
			}
		} else if (!geboortedatumJongste.equals(other.geboortedatumJongste)) {
			return false;
		}
		if (geboortedatumOudste == null) {
			if (other.geboortedatumOudste != null) {
				return false;
			}
		} else if (!geboortedatumOudste.equals(other.geboortedatumOudste)) {
			return false;
		}
		if (naamJongste == null) {
			if (other.naamJongste != null) {
				return false;
			}
		} else if (!naamJongste.equals(other.naamJongste)) {
			return false;
		}
		if (naamOudste == null) {
			if (other.naamOudste != null) {
				return false;
			}
		} else if (!naamOudste.equals(other.naamOudste)) {
			return false;
		}
		if (output == null) {
			if (other.output != null) {
				return false;
			}
		} else if (!output.equals(other.output)) {
			return false;
		}
		if (tellerFouteDatums != other.tellerFouteDatums) {
			return false;
		}
		return true;
	}

	/**
	 * @return the geboortedatumJongste
	 */
	public Datum getGeboortedatumJongste()
	{
		return geboortedatumJongste;
	}

	/**
	 * @param geboortedatumJongste
	 *            the geboortedatumJongste to set
	 */
	public void setGeboortedatumJongste(Datum geboortedatumJongste)
	{
		this.geboortedatumJongste = geboortedatumJongste;
	}

	/**
	 * @return the fouteDatums
	 */
	public ArrayList<String> getFouteDatums()
	{
		return fouteDatums;
	}

	/**
	 * @param fouteDatums
	 *            the fouteDatums to set
	 */
	public void setFouteDatums(ArrayList<String> fouteDatums)
	{
		this.fouteDatums = fouteDatums;
	}

	/**
	 * @return the tellerFouteDatums
	 */
	public int getTellerFouteDatums()
	{
		return tellerFouteDatums;
	}

	/**
	 * @param tellerFouteDatums
	 *            the tellerFouteDatums to set
	 */
	public void setTellerFouteDatums(int tellerFouteDatums)
	{
		this.tellerFouteDatums = tellerFouteDatums;
	}

	/**
	 * @return the geboortedatumOudste
	 */
	public Datum getGeboortedatumOudste()
	{
		return geboortedatumOudste;
	}

	/**
	 * @param geboortedatumOudste
	 *            the geboortedatumOudste to set
	 */
	public void setGeboortedatumOudste(Datum geboortedatumOudste)
	{
		this.geboortedatumOudste = geboortedatumOudste;
	}

	/**
	 * @return the naamJongste
	 */
	public String getNaamJongste()
	{
		return naamJongste;
	}

	/**
	 * @param naamJongste
	 *            the naamJongste to set
	 */
	public String setNaamJongste(String naamJongste)
	{
		this.naamJongste = naamJongste;
		return naamJongste;
	}

	/**
	 * @return the naamOudste
	 */
	public String getNaamOudste()
	{
		return naamOudste;
	}

	/**
	 * @param naamOudste
	 *            the naamOudste to set
	 */
	public void setNaamOudste(String naamOudste)
	{
		this.naamOudste = naamOudste;
	}

	/**
	 * @return the output
	 */
	public String getOutput()
	{
		return output;
	}

	/**
	 * @param output
	 *            the output to set
	 */
	public void setOutput(String output)
	{
		this.output = output;
	}

}

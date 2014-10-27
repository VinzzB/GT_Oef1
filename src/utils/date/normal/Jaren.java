package utils.date.normal;
/**
 * Bevat methods voor schrikkeljaren 
 * @author Vinzz
 *
 */
public class Jaren {

	/**
	 * Geeft het aantal dagen van een bepaald jaar terug.
	 * @param year Het jaartal waar op getest wordt.
	 * @return Het aantal dagen in een jaar. [int: 365-366]
	 */
	public static int getDagenInJaar(int year)
	{ return isLeapYear(year) ? 366 : 365; }
	
	
	/**
	 * Controleert of het opgegeven jaartal een schrikkeljaar is.
	 * @param year Het jaartal waar op getest wordt.
	 * @return True als het jaartal een schrikkeljaar is.  
	 */
	public static boolean isLeapYear(int year)
	{ return year % 4 == 0 && (year % 100 != 0 || (year % 400 == 0)); }
	//Schrikkeljaar = deelbaar door 4 en (niet in een eeuwjaar OF (wel in een eeuwjaar EN deelbaar door 400))	
}

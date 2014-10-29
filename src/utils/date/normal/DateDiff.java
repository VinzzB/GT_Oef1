package utils.date.normal;

/**
 * Helper class for NormalDate class. (Package visibility)
 * 
 * @author Vinzz
 */
class DateDiff
{

	private int days;
	private int months;

	// private int years;
	/**
	 * Berekent het verschil tussen 2 Datum objecten.
	 * 
	 * @param date1
	 * @param date2
	 */
	DateDiff(Datum date1, Datum date2)
	{
		// Return when equal
		if (date1.equals(date2))
		{
			return;
		}
		// Switch Min / Max
		Datum Maxdate = date1.kleinerDan(date2) ? date2 : date1;
		Datum minDate = date1.kleinerDan(date2) ? date1 : date2;
		minDate = new Datum(minDate); //Cloned for calculations

		// Not in same month or year? -> Add 1 month
		while (minDate.getMonth() != Maxdate.getMonth()
				|| minDate.getYear() != Maxdate.getYear())
		{
			//Get the days of the month being processed.
			int dim = Maanden.get(minDate.getMonth()).GetLength(minDate.getYear());
			minDate.veranderDatum(dim); // Add 1 month
			days += dim; //add days
			months++; // add one month
		}
		// Add or subtract remaining days (in same month)
		days += Maxdate.getDay() - minDate.getDay();
		//Subtract one month if the MaxDay is bigger then MinDay
		months -= (Maxdate.getDay() < minDate.getDay() ? 1 : 0);
	}

	int getDays()
	{
		return days;
	}

	int getMonths()
	{
		return months;
	}

	int getYears()
	{
		return months / 12; // as floored int
	}

}

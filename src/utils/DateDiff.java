package utils;
/* Created by: Vinzz
 * ------------------------------------
 * Helper class for NormalDate class.
 * ------------------------------------
 * */
public class DateDiff {

	private int days;
	private int months;
	//private int years;
	
	public DateDiff(NormalDate date1, NormalDate date2)		
	{
		//Return when equal
		if (date1.equals(date2))
		{ return; }		
		//Switch Min / Max
		NormalDate Maxdate = date1.kleinerDan(date2) ? date2 : date1;
		NormalDate minDate = (date1.kleinerDan(date2) ? date1 : date2);
		NormalDate minDateClone = (NormalDate)minDate.clone();
		//Not in same month or year? -> Go to first day of next month
		if (minDateClone.getMonth() != Maxdate.getMonth() || minDateClone.getYear() != Maxdate.getYear())
		{
			days = NormalDate.getDagenInMaand(minDateClone.getMonth(), minDateClone.getYear()) -  minDateClone.getDay()+1;
			minDateClone = minDateClone.veranderDatum(days);
			//Iterate until mindate is in the same month and year as maxdate.
			while(minDateClone.kleinerDan(Maxdate))
			{
				int DaysInMonth = NormalDate.getDagenInMaand(minDateClone.getMonth(), minDateClone.getYear());
				if (minDateClone.veranderDatum(DaysInMonth-1).kleinerDan(Maxdate))
				{
					minDateClone = minDateClone.veranderDatum(DaysInMonth);
					days += DaysInMonth;
					months++;				
				}
				else
				{ break; }			
			}
			//Add one month if maxdate is still bigger than mindate
			if(Maxdate.getDay() >= minDate.getDay())
			{ months++; }
			//calculate day difference is same month and year
			days += Maxdate.getDay()-1;
		}
		else //In same month
		{
			days += Maxdate.getDay() - minDate.getDay();
		}		
	}

	public int getDays() {
		return days;
	}

	public int getMonths() {
		return months;
	}

	public int getYears() {
		return months / 12; //as floored int
	}

}

package utils;
/* Created by: Vinzz
 * ------------------------------------
 * Helper class for NormalDate class.
 * ------------------------------------
 * */
public class DateDiff {

	private int days;
	private int months;
	private int years;
	
	public DateDiff(NormalDate date1, NormalDate date2)		
	{
		//Switch Min / Max
		NormalDate Maxdate = date1.kleinerDan(date2) ? date2 : date1;
		NormalDate Mindate = date1.kleinerDan(date2) ? date1 : date2;
		if (Mindate.getYear() != Maxdate.getYear())
		{
			for (int i = Mindate.getYear(); i <= Maxdate.getYear(); i++)
			{
				if (i == Maxdate.getYear())
				{								
					for (int m = 1; m < Maxdate.getMonth(); m++)
					{ days += NormalDate.getDagenInMaand(m, i); }
					months += Maxdate.getMonth();
				}
				else if (i == Mindate.getYear())
				{
					for (int m = Mindate.getMonth()+1; m <= 12; m++)
					{ days += NormalDate.getDagenInMaand(m, i); }
					months += 12 - (Mindate.getMonth());				
				}
				else
				{ 
					days += NormalDate.getDagenInJaar(i);
					months += 12;
				}			
			}
			days += NormalDate.getDagenInMaand(Mindate.getMonth(), Mindate.getYear()) - Mindate.getDay();
			days += Maxdate.getDay();
		}
		else
		{
			for (int m = Mindate.getMonth(); m < Maxdate.getMonth(); m++)
			{ 
				days += NormalDate.getDagenInMaand(m, Mindate.getYear());
				months +=1;
			}
			days +=  Maxdate.getDay() - Mindate.getDay();
		}
						
			
			years = months / 12; //=int
//		return (int)((date.year - year) * 365.25) + ((date.month - month) * 30) + (date.day - day);		
	}

	public int getDays() {
		return days;
	}

	public int getMonths() {
		return months;
	}

	public int getYears() {
		return years;
	}

}

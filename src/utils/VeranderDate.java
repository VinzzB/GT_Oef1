package utils;

/* Created by:
 * ------------------------------------
 * Helper class for NormalDate class.
 * ------------------------------------
 * */
public class VeranderDate {

	private int day;
	private int month;
	private int year;

	public VeranderDate(NormalDate date1, int aantalDagen) {
		// Switch Min / Max
		NormalDate Maxdate = aantalDagen > 0 ? null : date1;
		NormalDate Mindate = aantalDagen > 0 ? date1 : null;
		if (Maxdate == null) {
			day = Mindate.getDay();
			month = Mindate.getMonth();
			year = Mindate.getYear();

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
		} else {
			day = Maxdate.getDay();
			month = Maxdate.getMonth();
			year = Maxdate.getYear();

			for (int i = aantalDagen; i <= 0; i--) {
				if (day == 1) {
					if (month > 1) {
						month--;
					} else {
						month = 12;
						year--;
					}
					day = NormalDate.getDagenInMaand(month, year);
				} else {
					if (day > 1 && day < NormalDate.getDagenInMaand(month, year)) {
						day--;
					}
				}

			}
		}
	}

	public int getDay() {
		return day;
	}

	public int getMonth() {
		return month;
	}

	public int getYear() {
		return year;
	}

}

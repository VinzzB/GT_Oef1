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

		day = date1.getDay();
		month = date1.getMonth();
		year = date1.getYear();
		if (aantalDagen > 0) {
			// day = date.getDay();
			// month = Mindate.getMonth();
			// year = Mindate.getYear();

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

			} // end for
		} // end if
	} // end verander date

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

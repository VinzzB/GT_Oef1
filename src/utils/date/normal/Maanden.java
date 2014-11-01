package utils.date.normal;

/**
 * Een enumeration voor de maanden. Bevat methods om het maximum aantal dagen in
 * een maand te verkrijgen
 * 
 * @author Vinzz
 */
public enum Maanden {
	januari(1,31),	
	februari(2,28),	
	maart(3,31),
	april(4,30), 
	mei(5,31), 
	juni(6,30), 
	juli(7,31),
	augustus(8,31), 
	september(9,30), 
	oktober(10,31),
	november(11,30),
	december(12,31);

	private final int monthIndex;
	private final int monthLength;

	/**
	 * Constructor
	 * 
	 * @param pMonthIndex Numerieke waarde van de opgegeven maand
	 * @param pMonthLength Het maximum aantal dagen in een maand
	 */
	Maanden(int pMonthIndex, int pMonthLength)
	{
		monthIndex = pMonthIndex;
		monthLength = pMonthLength;
	}

	/**
	 * Zoekt de maand op a.d.h.v. de numerieke waarde van de maand
	 * 
	 * @param monthIndex
	 * @return Een enumeration object van Maanden
	 */
	public static Maanden get(int monthIndex)
	{
		for (Maanden m : Maanden.values())
		{
			if (m.monthIndex == monthIndex)
			{
				return m;
			}
		}
		return null; // Niet gevonden! (out of range?)
	}

	/**
	 * Numerieke waarde van maand.
	 * 
	 * @return De numerieke waarde van de maand. (int: 1-12)
	 */
	public int GetIndex()
	{
		return monthIndex;
	}

	/**
	 * Het maximum aantal dagen in een maand zonder rekening te houden met
	 * schrikkeljaren! (Februari telt altijd 28 dagen)
	 * 
	 * @return Het maximum aantal dagen in een maand. (int: 28-31)
	 */
	public int GetLength()
	{
		return monthLength;
	}

	/**
	 * Het maximum aantal dagen in een maand. (schrikkeljaren meegeteld)
	 * 
	 * @param year Het jaartal om op schrikkeljaren te controleren.
	 * @return Het maximum aantal dagen in een maand. (int: 28-31)
	 */
	public int GetLength(int year)
	{
		return (monthIndex == februari.GetIndex() && Jaren.isLeapYear(year)) ? 29
				: get(monthIndex).GetLength();		
	}

}

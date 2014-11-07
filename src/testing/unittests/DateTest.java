package testing.unittests;
import java.util.Date;
import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;

//----------------------------------
//import utils.date.gregorian.*;
import utils.date.normal.*;
//^^^ Switch Class To Test Above ^^^
//----------------------------------

/**
 * Shared Test class for Datum classes
 * 
 * Switch between Datum classes in import section above
 * 
 * ------------------------
 * Coverage: (Tested with eclEmma on 27/10/2014 22:11)
 * ------------------------
 * DateDiff  = 100%
 * Maanden  = 95%
 * Jaren = 61%
 * normal.Datum = 94,2%
 * gregorian.Datum = 63,8% (with 5 errors, to be continued...)
 * -------------------------
 * 
 * @author Isaak, Natalia, Nathalie, Vincent, Wouter (Joined together by Vinzz)
 *
 */
@SuppressWarnings("unused")
public class DateTest {
	
	private Datum  date, dateEqual, dateToday,
						dateMinOneDay, datePlusOneDay, 
						datePlusOneMonth, dateMinOneMonth, datePlusSixMonths,
						datePlusTenYears, 
						dateKleinerEnNietLeap, dateKleinerEnNietLeapPlusOneDay,
						dateGroterEnLeap, dateGroterEnLeapPlusOneDay,
						dateNewLeapYear, dateNewNotLeapYear,
						dateVerschilStart, dateVerschil674End, dateVerschil20End,
						dateNewVerschil365notLeap, dateNewVerschil366Leap,
						dateYearZero;
	@Before
	public void setUp()
	{
		date = new Datum(24,1,2010);			
		dateEqual = new Datum("24/01/2010");
		dateMinOneDay = new Datum(23,1,2010);
		datePlusOneDay = new Datum("25/01/2010");
		dateMinOneMonth = new Datum(24,12,2009);
		datePlusOneMonth = new Datum("24/02/2010");
		datePlusSixMonths = new Datum(24,8,2010);
		datePlusTenYears = new Datum("24/01/2020");	
		dateKleinerEnNietLeap = new Datum(28,2,1900);
		dateKleinerEnNietLeapPlusOneDay = new Datum("01/03/1900");
		dateGroterEnLeap = new Datum(29,2,2012);
		dateGroterEnLeapPlusOneDay = new Datum("1/03/2012");
		dateNewLeapYear = new Datum(1,1,1600);		
		dateNewNotLeapYear = new Datum("01/01/1700");
		dateVerschilStart = new Datum(1,3,2007);
		dateVerschil20End = new Datum("21/03/2007");
		dateVerschil674End = new Datum(3,1,2009);		
		dateNewVerschil365notLeap = new Datum("1/01/1701");
		dateNewVerschil366Leap= new Datum(1,1,1601);
		dateYearZero = new Datum("18/10/0000");
		dateToday = new Datum();
	}
	
	/*
	 * -----------------------------------------------
	 * TEST METHOD: getAmericanFormat()
	 * -----------------------------------------------
	*/

	@Test public void test_getAmericanFormat_Als_string()
	{		
		assertEquals("Amerikaans formaat", "2010/01/24", date.getAmericanFormat());
		assertEquals("Amerikaans formaat", "2010/01/23", dateMinOneDay.getAmericanFormat());
		assertEquals("Amerikaans formaat", "2010/01/25", datePlusOneDay.getAmericanFormat());
		assertEquals("Amerikaans formaat", "2009/12/24", dateMinOneMonth.getAmericanFormat());
		assertEquals("Amerikaans formaat", "2010/02/24", datePlusOneMonth.getAmericanFormat());
		assertEquals("Amerikaans formaat", "2010/08/24", datePlusSixMonths.getAmericanFormat());
		assertEquals("Amerikaans formaat", "2020/01/24", datePlusTenYears.getAmericanFormat());		
		assertEquals("Amerikaans formaat", "1900/02/28", dateKleinerEnNietLeap.getAmericanFormat());
		assertEquals("Amerikaans formaat", "1900/03/01", dateKleinerEnNietLeapPlusOneDay.getAmericanFormat());
		assertEquals("Amerikaans formaat", "2012/02/29", dateGroterEnLeap.getAmericanFormat());
		assertEquals("Amerikaans formaat", "2012/03/01", dateGroterEnLeapPlusOneDay.getAmericanFormat());		
		assertEquals("Amerikaans formaat", "1600/01/01", dateNewLeapYear.getAmericanFormat());		
		assertEquals("Amerikaans formaat", "1700/01/01", dateNewNotLeapYear.getAmericanFormat());		
		assertEquals("Amerikaans formaat", "2007/03/01", dateVerschilStart.getAmericanFormat());		
		assertEquals("Amerikaans formaat", "2007/03/21", dateVerschil20End.getAmericanFormat());		
		assertEquals("Amerikaans formaat", "2009/01/03", dateVerschil674End.getAmericanFormat());		
		assertEquals("Amerikaans formaat", "1701/01/01", dateNewVerschil365notLeap.getAmericanFormat());		
		assertEquals("Amerikaans formaat", "1601/01/01", dateNewVerschil366Leap.getAmericanFormat());	
		//Only the normal.Datum class accepts zero years
		 if (Datum.class.getCanonicalName() == utils.date.normal.Datum.class.getCanonicalName())
		{
			 assertEquals("Amerikaans formaat", "0000/10/18", dateYearZero.getAmericanFormat());
		}
	}
	
	/*
	 * -----------------------------------------------
	 * TEST METHOD: getEuropeanFormat()
	 * -----------------------------------------------
	*/
	
	@Test public void test_getEuropeanFormat_Als_string()
	{
		assertEquals("Europees formaat", "24/01/2010", date.getEuropeanFormat());
		
		assertEquals("Europees formaat", "23/01/2010", dateMinOneDay.getEuropeanFormat());
		assertEquals("Europees formaat", "25/01/2010", datePlusOneDay.getEuropeanFormat());
		assertEquals("Europees formaat", "24/12/2009", dateMinOneMonth.getEuropeanFormat());
		assertEquals("Europees formaat", "24/02/2010", datePlusOneMonth.getEuropeanFormat());
		assertEquals("Europees formaat", "24/08/2010", datePlusSixMonths.getEuropeanFormat());
		assertEquals("Europees formaat", "24/01/2020", datePlusTenYears.getEuropeanFormat());		
		
		assertEquals("Europees formaat", "28/02/1900", dateKleinerEnNietLeap.getEuropeanFormat());
		assertEquals("Europees formaat", "01/03/1900", dateKleinerEnNietLeapPlusOneDay.getEuropeanFormat());			
		assertEquals("Europees formaat", "29/02/2012", dateGroterEnLeap.getEuropeanFormat());
		
		assertEquals("Europees formaat", "01/03/2012", dateGroterEnLeapPlusOneDay.getEuropeanFormat());		
		assertEquals("Europees formaat", "01/01/1600", dateNewLeapYear.getEuropeanFormat());		
		assertEquals("Europees formaat", "01/01/1700", dateNewNotLeapYear.getEuropeanFormat());		
		assertEquals("Europees formaat", "01/03/2007", dateVerschilStart.getEuropeanFormat());		
		assertEquals("Europees formaat", "21/03/2007", dateVerschil20End.getEuropeanFormat());		
		assertEquals("Europees formaat", "03/01/2009", dateVerschil674End.getEuropeanFormat());		
		assertEquals("Europees formaat", "01/01/1701", dateNewVerschil365notLeap.getEuropeanFormat());		
		assertEquals("Europees formaat", "01/01/1601", dateNewVerschil366Leap.getEuropeanFormat());
		//Only the normal.Datum class accepts zero years
		if (Datum.class.getCanonicalName() == utils.date.normal.Datum.class.getCanonicalName())
		{
			 assertEquals("Europees formaat", "18/10/0000", dateYearZero.getEuropeanFormat());			
		}

	}
	
	/*
	 * -----------------------------------------------
	 * TEST METHOD: toString()
	 * -----------------------------------------------
	*/
	
	@Test
	public void test_toString_Als_datum_voluit()
	{
		assertEquals("toString method", "24 januari 2010", date.toString());		
		assertEquals("toString method", "23 januari 2010", dateMinOneDay.toString());
		assertEquals("toString method", "25 januari 2010", datePlusOneDay.toString());
		assertEquals("toString method", "24 december 2009", dateMinOneMonth.toString());
		assertEquals("toString method", "24 februari 2010", datePlusOneMonth.toString());
		assertEquals("toString method", "24 augustus 2010", datePlusSixMonths.toString());
		assertEquals("toString method", "24 januari 2020", datePlusTenYears.toString());				
		assertEquals("toString method", "28 februari 1900", dateKleinerEnNietLeap.toString());
		assertEquals("toString method", "1 maart 1900", dateKleinerEnNietLeapPlusOneDay.toString());	
		assertEquals("toString method", "29 februari 2012", dateGroterEnLeap.toString());		
		assertEquals("toString method", "1 maart 2012", dateGroterEnLeapPlusOneDay.toString());		
		assertEquals("toString method", "1 januari 1600", dateNewLeapYear.toString());		
		assertEquals("toString method", "1 januari 1700", dateNewNotLeapYear.toString());		
		assertEquals("toString method", "1 maart 2007", dateVerschilStart.toString());		
		assertEquals("toString method", "21 maart 2007", dateVerschil20End.toString());		
		assertEquals("toString method", "3 januari 2009", dateVerschil674End.toString());		
		assertEquals("toString method", "1 januari 1701", dateNewVerschil365notLeap.toString());		
		assertEquals("toString method", "1 januari 1601", dateNewVerschil366Leap.toString());	
		//Only the normal.Datum class accepts zero years
		if (Datum.class.getCanonicalName() == utils.date.normal.Datum.class.getCanonicalName())
		{
			 assertEquals("toString method", "18 oktober 0000", dateYearZero.toString());	
		}
	}
	
	
	/*
	 * -----------------------------------------------
	 * TEST METHOD: kleinerDan(Datum?)
	 * -----------------------------------------------
	*/	
	
	@Test(expected = NullPointerException.class)
	public void test_kleinerDan_Als_parameter_Null_Geef_fout()
	{
		date.kleinerDan(null);		
	}		
	
	@Test
	public void test_kleinerDan_Als_parameter_equals_False()
	{
		assertFalse(date.kleinerDan(date));		
	}
	
	@Test
	public void test_kleinerDan_Als_parameter_isGroter_True()
	{
		assertTrue(date.kleinerDan(dateGroterEnLeap));		
	}
	
	@Test
	public void test_kleinerDan_Als_parameter_iskleiner_False()
	{
		assertFalse(date.kleinerDan(dateKleinerEnNietLeap));		
	}	
		
	/*
	 * -----------------------------------------------
	 * TEST METHOD: groterDan(Datum?)
	 * -----------------------------------------------
	*/	
	
	@Test(expected = NullPointerException.class)
	public void test_groterDan_Als_parameter_Null_Geef_fout()
	{
		date.groterDan(null);		
	}		
	
	@Test
	public void test_groterDan_Als_parameter_equals_False()
	{
		assertFalse(date.groterDan(date));		
	}
	
	@Test
	public void test_groterDan_Als_parameter_isGroter_False()
	{
		assertFalse(date.groterDan(dateGroterEnLeap));		
	}
	
	@Test
	public void test_groterDan_Als_parameter_iskleiner_True()
	{
		assertTrue(date.groterDan(dateKleinerEnNietLeap));		
	}		
			
	/*
	 * -----------------------------------------------
	 * TEST METHOD: equals(Datum?)
	 * -----------------------------------------------
	*/
	
	@Test
	public void test_equals_Als_parameter_null_False()
	{
		assertFalse(date.equals(null));		
	}		
	
	@Test
	public void test_equals_Als_parameter_isGelijkEnAnderObject_True()
	{		
		assertTrue(date.equals(dateEqual));
	}
	
	@Test
	public void test_equals_Als_parameter_isGelijkEnZelfdeObject_True()
	{		
		assertTrue(date.equals(date));
	}
	
	@Test
	public void test_equals_Als_parameter_kleiner_False()
	{
		assertFalse(date.equals(dateKleinerEnNietLeap));			
	}
	
	@Test
	public void test_equals_Als_parameter_groter_False()
	{
		assertFalse(date.equals(dateGroterEnLeap));		
	}		
	
	/*
	 * -----------------------------------------------
	 * TEST CONSTRUCTORS (!=?) => NOT NULLABLE: AMBIGOUS CTORS
	 * -----------------------------------------------
	*/
	
	@SuppressWarnings("deprecation")
	public void test_Ctor_Zonder_parameters_Geeft_Vandaag()
	{
		assertEquals("Dag vandaag", new Date().getDay(), dateToday.getDay());
		assertEquals("Maand vandaag", new Date().getMonth(), dateToday.getMonth());
		assertEquals("Jaar vandaag", new Date().getYear(), dateToday.getYear());
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void test_Ctor_Als_parameter_legeString_Geef_fout()
	{
		Datum date = new Datum("");		
	}		
	
	@Test (expected = IllegalArgumentException.class) 
	public void test_Ctor_Als_foutieve_Schrikkeldag_Geef_fout()
	{
		assertNull("Schrikkeljaar Exception", new Datum(29, 2, 2014));
	}
		
	@Test (expected = IllegalArgumentException.class)
	public void test_Ctor_Als_parameter_dagIs0_Geef_fout()
	{
		Datum date = new Datum(0, 12, 2012);		
	}

	@Test (expected = IllegalArgumentException.class)
	public void test_Ctor_Als_parameter_dagTeGroot_Geef_fout()
	{
		Datum date = new Datum(31, 4, 2014);		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_Ctor_Als_parameter_maandIs0_Geef_fout()
	{
		Datum date = new Datum(1, 0, 2012);		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_Ctor_Als_parameter_maandTeGroot_Geef_fout()
	{
		Datum date = new Datum(1, 13, 2012);		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_Ctor_Als_parameter_jaarTeKlein_Geef_fout()
	{
		Datum date = new Datum(1, 1, -1);		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_Ctor_Als_parameters_negatief_Geef_fout()
	{
		Datum date = new Datum(-1, -1, -1);		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_Ctor_Als_String_parameter_verkeerd_formaat_Geef_fout()
	{
		Datum date = new Datum("1/1/1");		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_Ctor_Als_String_parameter_Verkeerde_separator_Geef_fout()
	{
		Datum date = new Datum("01-01-2011");				
	}	
	
//	@Test (expected = IllegalArgumentException.class)
//	public void test_Ctor_Als_String_parameter_lengte_dagFormatIs1_Geef_fout()
//	{
//		Datum date = new Datum("1/01/2011");				
//	}	

	@Test (expected = IllegalArgumentException.class)
	public void test_Ctor_Als_String_parameter_lengte_maandFormatIs1_Geef_fout()
	{
		Datum date = new Datum("01/1/2011");				
	}	
	
	@Test (expected = IllegalArgumentException.class)
	public void test_Ctor_Als_String_parameter_lengte_jaarFormatIs2_Geef_fout()
	{
		Datum date = new Datum("01/01/11");		
	}	
	
	
	/*
	 * -----------------------------------------------
	 * TEST METHOD: getDay()
	 * -----------------------------------------------
	*/
	@Test
	public void test_getDay_Geeft_Dag()
	{
		assertEquals("getDay method", 24, date.getDay());		
		assertEquals("getDay method", 23, dateMinOneDay.getDay());
		assertEquals("getDay method", 25, datePlusOneDay.getDay());
		assertEquals("getDay method", 24, dateMinOneMonth.getDay());
		assertEquals("getDay method", 24, datePlusOneMonth.getDay());
		assertEquals("getDay method", 24, datePlusSixMonths.getDay());
		assertEquals("getDay method", 24, datePlusTenYears.getDay());				
		assertEquals("getDay method", 28, dateKleinerEnNietLeap.getDay());
		assertEquals("getDay method", 1, dateKleinerEnNietLeapPlusOneDay.getDay());	
		assertEquals("getDay method", 29, dateGroterEnLeap.getDay());		
		assertEquals("getDay method", 1, dateGroterEnLeapPlusOneDay.getDay());		
		assertEquals("getDay method", 1, dateNewLeapYear.getDay());		
		assertEquals("getDay method", 1, dateNewNotLeapYear.getDay());		
		assertEquals("getDay method", 1, dateVerschilStart.getDay());		
		assertEquals("getDay method", 21, dateVerschil20End.getDay());		
		assertEquals("getDay method", 3, dateVerschil674End.getDay());		
		assertEquals("getDay method", 1, dateNewVerschil365notLeap.getDay());		
		assertEquals("getDay method", 1, dateNewVerschil366Leap.getDay());	
		assertEquals("getDay method", 18, dateYearZero.getDay());
	}	
	
	/*
	 * -----------------------------------------------
	 * TEST METHOD: getMonth()
	 * -----------------------------------------------
	*/
	
	@Test
	public void test_getMonth_Geeft_Maand()
	{	
		assertEquals("getMonth method", 1, date.getMonth());		
		assertEquals("getMonth method", 1, dateMinOneDay.getMonth());
		assertEquals("getMonth method", 1, datePlusOneDay.getMonth());
		assertEquals("getMonth method", 12, dateMinOneMonth.getMonth());
		assertEquals("getMonth method", 2, datePlusOneMonth.getMonth());
		assertEquals("getMonth method", 8, datePlusSixMonths.getMonth());
		assertEquals("getMonth method", 1, datePlusTenYears.getMonth());				
		assertEquals("getMonth method", 2, dateKleinerEnNietLeap.getMonth());
		assertEquals("getMonth method", 3, dateKleinerEnNietLeapPlusOneDay.getMonth());			
		assertEquals("getMonth method", 2, dateGroterEnLeap.getMonth());		
		assertEquals("getMonth method", 3, dateGroterEnLeapPlusOneDay.getMonth());		
		assertEquals("getMonth method", 1, dateNewLeapYear.getMonth());		
		assertEquals("getMonth method", 1, dateNewNotLeapYear.getMonth());		
		assertEquals("getMonth method", 3, dateVerschilStart.getMonth());		
		assertEquals("getMonth method", 3, dateVerschil20End.getMonth());		
		assertEquals("getMonth method", 1, dateVerschil674End.getMonth());		
		assertEquals("getMonth method", 1, dateNewVerschil365notLeap.getMonth());		
		assertEquals("getMonth method", 1, dateNewVerschil366Leap.getMonth());
		assertEquals("getMonth method", 10, dateYearZero.getMonth());
	}
	
	/*
	 * -----------------------------------------------
	 * TEST METHOD: getYear()
	 * -----------------------------------------------
	*/
	
	@Test
	public void test_getYear_Geeft_Jaar()
	{	
		assertEquals("getYear method", 2010, date.getYear());
		
		assertEquals("getYear method", 2010, dateMinOneDay.getYear());
		assertEquals("getYear method", 2010, datePlusOneDay.getYear());
		assertEquals("getYear method", 2009, dateMinOneMonth.getYear());
		assertEquals("getYear method", 2010, datePlusOneMonth.getYear());
		assertEquals("getYear method", 2010, datePlusSixMonths.getYear());
		assertEquals("getYear method", 2020, datePlusTenYears.getYear());				
		assertEquals("getYear method", 1900, dateKleinerEnNietLeap.getYear());
		assertEquals("getYear method", 1900, dateKleinerEnNietLeapPlusOneDay.getYear());			
		assertEquals("getYear method", 2012, dateGroterEnLeap.getYear());		
		assertEquals("getYear method", 2012, dateGroterEnLeapPlusOneDay.getYear());		
		assertEquals("getYear method", 1600, dateNewLeapYear.getYear());		
		assertEquals("getYear method", 1700, dateNewNotLeapYear.getYear());		
		assertEquals("getYear method", 2007, dateVerschilStart.getYear());		
		assertEquals("getYear method", 2007, dateVerschil20End.getYear());		
		assertEquals("getYear method", 2009, dateVerschil674End.getYear());		
		assertEquals("getYear method", 1701, dateNewVerschil365notLeap.getYear());		
		assertEquals("getYear method", 1601, dateNewVerschil366Leap.getYear());	
		//Only the normal.Datum class accepts zero years
		if (Datum.class.getCanonicalName() == utils.date.normal.Datum.class.getCanonicalName())
		{
			 assertEquals("getYear method", 0, dateYearZero.getYear());
		}
	}	
		
	/*
	 * -----------------------------------------------
	 * TEST METHOD: setDatum(int,int,int)
	 * -----------------------------------------------
	*/
	
	@Test//(expected = IllegalArgumentException.class)
	public void test_setDatum_Als_parameters_0_Geeft_false()
	{
		assertFalse("Zet datum naar 0 geeft false", date.setDatum(0, 0, 0));	
	}
	
	@Test
	public void test_setDatum_Als_parameters_random_Geeft_zelfdeDatum()
	{
		Random r = new Random();
		Datum testDate = new Datum();
		for (int i = 0; i < 1000; i++) {
			int d = r.nextInt(27)+1;
			int m = r.nextInt(11)+1;
			int y = r.nextInt(9999);
			testDate.setDatum(d,m, y);		
			assertEquals("setDatum dag",d, testDate.getDay());
			assertEquals("setDatum Maand",m, testDate.getMonth());
			assertEquals("setDatum Jaar",y, testDate.getYear());			
		}				
	}	

	@Test
	public void test_setDatum_Als_parameters_foutieveMaand_Geeft_vorigeDatum()
	{
		Datum oldDate = new Datum(date); // (Datum)date.clone();
		date.setDatum(1, 0, 2000);
		assertEquals("is oude waarde?", oldDate, date);
	}		
	
	/*
	 * -----------------------------------------------
	 * TEST METHOD: veranderDatum(int)
	 * -----------------------------------------------
	*/
	
	@Test
	public void test_veranderDatum_Verhoog__met0_Geeft_zelfdeDatum()
	{
		assertEquals("Verhoog 28 feb +0",date, date.veranderDatum(0));
	}
	
	@Test
	public void test_veranderDatum_Geeft_nieuw_object_terug()
	{
		Datum d = date.veranderDatum(1); //past date aan en geeft een nieuw object terug. --> d == date
		date.veranderDatum(1); //verander date object nog eens. d blijft onveranderd. --> d != date
		assertNotEquals("Nieuw object aangemaakt?", date.getDay(), d.getDay());
	}
	
	@Test
	public void test_veranderDatum_parameters_add1_Geeft_nieuw_object_metZelfdeWaarde_terug()
	{
		Datum d = date.veranderDatum(1); //past date aan en geeft een nieuw object terug. --> d == date		
		assertEquals("Nieuw object aangemaakt?", date, d);
	}
	
	
	@Test
	public void test_veranderDatum_Verhoog_28feb_inSchrikkelJaar_met1Dag_Geeft_29feb()
	{
		assertEquals("Verhoog 28 feb +1dag in schrikkeljaar",
				dateGroterEnLeapPlusOneDay, 
				dateGroterEnLeap.veranderDatum(1));
	}
	
	@Test
	public void test_veranderDatum_Verhoog_28feb_nietInSchrikkelJaar_met1Dag_Geeft_1maart()
	{
		assertEquals("Verhoog 28 feb +1dag niet in schrikkeljaar",
				dateKleinerEnNietLeapPlusOneDay , 
				dateKleinerEnNietLeap.veranderDatum(1));
	}
		
	@Test
	public void test_veranderDatum_Verhoog_1jan_met_31Dagen_geeft_1feb()
	{
		assertEquals("Verhoog met 31 dagen (Start Januari)",
				datePlusOneMonth , 
				date.veranderDatum(31));
	}
	
	@Test
	public void test_veranderDatum_Verminder_1maart_inSchrikkeljaar_met_1Dagen_geeft_29feb()
	{
		assertEquals("Verlaag 1 maart met 1dag in schrikkeljaar", 
				dateGroterEnLeap,
				dateGroterEnLeapPlusOneDay.veranderDatum(-1));
	}
	
	@Test
	public void test_veranderDatum_Verminder_1maart_nietInSchrikkeljaar_met_1Dagen_geeft_28feb()
	{
		assertEquals("Verlaag 1 maart met 1dag niet in schrikkeljaar", 
				dateKleinerEnNietLeap,
				dateKleinerEnNietLeapPlusOneDay.veranderDatum(-1));
	}
		
	@Test
	public void test_veranderDatum_Verminder_31dagen_inJan_Geeft_DecemberVorigJaar()
	{
		assertEquals("Verminder met 31 dagen (Start Januari)",
				dateMinOneMonth , 
				date.veranderDatum(-31));
	}	
	
	/*
	 * -----------------------------------------------
	 * TEST METHOD: verschilInDagen(Datum?)
	 * -----------------------------------------------
	*/
	
	@Test(expected=NullPointerException.class)
	public void test_verschilInDagen_Als_parameter_null_Geef_fout()
	{
		date.verschilInDagen(null);
	}
			
	@Test
	public void test_verschilInDagen_Als_parameter_gelijk_Geeft_0()
	{
		assertEquals("Verschil in dagen", 0, date.verschilInDagen(dateEqual));
		assertEquals("Verschil in dagen", 0, date.verschilInDagen(date));
	}
	
	@Test
	public void test_verschilInDagen_Als_parameter_DatumPlus1dag_Geeft_1()
	{
		assertEquals("Verschil in dagen", 1, date.verschilInDagen(datePlusOneDay));	
	}
	
	@Test
	public void test_verschilInDagen_Als_parameter_DatumPlus20dagen_Geeft_20()
	{
		assertEquals("Verschil in dagen", 20, dateVerschilStart.verschilInDagen(dateVerschil20End));
	}

	@Test
	public void test_verschilInDagen_Als_parameter_DatumPlus1jaar_nietInSchrikkeljaar_Geeft_365()
	{
		assertEquals("Verschil in dagen", 365, dateNewNotLeapYear.verschilInDagen(dateNewVerschil365notLeap));
	}
	
	@Test
	public void test_verschilInDagen_Als_parameter_DatumPlus1jaar_inSchrikkeljaar_Geeft_366()
	{
		assertEquals("Verschil in dagen in schrikkeljaar", 366,dateNewLeapYear.verschilInDagen(dateNewVerschil366Leap));
	}
	
	@Test
	public void test_verschilInDagen_Als_parameter_DatumPlus674dagen_Geeft_674()
	{		
		
		assertEquals("Verschil in dagen", 674, dateVerschilStart.verschilInDagen(dateVerschil674End));
	}
		
	@Test
	public void test_verschilInDagen_Als_parameter_DatumMin1dag_Geeft_1()
	{
		assertEquals("Verschil in dagen", 1, datePlusOneDay.verschilInDagen(date));	
	}
	
	@Test
	public void test_verschilInDagen_Als_parameter_DatumMin20dagen_Geeft_20()
	{
		assertEquals("Verschil in dagen", 20, dateVerschil20End.verschilInDagen(dateVerschilStart));
	}

	@Test
	public void test_verschilInDagen_Als_parameter_DatumMin1jaar_nietInSchrikkeljaar_Geeft_365()
	{
		assertEquals("Verschil in dagen", 365, dateNewVerschil365notLeap.verschilInDagen(dateNewNotLeapYear));
	}
	
	@Test
	public void test_verschilInDagen_Als_parameter_DatumMin1jaar_inSchrikkeljaar_Geeft_366()
	{
		Datum dateNewVerschil366Leap = new Datum(1,1,1601);;
		Datum dateNewLeapYear = new Datum(1,1,1600);	
			
		assertEquals("Verschil in dagen in schrikkeljaar", 366,dateNewVerschil366Leap.verschilInDagen(dateNewLeapYear));
	}
	
	@Test
	public void test_verschilInDagen_Als_parameter_DatumMin674dagen_Geeft_674()
	{		
		assertEquals("Verschil in dagen", 674, dateVerschil674End.verschilInDagen(dateVerschilStart));		
	}
	
	/*
	 * -----------------------------------------------
	 * TEST METHOD: verschilInMaanden(Datum?)
	 * -----------------------------------------------
	*/
	
	@Test(expected=NullPointerException.class)
	public void test_verschilInMaanden_Als_parameter_null_Geef_fout()
	{
		date.verschilInMaanden(null);
	}
	
	@Test
	public void test_verschilInMaanden_Als_parameter_gelijk_Geeft_0()
	{
		assertEquals("Verschil in maanden", 0, date.verschilInMaanden(dateEqual));
		assertEquals("Verschil in maanden", 0, date.verschilInMaanden(date));
	}
	
	@Test
	public void test_verschilInMaanden_Als_parameter_datum_verschilKleinerDanMaand_Geeft_0()
	{
		assertEquals("Verschil in maanden", 0, date.verschilInMaanden(datePlusOneDay));	
		assertEquals("Verschil in maanden", 0, date.verschilInMaanden(dateMinOneDay));
		assertEquals("Verschil in maanden", 0, date.verschilInMaanden(dateMinOneMonth.veranderDatum(1))); //30 dagen
	}	
		
	@Test
	public void test_verschilInMaanden_Als_parameter_DatumPlus1Maand_Geeft_1()
	{
		assertEquals("Verschil in maanden", 1, date.verschilInMaanden(datePlusOneMonth));		
	}
	
	@Test
	public void test_verschilInMaanden_Als_parameter_DatumPlus365dagen_inSchrikkeljaar_Geeft_11()
	{
		assertEquals("Verschil in maanden", 11, dateNewLeapYear.verschilInMaanden(dateNewVerschil366Leap.veranderDatum(-1)));
	}
	
	@Test
	public void test_verschilInMaanden_Als_parameter_DatumPlus364dagen_nietInSchrikkeljaar_Geeft_11()
	{
		assertEquals("Verschil in maanden", 11, dateNewNotLeapYear.verschilInMaanden(dateNewVerschil365notLeap.veranderDatum(-1)));
	}
	
	
	@Test
	public void test_verschilInMaanden_Als_parameter_DatumPlus1Jaar_inSchrikkeljaar_Geeft_12()
	{
		assertEquals("Verschil in maanden", 12, dateNewLeapYear.verschilInMaanden(dateNewVerschil366Leap));
	}
	
	@Test
	public void test_verschilInMaanden_Als_parameter_DatumPlus1Jaar_nietInSchrikkeljaar_Geeft_12()
	{
		assertEquals("Verschil in maanden", 12, dateNewNotLeapYear.verschilInMaanden(dateNewVerschil365notLeap));
	}
	
	@Test 
	public void test_verschilInMaanden_Als_parameter_DatumPlus674dagen_Geeft_22()
	{
		assertEquals("Verschil in maanden", 22, dateVerschilStart.verschilInMaanden(dateVerschil674End));
	}	
	
	@Test 
	public void test_verschilInMaanden_Als_parameter_DatumPlus10Jaar_Geeft_120()
	{
		assertEquals("Verschil in maanden", 120, date.verschilInMaanden(datePlusTenYears));
	}	
	
				
	@Test
	public void test_verschilInMaanden_Als_parameter_DatumMin365dagen_inSchrikkeljaar_Geeft_11()
	{
		assertEquals("Verschil in maanden", 11, dateNewVerschil366Leap.verschilInMaanden(dateNewLeapYear.veranderDatum(1)));
	}
	
	@Test
	public void test_verschilInMaanden_Als_parameter_DatumMin364dagen_nietInSchrikkeljaar_Geeft_11()
	{
		assertEquals("Verschil in maanden", 11, dateNewVerschil365notLeap.verschilInMaanden(dateNewNotLeapYear.veranderDatum(1)));
	}
	
	@Test
	public void test_verschilInMaanden_Als_parameter_DatumMin1Maand_Geeft_1()
	{
		assertEquals("Verschil in maanden", 1, date.verschilInMaanden(dateMinOneMonth));
	}	
	
	@Test
	public void test_verschilInMaanden_Als_parameter_DatumMin1Jaar_inSchrikkeljaar_Geeft_12()
	{
		assertEquals("Verschil in maanden", 12, dateNewVerschil366Leap.verschilInMaanden(dateNewLeapYear));
	}
	
	@Test
	public void test_verschilInMaanden_Als_parameter_DatumMin1Jaar_nietInSchrikkeljaar_Geeft_12()
	{
		assertEquals("Verschil in maanden", 12, dateNewVerschil365notLeap.verschilInMaanden(dateNewNotLeapYear));
	}
	
	@Test 
	public void test_verschilInMaanden_Als_parameter_DatumMin674dagen_Geeft_22()
	{
		assertEquals("Verschil in maanden", 22, dateVerschil674End.verschilInMaanden(dateVerschilStart));
	}	
	
	@Test 
	public void test_verschilInMaanden_Als_parameter_DatumMin10Jaar_Geeft_120()
	{
		assertEquals("Verschil in maanden", 120, datePlusTenYears.verschilInMaanden(date));
	}	
	
	/*
	 * -----------------------------------------------
	 * TEST METHOD: verschilInJaren(Datum?)
	 * -----------------------------------------------
	*/
	
	@Test(expected=NullPointerException.class)
	public void test_verschilInJaren_Als_parameter_null_Geef_fout()
	{
		date.verschilInJaren(null);
	}	
	
	@Test
	public void test_verschilInJaren_Als_parameter_gelijk_Geeft_0()
	{
		assertEquals("Verschil in Jaren", 0, date.verschilInJaren(dateEqual));
		assertEquals("Verschil in Jaren", 0, date.verschilInJaren(date));
	}
	
	@Test
	public void test_verschilInJaren_Als_parameter_datum_verschilKleinerDanJaar_Geeft_0()
	{
		assertEquals("Verschil in Jaren", 0, date.verschilInJaren(datePlusOneDay));	
		assertEquals("Verschil in Jaren", 0, date.verschilInJaren(dateMinOneDay));
		assertEquals("Verschil in Jaren", 0, date.verschilInJaren(dateMinOneMonth));
		assertEquals("Verschil in Jaren", 0, date.verschilInJaren(datePlusOneMonth));	
	}		
	
	@Test
	public void test_verschilInJaren_Als_parameter_datum_Plus365_inSchrikkeljaar_Geeft_0()
	{
		assertEquals("Verschil in Jaren", 0, dateNewLeapYear.verschilInJaren(dateNewVerschil366Leap.veranderDatum(-1)));
	}
	
	@Test
	public void test_verschilInJaren_Als_parameter_datum_Plus364_nietInSchrikkeljaar_Geeft_0()
	{
		assertEquals("Verschil in Jaren", 0, dateNewNotLeapYear.verschilInJaren(dateNewVerschil365notLeap.veranderDatum(-1)));
	}
	
	@Test
	public void test_verschilInJaren_Als_parameter_datum_Plus1Jaar_inSchrikkeljaar_Geeft_1()
	{
		assertEquals("Verschil in Jaren", 1, dateNewLeapYear.verschilInJaren(dateNewVerschil366Leap));
	}
	
	@Test
	public void test_verschilInJaren_Als_parameter_datum_Plus1Jaar_nietInSchrikkeljaar_Geeft_1()
	{
		assertEquals("Verschil in Jaren", 1, dateNewNotLeapYear.verschilInJaren(dateNewVerschil365notLeap));
	}
	
	@Test 
	public void test_verschilInJaren_Als_parameter_DatumPlus674dagen_Geeft_1()
	{
		assertEquals("Verschil in Jaren", 1,dateVerschilStart.verschilInJaren(dateVerschil674End));
	}	
	
	@Test 
	public void test_verschilInJaren_Als_parameter_DatumPlus10Jaar_Geeft_10()
	{
		assertEquals("Verschil in Jaren", 10, date.verschilInJaren(datePlusTenYears));
	}	

	@Test
	public void test_verschilInJaren_Als_parameter_datum_Min365_inSchrikkeljaar_Geeft_0()
	{
		assertEquals("Verschil in Jaren", 0, dateNewVerschil366Leap.verschilInJaren(dateNewLeapYear.veranderDatum(1)));
	}
	
	@Test
	public void test_verschilInJaren_Als_parameter_datum_Min364_nietInSchrikkeljaar_Geeft_0()
	{
		assertEquals("Verschil in Jaren", 0, dateNewVerschil365notLeap.verschilInJaren(dateNewNotLeapYear.veranderDatum(1)));
	}
	
	@Test
	public void test_verschilInJaren_Als_parameter_datum_Min1Jaar_inSchrikkeljaar_Geeft_1()
	{
		assertEquals("Verschil in Jaren", 1, dateNewVerschil366Leap.verschilInJaren(dateNewLeapYear));
	}
	
	@Test
	public void test_verschilInJaren_Als_parameter_datum_Min1Jaar_nietInSchrikkeljaar_Geeft_1()
	{
		assertEquals("Verschil in Jaren", 1,dateNewVerschil365notLeap.verschilInJaren(dateNewNotLeapYear));
	}
	
	@Test 
	public void test_verschilInJaren_Als_parameter_DatumMin674dagen_Geeft_1()
	{
		assertEquals("Verschil in Jaren", 1, dateVerschil674End.verschilInJaren(dateVerschilStart));
	}	
	
	@Test 
	public void test_verschilInJaren_Als_parameter_DatumMin10Jaar_Geeft_10()
	{
		assertEquals("Verschil in Jaren", 10, datePlusTenYears.verschilInJaren(date));
	}	
	
}

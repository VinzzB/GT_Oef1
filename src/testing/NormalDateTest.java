package testing;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import utils.NormalDate;

//@SuppressWarnings("unused")
public class NormalDateTest {
	
	private NormalDate  dateNorm, dateEqual,
						dateMinOneDay, datePlusOneDay, 
						datePlusOneMonth, dateMinOneMonth,
						dateKleinerEnNietLeap, dateKleinerEnNietLeapPlusOneDay,
						dateGroterEnLeap, dateGroterEnLeapPlusOneDay,
						dateNewLeapYear, 
						dateNewNotLeapYear,
						dateVerschilStart, dateVerschil674End, dateVerschil20End,
						dateVerschil365notLeap, dateVerschil366Leap;
		
	@Before public void setUp()
	{
		dateNorm = new NormalDate(24,1,2010);			
		dateEqual = new NormalDate("24/01/2010");
		dateMinOneDay = new NormalDate(23,1,2010);
		datePlusOneDay = new NormalDate(25,1,2010);
		dateMinOneMonth = new NormalDate(24,12,2009);
		datePlusOneMonth = new NormalDate(24,2,2010);
		dateKleinerEnNietLeap = new NormalDate(28,2,1900);
		dateKleinerEnNietLeapPlusOneDay = new NormalDate(1,3,1900);
		dateGroterEnLeap = new NormalDate(29,2,2012);
		dateGroterEnLeapPlusOneDay = new NormalDate(1,3,2012);
		dateNewLeapYear = new NormalDate(1,1,1600);		
		dateNewNotLeapYear = new NormalDate(1,1,1700);
		dateVerschilStart = new NormalDate(1,3,2007);
		dateVerschil20End = new NormalDate(21,3,2007);
		dateVerschil674End = new NormalDate(3,1,2009);		
		dateVerschil365notLeap = new NormalDate(1,1,1701);
		dateVerschil366Leap= new NormalDate(1,1,1601);
	}
	
	@Test public void test_getAmericanFormat()
	{		
		assertEquals("Amerikaans formaat", "2010/01/24", dateNorm.getAmericanFormat());
		assertEquals("Amerikaans formaat", "1900/02/28", dateKleinerEnNietLeap.getAmericanFormat());
		assertEquals("Amerikaans formaat", "2012/02/29", dateGroterEnLeap.getAmericanFormat());
	}
	@Test public void test_getEuropeanFormat()
	{
		assertEquals("Europees formaat", "24/01/2010", dateNorm.getEuropeanFormat());
		assertEquals("Europees formaat", "28/02/1900", dateKleinerEnNietLeap.getEuropeanFormat());
		assertEquals("Europees formaat", "29/02/2012", dateGroterEnLeap.getEuropeanFormat());
	}
	
	@Test
	public void test_toString()
	{
		assertEquals("ToString method", "24 januari 2010", dateNorm.toString());
		assertEquals("Is schrikkeldag", "28 februari 1900", dateKleinerEnNietLeap.toString());
		assertEquals("Is schrikkeldag", "29 februari 2012", dateGroterEnLeap.toString());
	}
	
	@Test
	public void test_Datum_Kleiner_Dan()
	{
		assertTrue(dateNorm.kleinerDan(dateGroterEnLeap));		
	}
	
	@Test
	public void test_Datum_NietKleiner_Dan()
	{
		assertFalse(dateNorm.kleinerDan(dateKleinerEnNietLeap));		
	}			
	
	@Test
	public void test_Equals_Datum_isGelijk()
	{		
		assertTrue(dateNorm.equals(dateEqual));
		assertTrue(dateNorm.equals(dateNorm));
	}
	
	@Test
	public void test_equals_Datum_isNietGelijk()
	{
		assertFalse(dateNorm.equals(dateGroterEnLeap));
		assertFalse(dateNorm.equals(dateKleinerEnNietLeap));			
	}
	
	
		@Test (expected = IllegalArgumentException.class) 
	public void test_LeapYear_Exception()
	{
		assertNull("Schrikkeljaar Exception", new NormalDate(29, 2, 2014));
	}
		
	@Test (expected = IllegalArgumentException.class)
	public void test_FoutieveDatumInvoer_Dag0()
	{
		NormalDate dateNorm = new NormalDate(0, 12, 2012);		
	}

	@Test (expected = IllegalArgumentException.class)
	public void test_FoutieveDatumInvoer_DagTeGroot()
	{
		NormalDate dateNorm = new NormalDate(31, 4, 2014);		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_FoutieveDatumInvoer_Maand0()
	{
		NormalDate dateNorm = new NormalDate(1, 0, 2012);		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_FoutieveDatumInvoer_MaandTeGroot()
	{
		NormalDate dateNorm = new NormalDate(1, 13, 2012);		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_FoutieveDatumInvoer_JaarTeKlein()
	{
		NormalDate dateNorm = new NormalDate(1, 1, -1);		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_FoutieveDatumInvoer_NegValues()
	{
		NormalDate dateNorm = new NormalDate(-1, -1, -1);		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_FoutieveDatumInvoer_StringFormat()
	{
		NormalDate dateNorm = new NormalDate("1/1/1");		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_FoutieveDatumInvoer_StringMissingSlashes()
	{
		NormalDate dateNorm = new NormalDate("01-01-2011");				
	}
	
	
	
	@Test
	public void test_veranderDatum_VerhoogDagen()
	{
		assertEquals("Verhoog 28 feb +1dag in schrikkeljaar",dateGroterEnLeapPlusOneDay, dateGroterEnLeap.veranderDatum(1));
		//assertEquals("Verhoog 28 feb in schrikkeljaar","29/02/2012" ,  new NormalDate(28,2,2012).veranderDatum(1).getEuropeanFormat());
		assertEquals("Verhoog 28 feb +1dag niet in schrikkeljaar",dateKleinerEnNietLeapPlusOneDay , dateKleinerEnNietLeap.veranderDatum(1));
		assertEquals("Verhoog met 31 dagen (Start Januari)",datePlusOneMonth , dateNorm.veranderDatum(31));
	}
	
	@Test
	public void test_veranderDatum_VerminderDagen()
	{
		assertEquals("Verminder 28 feb  met 1dag in schrikkeljaar",dateGroterEnLeapPlusOneDay, dateGroterEnLeap.veranderDatum(1));
		//assertEquals("Verhoog 28 feb in schrikkeljaar","29/02/2012" ,  new NormalDate(28,2,2012).veranderDatum(1).getEuropeanFormat());
		assertEquals("Verminder 28 feb met 1dag niet in schrikkeljaar",dateKleinerEnNietLeapPlusOneDay , dateKleinerEnNietLeap.veranderDatum(1));
		assertEquals("Verminder met 31 dagen (Start Januari)",dateMinOneMonth , dateNorm.veranderDatum(-31));
	}
	
		
	@Test
	public void test_verschilInDagen_DatumGelijk()
	{
		assertEquals("Verschil in dagen", 0, dateNorm.verschilInDagen(dateEqual));
	}
	
	@Test
	public void test_verschilInDagen_DatumGroter()
	{
		assertEquals("Verschil in dagen", 1, dateNorm.verschilInDagen(datePlusOneDay));	
		assertEquals("Verschil in dagen", 20, dateVerschilStart.verschilInDagen(dateVerschil20End));
		assertEquals("Verschil in dagen", 365, dateNewNotLeapYear.verschilInDagen(dateVerschil365notLeap));
		assertEquals("Verschil in dagen in schrikkeljaar", 366,dateNewLeapYear.verschilInDagen(dateVerschil366Leap));
		assertEquals("Verschil in dagen", 674, dateVerschilStart.verschilInDagen(dateVerschil674End));
	}
	
	@Test
	public void test_verschilInDagen_DatumKleiner()
	{	
		assertEquals("Verschil in dagen", 1, datePlusOneDay.verschilInDagen(dateNorm));	
		assertEquals("Verschil in dagen", 20, dateVerschil20End.verschilInDagen(dateVerschilStart));
		assertEquals("Verschil in dagen", 365, dateVerschil365notLeap.verschilInDagen(dateNewNotLeapYear));
		assertEquals("Verschil in dagen in schrikkeljaar", 366,dateVerschil366Leap.verschilInDagen(dateNewLeapYear));
		assertEquals("Verschil in dagen", 674, dateVerschil674End.verschilInDagen(dateVerschilStart));		
	}		
	
	@Test
	public void test_verschilInMaanden_DatumGelijk()
	{
		assertEquals("Verschil in maanden", 0, dateNorm.verschilInMaanden(dateEqual));
	}
		
	@Test
	public void test_verschilInMaanden_DatumGroter()
	{
		assertEquals("Verschil in maanden", 1, dateNorm.verschilInMaanden(datePlusOneMonth));
		
	}
	
	@Test
	public void test_verschilInMaanden_DatumKleiner()
	{
		assertEquals("Verschil in maanden", 1, dateNorm.verschilInMaanden(dateMinOneMonth));
		assertEquals("Verschil in maanden", 0, dateNorm.verschilInMaanden(dateMinOneMonth.veranderDatum(1)));
	}
	
	
	@Test
	public void test_verschilInJaren_DatumGelijk()
	{
		assertEquals("Verschil in maanden", 0, dateNorm.verschilInJaren(dateEqual));
	}
		
	@Test
	public void test_verschilInJaren_DatumGroter()
	{
		
	}
	
	@Test
	public void test_verschilInJaren_DatumKleiner()
	{
		
	}

	
	
}

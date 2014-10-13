package testing;

import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Before;

import utils.NormalDate;

public class NormalDateTest {
	
	private NormalDate dateNorm, dateEqual, dateKleinerEnNietLeap, dateGroterEnLeap, dateNewLeapYear, dateNewNotLeapYear;
	
	@Before public void setUp()
	{
		dateNorm = new NormalDate(24,1,2010);	
		dateEqual = new NormalDate("24/01/2010");
		dateKleinerEnNietLeap = new NormalDate(15,12,1900);
		dateGroterEnLeap = new NormalDate(29,2,2012);
		dateNewLeapYear = new NormalDate(1,1,1600);
		dateNewNotLeapYear = new NormalDate(1,1,1700);
	}
	
	@Test public void test_getAmericanFormat()
	{		
		assertEquals("Amerikaans formaat", "2010/01/24", dateNorm.getAmericanFormat());
		assertEquals("Amerikaans formaat", "1900/12/15", dateKleinerEnNietLeap.getAmericanFormat());
		assertEquals("Amerikaans formaat", "2012/02/29", dateGroterEnLeap.getAmericanFormat());
	}
	@Test public void test_getEuropeanFormat()
	{
		assertEquals("Europees formaat", "24/01/2010", dateNorm.getEuropeanFormat());
		assertEquals("Europees formaat", "15/12/1900", dateKleinerEnNietLeap.getEuropeanFormat());
		assertEquals("Europees formaat", "29/02/2012", dateGroterEnLeap.getEuropeanFormat());
	}
	
	@Test
	public void test_toString()
	{
		assertEquals("ToString method", "24 januari 2010", dateNorm.toString());
		assertEquals("Is schrikkeldag", "15 december 1900", dateKleinerEnNietLeap.toString());
		assertEquals("Is schrikkeldag", "29 februari 2012", dateGroterEnLeap.toString());
//		for (int i = 1; i <= 12; i++) {
//			assertEquals("ToString maanden voluit", , new NormalDate(5, i, 2014).toString());
//		}
	}
	
	@Test public void test_LeapYears()
	{
		assertEquals("Is geen schrikkeljaar", false, dateNorm.IsLeapYear());
		assertEquals("Is geen schrikkeljaar", false, dateKleinerEnNietLeap.IsLeapYear());
		assertEquals("Is schrikkeljaar", true, dateGroterEnLeap.IsLeapYear());
		for (int i = 0; i < 1000; i++) 
		{
			int y = 1000+i;
			assertEquals("Is Schrikkeljaar per jaar", y % 4 == 0 && (y % 100 != 0 || (y % 400 == 0)), new NormalDate(25, 3, y).IsLeapYear());
		}				
	}
	
	@Test (expected = IllegalArgumentException.class) 
	public void test_LeapYear_Exception()
	{
		assertNull("Schrikkeljaar Exception", new NormalDate(29, 2, 2014));
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
		assertEquals("Verhoog in schrikkeljaar","01/03/2012" , dateGroterEnLeap.veranderDatum(1).getEuropeanFormat());
		assertEquals("Verhoog 28 feb in schrikkeljaar","29/02/2012" , new NormalDate(28,2,2012).veranderDatum(1).getEuropeanFormat());
		assertEquals("Verhoog 28 feb niet in schrikkeljaar","01/03/2014" , new NormalDate(28,2,2014).veranderDatum(1).getEuropeanFormat());
	}
	
	@Test
	public void test_veranderDatum_VerminderDagen()
	{
		
	}
	
	
	
	
	@Test
	public void test_verschilInDagen_DatumGelijk()
	{
		assertEquals("Verschil in dagen", 0, dateNorm.verschilInDagen(dateEqual));
	}
	
	@Test
	public void test_verschilInDagen_DatumGroter()
	{
		assertEquals("Verschil in dagen", 1, dateNorm.verschilInDagen(new NormalDate(25, 1, 2010)));	
		assertEquals("Verschil in dagen", 20, dateNorm.verschilInDagen(new NormalDate(13, 2, 2010)));
		assertEquals("Verschil in dagen", 365, dateNewNotLeapYear.verschilInDagen(new NormalDate(1, 1, 1701)));
		assertEquals("Verschil in dagen in schrikkeljaar", 366,dateNewLeapYear.verschilInDagen(new NormalDate(1, 1, 1601)));
	}
	
	@Test
	public void test_verschilInDagen_DatumKleiner()
	{
		
	}
	
	
	
	@Test
	public void test_verschilInMaanden_DatumGelijk()
	{
		assertEquals("Verschil in maanden", 0, dateNorm.verschilInMaanden(dateEqual));
	}
		
	@Test
	public void test_verschilInMaanden_DatumGroter()
	{
		
	}
	
	@Test
	public void test_verschilInMaanden_DatumKleiner()
	{
		
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

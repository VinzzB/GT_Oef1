package testing;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import utils.Datum2;

public class Datum2Test 
{
	private Datum2 datumInt, datumString, datumKleiner, datumGroter;
	private Datum2[] data;
	private Random random = new Random();

	@Before
	public void setUp() throws Exception 
	{
		datumInt = new Datum2(3, 11, 2014);
		datumString = new Datum2("3/11/2014");
		datumKleiner = new Datum2(1, 11, 2014);
		datumGroter = new Datum2(30, 11, 2015);	
		
		data = new Datum2[20];
		
		for (int i = 0; i < 20; i++) 
		{
			data[i] = new Datum2(random.nextInt(27) + 1, random.nextInt(11) + 1, random.nextInt(200) + 1970);
		}
	}

	@Test
	public void test_Datum2_Met_gegeven_dag_maand_en_jaar_wordt_aangemaakt() 
	{
		Datum2 d = new Datum2(29, 11, 2014);
		assertEquals(29, d.getDag());
		assertEquals(11, d.getMaand());
		assertEquals(2014, d.getJaar());
	}

	@Test
	public void test_Datum2_default_Constructor_voor_vandaag() 
	{
		Datum2 d = new Datum2();
		assertEquals(new GregorianCalendar(), d.getCalendar());
	}

	@Test
	public void test_Datum2_Met_gegeven_dag_maand_en_jaar_AlsString() 
	{
		assertEquals(datumInt, datumString);
	}

	@Test
	public void testSetDatum() 
	{
		for (int i = 0; i < data.length; i++) 
		{
			int r1 = random.nextInt(27) + 1;
			int r2 = random.nextInt(11) + 1;
			int r3 = random.nextInt(200) + 1970;
			datumInt.setDatum(r1, r2, r3);
			assertEquals(r1, datumInt.getDag());
			assertEquals(r2, datumInt.getMaand());
			assertEquals(r3, datumInt.getJaar());
		}
	}

	@Test
	public void test_GetDatumInAmerikaansFormaat() 
	{
		for (Datum2 d : data) 
		{
			System.out.println(d.getDatumInAmerikaansFormaat());
		}
	}

	@Test
	public void test_GetDatumInEuropeesFormaat() 
	{
		for (Datum2 d : data) 
		{
			System.out.println(d.getDatumInEuropeesFormaat());
		}
	}

	@Test
	public void test_ToString() 
	{
		for (Datum2 d : data) 
		{
			System.out.println(d);
		}
	}

	@Test
	public void test_EqualsObject_True() 
	{
		assertTrue(datumInt.equals(datumString));
	}
	
	@Test
	public void test_EqualsObject_False()
	{
		assertFalse(datumInt.equals(datumKleiner));
	}

	@Test
	public void test_CompareTo() 
	{
		assertEquals(-1, datumInt.compareTo(datumGroter));
	}

	@Test
	public void test_KleinerDan_True() 
	{
		assertTrue(datumInt.kleinerDan(datumKleiner));
	}

	@Test
	public void test_KleinerDan_False() 
	{
		assertFalse(datumInt.kleinerDan(datumGroter));
	}
	
	@Test
	public void test_GroterDan_True() 
	{
		assertTrue(datumInt.groterDan(datumGroter));
	}
	
	@Test
	public void test_GroterDan_False() 
	{
		assertFalse(datumInt.groterDan(datumKleiner));
	}

	@Test
	public void test_VerschillInJaren() 
	{
		assertEquals(1, datumInt.verschillInJaren(datumGroter));
		assertEquals(0, datumInt.verschillInJaren(datumKleiner));
	}

	@Test
	public void test_VerschillInMaanden() 
	{
		assertEquals(12, datumInt.verschillInMaanden(datumGroter));
		assertEquals(0, datumInt.verschillInMaanden(datumKleiner));
	}

	@Test
	public void test_VerschillInDagen() 
	{
		assertEquals(2, datumInt.verschillInDagen(datumKleiner));
	}

	@Test
	public void test_VerschillInMillis() 
	{
		assertEquals(0, datumInt.verschillInDagen(datumString));
	}
}

package testing;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import utils.GregorianDatum;;

public class GregorianDatumTest 
{
	private GregorianDatum datumInt, zelfdeDatum, datumString1, datumString2,
							datumString3, datumString4, datumKleiner, 
							datumGroter, datumVinzz1, datumVinzz2;
	
	private GregorianDatum[] data; 
	private Random random = new Random();

	@Before
	public void setUp() throws Exception 
	{
		datumInt = new GregorianDatum(3, 11, 2014);
		zelfdeDatum = new GregorianDatum(3, 11, 2014);
		datumString1 = new GregorianDatum("3/11/2014");
		datumString2 = new GregorianDatum("3*11*2014");
		datumString3 = new GregorianDatum("3.11.2014");
		datumString4 = new GregorianDatum("3-11-2014");
		datumKleiner = new GregorianDatum(1, 11, 2014);
		datumGroter = new GregorianDatum(30, 11, 2015);	
		datumVinzz1 = new GregorianDatum(1, 3, 2007);
		datumVinzz2 = new GregorianDatum(3, 1, 2009);
		
		data = new GregorianDatum[20];
		
		for (int i = 0; i < 20; i++) 
		{
			data[i] = new GregorianDatum(random.nextInt(27) + 1, random.nextInt(11) + 1, random.nextInt(200) + 1970);
		}
	}

	@Test
	public void test_GregorianDatum_Met_gegeven_dag_maand_en_jaar_wordt_aangemaakt() 
	{
		GregorianDatum d = new GregorianDatum(29, 11, 2014);
		assertEquals(29, d.getDag());
		assertEquals(10, d.getMaand());
		assertEquals(2014, d.getJaar());
	}

	@Test
	public void test_GregorianDatum_default_Constructor_voor_vandaag() 
	{
		GregorianDatum d = new GregorianDatum();
		assertEquals(new GregorianCalendar(), d.getCalendar());
	}

	@Test
	public void test_GregorianDatum_Met_gegeven_dag_maand_en_jaar_AlsString() 
	{
		assertEquals(datumInt, datumString1);
		assertEquals(datumInt, datumString2);
		assertEquals(datumInt, datumString3);
		assertEquals(datumInt, datumString4);
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
			assertEquals(r2, datumInt.getMaand() + 1);
			assertEquals(r3, datumInt.getJaar());
		}
	}

	@Test
	public void test_GetDatumInAmerikaansFormaat() 
	{
		for (GregorianDatum d : data) 
		{
			System.out.println(d.getDatumInAmerikaansFormaat());
		}
	}

	@Test
	public void test_GetDatumInEuropeesFormaat() 
	{
		for (GregorianDatum d : data) 
		{
			System.out.println(d.getDatumInEuropeesFormaat());
		}
	}

	@Test
	public void test_ToString() 
	{
		for (GregorianDatum d : data) 
		{
			System.out.println(d);
		}
	}

	@Test
	public void test_EqualsObject_True() 
	{
		assertTrue(datumInt.equals(zelfdeDatum));
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
		assertTrue(datumKleiner.kleinerDan(datumInt));
	}

	@Test
	public void test_KleinerDan_False() 
	{
		assertFalse(datumGroter.kleinerDan(datumInt));
	}
	
	@Test
	public void test_GroterDan_True() 
	{
		assertTrue(datumGroter.groterDan(datumInt));
	}
	
	@Test
	public void test_GroterDan_False() 
	{
		assertFalse(datumKleiner.groterDan(datumInt));
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
	public void test_VerschillInDagen1() 
	{
		assertEquals(394, datumGroter.verschillInDagen(datumKleiner));
	}
	
	@Test
	public void test_VerschillInDagen2() 
	{
		assertEquals(2, datumInt.verschillInDagen(datumKleiner));
	}
	
	@Test
	public void test_VerschillInDagen3() 
	{
		assertEquals(392, datumGroter.verschillInDagen(datumInt));
	}
	
	@Test
	public void test_VerschillInDagen4() 
	{
		assertEquals(0, datumInt.verschillInDagen(datumString1));
	}
	@Test
	public void test_VerschillInDagen5() 
	{
		assertEquals(674, datumVinzz1.verschillInDagen(datumVinzz2));
	}
	
	@Test
	public void test_VerschillInMillis() 
	{
		assertEquals(0, datumInt.verschillInDagen(datumString1));
	}
}

package testing;

import static org.junit.Assert.*;

import java.util.Calendar;

import java.util.GregorianCalendar;

import org.junit.Before;

import org.junit.BeforeClass;

import org.junit.Test;

import utils.GregorianDatum;

import utils.DatumGreg;

public class TestDatum2_ {

private GregorianDatum datum, dezelfdeDatum, datumMetAndereMaand, datumMetAndereDag, datumMetAnderJaar;

private GregorianCalendar gcDezelfdeDatum;

@Before

public void setUp() throws Exception

{

datum = new GregorianDatum(3,5,2007);

dezelfdeDatum = new GregorianDatum(3,5,2007);

datumMetAndereMaand = new GregorianDatum(3,6,2007);

datumMetAndereDag = new GregorianDatum(1,5,2007);

datumMetAnderJaar = new GregorianDatum(3,5,2006);

gcDezelfdeDatum = new GregorianCalendar(3,5,2007);

}

@Test

public void test_Constructor_object_wordt_aangemaakt()

{

assertEquals(3,datum.getDag());

assertEquals(4,datum.getMaand());

assertEquals(2007,datum.getJaar());

}

@Test

public void test_Constructor_object_wordt_correct_gesplitst()

{

datum = new GregorianDatum("2/12/2014");

assertEquals(2,datum.getDag());

assertEquals(11,datum.getMaand());//Greg Cal: januari = maand 0, dec = 11

assertEquals(2014,datum.getJaar());

}

@Test (expected = IllegalArgumentException.class)

public void test_setDatum_Exception_als_dag_niet_bestaat()

{

datum = new GregorianDatum(0,4,2012);

}

@Test (expected = IllegalArgumentException.class)

public void test_setDatum_Exception_als_maand_niet_bestaat()

{

datum = new GregorianDatum(4,13,2012);

}

@Test (expected = IllegalArgumentException.class)

public void test_setDatum_Exception_als_jaar_niet_bestaat()

{

datum = new GregorianDatum(1,13,-2012);

}

@Test

public void test_setDatum_Jaar_0_wordt_aanvaard()

{

datum = new GregorianDatum(1,1,0);

}

@Test

public void test_Equals_True_als_data_gelijk()

{

assertTrue(datum.equals(dezelfdeDatum));

assertTrue(dezelfdeDatum.equals(datum));

}

@Test

public void test_Equals_False_als_data_niet_gelijk()

{

assertFalse(datum.equals(datumMetAndereMaand));

assertFalse(datumMetAndereMaand.equals(datum));

assertFalse(datum.equals(datumMetAndereDag));

assertFalse(datumMetAndereDag.equals(datum));

assertFalse(datum.equals(datumMetAnderJaar));

assertFalse(datumMetAnderJaar.equals(datum));

}

public void test_getCalendar_bevat_correcte_waarden()

{

assertEquals(this.gcDezelfdeDatum,datum.getCalendar());

}

@Test

public void test_getDatumInAmerikaansFormaat_datum_wordt_correct_weergegeven()

{

assertEquals("2007/5/3",datum.getDatumInAmerikaansFormaat());

}

@Test

public void test_getDatumInEuropeesFormaat_datum_wordt_correct_weergegeven()

{

assertEquals("3/5/2007",datum.getDatumInEuropeesFormaat());

}

@Test

public void test_toString_tekst_wordt_correct_weergegeven()

{

assertEquals("3 mei 2007", this.datum.toString());

//geeft fout, want data zijn niet in het NL gedeclareerd;

}

/*	public void test_compareTo_

public int compareTo(Datum2 datum)

{

return this.calendar.compareTo(datum.getCalendar());

}*/

@Test

public void test_kleinerDan_True_als_datum_kleiner()

{

assertTrue(this.datum.kleinerDan(this.datumMetAndereMaand));

//geeft fout, geen true als datum kleiner is;

//before en after moeten omgewisseld worden in de method

}

@Test

public void test_groterDan_True_als_datum_groter()

{

assertTrue(this.datumMetAndereMaand.groterDan(this.datum));

//geeft fout, geen true als datum groter is;

//before en after moeten omgewisseld worden in de method

}

@Test

public void test_verschilInJaren_geeft_correct_aantal_jaren_verschil()

{

assertEquals(1,this.datum.verschillInJaren(this.datumMetAnderJaar));

}

@Test

public void test_verschilInMaanden_geeft_correct_aantal_maanden_verschil()

{

assertEquals(1,this.datum.verschillInMaanden(this.datumMetAndereMaand));

}

@Test

public void test_verschilInDagen_geeft_correct_aantal_dagen_verschil()

{

assertEquals(2,this.datum.verschillInDagen(this.datumMetAndereDag));

}

@Test

public void test_veranderDatum_aantal_dagen_wordt_bijgeteld()

{

	GregorianDatum dd = this.datum.veranderedDatum(50); //verandered? naam methode veranderen
assertEquals(dd,this.datum);

}

@Test

public void test_veranderDatum_aantal_dagen_wordt_afgetrokken()

{

	GregorianDatum dd = this.datum.veranderedDatum(-26);

assertEquals(dd, this.datum);

}

}

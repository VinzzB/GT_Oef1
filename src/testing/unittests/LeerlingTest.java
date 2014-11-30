package model;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import utils.date.normal.Datum;

/**
 *
 * @author Silvia
 */
public class LeerlingTest
{
    private OpdrachtAntwoord opdrAntw;
    private QuizDeelname qDeeln;
    private QuizOpdracht qOpdr;
    private int antwTijd, aantPog, maxScore;
    private String ltstAntw;
    private Datum datum;
    private Quiz q;
    private Leerling l;
    private Opdracht opdr;
    private Set<OpdrachtAntwoord> sOpdrAntw;
    private Set<QuizDeelname> sQDeeln;
    
    public LeerlingTest()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
    }
    
    @AfterClass
    public static void tearDownClass()
    {
    }
    
    @Before
    public void setUp()
    {
        try
        {
            datum = new Datum();
            q = new Quiz();
            l = new Leerling("Jan Mulder", 4);
            maxScore = 10;
            opdr = new Opdracht("vraag","antwoord");
            qOpdr = new QuizOpdracht(q, opdr, maxScore);
            
            opdrAntw = new OpdrachtAntwoord(qDeeln, qOpdr, 1, "blabla", 5);
            sOpdrAntw.add(opdrAntw);
            qDeeln = new QuizDeelname(l,sOpdrAntw, datum, q);
            sQDeeln.add(qDeeln);
        }
        catch (Exception ex)
        {
            Logger.getLogger(LeerlingTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @After
    public void tearDown()
    {
    }

    /**
     * Test of getLeerlingNaam method, of class Leerling.
     */
    @Test
    public void testGetLeerlingNaam()
    {
        System.out.println("getLeerlingNaam");
        Leerling instance = new Leerling("Jan",4);
        String expResult = "Jan";
        String result = instance.getLeerlingNaam();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getLeerjaar method, of class Leerling.
     */
    @Test
    public void testGetLeerjaar()
    {
        System.out.println("getLeerjaar");
        Leerling instance = new Leerling("Joris", 5);
        int expResult = 5;
        int result = instance.getLeerjaar();
        assertEquals(expResult, result);
    }

    /**
     * Test of setQuizDeelnames method, of class Leerling.
     */
    @Test
    public void test_SetQuizDeelnames_geenDeelnames_null()
    {
        System.out.println("setQuizDeelnames");
        Leerling instance = new Leerling("Mieke", 6);
        instance.setQuizDeelnames(null);
        assertEquals(null, instance.getQuizDeelnames());
        
    }

    @Test
    public void test_SetQuizDeelnames_1Deelname_equal()
    {
        System.out.println("setQuizDeelnames");
        Leerling instance = new Leerling("Mieke", 6);
        Set<QuizDeelname> sqDeeln = new HashSet<>();
        sqDeeln.add(new QuizDeelname(instance, sOpdrAntw, datum, q));
        instance.setQuizDeelnames(sqDeeln);
        assertEquals(sqDeeln, instance.getQuizDeelnames());
        assertEquals(sqDeeln.size(),instance.getQuizDeelnames().size());
    }
    /**
     * Test of getQuizDeelnames method, of class Leerling.
     */
    @Test
    public void test_GetQuizDeelnames_geenDeelnames_null()
    {
        System.out.println("getQuizDeelnames");
        Leerling instance = new Leerling("Mira",4);
        instance.setQuizDeelnames(null);
        Set expResult = null;
        Set result = instance.getQuizDeelnames();
        assertEquals(expResult, result);
        
    }

    @Test
    public void test_GetQuizDeelnames_1Deelname()
    {
        System.out.println("getQuizDeelnames");
        Leerling instance = new Leerling("Mira",4);
        instance.addQuizDeelname(new QuizDeelname(instance ,sOpdrAntw, datum, q));
        int expResult = 1;
        int result = instance.getQuizDeelnames().size();
        assertEquals(expResult, result);
        
    }
    /**
     * Test of compareTo method, of class Leerling.
     */
    @Test (expected = NullPointerException.class)
    public void test_CompareTo_vergelijkMetnull_exception()
    {
        System.out.println("compareTo");
        Object o = null;
        Leerling instance = new Leerling("Tina",4);
        int expResult = 0;
        int result = instance.compareTo((Leerling) o);
        assertEquals(expResult, result);
        
    }
    
    @Test 
    public void test_CompareTo_vergelijkMetleerling_0()
    {
        System.out.println("compareTo");
        Object o = l;
        Leerling instance = new Leerling("Jan Mulder",4);
        int expResult = 0;
        int result = instance.compareTo((Leerling) o);
        assertEquals(expResult, result);
        
    }
    
    /**
     * Test of toString method, of class Leerling.
     */
    @Test (expected = NullPointerException.class)
    public void test_ToString_null_exception()
    {
        System.out.println("toString");
        Leerling instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    @Test 
    public void test_ToString_leerling_string()
    {
        System.out.println("toString");
        Leerling instance = l;
        String expResult = "Leerling: Jan Mulder Leerjaar: 4";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of clone method, of class Leerling.
     */
    @Test (expected = NullPointerException.class)
    public void test_Clone_null_exception() throws Exception
    {
        System.out.println("clone");
        Leerling instance = null;
        Object expResult = null;
        Object result = instance.clone();
        assertEquals(expResult, result);
        
    }

    @Test
    public void test_Clone_leerling_zelfdeLeerling() throws Exception
    {
        System.out.println("clone");
        Leerling instance = l;
        Object expResult = new Leerling("Jan Mulder", 4);
        Object result = instance.clone();
        assertEquals(expResult, result);   
    }
    /**
     * Test of hashCode method, of class Leerling.
     */
    @Test (expected = NullPointerException.class)
    public void test_HashCode_null_exception()
    {
        System.out.println("hashCode");
        Leerling instance = null;
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    @Test 
    public void test_HashCode_leerling_zelfdeHashcode()
    {
        System.out.println("hashCode");
        Leerling instance = l;
        int expResult = (new Leerling("Jan Mulder", 4)).hashCode();
        int result = instance.hashCode();
        assertEquals(expResult, result);
        
    }
    /**
     * Test of equals method, of class Leerling.
     */
    @Test (expected = NullPointerException.class)
    public void test_Equals_null_exception()
    {
        System.out.println("equals");
        Object obj = null;
        Leerling instance = null;
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    @Test 
    public void test_Equals_leerling_true()
    {
        System.out.println("equals");
        Object obj = l;
        Leerling instance = new Leerling("Jan Mulder", 4);
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }
    
    @Test 
    public void test_Equals_leerlingAnders_false()
    {
        System.out.println("equals");
        Object obj = l;
        Leerling instance = new Leerling("Jan", 4);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of addQuizDeelname method, of class Leerling.
     */
    @Test (expected = NullPointerException.class)
    public void test_AddQuizDeelname_null_exception()
    {
        System.out.println("addQuizDeelname");
        QuizDeelname q = null;
        Leerling instance = null;
        instance.addQuizDeelname(q);
    }

    @Test 
    public void test_AddQuizDeelname_leerlingMetDeelname_1deelname()
    {
        System.out.println("addQuizDeelname");
        Leerling instance = l;
        QuizDeelname qDn = new QuizDeelname(instance,sOpdrAntw, datum, q);
        instance.addQuizDeelname(qDn);
        int expResult = 1;
        int result = instance.getQuizDeelnames().size();
        assertEquals(expResult, result);
    }
    /**
     * Test of removeQuizDeelname method, of class Leerling.
     */
    @Test (expected = NullPointerException.class)
    public void test_RemoveQuizDeelname_null_exception()
    {
        System.out.println("removeQuizDeelname");
        QuizDeelname q = null;
        Leerling instance = null;
        instance.removeQuizDeelname(q);
    }
    
     @Test 
    public void test_RemoveQuizDeelname_leerlingMet1Deelname_0()
    {
        System.out.println("removeQuizDeelname");
        Leerling instance = l;
        QuizDeelname qDn = new QuizDeelname(instance, sOpdrAntw, datum, q);
        instance.addQuizDeelname(qDn);
        instance.removeQuizDeelname(qDn);
        int expResult = 0;
        int result = instance.getQuizDeelnames().size();
        assertEquals(expResult, result);
    }
}

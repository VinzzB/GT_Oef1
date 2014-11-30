package model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author silviamirisola
 */
public class LeerlingContainerTest
{
    private Map<Integer,Leerling> map;
    private Leerling leerling;
    
    public LeerlingContainerTest()
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
        map = new HashMap<>();
        map.put(1, new Leerling("Jan",4));
        map.put(2, new Leerling("Piet",2));
        map.put(3, new Leerling("Joris", 1));
        map.put(4, new Leerling("Ann", 5));
        map.put(5, new Leerling("Mieke",3));
        
        leerling = new Leerling("Lucky", 6);
        
    }
    
    @After
    public void tearDown()
    {
    }

    /**
     * Test of toString method, of class LeerlingContainer.
     */
    @Test
    public void test_ToString_met_gegeven_map_met_5_elementen_true()
    {
        System.out.println("toString");
        LeerlingContainer instance = new LeerlingContainer(map);
        String expResult = "LeerlingContainer{" + "leerlingContainer=" + map.toString()+ '}';
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of iterator method, of class LeerlingContainer.
     * test hasNext on an empty collection (returns false)
     * test next() on an empty collection (throws exception)
     * test hasNext on a collection with one item (returns true, several times)
     * test hasNext/next on a collection with one item: hasNext returns true, next returns the item, hasNext returns false, twice
     * test remove on that collection: check size is 0 after
     * test remove again: exception
     * final test with a collection with several items, make sure the iterator goes through each item, in the correct order (if there is one)
     * remove all elements from the collection: collection is now empty
     */
    @Test
    public void test_Iterator_MapMet5elementen_IteratorMet5elementen()
    {
        System.out.println("iterator");
        LeerlingContainer instance = new LeerlingContainer(map);
        Iterator<Leerling> itr = instance.iterator();
        int expResult = 5;
        int result = 0;
        while(itr.hasNext())
        {
            Leerling l = itr.next();
            if (instance.getLeerlingContainer().containsValue(l))
            {
                result++;
            }
            else
            {
                fail("Onbekend element in map " + l);
            }
            
        }
        assertEquals(expResult, result);
    }

    /**
     * Test of getLeerlingContainer method, of class LeerlingContainer.
     */
    @Test
    public void test_GetLeerlingContainer_legeContainer_null()
    {
        System.out.println("getLeerlingContainer");
        LeerlingContainer instance = new LeerlingContainer();
        Map<Integer, Leerling> expResult = new HashMap<>();
        Map<Integer, Leerling> result = instance.getLeerlingContainer();
        assertEquals(expResult, result);
   
    }

    /**
     * Test of hashCode method, of class LeerlingContainer.
     */
    @Test
    public void test_HashCode_2containersMetZelfde5Objecten_zelfdeHaschcode()
    {
        System.out.println("hashCode");
        LeerlingContainer instance = new LeerlingContainer(map);
        LeerlingContainer same = new LeerlingContainer(map);
        int expResult = same.hashCode();
        int result = instance.hashCode();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of equals method, of class LeerlingContainer.
     */
    @Test
    public void test_Equals_2containersMetZelfdeInhoud_true()
    {
        System.out.println("equals");
        LeerlingContainer instance = new LeerlingContainer(map);
        LeerlingContainer same = new LeerlingContainer(map);
        boolean expResult = true;
        boolean result = instance.equals(same);
        assertEquals(expResult, result);
        
    }
@Test
    public void test_Equals_1volle1legeContainer_false()
    {
        System.out.println("equals");
        LeerlingContainer leeg = null;
        LeerlingContainer instance = new LeerlingContainer(map);
        boolean expResult = false;
        boolean result = instance.equals(leeg);
        assertEquals(expResult, result);
        
    }
    /**
     * Test of voegLeerlingToe method, of class LeerlingContainer.
     */
    @Test (expected = IllegalArgumentException.class)
    public void test_VoegLeerlingToe_LegeContainer_LeerlingNull_exception()
    {
        System.out.println("voegLeerlingToe");
        Leerling l = null;
        LeerlingContainer instance = new LeerlingContainer();
        instance.voegLeerlingToe(l);
        
    }
    
}

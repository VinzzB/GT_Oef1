package testing.unittests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Quiz;

/**
 * @author      Natalia Dyubankova <fornnd@gmail.com>
 * @version     1.0                 
 * @since       2014-11-12          
 */
public class QuizJUnitTest
{

	private Quiz q1, q2, q3, q4, q5, q6;
	
	@Before
	public void setUp() throws Exception 
	{
		q1 = new Quiz();
		q2 = new Quiz(2, "Hoofdsteden", 3, false, false, "test");
		q3 = new Quiz(q2);
		q4 = new Quiz("Hoofdsteden", 3, false, false, "test");
		q5 = q4.clone();
		q6 = new Quiz(3, "Bieren", 4, false, false, "test");
	}
	
	@Test public void copyConstructor_Test()
	{
		assertEquals(q3, q4);
	}
	
	@Test public void equals_False()
	{
		assertFalse(q3.equals(q1));
	}
	
	@Test public void equals_True()
	{
		assertEquals(true, q3.equals(q4));
	}
	
	@Test public void clone_True()
	{
		assertEquals(q5, q4);
	}
	
	@Test public void compare_verschillendeQuizzen()
	{
		assertEquals(-3, q4.compareTo(q6)); //quizID: 0, 3
		assertEquals(-1, q2.compareTo(q6)); //quizID: 2, 3
	}
	
	@Test public void compare_deselfdeQuizzen()
	{
		assertEquals(0, q2.compareTo(q3)); 
		assertEquals(0, q4.compareTo(q5)); 
	}
	
	
}

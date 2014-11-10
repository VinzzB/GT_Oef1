package persistency;

import java.io.FileNotFoundException;
import java.io.IOException;

import model.OpdrachtCatalogus;
import model.QuizCatalogus;
/**
 * Root interface van persistentie package. 
 * Mogelijk objecten op te slaan op verschillende manieren
 * bv. als txt-file of MySQL-database
 * 
 * @author Natalia Dyubankova <fornnd@gmail.com>
 * @version     1.0                 
 * @since       2014-11-11 
 */
public interface IDatabaseStrategy
{
	void setCatalogus(OpdrachtCatalogus opdrachtCatalogus, QuizCatalogus quizCatalogus );
	
	void leesOpdrachten() throws FileNotFoundException, IOException;
	void leesQuzen() throws FileNotFoundException, IOException;
	void kopelQuizOpdrachten() throws FileNotFoundException, IOException;
	
	void safeOpdrachten();
	void safeQuizen();
	void safeQuizOpdrachten();
}

package persistency;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import src.model.opdracht.OpdrachtCatalogus;
import src.model.QuizCatalogus;
import src.model.QuizOpdracht;
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
	
	void leesOpdrachten() throws FileNotFoundException, IOException, NumberFormatException, Exception;
	void leesQuzen() throws FileNotFoundException, IOException, NumberFormatException, Exception;
	void kopelQuizOpdrachten() throws FileNotFoundException, IOException, SQLException;
	
//	QuizCatalogus getQuizCatalogus();
//	OpdrachtCatalogus getOpdrachtCatalogus();
//	ArrayList<QuizOpdracht> getQuizOpdrachten();
	
	void safeOpdrachten() throws SQLException, FileNotFoundException, IOException;
	void safeQuizen() throws SQLException, FileNotFoundException, IOException;
	void safeQuizOpdrachten() throws SQLException, FileNotFoundException, IOException;
}

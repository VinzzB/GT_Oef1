package persistency;

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

//	void setCatalogus(OpdrachtCatalogus opdrachtCatalogus, QuizCatalogus quizCatalogus );
	
	void leesOpdrachten() throws Exception;
	void leesQuizen() throws Exception;
//	void koppelQuizOpdrachten() throws FileNotFoundException, IOException, SQLException;
	
//	QuizCatalogus getQuizCatalogus();
//	OpdrachtCatalogus getOpdrachtCatalogus();
//	ArrayList<QuizOpdracht> getQuizOpdrachten();
	
	void safeOpdrachten() throws Exception;
	void safeQuizen() throws Exception;
//	void safeQuizOpdrachten() throws SQLException, FileNotFoundException, IOException;
}

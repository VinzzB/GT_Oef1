package persistency;

import model.OpdrachtCatalogus;
import model.QuizCatalogus;

public interface IDatabaseStrategy
{
	void setCatalogus(OpdrachtCatalogus opdrachtCatalogus, QuizCatalogus quizCatalogus );
	
	void leesOpdrachten();
	void leesQuzen();
	void leesQuizOpdachten();
	
	void kopelQuizOpdrachten();
	
	void safeOpdrachten();
	void safeQuizen();
	void safeQuizOpdrachten();
}

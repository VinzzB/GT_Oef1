package persistency;

public interface DatabaseStrategy
{
	void leesOpdrachten();
	void leesQuzen();
	void leesQuizOpdrachten();
	void kopelQuizOpdrachten();
	
	void safeOpdrachten();
	void safeQuizen();
	void safeQuizOpdrachten();
}

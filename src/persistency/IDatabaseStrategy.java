package persistency;

public interface IDatabaseStrategy
{
	void leesOpdrachten();
	void leesQuzen();
	void leesQuizOpdrachten();
	void kopelQuizOpdrachten();
	
	void safeOpdrachten();
	void safeQuizen();
	void safeQuizOpdrachten();
}

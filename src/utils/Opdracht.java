package utils;

import java.util.Date;
import java.util.List;

public class Opdracht {
	
	//ID toevoegen
	
	private String vraag;
	private String juisteAntwoord;
	private int maxAantalPogingen = 1;
	private List<String> antwoordHints; //meerdere hints per vraag mogelijk
	private int maxAntwoordTijdinSec = 0;
	private List<QuizOpdracht> quizOpdrachten;
	private boolean gekoppeldAanQuiz = false;
	private String editErrorMessage = "De opdracht kan niet meer gewijzigd worden, want ze is reeds gelinkt aan een Quiz";

	
	/**constructor*/
	
	public Opdracht (String vraag, String juisteAntwoord, int maxAantalPogingen, String antwoordHints, int maxAntwoordTijdinSec)
	{
		this.vraag = vraag;
		this.juisteAntwoord = juisteAntwoord;
		this.maxAantalPogingen = maxAantalPogingen;
		this.voegAntwoordHintsToe(antwoordHints);
		this.maxAntwoordTijdinSec = maxAntwoordTijdinSec;	
	}
	
	/**getters en setters*/
	
	public String getVraag() 
	{
		return vraag;
	}

	public void setVraag(String vraag) throws Exception
	{
		//opdracht mag enkel gewijzigd worden indien nog niet gekoppeld aan een quiz
		if (this.gekoppeldAanQuiz == false)
		{
			this.vraag = vraag;
		}
		else throw new Exception(this.editErrorMessage);
	}

	public String getJuisteAntwoord()
	{
		return juisteAntwoord;
	}

	public void setJuisteAntwoord(String juisteAntwoord) throws Exception
	{
		if (this.gekoppeldAanQuiz == false)
		{
			this.juisteAntwoord = juisteAntwoord;
		}
		else throw new Exception(this.editErrorMessage);
	}

	public int getMaxAantalPogingen()
	{
		return maxAantalPogingen;
	}

	public void setMaxAantalPogingen(int maxAantalPogingen) throws Exception
	{
		if (this.gekoppeldAanQuiz == false)
		{
			this.maxAantalPogingen = maxAantalPogingen;
		}
		else throw new Exception(this.editErrorMessage);
	}

	public List<String> getAntwoordHints() 
	{
		return antwoordHints;
	}

	public int getMaxAntwoordTijdinSec() 		
	{
		return maxAntwoordTijdinSec;
	}

	public void setMaxAntwoordTijdinSec(int maxAntwoordTijdinSec) throws Exception
	{
		if (this.gekoppeldAanQuiz == false)
		{
			this.maxAntwoordTijdinSec = maxAntwoordTijdinSec;
		}
		else throw new Exception(this.editErrorMessage);
	}

	public List<QuizOpdracht> getQuizOpdrachten()
	{
		return quizOpdrachten;
	}

	public void setQuizOpdrachten(List<QuizOpdracht> quizOpdrachten) 
	{
		this.quizOpdrachten = quizOpdrachten;
	}
	
	public boolean getGekoppeldAanQuiz()
	{
		return this.gekoppeldAanQuiz;
	}
	
	public void setGekoppeldAanQuiz(boolean gekoppeldAanQuiz)
	{
		this.gekoppeldAanQuiz = gekoppeldAanQuiz;
	}
	
	
	/**methods*/
	
	protected void voegQuizOpdrachtToe(QuizOpdracht quizOpdracht)
	{
		this.quizOpdrachten.add(quizOpdracht);
		this.gekoppeldAanQuiz = true;
		/**(Opdracht -> QuizOpdracht: 1 to many 
		 * koppel nieuwe quizopdracht in klasse Quizopdracht aan deze opdracht)*/
		//quizOpdracht.setOpdracht(this); //method moet nog toegevoegd worden in class QuizOpdracht
	}
		
	protected void voegAntwoordHintsToe(String hints)
	{
		this.antwoordHints.add(hints);
	}
	
	protected void verwijderAntwoordHints(String hints)
	{
		this.antwoordHints.remove(hints);
	}

	public String toString()
	{
		return vraag+"("+juisteAntwoord+")";
	}

	protected boolean isJuisteAntwoord(String antwoord)
	{
		if (this.juisteAntwoord == antwoord)
		{
			return true;
		}
		else return false;
	}


}

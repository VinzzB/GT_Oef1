package model.quizStatus;

import model.QuizDeelname;
import model.QuizOpdracht;

public abstract class QuizStatus
{

	public void voegQuizToe()
	{
		System.out.println("Quiz toevoegen is niet mogelijk");
	}

	public void voegQuizOpdrachtToe(QuizOpdracht q)
	{
		System.out.println("Quiz wijzigen is niet mogelijk");
	}

	public void verwijderQuizOpdracht(QuizOpdracht q)
	{
		System.out.println("Quiz wijzigen is niet mogelijk");
	}

	public void bewaarQuiz()
	{
		System.out.println("De Quiz kan niet bewaard worden");
	}

	public void stelQuizOpen()
	{
		System.out.println("Quiz openstellen is niet mogelijk");
	}

	public void addQuizDeelname(QuizDeelname quizDeelname)
	{
		System.out.println("Quizdeelname is niet mogelijk");
	}

	public void removeQuizDeelname(QuizDeelname quizDeelname)
	{
		System.out.println("Quiz verwijderen is niet mogelijk");
	}

	public void sluitQuizAf()
	{
		System.out.println("Quiz afsluiten is niet mogelijk");
	}

	public void geefLaatsteKans()
	{
		System.out.println("Quiz laatste kans geven is niet mogelijk");
	}

}

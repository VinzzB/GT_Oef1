package model.quizStatus;

import model.Quiz;
import model.QuizDeelname;
import model.QuizOpdracht;

/**
 * Abstracte classe die methods bevat die kunnen wijzigen naargelang de status
 * van de quiz.
 * @author silvia
 */
public abstract class QuizStatus
{
//	public void inConstructie(Quiz q)
//	{ System.out.println("Quiz kan niet naar omgezet worden naar de status 'In Constructie'"); }
//	public void afgewerkt(Quiz q) 
//	{System.out.println("Quiz kan niet naar omgezet worden naar de status 'afgewerkt'");  }
//	public void opengesteld(Quiz q) 
//	{ System.out.println("Quiz kan niet naar omgezet worden naar de status 'opengesteld'"); }
//	public void laatsteKans(Quiz q) 
//	{ System.out.println("Quiz kan niet naar omgezet worden naar de status 'laatsteKans'"); }			

	public abstract Statussen getEnumType();
	
	public void voegQuizToe(Quiz q)
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

	public void stelQuizOpen(Quiz q)
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

	public void sluitQuizAf(Quiz q)
	{
		System.out.println("Quiz afsluiten is niet mogelijk");
	}

	public void geefLaatsteKans(Quiz q)
	{
		System.out.println("Quiz laatste kans geven is niet mogelijk");
	}
        
        public boolean kanDeelnemen()
        { 
            return false;
        }
        
        public boolean magOpdrachtWijzigen()
        { 
            return false; 
        }
        
        public boolean magQuizWijzigen()
        { 
            return false; 
        }
        
        public boolean magQuizVerwijderen()
        { 
            return false; 
        }
        
        @Override
        public String toString()
        {
            return "Quiz Status";
        }
        
}

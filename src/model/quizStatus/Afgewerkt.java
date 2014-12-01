package model.quizStatus;

import model.Quiz;

public class Afgewerkt extends QuizStatus
{
    private static final Afgewerkt statusAfgewerkt = new Afgewerkt();
    private Afgewerkt(){}
    public static QuizStatus instance()
    {
        return statusAfgewerkt;
    }
    
    @Override
    public void geefLaatsteKans(Quiz q)
    {
        q.setStatus(LaatsteKans.instance());
    }
        
    @Override
    public void sluitQuizAf(Quiz q)
    {
            q.setStatus(Afgesloten.instance());
    }

    @Override
    public boolean magOpdrachtWijzigen()
    {
        return true;
    }

    @Override
    public boolean magQuizVerwijderen()
    {
        return true;
    }

    @Override
    public boolean kanDeelnemen()
    { 
        return true;
    }
    
    @Override
    public String toString()
    {
        return "Afgewerkt";
    }
    
}

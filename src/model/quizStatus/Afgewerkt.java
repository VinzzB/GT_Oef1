package model.quizStatus;

import model.Quiz;
/***
 * 
 * @Revisioned bloemevi on 10/01/2015
 *
 */
public class Afgewerkt extends QuizStatus
{
    Afgewerkt(){ /* Singleton via Enumeration! */ }
    
    @Override
    public Statussen getType()
    { return Statussen.Afgewerkt; }
    
    @Override
    public void geefLaatsteKans(Quiz q)
    {
        q.setStatus(Statussen.LaatsteKans.Instance());
    }
        
    @Override
    public void sluitQuizAf(Quiz q)
    {
        q.setStatus(Statussen.Afgesloten.Instance());
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

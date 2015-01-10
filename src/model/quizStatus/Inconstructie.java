package model.quizStatus;

import model.Quiz;
import model.QuizOpdracht;
/***
 * 
 * @Revisioned bloemevi on 10/01/2015
 *
 */
public class Inconstructie extends QuizStatus
{
    Inconstructie(){ /* Singleton via Enumeration! */ }
    
    @Override
    public Statussen getType()
    { return Statussen.InConstructie; }
            
    @Override
    public void voegQuizOpdrachtToe(QuizOpdracht q)
    {
        q.getQuiz().getQuizOpdrachten().add(q);
        q.getQuiz().setStatus(Statussen.InConstructie.Instance());

    }

    @Override
    public void verwijderQuizOpdracht(QuizOpdracht q)
    {
        q.getQuiz().getQuizOpdrachten().remove(q);
        q.getQuiz().setStatus(Statussen.InConstructie.Instance());
    }

    @Override
    public void stelQuizOpen(Quiz q)
    {
        q.setStatus(Statussen.Opengesteld.Instance());
    }

    @Override
    public boolean magOpdrachtWijzigen()
    {
        return true;
    }

    @Override
    public boolean magQuizWijzigen()
    {
        return true;
    }

    @Override
    public boolean magQuizVerwijderen()
    {
        return true;
    }

    @Override
    public String toString()
    {
        return "In constructie";
    }
    
}

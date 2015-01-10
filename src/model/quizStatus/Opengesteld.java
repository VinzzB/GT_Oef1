package model.quizStatus;

import model.Quiz;
/***
 * 
 * @Revisioned bloemevi on 10/01/2015
 *
 */
public class Opengesteld extends QuizStatus
{
    Opengesteld(){ /* Singleton via enumeration */ }
    
    @Override
    public Statussen getEnumType()
    { return Statussen.Opengesteld; }
    
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
    public boolean kanDeelnemen()
    { 
        return true;
    }

    @Override
    public String toString()
    {
        return "Opengesteld";
    }
}

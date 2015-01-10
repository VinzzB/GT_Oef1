package model.quizStatus;

import model.Quiz;
/***
 * 
 * @Revisioned bloemevi on 10/01/2015
 *
 */
public class LaatsteKans extends QuizStatus
{
    LaatsteKans(){/* Singleton via Enumeration! */ }

    @Override
    public Statussen getEnumType()
    { return Statussen.LaatsteKans; }
    
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
        return "Laatste Kans";
    }

}

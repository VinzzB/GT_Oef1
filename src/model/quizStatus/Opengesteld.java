package model.quizStatus;

import model.Quiz;

public class Opengesteld extends QuizStatus
{
    private static Opengesteld statusOpengesteld = new Opengesteld();
    private Opengesteld(){}
    public static Opengesteld instance()
    {
        return statusOpengesteld;
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

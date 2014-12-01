package model.quizStatus;

import model.Quiz;

public class LaatsteKans extends QuizStatus
{
    private static LaatsteKans statusLaatsteKans = new LaatsteKans();
    private LaatsteKans(){}
    public static LaatsteKans instance()
    {
        return statusLaatsteKans;
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
        return "Laatste Kans";
    }

}

package model.quizStatus;

import model.Quiz;
import model.QuizOpdracht;

public class Inconstructie extends QuizStatus
{
    private static final Inconstructie statusInconstructie = new Inconstructie();
    private Inconstructie(){}
    public static Inconstructie instance()
    {
        return statusInconstructie;
    }
            
    @Override
    public void voegQuizOpdrachtToe(QuizOpdracht q)
    {
        q.getQuiz().getQuizOpdrachten().add(q);
        q.getQuiz().setStatus(Inconstructie.instance());

    }

    @Override
    public void verwijderQuizOpdracht(QuizOpdracht q)
    {
        q.getQuiz().getQuizOpdrachten().remove(q);
        q.getQuiz().setStatus(Inconstructie.instance());
    }

    @Override
    public void stelQuizOpen(Quiz q)
    {
        q.setStatus(Opengesteld.instance());
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

package model.quizStatus;

import java.util.Objects;
import model.Quiz;
import model.QuizOpdracht;

public class Inconstructie extends QuizStatus
{
    private Quiz quiz;

    public Inconstructie(Quiz q)
    {
        this.quiz = q;
    }

    public Inconstructie()
    {
        quiz = null;
    }

    @Override
    public void voegQuizOpdrachtToe(QuizOpdracht q)
    {
        quiz.getQuizOpdrachten().add(q);
        quiz.setStatus(this);

    }

    @Override
    public void verwijderQuizOpdracht(QuizOpdracht q)
    {
        quiz.getQuizOpdrachten().remove(q);
        quiz.setStatus(this);
    }

    @Override
    public void stelQuizOpen()
    {
        quiz.setStatus(new Opengesteld(quiz));
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
    public int hashCode()
    {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.quiz);
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final Inconstructie other = (Inconstructie) obj;
        return Objects.equals(this.quiz, other.quiz);
    }
        
    @Override
    public String toString()
    {
        return "In constructie";
    }
    
    @Override
    public int compareTo(Object o)
    {
        if (o == null)
        {
            throw new NullPointerException("Object is null");
        }

        if (this.equals(o) == true)
        {
            return 0;
        }
        if (this.hashCode() > o.hashCode())
        {
            return 1;
        } else
        {
            return -1;
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }
}

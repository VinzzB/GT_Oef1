package model.quizStatus;

import java.util.Objects;
import model.Quiz;

public class Afgewerkt extends QuizStatus
{
    private Quiz quiz;

    public Afgewerkt(Quiz q)
    {
            this.quiz = q;
    }

    public Afgewerkt()
    {
        quiz = null;
    }
    
    @Override
    public void geefLaatsteKans()
    {
        quiz.setStatus(new LaatsteKans(quiz));
    }
        
    @Override
    public void sluitQuizAf()
    {
            quiz.setStatus(new Afgesloten(quiz));
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
    public int hashCode()
    {
        int hash = 3;
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
        final Afgewerkt other = (Afgewerkt) obj;
        return Objects.equals(this.quiz, other.quiz);
    }
    
    @Override
    public String toString()
    {
        return "Afgewerkt";
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

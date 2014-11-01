package model;
import utils.date.normal.*;
/**
 *
 * @author Silvia
 */
public class QuizDeelname
{
 private Leerling owner;
 private OpdrachtAntwoord opdrachtAntwoord;
 private Datum datumDeelname;
 private Quiz quiz;

    public QuizDeelname()
    {
        this.owner = new Leerling();
        this.opdrachtAntwoord = new OpdrachtAntwoord();
        this.datumDeelname = new Datum();
        this.quiz = new Quiz();
    }     
    public QuizDeelname(Leerling owner, OpdrachtAntwoord opdrachtAntwoord, Datum datumDeelname, Quiz quiz)
    {
        this.owner = owner;
        this.opdrachtAntwoord = opdrachtAntwoord;
        this.datumDeelname = datumDeelname;
        this.quiz = quiz;
    }

    public void setOwner(Leerling newOwner)
    {
        Leerling old = owner;
        owner = newOwner;
        if (newOwner != null)
        {
            newOwner.addQuizDeelname(this);
        }
        if (old != null)
        {
            old.removeQuizDeelname(this);
        }
    }

    public OpdrachtAntwoord getOpdrachtAntwoord()
    {
        return opdrachtAntwoord;
    }

    public void setOpdrachtAntwoord(OpdrachtAntwoord opdrachtAntwoord)
    {
        this.opdrachtAntwoord = opdrachtAntwoord;
    }

    public Datum getDatumDeelname()
    {
        return datumDeelname;
    }

    public void setDatumDeelname(Datum datumDeelname)
    {
        this.datumDeelname = datumDeelname;
    }

    public Quiz getQuiz()
    {
        return quiz;
    }

    public void setQuiz(Quiz quiz)
    {
        this.quiz = quiz;
    }
}

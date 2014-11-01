package model;
import java.util.HashSet;
import java.util.Set;
import utils.date.normal.Datum;
/**
 * Klasse Quizdeelname: Een quizdeelname is gelinkt aan 1 leerling en bevat 1 of 
 * meerdere opdrachtantwoorden en is ook gelinkt aan 1 quiz en bevat de datum van
 * deelname.
 * @author Silvia
 */
public class QuizDeelname
{
 private Leerling ownerLeerling;
 private Set opdrachtAntwoorden;
 private Datum datumDeelname;
 private Quiz ownerQuiz;

    /**
     * Linkt de leerling aan de quizdeelname
     * @param newOwner Leerling die bij deze quizdeelname hoort
     */
    public void setOwnerLeerling (Leerling newOwner)
    {
        if(ownerLeerling != newOwner)
        {
            Leerling old = ownerLeerling;
            ownerLeerling = newOwner;
            if (newOwner != null)
            {
                newOwner.addQuizDeelname(this);
            }
            if (old != null)
            {
                old.removeQuizDeelname(this);
            }
        }
    }

    /**
     * 
     * @return Set met opdrachtantwoorden
     */
    public Set getOpdrachtAntwoorden()
    {
        return opdrachtAntwoorden;
    }

    /**
     * 
     * @return Datum deelnamedatum
     */
    public Datum getDatumDeelname()
    {
        return datumDeelname;
    }

    /**
     * 
     * @param datumDeelname Datum
     */
    public void setDatumDeelname(Datum datumDeelname)
    {
        this.datumDeelname = datumDeelname;
    }

    /**
     * 
     * @return Quiz
     */
    public Quiz getQuiz()
    {
        return ownerQuiz;
    }

    /**
     * Linkt de Quiz aan de quizdeelname
     * @param newOwner Quiz waaraan de quizdeelname moet gelinkt worden
     */
    public void setOwnerQuiz(Quiz newOwner)
    {
        if(ownerQuiz != newOwner)
        {
            Quiz old = ownerQuiz;
            ownerQuiz = newOwner;
            if (newOwner != null)
            {
                newOwner.addQuizDeelname(this);
            }
            if (old != null)
            {
                old.removeQuizDeelname(this);
            }
        }
    }
    
    /**
     * Default Constructor
     */
    public QuizDeelname()
    {
        this.opdrachtAntwoorden = new HashSet();
        this.datumDeelname = new Datum();
        this.ownerQuiz = new Quiz();
    }     
    /**
     * Constructor met 4 parameters
     * @param newOwnerLeerling Leerling waaraan de quizdeelname zal gelinkt worden
     * @param opdrachtAntwoord Set met 1 of meer opdrachtantwoorden
     * @param datumDeelname Datum van deelname
     * @param newOwnerQuiz Quiz waaraan de quizdeelname zal gelinkt worden
     */
    public QuizDeelname(Leerling newOwnerLeerling, Set opdrachtAntwoord, Datum datumDeelname, Quiz newOwnerQuiz)
    {
        this.ownerLeerling = newOwnerLeerling;
        this.opdrachtAntwoorden = opdrachtAntwoord;
        this.datumDeelname = datumDeelname;
        this.ownerQuiz = newOwnerQuiz;
    }

    /**
     * Voegt een opdrachtantwoord toe aan de Set met opdrachtantwoorden 
     * en linkt deze met de quizdeelname
     * @param opdrachtAntwoord 
     */
    public void addOpdrachtAntwoord(OpdrachtAntwoord opdrachtAntwoord)
    {
        this.opdrachtAntwoorden.add(opdrachtAntwoord);
        opdrachtAntwoord.setOwnerQuizDeelname(this);
    }

    /**
     * Verwijdert een opdrachtantwoord van de Set met opdrachtantwoorden
     * en verwijdert de link met de quizdeelname
     * @param opdrAntw 
     */
    public void removeOpdrachtAntwoord(OpdrachtAntwoord opdrAntw)
    {
        this.opdrachtAntwoorden.remove(opdrAntw);
        opdrAntw.setOwnerQuizDeelname(null);
    }
    
    
}

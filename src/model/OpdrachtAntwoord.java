package model;

import java.sql.Time;

/**
 * Klasse OpdrachtAntwoord: bestaat uit antwoordtijd, laatsteantwoord en aantal 
 * pogingen. Een opdrachtantwoord is gleinkt aan 1 quizdeelname en aan 1 
 * quizopdracht.
 * @author Silvia
 */
public class OpdrachtAntwoord
{
    private QuizDeelname ownerQuizDeelname;
    private QuizOpdracht ownerQuizOpdracht;
    private Time antwoordTijd;
    private String laatsteAntwoord;
    private int aantalPogingen;
    
    

    /**
     * QuizDeelname waaraan het opdrachtantwoord gelinkt is
     *
     * @return the value of ownerQuizDeelname
     */
    public QuizDeelname getOwner()
    {
        return ownerQuizDeelname;
    }

    /**
     * Linkt het opdrachtantwoord aan de QuizDeelname
     *
     * @param newOwner Quizdeelname
     */
    public void setOwnerQuizDeelname(QuizDeelname newOwner)
    {
        if (ownerQuizDeelname != newOwner){
            QuizDeelname old = ownerQuizDeelname;
            ownerQuizDeelname = newOwner;
            if (newOwner != null){
                newOwner.addOpdrachtAntwoord(this);
            }
            if (old != null){
                old.removeOpdrachtAntwoord(this);
            }
        }
    }

    /**
     * Linkt opdrachtantwoord aan de QuizOpdracht
     * 
     * @param newOwner QuizOpdracht
     */
    public void setOwnerQuizOpdracht(QuizOpdracht newOwner)
    {
        if(ownerQuizOpdracht != newOwner){
            QuizOpdracht old = ownerQuizOpdracht;
            ownerQuizOpdracht = newOwner;
            if(newOwner != null){
                newOwner.addOpdrachtAntwoord(this);
            }
            if (old != null){
                old.removeOpdrachtAntwoord(this);
            }
        }
    }

    /**
     * Geeft de antwoordTijd
     *
     * @return Time 
     */
    public Time getAntwoordTijd()
    {
        return antwoordTijd;
    }

    /**
     * 
     *
     * @param antwoordTijd Time
     */
    public void setAntwoordTijd(Time antwoordTijd)
    {
        this.antwoordTijd = antwoordTijd;
    }

    /**
     * Get the value of aantalPogingen
     *
     * @return the value of aantalPogingen
     */
    public int getAantalPogingen()
    {
        return aantalPogingen;
    }

    /**
     * Set the value of aantalPogingen
     *
     * @param aantalPogingen new value of aantalPogingen
     */
    public void setAantalPogingen(int aantalPogingen)
    {
        this.aantalPogingen = aantalPogingen;
    }

    /**
     * Get the value of laatsteAntwoord
     *
     * @return the value of laatsteAntwoord
     */
    public String getLaatsteAntwoord()
    {
        return laatsteAntwoord;
    }

    /**
     * Set the value of laatsteAntwoord
     *
     * @param laatsteAntwoord new value of laatsteAntwoord
     */
    public void setLaatsteAntwoord(String laatsteAntwoord)
    {
        this.laatsteAntwoord = laatsteAntwoord;
    }

}

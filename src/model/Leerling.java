package model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author Silvia
 */
public class Leerling implements Comparable, Cloneable
{
private String leerlingNaam;
private int leerjaar;
private LeerlingContainer owner;
private Set quizDeelnames;

    public Leerling()
    {
        this.leerlingNaam = "";
        this.leerjaar = 0;
        this.quizDeelnames = new HashSet();
    }

    public Leerling(String leerlingNaam, int leerjaar)
    {
        this.leerlingNaam = leerlingNaam;
        this.leerjaar = leerjaar;
        this.quizDeelnames = new HashSet();
    }


/**
 * @param   o the object to be compared.
 * @return  a negative integer, zero, or a positive integer as this object
 *          is less than, equal to, or greater than the specified object.
 *
 * @throws NullPointerException if the specified object is null
 * @throws ClassCastException if the specified object's type prevents it
 *         from being compared to this object.
 */
    @Override
    public int compareTo(Object o)
    {
        if (o == null)
        {
            throw new NullPointerException("Object is null");
        }
        
        if (this.equals(o)== true)
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
    public String toString()
    {
        return "Leerling: " + this.leerlingNaam + " Leerjaar: " + this.leerjaar; 
    }

    @Override
    protected Object clone() throws CloneNotSupportedException
    {
        return (Leerling)super.clone(); 
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.leerlingNaam);
        hash = 53 * hash + this.leerjaar;
        hash = 53 * hash + Objects.hashCode(this.owner);
        hash = 53 * hash + Objects.hashCode(this.quizDeelnames);
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
        final Leerling other = (Leerling) obj;
        if (!Objects.equals(this.leerlingNaam, other.leerlingNaam))
        {
            return false;
        }
        if (this.leerjaar != other.leerjaar)
        {
            return false;
        }
        if (!Objects.equals(this.owner, other.owner))
        {
            return false;
        }
        return Objects.equals(this.quizDeelnames, other.quizDeelnames);
    }
    
    

    

    public void setLeerlingNaam(String leerlingNaam)
    {
        this.leerlingNaam = leerlingNaam;
    }

    public void setLeerjaar(int leerjaar)
    {
        if (leerjaar < 0 || leerjaar > 6)
        {
            throw new IllegalArgumentException("Deze quiz is bedoeld voor leerjaar 1 tot en met 6");
        }
        else
        {
        this.leerjaar = leerjaar;
        }
    }

    public String getLeerlingNaam()
    {
        return leerlingNaam;
    }

    public int getLeerjaar()
    {
        return leerjaar;
    }

    public void setQuizDeelnames(Set quizDeelnames)
    {
        this.quizDeelnames = quizDeelnames;
    }

    public Set getQuizDeelnames()
    {
        return quizDeelnames;
    }
    
    public void addQuizDeelname(QuizDeelname q)
    {
        quizDeelnames.add(q);
        q.setOwner(this);
    }
    public void removeQuizDeelname(QuizDeelname q)
    {
        quizDeelnames.remove(q);
        q.setOwner(null);
    }
    public void setOwner(LeerlingContainer newOwner)
    {
        if (owner != newOwner)
        {
            LeerlingContainer oldOwner = owner;
            owner = newOwner;
            if (newOwner != null)
            {
                newOwner.addLeerling(this);
            }
            if (oldOwner != null)
            {
                oldOwner.removeLeerling(this);
            }
        }
    }
}

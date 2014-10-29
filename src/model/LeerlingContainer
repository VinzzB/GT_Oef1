package model;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author Silvia
 */
public class LeerlingContainer
{
    private List<Leerling> leerlingContainer;

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.leerlingContainer);
        return hash;
    }

    @Override
    public String toString()
    {
        return "LeerlingContainer{" + "leerlingContainer=" + leerlingContainer + '}';
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
        final LeerlingContainer other = (LeerlingContainer) obj;
        return Objects.equals(this.leerlingContainer, other.leerlingContainer);
    }

    public LeerlingContainer(List<Leerling> leerlingContainer)
    {
        this.leerlingContainer = leerlingContainer;
    }
    public LeerlingContainer()
    {
        this.leerlingContainer = null;
    }

    public void setLeerlingContainer(List<Leerling> leerlingContainer)
    {
        this.leerlingContainer = leerlingContainer;
    }

    public List<Leerling> getLeerlingContainer()
    {
        return leerlingContainer;
    }

    void addLeerling(Leerling l) throws IllegalArgumentException
    {
        if(l == null)
        {
            throw new IllegalArgumentException("Leerling is null");
        }
        if (leerlingContainer != null)
        {
        leerlingContainer.add(l);
        }
    }

    void removeLeerling(Leerling l) throws IllegalArgumentException
    {
       if(l == null)
        {
            throw new IllegalArgumentException("Leerling is null");
        }
       if (leerlingContainer != null)
       {
        leerlingContainer.remove(l);
       }
    }
}

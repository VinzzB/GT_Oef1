package model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/**
 * Klasse die alle leerlingen bevat. De LeerlingContainer kan enkel geraadpleegd
 * worden door andere klassen. De Container wordt gevuld bij het begin van de
 * quizzApp met gegevens uit een file? of database?
 * @author Silvia
 */
final public class LeerlingContainer implements Iterable
{
    private static Map<Integer, Leerling> leerlingContainer = new HashMap<>();

    /**
     * Constructor verwacht een Map object met een Integer als sleutel en
     * Leerlingobjecten als value
     * @param leerlingC 
     */
    public LeerlingContainer(Map<Integer, Leerling> leerlingC)
    {
        leerlingContainer = leerlingC;
    }

    public static Map<Integer, Leerling> getLeerlingContainer()
    {
        return leerlingContainer;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.leerlingContainer);
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
        final LeerlingContainer other = (LeerlingContainer) obj;
        return Objects.equals(this.leerlingContainer, other.leerlingContainer);
    }

    @Override
    public String toString()
    {
        return "LeerlingContainer{" + "leerlingContainer=" + leerlingContainer + '}';
    }


    @Override
    public Iterator iterator()
    {
        Iterator leerlingcontainer = leerlingContainer.entrySet().iterator();
        return leerlingcontainer;
    }
}

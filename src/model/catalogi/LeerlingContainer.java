package model.catalogi;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import model.Leerling;

/**
 * Klasse die alle leerlingen bevat. De LeerlingContainer kan enkel geraadpleegd worden door andere klassen. De
 * Container wordt gevuld bij het begin van de quizzApp met gegevens uit een file? of database?
 *
 * @author Silvia
 */
public class LeerlingContainer implements Iterable<Leerling>
{
	private Map<Integer, Leerling> leerlingContainer;

	public LeerlingContainer()
        {
            leerlingContainer = new HashMap<>();
        }
        /**
	 * Constructor verwacht een Map object met een Integer als sleutel en Leerlingobjecten als value
	 *
	 * @param leerlingC
	 */
	public LeerlingContainer(Map<Integer, Leerling> leerlingC)
	{
            this();
            leerlingContainer = leerlingC;
	}

	public Map<Integer, Leerling> getLeerlingContainer()
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
	public Iterator<Leerling> iterator()
	{
		Iterator<Leerling> leerlingcontainer = leerlingContainer.values().iterator();
		return leerlingcontainer;
	}

	/**
	 * voeg leerling toe aan map
         * @param l
	 */
	public void voegLeerlingToe(Leerling l)
	{
            if(l != null)
            {
                leerlingContainer.put(getMaxKey(), l);
            }
            else
            {
                throw new IllegalArgumentException("Leerling is nog niet aangemaakt");
            }
	}

	/**
	 * method om grootste key te bepalen
	 *
	 * @return
	 */
	private int getMaxKey()
	{
		int maxkey = 1;
		Set<Integer> keySet = leerlingContainer.keySet();
		for (int k : keySet)
		{
			if (k > maxkey)
			{
				maxkey = k;
			}
		}
		return maxkey;
	}
}

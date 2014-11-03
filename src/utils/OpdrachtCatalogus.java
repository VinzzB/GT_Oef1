package utils;

import java.util.HashSet;
import java.util.Set;

public class OpdrachtCatalogus {
	//deep cloning
	
	private Set<Opdracht> opdrachten;
	
	public OpdrachtCatalogus()
	{
		opdrachten = new HashSet<Opdracht>();
	}
	
	public void addOpdracht(String vraag, String juisteAntwoord, int maxAantalPogingen, String antwoordHints, int maxAntwoordTijdinSec)
	{
		this.opdrachten.add(new Opdracht(vraag,juisteAntwoord,maxAantalPogingen,antwoordHints,maxAntwoordTijdinSec));
	}
	
	public void removeOpdracht(Opdracht o) throws Exception
	{
		//enkel mogelijk als nog niet gekoppeld aan quiz
		if (o.getGekoppeldAanQuiz() == false)
		{
			this.opdrachten.remove(o);
		}
		else throw new Exception("Opdracht kan niet verwijderd worden, want is reeds gelinkt aan een quiz.");
	}

}

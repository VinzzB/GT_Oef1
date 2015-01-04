package model.scoreStrategy;
/**
 * Klasse om flexibele rekenregels voor het berekenen 
 * van de quiz score te implemeteren.
 * 
 *  De score is onder beïnvloed door de antwoordtijd van de deelnemer
 *  
 *  @author Natalia Dyubankova
 */
import src.model.opdracht.OpdrachtAntwoord;

public class TijdStrategy implements IScoreStrategy
{
	private static TijdStrategy instance;
	
	private TijdStrategy(){}
	
	public static TijdStrategy getInstance()
	{
		if(instance == null)
		{
			instance = new TijdStrategy();
		}
		return instance;
	}

	@Override
	public int scoreBerekenen(OpdrachtAntwoord opdrachtAntwoord)
	{
		return (opdrachtAntwoord.getAntwoordTijd()/opdrachtAntwoord.getQuizOpdracht().getOpdracht().getMaxAntwoordTijdInSec())*opdrachtAntwoord.getQuizOpdracht().getMaxScore();

	}

}

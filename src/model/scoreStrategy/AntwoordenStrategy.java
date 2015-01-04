package model.scoreStrategy;

/**
 * Klasse om flexibele rekenregels voor het berekenen 
 * van de quiz score te implemeteren.
 * 
 *  De score is onder beïnvloed door de antwoordpogingen en antwoordtijd
 *  
 *  @author Natalia Dyubankova
 */
import src.model.opdracht.OpdrachtAntwoord;

public class AntwoordenStrategy implements IScoreStrategy
{
	private static AntwoordenStrategy instance;
	
	private AntwoordenStrategy(){}
	
	public static AntwoordenStrategy getInstance()
	{
		if(instance == null)
		{
			instance = new AntwoordenStrategy();
		}
		return instance;
	}

	@Override
	public int scoreBerekenen(OpdrachtAntwoord opdrachtAntwoord)
	{
		return (opdrachtAntwoord.getAantalPogingen()*opdrachtAntwoord.getAntwoordTijd()/
				(opdrachtAntwoord.getQuizOpdracht().getOpdracht().getMaxAantalPogingen()*
				opdrachtAntwoord.getQuizOpdracht().getOpdracht().getMaxAntwoordTijdInSec()))
				*opdrachtAntwoord.getQuizOpdracht().getMaxScore();
	}

}

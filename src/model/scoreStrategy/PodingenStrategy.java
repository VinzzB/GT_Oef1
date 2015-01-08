package model.scoreStrategy;

/**
 * Klasse om flexibele rekenregels voor het berekenen 
 * van de quiz score te implemeteren.
 * 
 *  De score is onder beïnvloed door de antwoordpogingen van de deelnemer
 *  
 *  @author Natalia Dyubankova
 */

import model.OpdrachtAntwoord;

public class PodingenStrategy implements IScoreStrategy
{
	private static PodingenStrategy instance;
	
	private PodingenStrategy(){}
	
	public static PodingenStrategy getInstance()
	{
		if(instance == null)
		{
			instance = new PodingenStrategy();
		}
		return instance;
	}
	
	@Override
	public float scoreBerekenen(OpdrachtAntwoord opdrachtAntwoord)
	{
		return (opdrachtAntwoord.getAantalPogingen()/opdrachtAntwoord.getQuizOpdracht().getOpdracht().getMaxAantalPogingen())*opdrachtAntwoord.getQuizOpdracht().getMaxScore();
	}

}

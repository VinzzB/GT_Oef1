package model.scoreStrategy;

import src.model.opdracht.OpdrachtAntwoord;

/**
 * Factory klasse om de gewenste implementatie van de strategy te initialiseren
 * 
 * @author Natalia
 *
 */
public class QuizScoreRegelsFactory
{
	
	public static IScoreStrategy getScoreStrategy(OpdrachtAntwoord opdrachtAntwoord)
	{
		if(opdrachtAntwoord.getAantalPogingen()/opdrachtAntwoord.getQuizOpdracht().getOpdracht().getMaxAantalPogingen() >
		opdrachtAntwoord.getAntwoordTijd()/opdrachtAntwoord.getQuizOpdracht().getOpdracht().getMaxAntwoordTijdInSec())
		{
			return PodingenStrategy.getInstance();
		}
		else if (opdrachtAntwoord.getAantalPogingen()/opdrachtAntwoord.getQuizOpdracht().getOpdracht().getMaxAantalPogingen() <
		opdrachtAntwoord.getAntwoordTijd()/opdrachtAntwoord.getQuizOpdracht().getOpdracht().getMaxAntwoordTijdInSec())
		{
			return TijdStrategy.getInstance();
		}
		else
		{
			return AntwoordenStrategy.getInstance();
		}
	}
}

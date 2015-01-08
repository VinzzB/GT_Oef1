package model.scoreStrategy;

import model.OpdrachtAntwoord;

/**
 * Factory klasse om de gewenste implementatie van de strategy te initialiseren
 * strategy naam kunt ingeladen worden van property-file of berekend afhankelik 
 * van properties van gegeven OpdrachtAntwoord-object
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
	
	public static IScoreStrategy getScoreStrategy(String scoreStrategy)
	{
		switch(scoreStrategy)
		{
		case "GroupT":
			return GroupTStrategy.getInstance();
			default:
				return null;
		}
	}
}

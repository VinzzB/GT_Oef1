package model.scoreStrategy;
/**
 * Klasse om flexibele rekenregels voor het berekenen 
 * van de quiz score te implemeteren. 
 * De klasse volgt deze businessregel voor het berekenen van de score 
 * van een deelnemer aan een quiz: 
 * 
 * Indien een deelnemer het juiste antwoord geeft op een opdracht in een eerste 
 * antwoordpoging verdient hij de maximum score op die opdracht, indien hij het 
 * juiste antwoord geeft in een (eventuele) vervolgpoging verdient hij de helft 
 * van de maximum score op die opdracht. Indien hij geen antwoord geeft of de 
 * (eventuele) antwoordtijd is verstreken krijgt hij geen punten op die opdracht. 
 * De scores van alle opdrachten van de quiz worden opgeteld en herleid naar 10 
 * (afgerond op 0 cijfers na de komma)
 *  
 *  @author Natalia Dyubankova
 */
import model.OpdrachtAntwoord;

public class GroupTStrategy implements IScoreStrategy
{
	private static GroupTStrategy instance;
	
	private GroupTStrategy(){}
	
	public static GroupTStrategy getInstance()
	{
		if(instance == null)
		{
			instance = new GroupTStrategy();
		}
		return instance;
	}

	@Override
	public float scoreBerekenen(OpdrachtAntwoord opdrachtAntwoord)
	{
		int maxTime = opdrachtAntwoord.getQuizOpdracht().getOpdracht().getMaxAntwoordTijdInSec();
		
		if (opdrachtAntwoord.getQuizOpdracht().getOpdracht().isJuisteAntwoord(opdrachtAntwoord.getLaatsteAntwoord()) 
			&& (maxTime == 0 || opdrachtAntwoord.getAntwoordTijd() <= maxTime))
		{			
			if (opdrachtAntwoord.getAantalPogingen() == 1)
				return opdrachtAntwoord.getQuizOpdracht().getMaxScore() ; 
			if (opdrachtAntwoord.getAantalPogingen() > 1)
				return opdrachtAntwoord.getQuizOpdracht().getMaxScore() / 2;			
		}
		return 0;
	}

}

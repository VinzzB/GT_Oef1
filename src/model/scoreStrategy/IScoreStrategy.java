package model.scoreStrategy;

import src.model.opdracht.OpdrachtAntwoord;

public interface IScoreStrategy
{
	int scoreBerekenen(OpdrachtAntwoord opdrachtAntwoord);
}

package model.scoreStrategy;

import model.OpdrachtAntwoord;

public interface IScoreStrategy
{
	float scoreBerekenen(OpdrachtAntwoord opdrachtAntwoord);
}

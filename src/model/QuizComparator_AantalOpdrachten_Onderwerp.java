package model;

import java.util.Comparator;

public class QuizComparator_AantalOpdrachten_Onderwerp implements Comparator<Quiz>
{
	@Override
	public int compare(Quiz quiz1, Quiz quiz2) 
	{
		int terug = quiz2.getOpdrachten().size() - quiz1.getOpdrachten().size();
		if (terug == 0)
		{
			terug = quiz1.getOnderwerp().compareTo(quiz2.getOnderwerp());
		}
		return terug;
	}
}

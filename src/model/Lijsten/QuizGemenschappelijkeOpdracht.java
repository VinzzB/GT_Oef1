package model.Lijsten;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import controller.OpstartController;
import model.Quiz;
import model.QuizCatalogus;
import model.Opdracht;
import persistency.Catalogi;

public class QuizGemenschappelijkeOpdracht implements ILijst
{
	QuizCatalogus quizzen = Catalogi.get().getQuizzen();
	Quiz quiz;
	String output = "";
	
	public QuizGemenschappelijkeOpdracht(){	}
	
	public QuizGemenschappelijkeOpdracht(Quiz quiz)
	{
		this.quiz = quiz;
		
		HashSet <Opdracht> set1 = new HashSet<Opdracht>((Collection<? extends Opdracht>) quiz.getOpdrachten());
		HashSet <Opdracht> set2 = null;
		output += quiz.toString();
		output += set1.toString();
		output += quiz.getOnderwerp()+"\n\n";
		for (Quiz quiztmp : quizzen)
		{
			if (!quiztmp.equals(quiz))
			{
				set1 = new HashSet<Opdracht>((Collection<? extends Opdracht>) quiz.getOpdrachten());
				set2 = new HashSet<Opdracht>((Collection<? extends Opdracht>) quiztmp.getOpdrachten());
				set1.retainAll(set2);
				output += quiztmp.getOnderwerp()+"  "+set2.toString();
				output += set1.toString();
				if (set1.size() > 0)
				{
					output += quiztmp.getOnderwerp()+"\n"+ set1+"\n";
				}
			}
		}
		
	}

	@Override
	public String getLijst()
	{
		return output;
	}
}

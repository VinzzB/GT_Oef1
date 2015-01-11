package model.Lijsten;


import java.util.Collection;
import java.util.HashSet;
import java.util.Map.Entry;
import model.Quiz;
import model.Opdracht;
import model.catalogi.Catalogi;
import model.catalogi.QuizCatalogus;

public class QuizGemenschappelijkeOpdracht implements ILijst
{
	QuizCatalogus quizzen = Catalogi.getQuizzen();
	Quiz quiz;
	String output = "";
	
	public QuizGemenschappelijkeOpdracht(){	}
	
	@SuppressWarnings("unchecked")
	public QuizGemenschappelijkeOpdracht(Quiz quiz)
	{
		this.quiz = quiz;
		
		HashSet <Opdracht> set1 = new HashSet<Opdracht>((Collection<? extends Opdracht>) quiz.getOpdrachten());
		HashSet <Opdracht> set2 = null;
		output += quiz.toString();
		output += set1.toString();
		output += quiz.getOnderwerp()+"\n\n";
		for (Entry<Integer, Quiz> quiztmp : quizzen)
		{
			if (!quiztmp.getValue().equals(quiz))
			{
				set1 = new HashSet<Opdracht>((Collection<? extends Opdracht>) quiz.getOpdrachten());
				set2 = new HashSet<Opdracht>((Collection<? extends Opdracht>) quiztmp.getValue().getOpdrachten());
				set1.retainAll(set2);
				output += quiztmp.getValue().getOnderwerp()+"  "+set2.toString();
				output += set1.toString();
				if (set1.size() > 0)
				{
					output += quiztmp.getValue().getOnderwerp()+"\n"+ set1+"\n";
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

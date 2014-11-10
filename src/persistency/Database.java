package persistency;

import java.io.File;
import java.sql.Time;
import java.util.ArrayList;

import model.Opdracht;
import model.OpdrachtCatalogus;
import model.Quiz;
import model.QuizCatalogus;
import model.QuizOpdracht;

public abstract class Database implements IDatabaseStrategy
{
	protected File opdrachtenDB;
	protected File quizzenDB;
	protected File quizOpdrachtDB;
	protected OpdrachtCatalogus opdrachten; 
	protected QuizCatalogus quizzen;
	protected ArrayList<QuizOpdracht> quizOpdrachten;
	
	public Database() 
	{
		quizOpdrachten = new ArrayList<QuizOpdracht>();
		if (quizzen == null)	quizzen = new QuizCatalogus();
		if (opdrachten == null) opdrachten = new OpdrachtCatalogus();
	}
	
	@Override
	public void leesOpdrachten()
	{
		Object[] objecten = leesVanBestand(opdrachtenDB);
		for (Object object : objecten)
		{
			String[] s = (String[]) object;
			opdrachten.voegOpdrachtToe(new Opdracht(Integer.parseInt(s[0]), s[1], s[2], s[3],
					Integer.parseInt(s[4]), Time.valueOf(s[5])));
		}
	}

	@Override
	public void leesQuzen()
	{
		Object[] objecten = leesVanBestand(quizzenDB);
		for (Object object : objecten)
		{
			String[] s = (String[]) object;
			quizzen.voegQuizToe(new Quiz(Integer.parseInt(s[0]), s[1], Integer.parseInt(s[2]), 
					Boolean.parseBoolean(s[3]), Boolean.parseBoolean(s[4]),	s[5]));
		}
	}
	
	@Override
	public void leesQuizOpdachten()
	{
		Object[] objecten = leesVanBestand(quizOpdrachtDB);
		for (Object object : objecten)
		{
			String[] s = (String[]) object;
			for (Opdracht opdracht : opdrachten.getOpdrachten())
				if (Integer.parseInt(s[1]) == opdracht.getOpdrachtID())
				{
					quizOpdrachten.add(QuizOpdracht.getQuizOpdracht(Integer.parseInt(s[0]), Integer.parseInt(s[1]), Integer.parseInt(s[2])));

				}
		}
	}

	@Override
	public void kopelQuizOpdrachten()
	{
		for (QuizOpdracht quizOpdracht : quizOpdrachten)
		{
			QuizOpdracht.koppelOpdrachtAanQuiz(quizOpdracht.getQuiz(), quizOpdracht.getOpdracht(), quizOpdracht.getMaxScore());
		}
	}

	@Override
	public void safeOpdrachten()
	{
		int i = 0;
		String[] objecten = new String[opdrachten.getOpdrachten().size()];
		for (Opdracht opdracht : opdrachten.getOpdrachten())
		{
			objecten[i] = opdracht.toBestand();
			i ++;
		}
		schrijfNaarBestand(objecten, opdrachtenDB);
		
	}

	@Override
	public void safeQuizen()
	{
		int i = 0;
		String[] objecten = new String[quizzen.getQuizzen().size()];
		for (Quiz quiz : quizzen.getQuizzen())
		{
			objecten[i] = quiz.toBestand();
			i ++;
		}
		schrijfNaarBestand(objecten, quizzenDB);
	}

	@Override
	public void safeQuizOpdrachten()
	{
		ArrayList<String> objecten = new ArrayList<String>();
		for(Quiz quiz : quizzen.getQuizzen())
		{
			for(Opdracht opdracht : quiz.getOpdrachten())
			{
				objecten.add(opdracht.getOpdracht(1).toBestand());
			}
		}
		schrijfNaarBestand(objecten.toArray(new String[objecten.size()]), quizOpdrachtDB);
	}
	
	public void setCatalogus(OpdrachtCatalogus opdrahten, QuizCatalogus quizzen)
	{
		this.opdrachten = opdrahten;
		this.quizzen = quizzen;
	}
	
	abstract Object[] leesVanBestand(File file);
	abstract void schrijfNaarBestand(String[] objecten, File file);
	
}

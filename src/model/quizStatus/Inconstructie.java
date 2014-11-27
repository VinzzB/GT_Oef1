package model.quizStatus;

import model.Quiz;
import model.QuizOpdracht;

public class Inconstructie extends QuizStatus
{
	private Quiz quiz;

	public Inconstructie(Quiz q)
	{
		this.quiz = q;
	}

	@Override
	public void voegQuizOpdrachtToe(QuizOpdracht q)
	{
		quiz.getQuizOpdrachten().add(q);
		quiz.setStatus(quiz.getInconstructie());

	}

	@Override
	public void verwijderQuizOpdracht(QuizOpdracht q)
	{
		quiz.getQuizOpdrachten().remove(q);
	}

	@Override
	public void bewaarQuiz()
	{
		quiz.setStatus(quiz.getAfgewerkt());

	}

}

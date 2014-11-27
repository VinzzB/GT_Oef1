package model.quizStatus;

import model.Quiz;

public class Afgesloten extends QuizStatus
{
	@SuppressWarnings("unused")
	private Quiz quiz;

	public Afgesloten(Quiz q)
	{
		this.quiz = q;
	}
}

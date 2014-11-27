package model.quizStatus;

import model.Quiz;

public class Afgewerkt extends QuizStatus
{
	private Quiz quiz;

	public Afgewerkt(Quiz q)
	{
		this.quiz = q;
	}

	@Override
	public void stelQuizOpen()
	{
		quiz.setStatus(quiz.getOpengesteld());
	}

}

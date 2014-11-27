package model.quizStatus;

import model.Quiz;

public class Opengesteld extends QuizStatus
{
	private Quiz quiz;

	public Opengesteld(Quiz q)
	{
		this.quiz = q;
	}

	@Override
	public void geefLaatsteKans()
	{
		quiz.setStatus(quiz.getLaatsteKans());
	}

	@Override
	public void sluitQuizAf()
	{
		quiz.setStatus(quiz.getAfgesloten());
	}
}

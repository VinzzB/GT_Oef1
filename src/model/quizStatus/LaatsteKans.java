package model.quizStatus;

import model.Quiz;

public class LaatsteKans extends QuizStatus
{
	private Quiz quiz;

	public LaatsteKans(Quiz q)
	{
		this.quiz = q;
	}

	@Override
	public void sluitQuizAf()
	{
		quiz.setStatus(quiz.getAfgesloten());
	}

}

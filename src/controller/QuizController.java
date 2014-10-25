package controller;

import model.*;
public class QuizController {
	
	public static void main(String[] args) 
	{
		Opdracht opdracht1 = new Opdracht("Wat is de hoofdstad van Franrijk?","Parijs");
		Opdracht opdracht2 = new Opdracht("Wat is de hoofdstad van Spanje?","Madrid");
		Quiz quiz = new Quiz("Hoofdsteden Europa");
		QuizOpdracht.koppelOpdrachtAanQuiz(quiz, opdracht1, 2);
		QuizOpdracht.koppelOpdrachtAanQuiz(quiz, opdracht2, 2);
		System.out.println(quiz.getOpdrachten());
		QuizOpdracht quizOpdracht = quiz.getOpdracht(1);
		quizOpdracht.ontKoppelOpdrachtVanQuiz();
		System.out.println(quiz.getOpdrachten());
	}
}


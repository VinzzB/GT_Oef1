package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Leerling;
import model.QuizDeelname;
import view.QuizKiezenView;

public class QuizKiezenViewController extends Controller
{
	private Leerling leerling;
	private QuizKiezenView quizKiezenView;
	
	public QuizKiezenViewController() {	}
	
	public QuizKiezenViewController(Leerling leerling) 
	{
		this();
		this.leerling = leerling;
		quizKiezenView = new QuizKiezenView(this.leerling);
		addListeners();
	}

	private void addListeners()
	{
		this.quizKiezenView.addQuizSelectedListener(new QuizSelectedListeer());
		this.quizKiezenView.addBtnKiezListener(new BtnKiezListener());
		this.quizKiezenView.addBtnQuitListener(new BtnQuitListeer());
	}
	
	class QuizSelectedListeer implements ListSelectionListener{

		@Override
		public void valueChanged(ListSelectionEvent e)
		{
			quizKiezenView.setBtnKiez(true);
		}}
	class BtnKiezListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			QuizAfleggenViewController qavc = new QuizAfleggenViewController(new QuizDeelname(quizKiezenView.getSelectedQuiz(), leerling));
		}}
	class BtnQuitListeer implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			quizKiezenView.dispose();
		}}
}

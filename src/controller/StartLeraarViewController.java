package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import src.controller.StartViewController.BtnLeerlingListener;
import src.controller.StartViewController.BtnLeraarListener;
import src.controller.StartViewController.WindowClosedListener;
import src.view.StartLeraarView;

public class StartLeraarViewController extends Controller
{
	private StartLeraarView startLeraarView;
	
	public StartLeraarViewController()
	{
		startLeraarView = new StartLeraarView();
		addListeners();
	}
	
	private void addListeners()
	{
		this.startLeraarView.addBtnOpdrachten(new BtnOpdrachtenListener());
		this.startLeraarView.addBtnQuizzen(new BtnQuizzenListener());
		this.startLeraarView.addBtnScores(new BtnScoresListener());
		this.startLeraarView.addBtnInstellingen(new BtnInstellingenListener());
		this.startLeraarView.addBtnQuit(new BtnQuitListener());
	}
	class BtnOpdrachtenListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			OpdrachtenViewController ovc = new OpdrachtenViewController();			
		}}
	class BtnQuizzenListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			try
			{
				QuizzenViewController qvc = new QuizzenViewController("Overzicht Quizzen");
			} catch (Exception e1)
			{
				e1.printStackTrace();
			}			
		}}
	class BtnScoresListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			// TODO Auto-generated method stub
			
		}}
	class BtnInstellingenListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			// TODO Auto-generated method stub
			
		}}
	class BtnQuitListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			// TODO Auto-generated method stub
			
		}}
}

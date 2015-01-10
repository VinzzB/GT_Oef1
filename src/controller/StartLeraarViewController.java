package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.StartLeraarView;

public class StartLeraarViewController //extends Controller
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
		this.startLeraarView.addBtnScoresListener(new BtnScoresListener());
		this.startLeraarView.addBtnQuizLijsten(new BtnQuizLijstenListener());
		this.startLeraarView.addBtnInstellingen(new BtnInstellingenListener());
		this.startLeraarView.addBtnQuit(new BtnQuitListener());
	}
	class BtnOpdrachtenListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			OpdrachtenController ovc = new OpdrachtenController();			
		}}
	
	class BtnScoresListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			// TODO Auto-generated method stub
			
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
	class BtnQuizLijstenListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			QuizLijstenViewController rc = new QuizLijstenViewController();
			
		}}
	class BtnInstellingenListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			InstellingenViewController ivc = new InstellingenViewController();
		}}
	class BtnQuitListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			startLeraarView.dispose();
		}}
}

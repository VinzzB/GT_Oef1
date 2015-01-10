package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Leerling;
import view.StartLeerlingView;

public class StartLeerlingViewController //extends Controller
{
	private Leerling leerling;
	private StartLeerlingView startLeerlingView;
	
	public StartLeerlingViewController(){}
	
	public StartLeerlingViewController(Leerling leerling)
	{
		this.setLeerling(leerling);
		startLeerlingView = new StartLeerlingView();
		addListeners();
	}
	
	private void addListeners()
	{
		startLeerlingView.addBtnDeelnemenListener(new BtnDeelnemenListener());
		startLeerlingView.addBtnQuizRapportListener(new BtnQuizRapportListeener());
		startLeerlingView.addBtnInstellingenListener(new BtnInstelligenLitener());
		startLeerlingView.addBtnQuitListener(new BtnQuizListener());
	}

	public Leerling getLeerling()
	{
		return leerling;
	}

	public void setLeerling(Leerling leerling)
	{
		this.leerling = leerling;
	}
	
	class BtnDeelnemenListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			QuizKiezenViewController qlvc =  new QuizKiezenViewController(leerling);			
		}}
	
	class BtnQuizRapportListeener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			//TODO
		}}
	class BtnInstelligenLitener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			InstellingenViewController ivc = new InstellingenViewController();
		}}
	
	class BtnQuizListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			startLeerlingView.dispose();
		}}
	
	public static void main(String[] args) throws Exception
	{
		StartLeerlingViewController d = new StartLeerlingViewController();
	}
}

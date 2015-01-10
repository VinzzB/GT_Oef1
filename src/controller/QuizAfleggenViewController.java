package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.*;
import utils.tijdObservation.*;
import view.QuizAfleggenView;

public class QuizAfleggenViewController implements ITijdObserver
{
	private QuizDeelname quizDeelname;
	private OpdrachtAntwoord opdrachtAntwoord;
	private QuizOpdracht quizOpdracht;
	private Opdracht opdracht;
	private AntwoordStopwatch stopwatch;
	private int opdrachtNr = 1;
	
	private QuizAfleggenView quizAfleggenView;
	
	public QuizAfleggenViewController()	{}
	
	public QuizAfleggenViewController(QuizDeelname quizDeelname)
	{
		this.quizDeelname = quizDeelname;
		quizOpdracht = quizDeelname.getQuiz().getQuizOpdracht(this.opdrachtNr);
		this.opdrachtAntwoord = new OpdrachtAntwoord(quizDeelname, quizOpdracht);
		this.opdracht = quizOpdracht.getOpdracht();
		this.setView();
		this.addListeners();
		
	}
	
	public QuizAfleggenViewController(QuizDeelname quizDeelname, int opdrachtNr)
	{
		this.quizDeelname = quizDeelname;
		this.opdrachtNr = opdrachtNr;
		if(quizDeelname.getQuiz().getQuizOpdracht(opdrachtNr) != null)
		{
			this.quizOpdracht = quizDeelname.getQuiz().getQuizOpdracht(opdrachtNr);
			this.opdrachtAntwoord = new OpdrachtAntwoord(quizDeelname, quizOpdracht);
			this.opdracht = quizOpdracht.getOpdracht();
			this.setView();
		}
		else
		{
			JOptionPane.showMessageDialog(quizAfleggenView, "Einde van de quiz. Berekende score is " + quizDeelname.scoreBerekenen());
		// hier moet nog logica van registratie van deelnamen is database
		}
		this.addListeners();
	}
	
	private void setView()
	{
		this.quizAfleggenView = new QuizAfleggenView();
		this.stopwatch = new AntwoordStopwatch();
		this.stopwatch.addObserver(this);
		this.quizAfleggenView.setVraag(opdracht.getVraag());
		this.quizAfleggenView.setVraagNummer((quizDeelname.getQuiz().getOpdrachtIndex(opdracht)+1) + " / " + quizDeelname.getQuiz().getOpdrachten().size());
		this.quizAfleggenView.setPoging(opdrachtAntwoord.getAantalPogingen() + " / " + opdracht.getMaxAantalPogingen());
		this.quizAfleggenView.setTijd(stopwatch.getAntwoordTijd() + " / " + opdracht.getMaxAntwoordTijdInSec() );
	}
	
	private void addListeners()
	{
		this.quizAfleggenView.addBtnSubmitListener(new BtnSubmitListener());
		this.quizAfleggenView.addBtnVolgendeListener(new BtnVolgendelistener());
		this.quizAfleggenView.addBtnGetHintListener(new BtnGetHintListener());
		this.quizAfleggenView.addBtnQuitListener(new BtnQuitListener());
	}
	
	private void nextOprachtAntwoord()
	{
		new QuizAfleggenViewController(quizDeelname, ++opdrachtNr);
	}

	@Override
	public void handleTijdNotification(ITijdObservable tijdObservable)
	{
		int tijd = ((AntwoordStopwatch)tijdObservable).getAntwoordTijd();
		if(tijd < opdracht.getMaxAntwoordTijdInSec())
			this.quizAfleggenView.setTijd(tijd + " / " + opdracht.getMaxAntwoordTijdInSec() );
		else
		{
			((AntwoordStopwatch)tijdObservable).stop();
			((AntwoordStopwatch)tijdObservable).removeObserver(this);
			JOptionPane.showMessageDialog(quizAfleggenView, "Tijd is op!");
			if (this.opdrachtAntwoord.getOpdacht().isJuisteAntwoord(quizAfleggenView.getAntwoord()))
			{
				this.quizDeelname.addOpdrachtAntwoord(opdrachtAntwoord);
			}
			quizAfleggenView.dispose();
			nextOprachtAntwoord();
		}
	}
	
	class BtnSubmitListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			if (opdrachtAntwoord.getOpdacht().isJuisteAntwoord(quizAfleggenView.getAntwoord()))
			{
				quizDeelname.addOpdrachtAntwoord(opdrachtAntwoord);
				quizAfleggenView.dispose();
				nextOprachtAntwoord();
			}
			else
			{
				JOptionPane.showMessageDialog(quizAfleggenView, "Niet correct. Probeer opnieuw.");
				opdrachtAntwoord.setAantalPogingen(opdrachtAntwoord.getAantalPogingen() + 1);
				quizAfleggenView.setPoging(opdrachtAntwoord.getAantalPogingen() + " / " + opdracht.getMaxAantalPogingen());
			}
		}}
	class BtnVolgendelistener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			quizAfleggenView.dispose();
			nextOprachtAntwoord();
		}}
	class BtnGetHintListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			JOptionPane.showMessageDialog(quizAfleggenView, 
					opdracht.getAntwoordHint() == null ? "Geen Hint beschikbaar" : opdracht.getAntwoordHint());
			
		}}
	class BtnQuitListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			quizAfleggenView.dispose();
		}}
}

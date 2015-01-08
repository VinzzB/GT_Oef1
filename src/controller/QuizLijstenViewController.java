package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import model.Lijsten.DecoratorFactory;
import model.Lijsten.ILijst;
import model.Lijsten.LijstFactory;
import view.QuizLijstenView;

public class QuizLijstenViewController
{
	private QuizLijstenView rv;
	private ILijst rapport;
	
	public QuizLijstenViewController()
	{
		rv = new QuizLijstenView();
		addListeners();
	}
	
	private void addListeners()
	{
		rv.addCbxRapportenListener(new RapportSelectionListener());
		rv.addChbDatumDecorListener(new DatumDecorListener());
		rv.addChbAuteurDecorListener(new AuteurDecorListener());
	}
	
	class RapportSelectionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			rapport = LijstFactory.getRapport(rv.getCbxRapporten());
			rv.setTxtRapport(rapport.getLijst());
		}}
	
	class DatumDecorListener implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent e)
		{
			if(rapport != null)
			{
				if(rv.isChbDatumDecorSelected())
				{
					rapport = DecoratorFactory.getDecorator("PlusDatum", rapport);
					rv.setTxtRapport(rapport.getLijst());
				}
			}
		}}
	class AuteurDecorListener implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent e)
		{
			if(rapport != null)
			{
				if(rv.isChbAuteurDecorSelected())
				{
					rapport = DecoratorFactory.getDecorator("PlusAuteur", rapport);
					rv.setTxtRapport(rapport.getLijst());
				}
			}
		}}
}

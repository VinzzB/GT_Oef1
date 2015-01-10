package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Opdracht;
import model.OpdrachtCategorie;
import utils.date.gregorian.Datum;
import view.OpdrachtView;

/** @author      Nathalie Mathieu*/

public class OpdrachtController {
	
	private Opdracht model;
	private OpdrachtView view;
	
	public OpdrachtController(Opdracht model, OpdrachtView view)
	{
		this.model = model;
		this.view = view;
		
		this.view.getOpdrachtPanel().addAddButtonListener(new ListenerAddButton());
		this.view.getOpdrachtPanel().addDeleteButtonListener(new ListenerDeleteButton());
	}
	
	class ListenerAddButton implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {		
			int id = view.getOpdrachtPanel().getID();
			String vraag = view.getOpdrachtPanel().getVraag();
			String antwoord = view.getOpdrachtPanel().getAntwoord();
			String type = view.getOpdrachtPanel().getType();
			String auteur = view.getOpdrachtPanel().getAuteur();
			String registratie = view.getOpdrachtPanel().getRegistratieDatum();
			String hints = view.getOpdrachtPanel().getHint();
			int maxTijd = view.getOpdrachtPanel().getMaxTijd();
			int maxPogingen = view.getOpdrachtPanel().getMaxPogingen();
			String categorie = view.getOpdrachtPanel().getCategorie();
			
			try 
			{
				model.editOpdracht(vraag, antwoord, maxPogingen, maxTijd);
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			model.setOpdrachtID(id);
			model.setCategorie(OpdrachtCategorie.valueOf(categorie));
			model.setAntwoordHint(hints);
			model.setAuteur(auteur);
			Datum gregDate = new Datum(registratie);			
			model.setRegistratie(gregDate);
			//todo: type,
		}
		
		
	}
	
	class ListenerDeleteButton implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}}
	

}

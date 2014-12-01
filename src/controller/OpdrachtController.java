package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Opdracht;
import view.OpdrachtPanel;
import view.OpdrachtView;

public class OpdrachtController {
	
	private Opdracht model;
	private OpdrachtView view;
	
	public OpdrachtController(Opdracht model, OpdrachtView view)
	{
		this.model = model;
		this.view = view;
		
		this.view.getOpdrachtPanel().addAddButtonListener(new ListenerAddButton());
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
			//toevoegen: hints, maxtijd, maxpogingen, categorie
			
			model.setOpdrachtID(id);
			model.setVraag(vraag);
			model.setJuisteAntwoord(antwoord);
			//model.set hints, keuzen, maxtijd, maxpogingen,
			//model.setCategorie(categorie);
			
		}
		
		
	}
	

}

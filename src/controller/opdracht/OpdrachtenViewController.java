package controller.opdracht;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map.Entry;
import javax.swing.JOptionPane;


import model.Opdracht;
import model.catalogi.Catalogi;
import view.OpdrachtView;
import view.OpdrachtenView;
import view.View;


public class OpdrachtenViewController extends View
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6803408196520072554L;
	private OpdrachtenView opdrachtenView;
	
	public OpdrachtenViewController()
	{
		opdrachtenView = new OpdrachtenView();
		addListeners();
	}
	
	private void addListeners()
	{
		opdrachtenView.addBtnNieuweListener(new BtnNieuweListener());
		opdrachtenView.addBtnUpdateListener(new BtnUpdateListener());
		opdrachtenView.addBtnVerwijderListener(new BtnVerwijderListener());
		opdrachtenView.addWindowClosingListener(new WindowClosedListener());
	}
	
	public void updateOpdrachtenView()
	{
		//TODO
	//	this.opdrachtenView.updateOpdrachtenView();
	}
	
	class BtnNieuweListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			try
			{
				OpdrachtView view = new OpdrachtView();
				new OpdrachtController(null, view); // OpdrachtViewController("Maak nieuwe Opdracht", OpdrachtenViewController.this);
			} catch (Exception e1)
			{
				JOptionPane.showMessageDialog(null, e1.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
		}}
	
	class BtnUpdateListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			try
			{
				Opdracht o = opdrachtenView.getSelectedOpdracht(); //TODO: Opdracht reference niet correct in view...
				if(o != null)
				{
					OpdrachtView view = new OpdrachtView();
					new OpdrachtController(o, view);
					//OpdrachtViewController(opdrachtenView.getSelectedOpdracht());
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Selecteer een Quiz om up te daten", "alert", JOptionPane.ERROR_MESSAGE);
				}
			} catch (Exception e1)
			{
				JOptionPane.showMessageDialog(null, e1.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
			
		}}
	
	class BtnVerwijderListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			try
			{
				Catalogi.getOpdrachten().verwijderOpdracht(opdrachtenView.getSelectedOpdracht());
			} catch (Exception e1)
			{
				e1.printStackTrace();
			};
		}}
	
	class WindowClosedListener extends WindowAdapter
	{
		@Override
		public void windowClosing(WindowEvent e)
		{
			for(Entry<Integer, Opdracht> opdracht: Catalogi.getOpdrachten())
			{
				for(int i = 0; i < opdrachtenView.getTableModel().getRowCount(); i++)
				{
					if(opdracht.getValue().getOpdrachtID() == Integer.parseInt(opdrachtenView.getTableModel().getValueAt(i, 0).toString()))	
					{
						try
						{	Opdracht gewijzigd = opdracht.getValue().clone();
							gewijzigd.setVraag(opdrachtenView.getTableModel().getValueAt(i, 2).toString());
							gewijzigd.setMaxAantalPogingen(Integer.parseInt(opdrachtenView.getTableModel().getValueAt(i, 3).toString()));
							gewijzigd.setMaxAntwoordTijdinSec(Integer.parseInt(opdrachtenView.getTableModel().getValueAt(i, 4).toString()));
							//TODO
							//Catalogi.getOpdrachten().wijzigOpdracht(gewijzigd);
							Catalogi.getOpdrachten().verwijderOpdracht(opdracht.getValue());
							Catalogi.getOpdrachten().voegOpdrachtToe(gewijzigd);
						} catch (Exception e1)
						{
							e1.printStackTrace();
						};
					}
				}
			}
			opdrachtenView.getTableModel().fireTableDataChanged();
		}
	}
}

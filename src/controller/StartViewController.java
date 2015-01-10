package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import model.Leerling;
import view.StartView;


public class StartViewController extends Controller
{
	private StartView startView;
	
	public StartViewController()
	{
		startView = new StartView();
		addListeners();
	}
	
	private void addListeners()
	{
		this.startView.addWindowClosingListener(new WindowClosedListener());
		this.startView.addBtnLeraarListener(new BtnLeraarListener());
		this.startView.addBtnLeerlingListener(new BtnLeerlingListener());
	}
	
	class WindowClosedListener extends WindowAdapter
	{
		@Override
		public void windowClosing(WindowEvent e)
		{
			try
			{
				db.safeCatalogus();
				System.exit(0);
			} catch (SQLException | IOException e1)
			{
				e1.printStackTrace();
			}
		}
	}
	
	class BtnLeraarListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			JOptionPane.showMessageDialog(startView, "Hier moet nog inloggen logica an bod komen. Nog te bewerken");
			StartLeraarViewController slc = new StartLeraarViewController();
			
		}}
	class BtnLeerlingListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e)
		{
			JOptionPane.showMessageDialog(startView, "Hier moet nog inloggen logica an bod komen. Nog te bewerken");
			//LeerlingCatalogus is niet afgewerkt. Om te testen leerling ler naam Leerling vn 1ste leerjaar is gemakt
			StartLeerlingViewController slc = new StartLeerlingViewController(new Leerling("Leerling", 1));
		}}
}
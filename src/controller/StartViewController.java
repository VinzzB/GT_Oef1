package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;
import model.Catalogi;
import model.Leerling;
import view.StartView;


public class StartViewController //extends Controller
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
				Catalogi.SaveData();				
				System.exit(0); 
			} catch (Exception e1)
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
			new StartLeraarViewController();
			
		}}
	class BtnLeerlingListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e)
		{
			JOptionPane.showMessageDialog(startView, "Hier moet nog inloggen logica an bod komen. Nog te bewerken");
			//LeerlingCatalogus is niet afgewerkt. Om te testen leerling ler naam Leerling vn 1ste leerjaar is gemakt
			new StartLeerlingViewController(new Leerling("Leerling", 1));
		}}
}

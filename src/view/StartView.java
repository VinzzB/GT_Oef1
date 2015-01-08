package view;

/**
 * StartView
 * 
 * @auteur Natalia Dyubankova
 * 
 */
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import utils.Constants;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;

@SuppressWarnings("serial")
public class StartView extends View
{
	/**
	 * @param args
	 */	
	private Dimension size = new Dimension(600, 400);
	private JPanel main;
	private JLabel lbWelcome, lbLogo, lbDatabaseInfo, lbAuteurs;
	private JButton btnLeraar, btnLeerling;

	/**
	 * Sole constructor
	 */
	public StartView()
	{
		super("QUIZ GroepT Practicum Java 2-de jaar");
		initializeComponents();
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setSize(size);
		this.setVisible(true);
	}

	private void initializeComponents()
	{
		// main panel with GridBagLayout
		main = (JPanel) this.getContentPane();
		main.setLayout(new GridBagLayout());
		
		//labels
		lbWelcome = new JLabel("Welcome to Quiz Applicatie");
		lbLogo = new JLabel(new ImageIcon(Constants.IMAGE_PATH + "groept.jpg"));
		lbAuteurs = new JLabel(String.format("<html>Applicatie team: <br> Natalia Dyubankova, Vincent, <br> Nathalie, Silvia, Isaak, <br> Wouter</html>"));
		lbDatabaseInfo = new JLabel("<html> Gelezen database: <br> " + db.getDatabaseStrategy().getClass() + "</html>");
		
		//buttons
		btnLeraar = new JButton("Ik ben leraar");
//		btnLeraar.setIcon(new ImageIcon("/javax/swing/plaf/metal/icons/Question.gif"));
		btnLeerling = new JButton("Ik ben leerling");
//		btnLeerling.setIcon(new ImageIcon("/javax/swing/plaf/metal/icons/Inform.gif"));
		
		
		//set constants for GridBagLayout
		GridBagConstraints c = new GridBagConstraints();
		// for all GridBadPanels:
		c.insets = new Insets(10, 10, 10, 10);
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0.1;
		c.weighty = 0.1;
		
		c.anchor = GridBagConstraints.CENTER;
		c.gridx = 0;
		c.gridy = 0;
		main.add(lbWelcome, c);
		
		c.gridx = 0;
		main.add(lbLogo);
		
		c.gridx = 0;
		c.gridy = 1;
		main.add(btnLeraar, c);
		
		c.gridx = 1;		
		main.add(btnLeerling, c);
		
		c.gridx = 0;
		c.gridy = 2;		
		main.add(lbAuteurs, c);
		
		c.gridx = 1;
		main.add(lbDatabaseInfo, c);
	}
	
	//Listeners:
	public void addBtnLeraarListener(ActionListener listenForBtnLeraar)
	{
		btnLeraar.addActionListener(listenForBtnLeraar);
	}
	
	public void addBtnLeerlingListener(ActionListener listenForBtnLeerling)
	{
		btnLeerling.addActionListener(listenForBtnLeerling);
	}
	
	public void addWindowClosingListener(WindowListener listenForWindowClose)
	{
		this.addWindowListener(listenForWindowClose);
	}
}

package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class StartLeraarView extends View
{
	/**
	 * @param args
	 */
	private Dimension size = new Dimension(600, 400);
	private JPanel main;
	private JLabel lbWelcome;
	private JButton btnOpdrachten, btnQuizzen, btnScores, btnInstellingen, btnQuit;
	
	public StartLeraarView()
	{
		super();
		initializeComponents();
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setSize(size);
		this.setVisible(true);
	}
	
	private void initializeComponents()
	{
		// main panel with BorderLayout
		main = (JPanel) this.getContentPane();
		main.setLayout(new GridBagLayout());
		
		btnOpdrachten = new JButton("Beheren van opdrachten ");
		btnQuizzen = new JButton("Beheren van quizzen/testen");
		btnScores = new JButton("Overzicht scores");
		btnInstellingen = new JButton("Instellingen van de quiz applicatie");
		btnQuit = new JButton("Sluit");
		
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10, 10, 10, 10);
		c.weightx = 0.1;
		c.weighty = 0.1;
		c.gridwidth = GridBagConstraints.REMAINDER; // end row
		
		main.add(btnOpdrachten, c);
		main.add(btnQuizzen, c);
		main.add(btnScores, c);
		main.add(btnInstellingen, c);
		main.add(btnQuit, c);
	}
	
	
	//Listeners:
	public void addBtnOpdrachten(ActionListener listenForBtnOpdrachten)
	{
		btnOpdrachten.addActionListener(listenForBtnOpdrachten);
	}
	
	public void addBtnQuizzen(ActionListener listenForBtnQuizzen)
	{
		btnQuizzen.addActionListener(listenForBtnQuizzen);
	}
	public void addBtnScores(ActionListener listenForBtnScores)
	{
		btnScores.addActionListener(listenForBtnScores);
	}
	
	public void addBtnInstellingen(ActionListener listenForBtnInstellingen)
	{
		btnInstellingen.addActionListener(listenForBtnInstellingen);
	}
	public void addBtnQuit(ActionListener listenForBtnQuit)
	{
		btnQuit.addActionListener(listenForBtnQuit);
	}
}

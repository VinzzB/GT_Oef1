package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class StartLeerlingView extends View
{
	/**
	 * @param args
	 */
	private Dimension size = new Dimension(600, 400);
	private JPanel main;
	private JButton btnDeelnemen, btnQuizRapport, btnInstellingen, btnQuit;
	
	public StartLeerlingView()
	{
		super();
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
		
		btnDeelnemen = new JButton("Deelnemen aan quiz ");
		btnQuizRapport = new JButton("Quiz rapport ");
		btnInstellingen = new JButton("Instellingen van de quiz applicatie");
		btnQuit = new JButton("Sluit");
		
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10, 10, 10, 10);
		c.weightx = 0.1;
		c.weighty = 0.1;
		c.gridwidth = GridBagConstraints.REMAINDER; // end row
		
		main.add(btnDeelnemen, c);
		main.add(btnQuizRapport, c);
		main.add(btnInstellingen, c);
		main.add(btnQuit, c);
	}
	
	public void addBtnDeelnemenListener(ActionListener listenForBtnDeelnemen)
	{
		btnDeelnemen.addActionListener(listenForBtnDeelnemen);
	}
	
	public void addBtnQuizRapportListener(ActionListener listenForBtnQuizRapport)
	{
		btnQuizRapport.addActionListener(listenForBtnQuizRapport);
	}
	public void addBtnInstellingenListener(ActionListener listenForBtnInstellingen)
	{
		btnInstellingen.addActionListener(listenForBtnInstellingen);
	}
	
	public void addBtnQuitListener(ActionListener listenForBtnQuit)
	{
		btnQuit.addActionListener(listenForBtnQuit);
	}	
}

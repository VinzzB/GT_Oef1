package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


@SuppressWarnings("serial")
public class QuizAfleggenView extends View
{
	private Dimension size = new Dimension(400, 300);
	
	private JLabel lbVraag, lbVraagNummer, lbPoging, lbTijd, lbAntwoord;
	private JTextField txtAntwoord;
	private JButton btnSubmit, btnVolgende, btnGetHint, btnQuit;
	private JPanel main, buttonPanel, antwoorPanel;
	private GridBagConstraints c;
	
	public QuizAfleggenView()
	{
		super("Quiz afleggen");
		initializeComponents();
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setSize(size);
		this.setVisible(true);
	}
	
	private void initializeComponents()
	{
		// main panel with BorderLayout
		main = (JPanel) this.getContentPane();
		main.setLayout(new BorderLayout());
		
		buttonPanel = new JPanel(new FlowLayout());
		antwoorPanel = new JPanel(new GridBagLayout());
		
		//labels
		lbVraag = new JLabel();
		lbVraagNummer = new JLabel();
		lbPoging = new JLabel();
		lbTijd = new JLabel();
		lbAntwoord = new JLabel();
		
		txtAntwoord = new JTextField("Geef hier je antwoord en druk SUBMIT", 300);
		
		//buttons
		btnSubmit = new JButton("SUBMIT");
		btnGetHint = new JButton("Get Hint");
		btnVolgende = new JButton("Volgende");
		btnQuit = new JButton("Sluit");
		
		//set constants for GridBagLayout
		c = new GridBagConstraints();
		// for all GridBadPanels:
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(10, 10, 10, 10);
		c.weightx = 0.1;
		c.weighty = 0.1;
		
		c.gridx = 0;
		c.gridy = 0;
		antwoorPanel.add(this.lbVraagNummer, c);
		
		c.gridx = 2;
		antwoorPanel.add(this.lbPoging, c);
		
		c.gridy = 1;
		antwoorPanel.add(this.lbTijd, c);
		
		c.gridx = 0;
		c.gridy = 2;
		antwoorPanel.add(this.lbVraag, c);
		
		c.gridy = 3;
		antwoorPanel.add(this.txtAntwoord, c);
		
		buttonPanel.add(btnSubmit);
		buttonPanel.add(btnGetHint);
		buttonPanel.add(btnVolgende);
		buttonPanel.add(btnQuit);
		
		main.add(antwoorPanel, BorderLayout.CENTER);
		main.add(buttonPanel, BorderLayout.SOUTH);
	}
	
	public void setVraag(String vraag)
	{
		this.lbVraag.setText("Vraag: " + vraag);
	}
	
	public void setVraagNummer(String nummer)
	{
		this.lbVraagNummer.setText("Nr. " + nummer);
	}
	
	public void setPoging(String poging)
	{
		this.lbPoging.setText("Poging " + poging);
	}
	
	public void setTijd(String tijd)
	{
		this.lbTijd.setText("Tijd " + tijd + "sec");
	}
	
	public String getAntwoord()
	{
		return txtAntwoord.getText();
	}
	
	//Listeners
	public void addBtnSubmitListener(ActionListener listenForBtnSubmit)
	{
		btnSubmit.addActionListener(listenForBtnSubmit);
	}
	public void addBtnVolgendeListener(ActionListener listenForBtnVolgende)
	{
		btnVolgende.addActionListener(listenForBtnVolgende);
	}	
	public void addBtnGetHintListener(ActionListener listenForBtnGetHint)
	{
		btnGetHint.addActionListener(listenForBtnGetHint);
	}
	public void addBtnQuitListener(ActionListener listenForBtnQuit)
	{
		btnQuit.addActionListener(listenForBtnQuit);
	}
}

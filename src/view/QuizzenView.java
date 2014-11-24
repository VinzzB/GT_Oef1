package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class QuizzenView extends JFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2468692264785643435L;
	
	private static final int width = 800, height = 600;
	
	private JPanel northPanel, centerPanel, buttonsPanel, centerPanelNorth;
	private JLabel lbQuizzen, lbOpdrachten, lbAuteur, lbCreatieDatum;
	private JButton btnNieuwe, btnUpdate, btnVerwijder;
	private JScrollPane paneQuizzen, paneOpdrachten;
	private JTextArea areaQuizzen, areaOpdrachten;
	
	/**
	 * Sole constructor
	 */
	public QuizzenView()
	{
		super();
		initializeComponents();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(width, height);
		this.setVisible(true);
	}
	
	/**
	 * label: Nieuwe Quiz of Quiz
	 * 
	 * @param label
	 */
	public QuizzenView(String label)
	{
		super(label);
		initializeComponents();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(width, height);
		this.setVisible(true);
	}
	
	private void initializeComponents()
	{
		// main panel with BorderLayout
		JPanel main = (JPanel) this.getContentPane();
		main.setLayout(new BorderLayout());
		
		northPanel = new JPanel(new BorderLayout());
		centerPanel = new JPanel(new BorderLayout());
		centerPanelNorth = new JPanel(new FlowLayout());
		buttonsPanel = new JPanel(new FlowLayout());
		
		//labels
		lbQuizzen = new JLabel("Quizzen:");
		lbOpdrachten = new JLabel("Opdrachten in geselecteerde Quiz: ");
		lbAuteur = new JLabel("Auteur: ");
		lbCreatieDatum = new JLabel("Datum van creatie: ");
		
		//buttons
		btnNieuwe = new JButton("Nieuwe quiz");
		btnUpdate = new JButton("Quiz update");
		btnVerwijder =  new JButton("Quiz verwijder");
		
		//scrollpanes
		paneQuizzen = new JScrollPane(createResultArea(areaQuizzen));
		paneOpdrachten = new JScrollPane(createResultArea(areaOpdrachten));
		
		northPanel.setBorder(BorderFactory.createTitledBorder("northPanel"));
		northPanel.setPreferredSize(new Dimension(800,250));
		northPanel.add(lbQuizzen, BorderLayout.NORTH);
		northPanel.add(paneQuizzen, BorderLayout.CENTER);
		
		centerPanelNorth.add(lbOpdrachten);
		centerPanelNorth.add(lbAuteur);
		centerPanelNorth.add(lbCreatieDatum);
		
		centerPanel.setBorder(BorderFactory.createTitledBorder("centerPanel"));		
		centerPanel.setPreferredSize(new Dimension(800,250));
		centerPanel.add(centerPanelNorth, BorderLayout.NORTH);
		centerPanel.add(paneOpdrachten, BorderLayout.CENTER);

		buttonsPanel.setBorder(BorderFactory.createTitledBorder("buttonsPanel"));
		buttonsPanel.add(btnNieuwe);
		buttonsPanel.add(btnUpdate);
		buttonsPanel.add(btnVerwijder);
		
		main.add(northPanel, BorderLayout.NORTH);
		main.add(centerPanel, BorderLayout.CENTER);
		main.add(buttonsPanel, BorderLayout.SOUTH);

	}
	
	private JScrollPane createResultArea(JTextArea area)
	{
		// set up display area
		area = new JTextArea();
		area.setEditable(false);
		return new JScrollPane(area);
	}

	public static void main(String[] args)
	{
		QuizzenView qv = new QuizzenView();
	}

}

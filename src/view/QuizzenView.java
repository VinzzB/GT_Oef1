package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionListener;

import model.Opdracht;
import model.Quiz;
import model.QuizCatalogus;
import persistency.Database;

public class QuizzenView extends JFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2468692264785643435L;
	
	private Dimension size = new Dimension(900, 600);
	
	private JPanel northPanel, centerPanel, buttonsPanel, centerPanelNorth;
	private JLabel lbAuteur, lbCreatieDatum;
	private JButton btnNieuwe, btnUpdate, btnVerwijder;
	private JScrollPane paneQuizzen, paneOpdrachten;
	private JList viewQuizzen;
	private JTextArea viewOpdrachten;
	DefaultListModel model;
	
	private QuizCatalogus quizCatalogus = Database.getQuizCatalogus();
	
	/**
	 * Sole constructor
	 */
	public QuizzenView()
	{
		super();
		initializeComponents();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(size);
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
		this.setSize(size);
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
		lbAuteur = new JLabel("Auteur: ");
		lbCreatieDatum = new JLabel("Datum van creatie: ");
		
		//buttons
		btnNieuwe = new JButton("Nieuwe quiz");
		btnUpdate = new JButton("Quiz update");
		btnVerwijder =  new JButton("Quiz verwijder");
		
		//scrollpanes
		paneQuizzen = setQuizzenView();
		paneOpdrachten = newOpdrachtenView();
		
		
		northPanel.setBorder(BorderFactory.createTitledBorder("Beschikbare quizzen"));
		northPanel.setPreferredSize(new Dimension(800,250));
		northPanel.add(paneQuizzen);
		
		centerPanelNorth.add(lbAuteur);
		centerPanelNorth.add(lbCreatieDatum);
		
		centerPanel.setBorder(BorderFactory.createTitledBorder("Geselecteerde Quiz details"));		
		centerPanel.setPreferredSize(new Dimension(800,250));
		centerPanel.add(centerPanelNorth, BorderLayout.NORTH);
		centerPanel.add(paneOpdrachten, BorderLayout.CENTER);

		buttonsPanel.setBorder(BorderFactory.createTitledBorder("Action mogelijk"));
		buttonsPanel.add(btnNieuwe);
		buttonsPanel.add(btnUpdate);
		buttonsPanel.add(btnVerwijder);
		
		main.add(northPanel, BorderLayout.NORTH);
		main.add(centerPanel, BorderLayout.CENTER);
		main.add(buttonsPanel, BorderLayout.SOUTH);

	}
	
	private JScrollPane setQuizzenView()
	{
		model = new DefaultListModel();
		viewQuizzen = new JList(model);
		for(Quiz quiz : quizCatalogus)
		{
			model.addElement(quiz);
		}
	
		return new JScrollPane(viewQuizzen);
	}
	
	private JScrollPane newOpdrachtenView()
	{	
		viewOpdrachten = new JTextArea();
		viewOpdrachten.setText("");

		return new JScrollPane(viewOpdrachten);
	}
	
	public void updateViewQuizzen()
	{
		model.removeAllElements();
		for(Quiz quiz : quizCatalogus)
		{
			model.addElement(quiz);
		}
	}
	
	public void setOpdrachten(String[] opdrachten)
	{
		viewOpdrachten.setText("");
		if(opdrachten != null)
		{
			for(String opdracht : opdrachten)
			{
				viewOpdrachten.append(opdracht + "\n");
			}
		viewOpdrachten.setEditable(false);
		}
	}
	
	public void setLbAuteur(String auteur)
	{
		lbAuteur.setText("Auteur: " + auteur);
	}
	
	public void setLbCreatieDatum(String datum)
	{
		lbCreatieDatum.setText("Datum van creatie: " + datum);
	}
	
	public Quiz getSelectedQuiz()
	{
		return (Quiz)viewQuizzen.getSelectedValue();
	}
	
	public int getSelectedIndex()
	{
		return viewQuizzen.getSelectedIndex();
	}
	
	public void removeQuiz(int index)
	{
		model.remove(index);
	}
	
	//Liseners:
	public void addQuizSelectedListener(ListSelectionListener listenForSelectedQuiz)
	{
		viewQuizzen.getSelectionModel().addListSelectionListener(listenForSelectedQuiz);
	}
		
	public void addBtnNieuweListener(ActionListener listenForBtnNieuweListener)
	{
		btnNieuwe.addActionListener(listenForBtnNieuweListener);
	}
	
	public void addBtnUpdateListener(ActionListener listenForBtnUpdateListener)
	{
		btnUpdate.addActionListener(listenForBtnUpdateListener);
	}
	
	public void addBtnVerwijderListener(ActionListener listenForBtnVerwijderListener)
	{
		btnVerwijder.addActionListener(listenForBtnVerwijderListener);
	}
}

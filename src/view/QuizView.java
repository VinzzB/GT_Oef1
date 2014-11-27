package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionListener;

import model.Leraar;
import model.Opdracht;
import model.OpdrachtCatalogus;
import model.OpdrachtCategorie;
import model.quizStatus.*;
import persistency.Database;

public class QuizView extends JFrame
{

	/**
	 * Generated version UID
	 */
	private static final long serialVersionUID = 846842690361655830L;
	/*
	 * displayfields
	 */
	private JPanel panelAlgemeen, panelOpdrachten, panelOpdrachtenLeft, 
					panelOpdrachtenLeftUp, panelOpdrachtenCenter, 
					panelOpdrachtenRight, panelOpdrachtenRightUp, panelOpdrachtDetails;
	private JLabel lbOnderwerp, lbKlas, lbAuteur, lbStatus,
					lbOpdrachtenCategorie, lbOpdrachtenSorteren, lbAantal, lbOpdrachtAantal,
					lbOpdrachtDetails;
	private JTextField txtOnderwerp;
	private JButton btnRegistreer, btnVoegOpdrachtToe, btnVerwijderOpdracht, btnOpdrachtUp;
	private JComboBox<Integer> cbxLeerjaar;
	private JComboBox<Leraar> cbxAuteur;
	private JComboBox<QuizStatus> cbxStatus;
	private JComboBox<OpdrachtCategorie> cbxCategorie;
	private JComboBox<String> cbxSortering;
	private JScrollPane paneAllOpdrachten, paneSelectedOpdrachten;
	private JTextArea area;
	private ModelTable modelTable;
	private JTable table;
	
	private Dimension size = new Dimension(900, 600);
	
	private OpdrachtCatalogus opdrachten = Database.getOpdrachtCatalogus();
	
	/**
	 * Sole constructor
	 */
	public QuizView()
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
	 * @throws Exception 
	 */
	public QuizView(String label) throws Exception
	{
		super(label);
		setOpdrachten(paneAllOpdrachten, opdrachten); 
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

		//sub-panels with GridBag or BorderLayout
		panelAlgemeen = new JPanel(new GridBagLayout()); 
		panelOpdrachten = new JPanel(new GridBagLayout());
		panelOpdrachtenLeft = new JPanel(new BorderLayout()); 
		panelOpdrachtenLeftUp = new JPanel(new GridBagLayout());
		panelOpdrachtenCenter = new JPanel(new GridBagLayout());
		panelOpdrachtenRight = new JPanel(new BorderLayout()); 
		panelOpdrachtenRightUp = new JPanel(new GridBagLayout());
		panelOpdrachtDetails = new JPanel(new FlowLayout());
		panelOpdrachtenLeftUp.setMaximumSize(new Dimension(300, 500));
		
		//labels
		lbOnderwerp = new JLabel("Onderwerp");
		lbKlas = new JLabel("Klas:");
		lbAuteur = new JLabel("Auteur");
		lbStatus = new JLabel("Status");
		lbOpdrachtenCategorie = new JLabel("Toon opdrachten van categorie:");
		lbOpdrachtenSorteren = new JLabel("Sorteer opdrachten op:");
		lbOpdrachtDetails = new JLabel(" ");
		lbAantal = new JLabel("Aantal toegevoegde opdrachten:");
		lbOpdrachtAantal = new JLabel("0");
		lbOpdrachtAantal.setBorder(BorderFactory.createEtchedBorder());

		//text fields
		txtOnderwerp = new JTextField("Voeg onderwerp toe", 25);
		
		//comboboxes
		cbxLeerjaar = new JComboBox<Integer>(new Integer[] {1, 2, 3, 4, 5, 6});
		cbxAuteur = new JComboBox<Leraar>(Leraar.values());
		cbxStatus = new JComboBox<QuizStatus>(new QuizStatus[]{new Afgesloten(), new Afgewerkt(), new Inconstructie(), new LaatsteKans(), new Opengesteld()});
		cbxCategorie = new JComboBox<OpdrachtCategorie>(OpdrachtCategorie.values());
		cbxSortering = new JComboBox<String>(new String[] {"geen", "categorie", "vraag"});
		
		//buttons
		btnRegistreer = new JButton("Registreren");
		btnVoegOpdrachtToe = new JButton(">>>");
		btnVerwijderOpdracht = new JButton("<<<");
		btnOpdrachtUp = new JButton("^^^^");
		
		//scrollpanes
//		paneAllOpdrachten = new JScrollPane(createResultArea());
		paneSelectedOpdrachten = new JScrollPane(createResultArea());
		
		//set constants for GridBagLayout
		GridBagConstraints c = new GridBagConstraints();
		// for all GridBadPanels:
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(10, 10, 5, 5);
		
		//Fill in top panel and add border with title 
		panelAlgemeen.setBorder(BorderFactory.createTitledBorder("Algemeen"));
		c.weightx = 1.0;
		panelAlgemeen.add(lbOnderwerp, c);
		panelAlgemeen.add(txtOnderwerp, c);
		panelAlgemeen.add(lbKlas, c);
		panelAlgemeen.add(cbxLeerjaar, c);
		panelAlgemeen.add(lbAuteur, c);
		panelAlgemeen.add(cbxAuteur, c);
		panelAlgemeen.add(lbStatus, c);
		c.gridwidth = GridBagConstraints.REMAINDER; // end row
		panelAlgemeen.add(cbxStatus, c);
		panelAlgemeen.add(btnRegistreer, c);

		//Bottom panel with border and title
		panelOpdrachten.setBorder(BorderFactory.createTitledBorder("Opdrachten"));
		
		//Fill in Upper part of the left bottom panel
		c.insets = new Insets(0, 0, 10, 0); // change insets for GridGagLyout
		c.gridwidth = 1; //reset to default
		panelOpdrachtenLeftUp.add(lbOpdrachtenCategorie, c);
		c.gridwidth = GridBagConstraints.REMAINDER; // end row
		panelOpdrachtenLeftUp.add(cbxCategorie, c);
		c.gridwidth = 1; //reset to default
		panelOpdrachtenLeftUp.add(lbOpdrachtenSorteren, c);
		c.gridwidth = GridBagConstraints.REMAINDER; // end row
		panelOpdrachtenLeftUp.add(cbxSortering, c);
		
		//Fill in Left bottom panel
		panelOpdrachtenLeft.add(panelOpdrachtenLeftUp, BorderLayout.NORTH);
		panelOpdrachtenLeft.add(paneAllOpdrachten, BorderLayout.CENTER);
		panelOpdrachtenLeft.add(lbOpdrachtDetails, BorderLayout.SOUTH);
		
		//Fill in Center bottom panel
		panelOpdrachtenCenter.add(btnVoegOpdrachtToe, c);
		panelOpdrachtenCenter.add(btnVerwijderOpdracht, c);
		
		//Fill in Upper part of the right bottom panel
		c.gridwidth = 1; //reset to default
		panelOpdrachtenRightUp.add(lbAantal, c);
		c.gridwidth = GridBagConstraints.REMAINDER; // end row
		panelOpdrachtenRightUp.add(lbOpdrachtAantal, c);
		panelOpdrachtenRightUp.add(btnOpdrachtUp, c);
		
		//Fill in Right bottom panel
		panelOpdrachtenRight.add(panelOpdrachtenRightUp, BorderLayout.NORTH);
		panelOpdrachtenRight.add(paneSelectedOpdrachten, BorderLayout.CENTER);
		
		//Fill in Bottom panel
		c.gridwidth = 1; //reset to default
		c.weighty = 1.0;
		panelOpdrachten.add(panelOpdrachtenLeft, c);
		c.weightx = 0.0;
		panelOpdrachten.add(panelOpdrachtenCenter, c);
		c.weightx  = 1.0;
		panelOpdrachten.add(panelOpdrachtenRight, c);
		
		//Fill in main window
		main.add(panelAlgemeen, BorderLayout.NORTH);
		main.add(panelOpdrachten, BorderLayout.CENTER);

	}
	
	private JScrollPane createResultArea()
	{
		// set up display area
		area = new JTextArea();
		area.setEditable(false);
		return new JScrollPane(area);
	}
	
	public String getOnderwerp()
	{
		return txtOnderwerp.getText();
	}
	
	public Integer getKlas()
	{
		return Integer.parseInt(cbxLeerjaar.getSelectedItem().toString());
	}
	
	public Leraar getAuteur()
	{
		return (Leraar) cbxAuteur.getSelectedItem();
	}

	public QuizStatus getQuizStatus()
	{
		return (QuizStatus) cbxStatus.getSelectedItem();
	}
	
	public OpdrachtCategorie getOpdrachtCategorie()
	{
		return (OpdrachtCategorie) cbxCategorie.getSelectedItem();
	}
	
	public Opdracht getSelectedOpdracht(JScrollPane pane)
	{
		return new Opdracht();
	}
	
	public JTable getTable()
	{
		return this.table;
	}
	
	public Opdracht getOpdracht(int index)
	{
		return opdrachten.getOpdracht(index);
	}
	
	public void setOpdrachten(JScrollPane pane, OpdrachtCatalogus opdrachten)
	{
		ArrayList<Object[]> values = new ArrayList<Object[]>();
		for (Opdracht opdracht : opdrachten.getOpdrachten().values())
		{
			values.add(new Object[]{opdracht.getCategorie(), opdracht.getVraag()});
		}
		modelTable = new ModelTable(values, new String[]{"Categorie", "Vraag"}, 2);
		table = new JTable(modelTable);
		table.getColumnModel().getColumn(0).setMaxWidth(30); // set column Categorie width to 30
		paneAllOpdrachten = new JScrollPane(table);
	}
	
	public void setOpdrachtCatalogus(OpdrachtCatalogus opdrachtCatalogus)
	{
		this.opdrachten = opdrachtCatalogus;
	}
	
	public void setLbOpdrachtDetails(Opdracht opdracht)
	{
		lbOpdrachtDetails.setText("Opdracht details: " + opdracht.toString());
	}
	//Liseners:
	public void addOpdrachtSelectedListener(ListSelectionListener listenForSelectedRow)
	{
		table.getSelectionModel().addListSelectionListener(listenForSelectedRow);
	}
}

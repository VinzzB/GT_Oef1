package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.peer.PanelPeer;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import model.*;
import model.quizStatus.*;
import persistency.Database;
import utils.ModelTable;

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
					panelOpdrachtenRight, panelOpdrachtenRightUp;
	private JLabel lbOnderwerp, lbKlas, lbAuteur, lbStatus,
					lbOpdrachtenCategorie, lbOpdrachtenSorteren, lbAantal, lbOpdrachtAantal,
					lbOpdrachtDetails;
	private JTextField txtOnderwerp;
	private JButton btnRegistreer, btnVoegOpdrachtToe, btnVerwijderOpdracht, 
					btnOpdrachtUp, btnNieuweOpdracht;
	private JComboBox<Integer> cbxLeerjaar;
	private JComboBox<Leraar> cbxAuteur;
	private JComboBox<QuizStatus> cbxStatus;
	private JComboBox<OpdrachtCategorie> cbxCategorie;
	private JComboBox<String> cbxSortering;
	private JScrollPane paneAllOpdrachten, paneSelectedOpdrachten;
	private DefaultTableModel modelTableAllOpdrachten, modelTableSelectedOpdrachten;
	private JTable tableAllOpdrachten, tableSelectedOpdrachten;
	
	
	private Dimension size = new Dimension(900, 600);
	
	private OpdrachtCatalogus opdrachten = Database.getOpdrachtCatalogus();
	private TreeMap<Integer, Opdracht> selectedOpdrachten = new TreeMap<Integer, Opdracht>();
	private Quiz quiz;
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
	public QuizView(String label)
	{
		super(label);
		initializeComponents();
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setSize(size);
		this.setVisible(true);
	}
	
	public QuizView(Quiz quiz)
	{
		super("Quiz: " + quiz.getOnderwerp() + " update");

		this.quiz = quiz;
		selectedOpdrachten = quiz.getOpdrachten();
		
		initializeComponents();
		
		this.setOnderwerp(quiz.getOnderwerp());
		this.setKlass(quiz.getLeerjaar());
		this.setAuteur(quiz.getAuteur());
		this.setStatus(quiz.getStatus());
		
		for(QuizOpdracht quizOpdracht : quiz.getQuizOpdrachten())
		{
			this.setMaxScorePerOpdracht(quizOpdracht.getOpdracht().getVraag(), quizOpdracht.getMaxScore());
		}
		
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
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
		cbxStatus = new JComboBox<QuizStatus>(new QuizStatus[]{null, Afgesloten.instance(), Afgewerkt.instance(), Inconstructie.instance(), LaatsteKans.instance(), Opengesteld.instance()});
		cbxCategorie = new JComboBox<OpdrachtCategorie>(OpdrachtCategorie.values());
		cbxCategorie.insertItemAt(null, 0);
		cbxCategorie.setSelectedItem(null);
		cbxSortering = new JComboBox<String>(new String[] {"geen", "categorie", "vraag"});
		
		//buttons
		btnRegistreer = new JButton("Registreren");
		btnVoegOpdrachtToe = new JButton(">>>");
		btnVerwijderOpdracht = new JButton("<<<");
		btnOpdrachtUp = new JButton("^^^^");
		btnNieuweOpdracht = new JButton("Nieuwe opdracht maken");
		
		//scrollpanes
		setOpdrachten(paneAllOpdrachten, opdrachten); 
		setSelectedOpdrachten(paneSelectedOpdrachten, selectedOpdrachten);
		
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
		panelOpdrachtenRight.add(btnNieuweOpdracht, BorderLayout.SOUTH);
		
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
	
	public String getOnderwerp()
	{
		return txtOnderwerp.getText();
	}
	
	public void setOnderwerp(String onderwerp)
	{
		txtOnderwerp.setText(onderwerp);
	}
	
	public Integer getKlas()
	{
		return Integer.parseInt(cbxLeerjaar.getSelectedItem().toString());
	}
	
	public void setKlass(int klass)
	{
		cbxLeerjaar.setSelectedItem(klass);
	}
	
	public Leraar getAuteur()
	{
		return (Leraar) cbxAuteur.getSelectedItem();
	}
	
	public void setAuteur(Leraar leraar)
	{
		cbxAuteur.setSelectedItem(leraar);
	}

	public QuizStatus getQuizStatus()
	{
		return (QuizStatus)cbxStatus.getSelectedItem();
	}
	
	public void setStatus(QuizStatus status)
	{
		cbxStatus.setSelectedItem(status);
	}
	
	public OpdrachtCategorie getOpdrachtCategorie()
	{
		return (OpdrachtCategorie) cbxCategorie.getSelectedItem();
	}
	
	public JTable getAllOpdrachtenTable()
	{
		return this.tableAllOpdrachten;
	}
	
	public JTable getSelectedOpdrachtenTable()
	{
		return this.tableSelectedOpdrachten;
	}
	
	public Opdracht getOpdracht(int index)
	{
		return opdrachten.getOpdracht(index);
	}
	
	public Opdracht getSelectedOpdracht(JTable table)
	{
		if (table.getSelectedRowCount() == 0)
			return null;
		else
			return opdrachten.getOpdracht(table.getValueAt(table.getSelectedRow(), 1).toString());
	}
	
	public OpdrachtCategorie getSelectedOpdrachCategorie()
	{
		return (OpdrachtCategorie)this.cbxCategorie.getSelectedItem();
	}
	
	public String getSelectedSorterigType()
	{
		return this.cbxSortering.getSelectedItem().toString();
	}
	
	public int getMaxScorePerQuizOpdracht(Opdracht opdracht)
	{
		for(Integer i = 0; i <= modelTableSelectedOpdrachten.getRowCount(); i++)
		{
			if(tableSelectedOpdrachten.getModel().getValueAt(i, 1) == opdracht.getVraag())
			{
				return Integer.parseInt(tableSelectedOpdrachten.getModel().getValueAt(i, 2).toString());
			}
		}
		return 0;
	}
	
	public void setMaxScorePerOpdracht(String vraag, int maxScore)
	{
		for(int i = 0; i < tableSelectedOpdrachten.getRowCount(); i++)
		{
			if(tableSelectedOpdrachten.getValueAt(i, 1) == vraag) 
				tableSelectedOpdrachten.setValueAt(maxScore, i, 2);
		}
	}
	
	public ArrayList<Opdracht> getOpdrachtenInTable(JTable table)
	{
		ArrayList<Opdracht> opdrachten = new ArrayList<Opdracht>();
		
		for(int i = 0; i < table.getModel().getRowCount(); i++)
		{
			for(Opdracht opdracht : this.opdrachten.getOpdrachten().values())
			{
				if(table.getModel().getValueAt(i, 1) == opdracht.getVraag())
				{
					opdrachten.add(opdracht);
				}
			}
		}	
		return opdrachten;
	}
	
	private void setOpdrachten(JScrollPane pane, OpdrachtCatalogus opdrachten)
	{
		Object[][] values = new Object[opdrachten.getOpdrachten().size()][];
		int i = 0;
		for (Opdracht opdracht : opdrachten.getOpdrachten().values())
		{
			values[i] = new Object[]{opdracht.getCategorie(), opdracht.getVraag()};
			i++;
		}
		modelTableAllOpdrachten = new DefaultTableModel(values, new String[]{"Categorie", "Vraag"});
		tableAllOpdrachten = new JTable(modelTableAllOpdrachten);
		tableAllOpdrachten.getColumnModel().getColumn(0).setMaxWidth(100);
		paneAllOpdrachten = new JScrollPane(tableAllOpdrachten);
	}
	
	private void setSelectedOpdrachten(JScrollPane pane, TreeMap<Integer, Opdracht> selectedOpdrachten)
	{
		Object[][] values = new Object[selectedOpdrachten.size()][];
		int i = 0;
		for (Opdracht opdracht : selectedOpdrachten.values())
		{
			values[i] = new Object[]{opdracht.getCategorie(), opdracht.getVraag(), "0"};
			i++;
		}
		modelTableSelectedOpdrachten = new DefaultTableModel(values, new String[]{"Categorie", "Vraag", "Score"});
		tableSelectedOpdrachten = new JTable(modelTableSelectedOpdrachten);
		tableSelectedOpdrachten.getColumnModel().getColumn(0).setMaxWidth(100);
		tableSelectedOpdrachten.getColumnModel().getColumn(2).setMaxWidth(50);
		paneSelectedOpdrachten = new JScrollPane(tableSelectedOpdrachten);
	}
	
	public void setGesoorteerdeOpdrachten(TreeMap<Integer, Opdracht> opdrachten)
	{
		for(int i = modelTableAllOpdrachten.getRowCount() - 1; i >= 0; i--)
		{
			modelTableAllOpdrachten.removeRow(i);
		}

		for(Opdracht opdracht : opdrachten.values())
		{
			modelTableAllOpdrachten.addRow(new String[] {opdracht.getCategorie().toString(), opdracht.getVraag()});
		}
	}

	public void addSelectedOpdrachtToPane(Opdracht opdracht)
	{
		
		modelTableSelectedOpdrachten.addRow(new String[] {opdracht.getCategorie().toString(), opdracht.getVraag(), "0"});
	}
	
	public void removeSelectedOpdrachtVanPane(int opdrachtRow)
	{
		modelTableSelectedOpdrachten.removeRow(opdrachtRow);
	}
	public void setOpdrachtCatalogus(OpdrachtCatalogus opdrachtCatalogus)
	{
		this.opdrachten = opdrachtCatalogus;
	}
	
	public void setLbOpdrachtDetails(Opdracht opdracht)
	{
		lbOpdrachtDetails.setText("<html>" + "Opdracht details: " + opdracht.toString() + "</html>");
	}
	
	public void setLbOpdrachtenAantal(Integer aantal)
	{
		lbOpdrachtAantal.setText(aantal.toString());
	}
	
	public void moveUp(Integer row)
	{
		modelTableSelectedOpdrachten.moveRow(row, row, row-1);
	}
	
	//Liseners:
	public void addOpdrachtSelectedListener(ListSelectionListener listenForSelectedRow)
	{
		tableAllOpdrachten.getSelectionModel().addListSelectionListener(listenForSelectedRow);
	}
	
	public void addBtnVoegOpdrachtToeListener(ActionListener listenForBtnVoegOpdrachtToe)
	{
		btnVoegOpdrachtToe.addActionListener(listenForBtnVoegOpdrachtToe);
	}
	
	public void addBtnVerwijderOpdrachtListener(ActionListener listenForBtnVerwijderOpdracht)
	{
		btnVerwijderOpdracht.addActionListener(listenForBtnVerwijderOpdracht);
	}
	
	public void addBtnOpdrachtUpListener(ActionListener listenForBtnOpdrachtUp)
	{
		btnOpdrachtUp.addActionListener(listenForBtnOpdrachtUp);
	}
	
	public void addBtnRegistreerListener(ActionListener listenForRegistration)
	{
		btnRegistreer.addActionListener(listenForRegistration);
	}
	
	public void addBtnNieuweOpdrachtListener(ActionListener listenForNieuweOpdracht)
	{
		btnNieuweOpdracht.addActionListener(listenForNieuweOpdracht);
	}
	
	public void addCbxCategorieListener(ActionListener listenForSelectCategorie)
	{
		cbxCategorie.addActionListener(listenForSelectCategorie);
	}
	
	public void addCbxSorteringListener(ActionListener listenForSelectSortering)
	{
		cbxSortering.addActionListener(listenForSelectSortering);
	}
}


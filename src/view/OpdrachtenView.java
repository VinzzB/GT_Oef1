package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import model.Opdracht;

@SuppressWarnings("serial")
public class OpdrachtenView extends View
{
	private JPanel main, buttonsPanel;
	private JButton btnNieuwe, btnUpdate, btnVerwijder;
	private JScrollPane paneOpdrachten;
	private JTable tblOpdrachten;
	private DefaultTableModel modelTableOpdrachten;
	
	private Dimension size = new Dimension(900, 600);
	
	public OpdrachtenView()
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
		main.setLayout(new BorderLayout());
		
		buttonsPanel = new JPanel(new FlowLayout());
		
		SetOpdrachten();
		
		//buttons
		btnNieuwe = new JButton("Nieuwe opdracht");
		btnUpdate = new JButton("Opdracht update");
		btnVerwijder =  new JButton("Opdracht verwijder");
		
		buttonsPanel.add(btnNieuwe);
		buttonsPanel.add(btnUpdate);
		buttonsPanel.add(btnVerwijder);
		
		main.add(paneOpdrachten, BorderLayout.CENTER);
		main.add(buttonsPanel, BorderLayout.SOUTH);
	}
	private void SetOpdrachten()
	{
		Object[][] values = new Object[opdrachtCatalogus.getOpdrachten().size()][];
		int i = 0;
		for (Opdracht opdracht : opdrachtCatalogus.getOpdrachten().values())
		{
			values[i] = new Object[]{opdracht.getOpdrachtID(), opdracht.getCategorie(), opdracht.getVraag(), opdracht.getMaxAantalPogingen(), opdracht.getMaxAntwoordTijdInSec()};
			i++;
		}
		modelTableOpdrachten = new DefaultTableModel(values, new String[]{"ID", "Categorie", "Vraag", "Pogingen", "Max antwoord tijd"})
		{
			@Override
		    public boolean isCellEditable(int row, int column) 
			{
		        return column == 0 || column == 1 ? false : true;
			}
		};
		tblOpdrachten = new JTable(modelTableOpdrachten);
		paneOpdrachten = new JScrollPane(tblOpdrachten);
	}
	
	public Opdracht getSelectedOpdracht()
	{
		if (tblOpdrachten.getSelectedRowCount() == 0)
			return null;
		else
		{
			return opdrachtCatalogus.getOpdracht(tblOpdrachten.getValueAt(tblOpdrachten.getSelectedRow(), 1).toString());
		}
	}
	
	public DefaultTableModel getTableModel()
	{
		return this.modelTableOpdrachten;
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
	
	public void addWindowClosingListener(WindowListener listenForWindowClose)
	{
		this.addWindowListener(listenForWindowClose);
	}
}

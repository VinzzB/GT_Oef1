package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import java.awt.event.ActionListener;
import java.awt.event.ItemListener;


import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;


/**
 * View om lijsten te tonen
 * 
 * @author Natalia
 *
 */
@SuppressWarnings("serial")
public class QuizLijstenView extends View
{
	/**
	 * @param args
	 */	
	private Dimension size = new Dimension(600, 400);
	private JPanel main, panelNorth, panelSouth;
	private JLabel lbKiez;
	private JComboBox<String> cbxRapporten;
	private JTextArea txtRapport;
	private JCheckBox chbDatumDecor, chbAuteurDecor;

	public QuizLijstenView()
	{
		super("Rapporten");
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
		
		panelNorth = new JPanel(new FlowLayout());
		panelSouth = new JPanel(new FlowLayout());
		
		lbKiez = new JLabel("Kiez quiz lijst");
		cbxRapporten = new JComboBox<String>(new String[]{"OpdrachtenPerCategorie", "QuizzenSorteerd"});
		txtRapport = new JTextArea();
		chbDatumDecor = new JCheckBox("Add datum");
		chbAuteurDecor = new JCheckBox("Add auteur");
		
		panelNorth.add(lbKiez);
		panelNorth.add(cbxRapporten);
		
		panelSouth.add(chbDatumDecor);
		panelSouth.add(chbAuteurDecor);
		
		main.add(panelNorth, BorderLayout.NORTH);
		main.add(txtRapport, BorderLayout.CENTER);
		main.add(panelSouth, BorderLayout.SOUTH);
	}
	
	
	
	/**
	 * @return the cbxRapporten
	 */
	public String getCbxRapporten()
	{
		return cbxRapporten.getSelectedItem().toString();
	}

	/**
	 * @param txtRapport the txtRapport to set
	 */
	public void setTxtRapport(String rapport)
	{
		this.txtRapport.setText(rapport);
	}

	/**
	 * @return the chbDatumDecor
	 */
	public Boolean isChbDatumDecorSelected()
	{
		return chbDatumDecor.isSelected();
	}

	/**
	 * @return the chbAuteurDecor
	 */
	public Boolean isChbAuteurDecorSelected()
	{
		return chbAuteurDecor.isSelected();
	}

	public void addCbxRapportenListener(ActionListener listenForSelectRapport)
	{
		cbxRapporten.addActionListener(listenForSelectRapport);
	}
	
	public void addChbDatumDecorListener(ItemListener listenForChbDatumDecor)
	{
		chbDatumDecor.addItemListener(listenForChbDatumDecor);
	}
	public void addChbAuteurDecorListener(ItemListener listenForChbAuteurDecor)
	{
		chbAuteurDecor.addItemListener(listenForChbAuteurDecor);
	}	
	
}

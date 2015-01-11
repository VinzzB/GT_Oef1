package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import javax.swing.*;
import model.Leraar;
import model.opdrachten.OpdrachtCategorie;

/** @author      Nathalie Mathieu*/

public class OpdrachtPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3076576153554349798L;
	private JLabel lbl_id;
	private JLabel lbl_vraag;
	private JLabel lbl_antwoord;
	private JLabel lbl_type;
	private JLabel lbl_auteur;
	private JLabel lbl_registratie;
	private JLabel lbl_hints; 
	private JLabel lbl_maxTijd;
	private JLabel lbl_categorie;
	private JLabel lbl_maxPogingen;
	
	private JTextField tf_id;
	private JTextField tf_vraag;
	private JTextField tf_antwoord;
	private JTextField tf_type;
	private JTextField tf_registratie;
	private JTextField tf_hints;
	private JTextField tf_maxTijd;
	private JTextField tf_maxPogingen;
	
	private JComboBox<OpdrachtCategorie> cb_categorie;
	private JComboBox<Leraar> cb_leraar;
	
	private JList<String> lst_type;
	
	private JButton btn_add;
	private JButton btn_edit;
	private JButton btn_save;
	private JButton btn_delete;
	
	public OpdrachtPanel()
	{
		Dimension size = getPreferredSize();
		size.width = 10;
		setPreferredSize(size);
		
		setBorder(BorderFactory.createTitledBorder("Details"));
		
		lbl_id = new JLabel("ID: ");
		lbl_vraag = new JLabel("Vraag: ");
		lbl_antwoord = new JLabel("Antwoord: ");
		lbl_type = new JLabel("Type: ");
		lbl_auteur = new JLabel("Auteur: ");
		lbl_registratie = new JLabel("Datum registratie: ");
		lbl_hints = new JLabel("Hint: ");
		lbl_maxTijd = new JLabel("Maximum tijd in sec: ");
		lbl_categorie = new JLabel("Categorie: ");
		lbl_maxPogingen = new JLabel("Maximum aantal pogingen: ");
		
		tf_id = new JTextField(5);
		tf_vraag = new JTextField(50);
		tf_antwoord = new JTextField(50);
		tf_type = new JTextField(50);
		tf_registratie = new JTextField(10);
		tf_hints = new JTextField(25);
		tf_maxTijd = new JTextField(4);
		tf_maxPogingen = new JTextField(4);
		
		cb_categorie = new JComboBox<OpdrachtCategorie>(OpdrachtCategorie.values());
		cb_leraar = new JComboBox<Leraar>(Leraar.values());
		
		String[]test = {"Opsomming", "Meerkeuze", "Reproductie"};
		lst_type = new JList<String>(test);
		lst_type.setVisibleRowCount(3);
		lst_type.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);		
		Dimension d = new Dimension(300, 100);
		lst_type.setPreferredSize(d);
		
		btn_add = new JButton("Add");
		btn_edit = new JButton("Edit");
		btn_save = new JButton("Save");
		btn_delete = new JButton("Delete");		
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		
		//First column
		gc.anchor = GridBagConstraints.LINE_END;
		
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.gridy = 0;		
		add(lbl_id, gc);
		
		gc.gridx = 0;
		gc.gridy = 1;		
		add(lbl_vraag, gc);
		
		gc.gridx = 0;
		gc.gridy = 2;		
		add(lbl_antwoord, gc);
		
		gc.gridx = 0;
		gc.gridy = 3;		
		add(lbl_type, gc);
		
		gc.gridx = 0;
		gc.gridy = 4;	
		add(lbl_auteur, gc);
		
		gc.gridx = 0;
		gc.gridy = 5;		
		add(lbl_registratie, gc);		
		
		gc.gridx = 0;
		gc.gridy = 6;		
		add(lbl_hints, gc);
		
		gc.gridx = 0;
		gc.gridy = 7;
		add(lbl_maxTijd, gc);
		
		gc.gridx = 0;
		gc.gridy = 8;
		add(lbl_categorie, gc);
		
		gc.gridx = 0;
		gc.gridy = 9;
		add(lbl_type, gc);
		
		gc.gridx = 0;
		gc.gridy = 10;
		add(lbl_maxPogingen, gc);
		
		//Second column
		gc.anchor = GridBagConstraints.LINE_START;
		gc.gridx = 1;
		gc.gridy = 0;		
		add(tf_id, gc);
		
		gc.gridx = 1;
		gc.gridy = 1;		
		add(tf_vraag, gc);
		
		gc.gridx = 1;
		gc.gridy = 2;		
		add(tf_antwoord, gc);
		
		gc.gridx = 1;
		gc.gridy = 3;		
		add(tf_type, gc);
		
		gc.gridx = 1;
		gc.gridy = 4;		
		add(cb_leraar, gc);
		
		gc.gridx = 1;
		gc.gridy = 5;		
		add(tf_registratie, gc);
		
		gc.gridx = 1;
		gc.gridy = 6;
		add(tf_hints, gc);
		
		gc.gridx = 1;
		gc.gridy = 7;
		add(tf_maxTijd, gc);		
		
		gc.gridx = 1;
		gc.gridy = 8;
		add(cb_categorie,gc);		
		
		gc.gridx = 1;
		gc.gridy = 9;
		add(new JScrollPane(lst_type),gc);		
		
		gc.gridx = 1;
		gc.gridy = 10;
		add(tf_maxPogingen,gc);	
				
		//last row
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.weighty = 10;
		gc.insets = new Insets(5, 5, 1, 1);
		
		gc.gridx = 0;
		gc.gridy = 11;				
		add(btn_add, gc);
		
		gc.gridx = 0;
		gc.gridy = 12;				
		add(btn_edit, gc);
		
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(5, 5, 1, 1);
		gc.gridx = 1;
		gc.gridy = 11;			
		add(btn_save, gc);
		
		gc.gridx = 1;
		gc.gridy = 12;				
		add(btn_delete, gc);	
	}
	
	public int getID()
	{
		return Integer.parseInt(this.tf_id.getText());
	}
	
	public String getVraag()
	{
		return this.tf_vraag.getText();
	}
	
	public String getAntwoord()
	{
		return this.tf_antwoord.getText();
	}
	
	public String getType()
	{
		return this.tf_type.getText();
	}
	
	public String getAuteur()
	{
		return this.cb_leraar.getSelectedItem().toString();
	}
	
	public String getRegistratieDatum()
	{
		return this.tf_registratie.getText();
	}
	
	public String getHint()
	{
		return this.tf_hints.getText();
	}
	
	public String getCategorie()
	{
		return this.cb_categorie.getSelectedItem().toString();
	}
	
	public int getMaxTijd()
	{
		return Integer.parseInt(this.tf_maxTijd.getText());
	}
	
	public int getMaxPogingen()
	{
		return Integer.parseInt(this.tf_maxPogingen.getText());
	}
	
	public void setID(int ID)
	{
		 this.tf_id.setText(Integer.toString(ID));
	}
	
	public void setVraag(String vraag)
	{
		 this.tf_vraag.setText(vraag);
	}

	public void setAntwoord(String antwoord)
	{
		 this.tf_antwoord.setText(antwoord);
	}	

	public void setType(String type)
	{
		 this.tf_type.setText(type);
	}

	public void setAuteur(String auteur)
	{
		 this.cb_leraar.setSelectedItem(auteur);
	}

	public void setRegistratie(String registratie)
	{
		 this.tf_registratie.setText(registratie);
	}
	
	public void setHint(String hint)
	{
		 this.tf_hints.setText(hint);
	}
	
	public void setMaxPogingen(int pogingen)
	{
		this.tf_maxPogingen.setText(Integer.toString(pogingen));
	}
	
	public void addAddButtonListener(ActionListener listenForAddButton)
	{
		btn_add.addActionListener(listenForAddButton);
	}
	
	public void addDeleteButtonListener(ActionListener listenForDeleteButton)
	{
		btn_delete.addActionListener(listenForDeleteButton);
	}

}

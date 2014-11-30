package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*;

/**@author      Nathalie Mathieu*/

public class OpdrachtPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OpdrachtPanel()
	{
		Dimension size = getPreferredSize();
		size.width = 10;
		setPreferredSize(size);
		
		setBorder(BorderFactory.createTitledBorder("Details"));
		
		JLabel lbl_id = new JLabel("ID: ");
		JLabel lbl_vraag = new JLabel("Vraag: ");
		JLabel lbl_antwoord = new JLabel("Antwoord: ");
		JLabel lbl_type = new JLabel("Type: ");
		JLabel lbl_auteur = new JLabel("Auteur: ");
		JLabel lbl_registratie = new JLabel("Datum registratie: ");
		
		JTextField tf_id = new JTextField(5);
		JTextField tf_vraag = new JTextField(50);
		JTextField tf_antwoord = new JTextField(50);
		JTextField tf_type = new JTextField(50);
		JTextField tf_auteur = new JTextField(50);
		JTextField tf_registratie = new JTextField(10);
		
		JButton btn_add = new JButton("Add");
		JButton btn_edit = new JButton("Edit");
		JButton btn_save = new JButton("Save");
		JButton btn_delete = new JButton("Delete");
		
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		
		//First column
		gc.anchor = GridBagConstraints.LINE_END;
		
		gc.weightx = 0.3;
		gc.weighty = 0.3;
		
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
		
		add(tf_auteur, gc);
		
		gc.gridx = 1;
		gc.gridy = 5;
		
		add(tf_registratie, gc);
		
		//last row
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.weighty = 10;
		gc.insets = new Insets(5, 5, 1, 1);
		
		gc.gridx = 0;
		gc.gridy = 6;		
		
		add(btn_add, gc);
		
		gc.gridx = 0;
		gc.gridy = 7;		
		
		add(btn_edit, gc);
		
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(5, 5, 1, 1);
		gc.gridx = 1;
		gc.gridy = 6;		
		
		add(btn_save, gc);
		
		gc.gridx = 1;
		gc.gridy = 7;		
		
		add(btn_delete, gc);	
		
		
		
		
	}
}


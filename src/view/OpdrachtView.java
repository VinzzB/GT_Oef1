package view;

import java.awt.BorderLayout;
import java.awt.Container;


import javax.swing.*;


/**@author      Nathalie Mathieu*/

public class OpdrachtView extends JFrame {

	
	private static final long serialVersionUID = 1L;	
	private String[] columnNames = {"ID","Vraag", "Antwoord", "Type", "Auteur", "Registratie"};
	private Object[][] data = {{null, null, null, null, null, null}};
	//private JTable tabel;
	private OpdrachtPanel opdrachtPanel;
	private JScrollPane scrollPane;
		
	public OpdrachtView()
	{
		setTitle("Opdrachten");	
		//set layout manager
		getContentPane().setLayout(new BorderLayout());
		
		//create swing component
		 JTable tabel = new JTable(data,columnNames);
		 
		 scrollPane = new JScrollPane(tabel);
		 
		 opdrachtPanel = new OpdrachtPanel();
		
		//add swing components to content pane
		Container contPane = getContentPane();
		contPane.add(scrollPane, BorderLayout.NORTH);
		contPane.add(opdrachtPanel, BorderLayout.CENTER);		
	}
	
	public OpdrachtPanel getOpdrachtPanel()
	{
		return this.opdrachtPanel;
	}
	
	public JScrollPane getScrollPane()
	{
		return this.scrollPane;
	}
	
public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable(){	
			public void run() 
			{
				JFrame frame = new OpdrachtView();
				frame.setSize(800,900);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});

	}

}



package view;

import javax.swing.JOptionPane;

import persistency.*;

public class LeesDatabaseGui 
{	
	//private Class<? extends IDatabaseStrategy> dbStrategy;
	
	public LeesDatabaseGui() {}
	
	public String kiesDatabaseStrategy()
	{
		Object selectie = null;
		selectie =  JOptionPane.showInputDialog(null,"Selecteer Database",
                                       "Database Selection", JOptionPane.INFORMATION_MESSAGE, null, 
                                       MogelijkeDatabasen.values(), MogelijkeDatabasen.TXTbestand);		
		//if (selectie != null) DatabaseHandler.setDatabaseStrategy(selectie.toString()); --> VOOR CONTROLLER!
		
		return selectie.toString(); // dbStrategy.getName();
	}
}





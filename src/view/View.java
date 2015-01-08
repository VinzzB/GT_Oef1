package view;

import javax.swing.JFrame;

import controller.*;
import model.*;
import persistency.*;

@SuppressWarnings("serial")
public class View extends JFrame
{	
	protected DatabaseHandler db = OpstartController.getDatabaseStrategy();
	protected QuizCatalogus quizCatalogus = Catalogi.get().getQuizzen();
	protected OpdrachtCatalogus opdrachtCatalogus = Catalogi.get().getOpdrachten();
	
	private Controller controller;

	public void setController(Controller controller) 
	{
		this.controller = controller;
	}

	public Controller getController() 
	{
		return this.controller;
	}
	
	public View() {}
	
	public View (String title)
	{
		super(title);
	}
}

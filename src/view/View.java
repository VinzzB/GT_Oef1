package view;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Formatter;

import javax.swing.JFrame;

import src.controller.Controller;
import src.driver.Driver;
import src.model.QuizCatalogus;
import src.model.QuizOpdracht;
import src.model.opdracht.OpdrachtCatalogus;
import src.persistency.DatabaseHandler;

@SuppressWarnings("serial")
public class View extends JFrame
{	
	protected DatabaseHandler db = Driver.getDatabaseStrategy();
	protected QuizCatalogus quizCatalogus = Driver.getQuizCatalogus();
	protected OpdrachtCatalogus opdrachtCatalogus = Driver.getOpdrachtCatalogus();
	protected ArrayList<QuizOpdracht> quizOpdrachten = Driver.getQuizOpdrachten();
	
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

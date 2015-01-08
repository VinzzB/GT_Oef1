package model.Lijsten;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.TreeSet;

import model.Quiz;
import model.QuizComparator_AantalOpdrachten_Onderwerp;
import persistency.Catalogi;

/**
 * Maak een klasse die alle quizen uit de quizdatabase leest en op de console 
 * een gesorteerd overzicht toont van deze quizen. 
 * De quizen met de meeste opdrachten staan vooraan en de quizen met hetzelfde 
 * aantal  opdrachten zijn geordend alfabetisch op onderwerpnaam. 
 * 
 * @author Natalia
 *
 */
public class QuizzenSorteerd implements ILijst
{
	ArrayList <Quiz> quizen = Catalogi.get().getQuizzen().getQuizzen();		
	
	TreeSet<Quiz> quizenTree = new TreeSet<Quiz>(new QuizComparator_AantalOpdrachten_Onderwerp());

	public QuizzenSorteerd()
	{
		quizenTree.addAll(quizen);
	}

	@Override
	public String getLijst()
	{
		String output ="";
		Iterator<Quiz> it = quizenTree.iterator();
		while (it.hasNext())
		{
			Quiz quiz = it.next();
			output += String.format("%3d  %-20s\n", quiz.getOpdrachten().size(), quiz.getOnderwerp());
		}
		return output;
	}

}

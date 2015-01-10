package model.quizStatus;

import model.Quiz;
/***
 * 
 * Bevat alle mogelijke statussen voor een Quiz. Een statusobject kan niet aangemaakt worden vanuit een ander package!
 * 
 * @author bloemevi
 * 
 */
public enum Statussen
{
	/**
	 * Een leraar kan de quiz bewerken. De quiz is niet beschikbaar voor leerlingen.  
	 */
	InConstructie("In constructie", model.quizStatus.Inconstructie.class),
	
	/**
	 * De quiz is afgewerkt maar nog niet zichtbaar voor de leerlingen
	 */
	Afgewerkt("Afgewerkt", model.quizStatus.Afgewerkt.class),
	
	/**
	 * De quiz kan gestart worden door de leerlingen.
	 */
	Opengesteld("Opengesteld", model.quizStatus.Opengesteld.class),
	
	/**
	 * Het is de laatste kans om deze quiz te spelen.
	 */	
	LaatsteKans("Laatste kans", model.quizStatus.LaatsteKans.class),
	
	/**
	 * De quiz is afgesloten en bijgevolg niet meer beschikbaar voor de leerlingen.
	 */	
	Afgesloten("Afgesloten", model.quizStatus.LaatsteKans.class);
	
	private final String omschrijving;
	private final QuizStatus quizState;
	
	private Statussen(String omschrijving, Class<? extends QuizStatus> quizState)
	{
		this.omschrijving = omschrijving;
		this.quizState = initClass(quizState);
	}
	
	private QuizStatus initClass(Class<? extends QuizStatus> quizState)
	{
			try
			{ return QuizStatus.class.cast(quizState.newInstance()); }
			catch (InstantiationException | IllegalAccessException e)
			{ }
			return null;
	}
	
	public QuizStatus Instance()
	{ return quizState; }
	
	public String Omschrijving()
	{
		return omschrijving;
	}	
}


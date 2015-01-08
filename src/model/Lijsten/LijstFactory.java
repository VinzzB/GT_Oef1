package model.Lijsten;

public class LijstFactory
{
	public static ILijst getRapport(String lijstType)
	{
		switch(lijstType)
		{
		case "OpdrachtenPerCategorie":
			return new OpdrachtenPerCategorie();
		case "QuizGemenschappelijkeOpdracht":
			return new QuizGemenschappelijkeOpdracht();
		case "QuizzenSorteerd":
			return new QuizzenSorteerd();
			default:
				return null;
		} 
	}
	
	
}

package model.Lijsten;

public class PlusAuteur extends Decorator
{

	private String auteur = "logged person";
	
	public PlusAuteur(ILijst rapport)
	{
		super(rapport);
	}
	
	@Override
	public String getLijst()
	{
		return rapport.getLijst() + auteur;
	}

}

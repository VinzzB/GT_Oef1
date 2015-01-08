package model.Lijsten;

public abstract class Decorator implements ILijst
{
	protected ILijst rapport;
	
	public Decorator(){	}
	
	public Decorator(ILijst rapport)
	{
		this.rapport = rapport;
	}

}

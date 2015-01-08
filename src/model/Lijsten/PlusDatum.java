package model.Lijsten;

import utils.date.gregorian.*;

public class PlusDatum extends Decorator
{
	private Datum datum = new Datum();
	
	public PlusDatum(ILijst rapport)
	{
		super(rapport);
	}
	@Override
	public String getLijst()
	{
		return datum.toString() + rapport.getLijst();
	}

}

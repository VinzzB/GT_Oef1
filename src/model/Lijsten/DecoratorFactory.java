package model.Lijsten;

public class DecoratorFactory
{
	public static ILijst getDecorator(String decoratorType, ILijst toDecorate)
	{
		switch(decoratorType)
		{
		case "PlusDatum":
			return new PlusDatum(toDecorate);
		case "PlusAuteur":
			return new PlusAuteur(toDecorate);
			default:
				return null;
		}
	}

}

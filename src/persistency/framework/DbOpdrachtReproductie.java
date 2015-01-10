package persistency.framework;

import persistency.DbSqlHandler;
import model.Opdracht;
import model.OpdrachtTypen;
import model.Reproductie;

public class DbOpdrachtReproductie extends DbOpdrachtBase
{


	private int minAantalJuisteTrefwoorden = 0;

	DbOpdrachtReproductie(Reproductie opdracht)
	{
		super(opdracht);
		this.minAantalJuisteTrefwoorden = opdracht.getMinAantalJuisteTrefwoorden();
	}

	@Override
	public OpdrachtTypen getType()
	{		
		return OpdrachtTypen.REPRODUCTIE;
	}

	@Override
	public String[] asStringArray()
	{
		String[] dataRow = new String[11];
		super.fillStringArray(dataRow);
		dataRow[10] = Integer.toString(minAantalJuisteTrefwoorden);
		return dataRow;
	}
	
	/**
	 * @return the minAantalJuisteTrefwoorden
	 */
	public int getMinAantalJuisteTrefwoorden()
	{
		return minAantalJuisteTrefwoorden;
	}

	/**
	 * @param minAantalJuisteTrefwoorden the minAantalJuisteTrefwoorden to set
	 */
	public void setMinAantalJuisteTrefwoorden(int minAantalJuisteTrefwoorden)
	{
		this.minAantalJuisteTrefwoorden = minAantalJuisteTrefwoorden;
	}

	@Override
	public Opdracht CreateOpdracht()
	{
		try
		{ return new Reproductie(this); }
		catch (Exception e)
		{ return null; }
	}

	@Override
	public void SaveData(DbSqlHandler sqlHandler)
	{
		//TODO: not implemented yet...
	}

}

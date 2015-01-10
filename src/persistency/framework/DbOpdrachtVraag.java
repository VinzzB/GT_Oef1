package persistency.framework;

import java.sql.SQLException;
import javax.sql.RowSet;
import persistency.DbSqlHandler;
import model.Opdracht;
import model.OpdrachtTypen;
import model.OpdrachtVraag;

public class DbOpdrachtVraag extends DbOpdrachtBase {

	DbOpdrachtVraag(Opdracht opdracht) {
		super(opdracht);
	}

	public DbOpdrachtVraag(String[] dbData) {
		super(dbData);
	}

	public DbOpdrachtVraag(RowSet sqlData) throws SQLException
	{
		super(sqlData);		
	}

	@Override
	public OpdrachtTypen getType() {
		return OpdrachtTypen.VRAAG;
	}

	@Override
	public String[] asStringArray() {
		String[] dataRow = new String[10];
		super.fillStringArray(dataRow);
		return dataRow;
	}

	@Override
	public Opdracht CreateOpdracht() 
	{		
		try
		{ return new OpdrachtVraag(this); }
		catch (Exception e)
		{ return null; }
	}

	@Override
	public void SaveData(DbSqlHandler sqlHandler)
	{
		//We are in Opdracht Vraag, Do Nothing here		
	}
}
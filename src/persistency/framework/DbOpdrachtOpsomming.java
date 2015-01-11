package persistency.framework;

import java.sql.SQLException;
import javax.sql.RowSet;
import persistency.DbSqlHandler;
import model.Opdracht;
import model.opdrachten.OpdrachtTypen;
import model.opdrachten.Opsomming;

public class DbOpdrachtOpsomming extends DbOpdrachtBase {

	boolean inJuisteVolgorde;
	
	DbOpdrachtOpsomming(String[] dataRow) {
		super(dataRow);
		inJuisteVolgorde = Boolean.getBoolean(dataRow[10]);
	}
	
	DbOpdrachtOpsomming(RowSet dataRow) throws SQLException {
		super(dataRow);
		inJuisteVolgorde = dataRow.getBoolean("inJuisteVolgorde");
	}

	public DbOpdrachtOpsomming(Opsomming opdracht) {
		super(opdracht);
		inJuisteVolgorde = opdracht.getInJuisteVolgorde();
	}

	public boolean getInJuisteVolgorde() {
		return inJuisteVolgorde;
	}

	@Override
	public OpdrachtTypen getType() {
		return OpdrachtTypen.OPSOMMING;
	}

	@Override
	public String[] asStringArray() {
		String[] dataRow = new String[11];
		super.fillStringArray(dataRow);
		dataRow[10] = Boolean.toString(inJuisteVolgorde);
		return dataRow;
	}

	@Override
	public Opdracht CreateOpdracht()
	{
		try
		{ return new Opsomming(this); }
		catch (Exception e)
		{ return null;}
	}

	@Override
	public void SaveData(DbSqlHandler sqlHandler) throws SQLException
	{
		sqlHandler.SaveOpsomming(this);		
	}
}


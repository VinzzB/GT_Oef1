package persistency.framework;


import java.sql.SQLException;
import javax.sql.RowSet;
import persistency.DbSqlHandler;
import utils.Arrays;
import model.Opdracht;
import model.opdrachten.Meerkeuze;
import model.opdrachten.OpdrachtTypen;

public class DbOpdrachtMeerkeuze extends DbOpdrachtBase {
	
	private	String[] keuzen;
	private final String Delimiter = ";";
	DbOpdrachtMeerkeuze(String[] dataRow) {
		super(dataRow);
		keuzen = dataRow[10].split(Delimiter);
	}
	
	DbOpdrachtMeerkeuze(RowSet row) throws SQLException
	{
		super(row);
		keuzen = row.getString("keuzen").split(";");
	}
	
	public DbOpdrachtMeerkeuze(Meerkeuze opdracht) {
		super(opdracht);
		keuzen = opdracht.getKeuzen();
	}
	public String[] getKeuzen() {
		return keuzen;
	}
	@Override
	public OpdrachtTypen getType() {
		return OpdrachtTypen.MEERKEUZE;
	}
	@Override
	public String[] asStringArray() {
		String[] dataRow = new String[11];
		super.fillStringArray(dataRow);
		dataRow[10] = Arrays.Join(Delimiter, keuzen);
		return dataRow;
	}
	@Override
	public Opdracht CreateOpdracht() 
	{
		try
		{ return new Meerkeuze(this); }
		catch (Exception e)
		{ return null; }
	}

	@Override
	public void SaveData(DbSqlHandler sqlHandler) throws SQLException
	{
		sqlHandler.SaveMeerkeuzeOpdracht(this);		
	}

}

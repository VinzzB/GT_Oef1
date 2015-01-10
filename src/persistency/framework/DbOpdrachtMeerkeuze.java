package persistency.framework;


import utils.Arrays;
import model.Meerkeuze;
import model.Opdracht;
import model.OpdrachtTypen;

public class DbOpdrachtMeerkeuze extends DbOpdrachtBase {
	
	private	String[] keuzen;
	private final String Delimiter = ";";
	DbOpdrachtMeerkeuze(String[] dataRow) {
		super(dataRow);
		keuzen = dataRow[10].split(Delimiter);
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
	public Opdracht CreateOpdracht() throws Exception
	{
		return new Meerkeuze(this);
	}

}

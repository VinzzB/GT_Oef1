package persistency.framework;

import model.Opdracht;
import model.Opsomming;
import model.OpdrachtTypen;

public class DbOpdrachtOpsomming extends DbOpdrachtBase {

	boolean inJuisteVolgorde;
	
	DbOpdrachtOpsomming(String[] dataRow) {
		super(dataRow);
		inJuisteVolgorde = Boolean.getBoolean(dataRow[10]);
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
	public Opdracht CreateOpdracht() throws Exception
	{
		return new Opsomming(this);
	}
}


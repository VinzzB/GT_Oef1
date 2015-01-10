package persistency.framework;

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
	public Opdracht CreateOpdracht() throws Exception
	{		
		return new OpdrachtVraag(this);
	}
}
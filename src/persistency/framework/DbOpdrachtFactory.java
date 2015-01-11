package persistency.framework;

import java.sql.SQLException;
import javax.sql.RowSet;
import model.*;
import model.opdrachten.Meerkeuze;
import model.opdrachten.OpdrachtTypen;
import model.opdrachten.Opsomming;

public class DbOpdrachtFactory {

	public static DbOpdrachtBase getDbOpdracht(OpdrachtTypen typeOpdracht, String[] dataRow)
	{		
		switch (typeOpdracht) {
		case VRAAG: // "OpdrachtVraag":
			return new DbOpdrachtVraag(dataRow);		
		case MEERKEUZE: // "OpdrachtMeerkeuze":
			return new DbOpdrachtMeerkeuze(dataRow);
		case OPSOMMING: // "OpdrachtOpsomming":
			return new DbOpdrachtOpsomming(dataRow);
		default:
			return null;
		}		
	}
	public static DbOpdrachtBase getDbOpdracht(OpdrachtTypen typeOpdracht, RowSet sqlData)
	{	
		try
		{
			switch (typeOpdracht) {
			case VRAAG: 		return new DbOpdrachtVraag(sqlData);
			case MEERKEUZE: 	return new DbOpdrachtMeerkeuze(sqlData);
			case OPSOMMING:  	return new DbOpdrachtOpsomming(sqlData);
			default:  			return null;
			}	
		}
		catch (SQLException e)
		{ }		
		return null;
	}
	public static DbOpdrachtBase getDbOpdracht(String typeOpdracht, String[] dataRow)
	{
		return getDbOpdracht(OpdrachtTypen.valueOf(typeOpdracht), dataRow);
	}
	
	public static DbOpdrachtBase getDbOpdracht(Opdracht opdracht)
	{
		// or evaluate as if(opdracht instanceOf OpdrachtVraag), ...
		switch (opdracht.getType()) {
		case VRAAG:
			return new DbOpdrachtVraag(opdracht);
		case MEERKEUZE:
			return new DbOpdrachtMeerkeuze((Meerkeuze)opdracht);
		case OPSOMMING:
			return new DbOpdrachtOpsomming((Opsomming)opdracht);
		default:
			return null;
		}
	}
}

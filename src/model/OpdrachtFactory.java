package model;

import persistency.framework.*;

public class OpdrachtFactory 
{
	
	/**
	 * static methode om instantie van opdacht van verschillende type te crieeren
	 * 
	 * @param opdrachtType
	 * @param dbData
	 * @return
	 * @throws NumberFormatException
	 * @throws Exception
	 */
	public static Opdracht getOpdracht(OpdrachtTypen opdrachtType, DbOpdrachtBase TXTbestand) 
			throws NumberFormatException, Exception
	{		
		switch (opdrachtType) 
		{
		case VRAAG: // "OpdrachtVraag":
			return new OpdrachtVraag(TXTbestand);		
		case MEERKEUZE: // "OpdrachtMeerkeuze":
			return new Meerkeuze((DbOpdrachtMeerkeuze)TXTbestand);
		case OPSOMMING: // "OpdrachtOpsomming":
			return new Opsomming((DbOpdrachtOpsomming)TXTbestand);
		case REPRODUCTIE:
			return new Reproductie((DbOpdrachtReproductie)TXTbestand);
		default:
			return null;
		}		
	}
}
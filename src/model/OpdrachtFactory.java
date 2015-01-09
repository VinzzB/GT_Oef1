package model;

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
	public static Opdracht getOpdracht(OpdrachtTypen opdrachtType, String[] TXTbestand) 
			throws NumberFormatException, Exception
	{		
		switch (opdrachtType) 
		{
		case VRAAG: // "OpdrachtVraag":
			return new OpdrachtVraag(TXTbestand);		
		case MEERKEUZE: // "OpdrachtMeerkeuze":
			return new OpdrachtMeerkeuze(TXTbestand);
		case OPSOMMING: // "OpdrachtOpsomming":
			return new OpdrachtOpsomming(TXTbestand);
		case REPRODUCTIE:
			return new Reproductie(TXTbestand);
		default:
			return null;
		}		
	}
}
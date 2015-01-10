//package persistency;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.Scanner;
//
//import model.Quiz;
//import model.QuizOpdracht;
//import model.Opdracht;
//import utils.Constants;
//
///**
// * Maakt mogelijk txt-bestant lesen en schrijven om QUIZ programma laten runnen
// * 
// * @author Natalia
// * @version     1.0                 
// * @since       2014-11-11 
// */
//public class DatabaseTXT extends Database
//{
//	public DatabaseTXT() throws IOException
//	{
//		super();
//		opdrachtenDB = new File(Constants.BESTAND_PATH + properties.getProperty("opdrachten"));
//		quizzenDB = new File(Constants.BESTAND_PATH + properties.getProperty("quizzen"));
//		quizOpdrachtDB = new File(Constants.BESTAND_PATH + properties.getProperty("quizOpdrachten"));
//	}
//	
///**
// * Methode om file te lezen
// */
//	@Override
//	String[][] leesVanBestand(File file) throws IOException
//	{
//		int lines = 0;
//		try
//		{
//			Scanner scanner = new Scanner(file);
//			while(scanner.hasNext())
//			{
//				lines++;
//				String waste = scanner.nextLine();
//			}
//			if (scanner != null)
//			{
//			  scanner.close();
//			}
//		}
//		 catch(FileNotFoundException ex)
//		 {
//			 System.out.println("bestand niet gevonden");
//		 }
//		 catch(Exception ex)
//		 {
//			 System.out.println(ex.getMessage());
//		 }
//		String [][] objecten = new String[lines][];
//		try
//		{
//			Scanner scanner = new Scanner(file);
//			int i = 0;
//			while (scanner.hasNext())
//			{
//		      String lijn = scanner.nextLine();
//		      String[] velden = lijn.split("\t");
//		      objecten[i] = velden;
//		      i++;
//			}
//			if (scanner != null)
//			{
//			  scanner.close();
//			}
//		 }
//		 catch(FileNotFoundException ex)
//		 {
//			 System.out.println("bestand niet gevonden");
//		 }
//		 catch(Exception ex)
//		 {
//			 System.out.println(ex.getMessage());
//		 }
//		 return objecten;
//	}
//
//	/**
//	 * Methode om file weg te schrijven
//	 */	
//	@Override
//	void schrijfNaarBestand(String[] objecten, File file)
//	{
//		try
//		{
//			PrintWriter writer = new PrintWriter(file);
//			for (String object : objecten)
//			{
//				writer.println(object);
//			}
//			if (writer != null)
//			{
//				writer.close();
//			}
//		}
//		catch(Exception ex)
//		{
//			System.out.println(ex.getMessage());
//		}
//	}
//}

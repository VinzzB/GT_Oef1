package persistency;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.sql.rowset.CachedRowSet;

import com.sun.rowset.CachedRowSetImpl;

import src.controller.OpstartController;
import src.model.Leraar;
import src.model.Quiz;
import src.model.QuizCatalogus;
import src.model.QuizOpdracht;
import src.model.opdracht.Opdracht;
import src.model.opdracht.OpdrachtCatalogus;
import src.model.opdracht.OpdrachtCategorie;
import src.model.opdracht.OpdrachtMeerkeuze;
import src.model.opdracht.OpdrachtOpsomming;
import src.model.opdracht.OpdrachtTypen;
import src.model.quizStatus.QuizStatus;
import src.utils.Constants;
import src.utils.GregorianDatum;
import src.utils.LoadProperties;

public class DatabaseMySQL extends Database
{
	private CachedRowSet rowSet;
	private LoadProperties properties;
			
	private DatabaseMySQL() throws IOException
	{
		super();
		this.properties = OpstartController.getProperties();
	}
	@Override
	public void setCatalogus(OpdrachtCatalogus opdrachtCatalogus,
			QuizCatalogus quizCatalogus)
	{
		this.opdrachten = opdrachtCatalogus;
		this.quizzen = quizCatalogus;
	}

	@Override
	public void leesOpdrachten() throws FileNotFoundException, IOException,
			NumberFormatException, Exception
	{
		leesVanBestand(null);
		rowSet.setCommand("SELECT * FROM tblOpdrachten");
		rowSet.execute();
		
		while(rowSet.next())
		{
			opdrachten.voegOpdrachtToe(new Opdracht(rowSet.getInt("OpdrachtID"), 
										rowSet.getString("Vraag"), rowSet.getString("JuisteAntwoord"),
										OpdrachtCategorie.valueOf(rowSet.getString("Categorie")),
										rowSet.getString("Hints"), rowSet.getInt("maxAantalPogingen"),
										rowSet.getInt("maxAntwoordTijd"), 
										new GregorianDatum(rowSet.getDate("datumRegistratie").getTime()),
										Leraar.valueOf(rowSet.getString("auteur")), 
										OpdrachtTypen.valueOf(rowSet.getString("Type"))));
			
		}
		rowSet.close();
	}

	@Override
	public void leesQuzen() throws FileNotFoundException, IOException,
			NumberFormatException, Exception
	{
		leesVanBestand(null);
		rowSet.setCommand("SELECT * FROM tblQuiz");
		rowSet.execute();
		while(rowSet.next())
		{
			quizzen.voegQuizToe(rowSet.getInt("QuizID"), new Quiz(rowSet.getInt("QuizID"), 
					rowSet.getString("Onderwerp"), rowSet.getInt("Leerjaren"),
					rowSet.getBoolean("isTest"), rowSet.getBoolean("isuniekeDeelname"),
					Quiz.vanStringNaarQuizStatus(rowSet.getString("Status")), Leraar.valueOf(rowSet.getString("Auteur")),
					new GregorianDatum(rowSet.getDate("datumVanCreatie").getTime())));
		}
		rowSet.close();
	}

	@Override
	public void kopelQuizOpdrachten() throws FileNotFoundException,
			IOException, SQLException
	{
		leesVanBestand(null);
		rowSet.setCommand("SELECT * FROM tblQuizOpdrachten");
		rowSet.execute();
		
		while(rowSet.next())
		{
			QuizOpdracht.koppelOpdrachtAanQuiz(quizzen.getQuiz(rowSet.getInt("tblQuizID")), 
					opdrachten.getOpdracht(rowSet.getInt("tblOpdrachtID")),
					rowSet.getInt("MaxScore"));
		}
		rowSet.close();
	}

	@Override
	public void safeOpdrachten() throws SQLException, FileNotFoundException, IOException
	{
		schrijfNaarBestand(null, null);

		rowSet.setCommand("INSERT into tblOpdrachten "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		
		for(Opdracht opdracht : opdrachten)
		{
			rowSet.setInt(1, opdracht.getOpdrachtID());
			rowSet.setString(2, opdracht.getVraag());
			rowSet.setString(3, opdracht.getAntwoord());
			rowSet.setString(4, opdracht.getAntwoordHint());
			rowSet.setInt(5, opdracht.getMaxAantalPogingen());
			rowSet.setInt(6, opdracht.getMaxAntwoordTijdInSec());
			rowSet.setString(7, opdracht.getCategorie().toString());
			rowSet.setDate(8, new java.sql.Date(opdracht.getCreatieDatum().getCalendar().getTimeInMillis()));
			rowSet.setString(9, opdracht.getAuteur().toString());
			rowSet.setString(10, opdracht.getType().name());

			rowSet.execute();
		}
		rowSet.close();
		
		rowSet.setCommand("INSERT into tblopdrachtMeerkeuze "
				+ "VALUES (?, ?)");
		for(Opdracht opdracht : opdrachten)
		{
			if(opdracht.getType() == OpdrachtTypen.MEERKEUZE)
			{
				rowSet.setInt(1, opdracht.getOpdrachtID());
				rowSet.setString(2, ((OpdrachtMeerkeuze)opdracht).getKeuzen());
				rowSet.execute();
			}
		}
		rowSet.close();
		
		rowSet.setCommand("INSERT into tblopdrachtOpsomming "
				+ "VALUES (?, ?)");
		for(Opdracht opdracht : opdrachten)
		{
			if(opdracht.getType() == OpdrachtTypen.OPSOMMING)
			{
				rowSet.setInt(1, opdracht.getOpdrachtID());
				rowSet.setBoolean(2, ((OpdrachtOpsomming)opdracht).isInJuisteVolgorde());
				rowSet.execute();
			}
		}
		rowSet.close();
	}

	@Override
	public void safeQuizen() throws SQLException, FileNotFoundException, IOException
	{
	
		rowSet.setCommand("INSERT into tblQuiz "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
		for(Quiz quiz : quizzen)
		{
			rowSet.setInt(1, quiz.getQuizID());
			rowSet.setString(2, quiz.getOnderwerp());
			rowSet.setInt(3, quiz.getLeerjaar());
			rowSet.setString(4, quiz.getAuteur().name());
			rowSet.setBoolean(5, quiz.isTest());
			rowSet.setBoolean(6, quiz.isUniek());
			rowSet.setString(7, quiz.getStatus().toString());
			rowSet.setDate(8, new java.sql.Date(quiz.getDatumVanCreatie().getCalendar().getTimeInMillis()));
			rowSet.execute();
		}
		rowSet.close();
	}

	@Override
	public void safeQuizOpdrachten() throws SQLException, FileNotFoundException, IOException
	{

		rowSet.setCommand("INSERT into tblQuizOpdrachten "
				+ "VALUES (?, ?, ?)");
		for(QuizOpdracht quizopdracht : quizOpdrachten)
		{
			rowSet.setInt(1, quizopdracht.getQuiz().getQuizID());
			rowSet.setInt(2, quizopdracht.getOpdracht().getOpdrachtID());
			rowSet.setInt(3, quizopdracht.getMaxScore());
		}
		rowSet.close();
	}
	
	@Override
	String[][] leesVanBestand(File file) throws FileNotFoundException,
			IOException, SQLException
	{
		try
		{
			rowSet = new CachedRowSetImpl();
	        rowSet.setUrl(this.properties.getProperty(Constants.URL));
	        rowSet.setUsername(this.properties.getProperty(Constants.USER));
	        rowSet.setPassword(this.properties.getProperty(Constants.PASSWORD));
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
	@Override
	void schrijfNaarBestand(String[] objecten, File file) throws SQLException, FileNotFoundException, IOException
	{
		leesVanBestand(null);
		rowSet.setCommand("DELETE FROM tblQuizOpdrachten");
		rowSet.execute();
		leesVanBestand(null);
		rowSet.setCommand("DELETE FROM tblQuiz");
		rowSet.execute();
		leesVanBestand(null);
		rowSet.setCommand("DELETE FROM tblOpdrachten");
		rowSet.execute();
		leesVanBestand(null);
	}
}

package persistency;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.rowset.CachedRowSet;

import com.sun.rowset.CachedRowSetImpl;

import model.QuizCatalogus;
import model.QuizOpdracht;
import model.Opdracht;
import model.OpdrachtCatalogus;
import utils.Constants;
import utils.LoadProperties;

public class DatabaseMySQL implements IDatabaseStrategy
{
	private CachedRowSet rowSet;
	
	protected static OpdrachtCatalogus opdrachten; 
	protected static QuizCatalogus quizzen;
	protected static ArrayList<QuizOpdracht> quizOpdrachten;
	
	protected static LoadProperties properties;
		
	public DatabaseMySQL() throws IOException
	{
		properties = new LoadProperties(new File(Constants.SETTINGS_PATH + 
				Constants.SETTINGS_FILE));
		try
		{
			rowSet = new CachedRowSetImpl();
	        rowSet.setUrl(properties.getProperty(Constants.URL));
	        rowSet.setUsername(properties.getProperty(Constants.USER));
	        rowSet.setPassword(properties.getProperty(Constants.PASSWORD));
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
			System.exit(1);
		}
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
		rowSet.setCommand("SELECT * FROM tbl_opdracht");
		rowSet.execute();
		
		while(rowSet.next())
		{
			opdrachten.voegOpdrachtToe(new Opdracht(rowSet.getObject(1)));
		}
	}

	@Override
	public void leesQuzen() throws FileNotFoundException, IOException,
			NumberFormatException, Exception
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void kopelQuizOpdrachten() throws FileNotFoundException,
			IOException, SQLException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void safeOpdrachten() throws SQLException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void safeQuizen() throws SQLException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void safeQuizOpdrachten() throws SQLException
	{
		// TODO Auto-generated method stub
		
	}

}

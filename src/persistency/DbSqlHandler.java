package persistency;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.rowset.CachedRowSet;
import persistency.framework.*;
import model.OpdrachtTypen;
import utils.Arrays;
import utils.Constants;
import com.sun.rowset.CachedRowSetImpl;
/***
 * Deze Template klasse is enkel voor JDBC SQL statements!
 *  
 * @author Natalia Dyubankova <fornnd@gmail.com>
 * @author bloemevi
 * @version     1.1                 
 * @since       2014-11-11 - 10/01/2015 
 * 
 */
public abstract class DbSqlHandler implements IDatabaseStrategy
{
	
	private CachedRowSet rowSet;
	
	//Changeable datamembers.
	protected String sqlSelectQuizzen = "SELECT * FROM tblQuiz";
	protected String sqlSelectQuizopdrachten = "SELECT * FROM tblQuizOpdrachten";
	protected String sqlSelectOpdrachten = "SELECT * FROM tblOpdrachten";
	protected String sqlInsertQuiz = "INSERT into tblQuiz VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	protected String sqlInsertQuizOpdracht = "INSERT into tblQuizOpdrachten VALUES (?, ?, ?)";
	protected String sqlInsertOpdracht = "INSERT into tblOpdrachten VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	protected String sqlInsertOpdMeerkeuze = "INSERT into tblopdrachtMeerkeuze VALUES (?, ?)";
	protected String sqlInsertOpdOpsomming = "INSERT into tblopdrachtOpsomming VALUES (?, ?)";
	protected String sqlInsertReproductie = "INSERT into tblopdrachtReproductie VALUES (?, ?)";
	protected String sqlDeleteQuizzen = "DELETE * FROM tblQuiz";
	protected String sqlDeleteOpdrachten = "DELETE * FROM tblOpdrachten";
	protected String sqlDeleteQuizOpdrachten = "DELETE * FROM tblQuizOpdrachten"; //zou al leeg moeten zijn via foreign key...
	
	//OVERRIDABLE METHODS
	void SaveOpdrachten(List<DbOpdrachtBase> dbOpdrachten) throws Exception 
	{
		rowSet.setCommand(sqlInsertOpdracht);
		for (DbOpdrachtBase dbO : dbOpdrachten)
		{
			rowSet.setInt(1, dbO.getId());
			rowSet.setString(2, dbO.getVraag());
			rowSet.setString(3, dbO.getJuisteAntwoord());
			rowSet.setString(4, dbO.getHint());
			rowSet.setInt(5, dbO.getMaxAantalPogingen());
			rowSet.setInt(6, dbO.getMaxAntwoordTijd());
			rowSet.setString(7, dbO.getCategorie().toString());
			rowSet.setDate(8, new java.sql.Date(dbO.getDatumRegistratie().getCalendar().getTimeInMillis()));
			rowSet.setString(9, dbO.getAuteur().name());
			rowSet.setString(10, dbO.getType().name());
			rowSet.execute();	
			dbO.SaveData(this); //bewaar gegevens gerelateerd aan opsomming, meerkeuze, reproductie,... 
		}		
	}
	
	void deleteQuizzen() throws SQLException
	{
		rowSet.setCommand("DELETE FROM tblQuiz");
		rowSet.execute();
	}

	void deleteOpdrachten() throws SQLException
	{
		rowSet.setCommand("DELETE FROM tblOpdrachten");
		rowSet.execute();
	}
	
	void deleteQuizOpdrachten() throws SQLException
	{
		rowSet.setCommand("DELETE FROM tblQuizOpdrachten");
		rowSet.execute();
	}
	
	public void SaveMeerkeuzeOpdracht(DbOpdrachtMeerkeuze o) throws SQLException
	{
		rowSet.setCommand(sqlInsertOpdMeerkeuze);
		rowSet.setInt(1, o.getId());
		rowSet.setString(2, Arrays.Join(";", o.getKeuzen()));
		rowSet.execute();
	}
	
	public void SaveOpsomming(DbOpdrachtOpsomming o) throws SQLException
	{
		rowSet.setCommand(sqlInsertOpdOpsomming);
		rowSet.setInt(1, o.getId());
		rowSet.setBoolean(2, o.getInJuisteVolgorde());
		rowSet.execute();
	}	
	
	public void SaveReproductie( DbOpdrachtReproductie o) throws SQLException
	{
		rowSet.setCommand(sqlInsertReproductie);
		rowSet.setInt(1, o.getId());
		rowSet.setInt(2, o.getMinAantalJuisteTrefwoorden());
		rowSet.execute();
	}		
	
	void SaveQuizzen(List<DbQuiz> dbQuizzen) throws SQLException
	{
		rowSet.setCommand(sqlInsertQuiz);
		for (DbQuiz dbQ : dbQuizzen)
		{			
		rowSet.setInt(1, dbQ.getId());
		rowSet.setString(2, dbQ.getOnderwerp());
		rowSet.setString(3, Arrays.Join(";",dbQ.getLeerjaren()));
		rowSet.setString(4, dbQ.getAuteur().name());
		rowSet.setBoolean(5, dbQ.isTest());
		rowSet.setBoolean(6, dbQ.isUniekeDeelname());
		rowSet.setString(7, dbQ.getStatus() == null ? null : dbQ.getStatus().toString());
		rowSet.setDate(8, new java.sql.Date(dbQ.getDatumRegistratie().getCalendar().getTimeInMillis()));
		rowSet.execute();		
		}
		

	}	
	
	void SaveQuizOpdrachten(List<DbQuizOpdracht> dbQuizOpdrachten) throws SQLException
	{
		rowSet.setCommand(sqlInsertQuizOpdracht);
		for (DbQuizOpdracht dbQo : dbQuizOpdrachten)
		{
			rowSet.setInt(1, dbQo.getQuizIndex());
			rowSet.setInt(2, dbQo.getOpdrachtIndex());
			rowSet.setInt(3, dbQo.getMaxScore());
			rowSet.execute();	
		}
		
	}
	
	List<DbQuiz> GetQuizen() throws SQLException
	{
		List<DbQuiz> items = new ArrayList<DbQuiz>();
		rowSet.setCommand(sqlSelectQuizzen);
		rowSet.execute();
		while(rowSet.next())
		{
			items.add(new DbQuiz(rowSet));
		}
		rowSet.close();
		return items;
	}
	
	List<DbOpdrachtBase> GetOpdrachten() throws SQLException
	{
		List<DbOpdrachtBase> items = new ArrayList<>();
		rowSet.setCommand(sqlSelectOpdrachten);
		rowSet.execute();
		while(rowSet.next())
		{
			items.add(DbOpdrachtFactory.getDbOpdracht(OpdrachtTypen.valueOf(rowSet.getString("ClassType")), rowSet));
		}		
		return items;
	}
	
	List<DbQuizOpdracht> getQuizOpdrachten() throws SQLException
	{
		List<DbQuizOpdracht> items = new ArrayList<>();
		rowSet.setCommand(sqlSelectQuizopdrachten);
		rowSet.execute();
		while(rowSet.next())
		{
			items.add(new DbQuizOpdracht(rowSet));
		}
		return items;
	}
	
	
	/**
	 * @return the rowSet
	 */
	public CachedRowSet getRowSet()
	{
		return rowSet;
	}

	void InitRowSet() throws Exception
	{ InitRowSet(true); }
	
	void InitRowSet(boolean doReset) throws Exception //overridable
	{		
		if (rowSet == null || doReset)
		{
			rowSet = new CachedRowSetImpl();
	        rowSet.setUrl(Constants.URL);
	        rowSet.setUsername(Constants.USER);
	        rowSet.setPassword(Constants.PASSWORD);
		}
	}
}

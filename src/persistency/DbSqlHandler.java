package persistency;

import java.sql.SQLException;
import javax.sql.rowset.CachedRowSet;
import persistency.framework.DbOpdrachtBase;
import persistency.framework.DbOpdrachtMeerkeuze;
import persistency.framework.DbOpdrachtOpsomming;
import persistency.framework.DbOpdrachtReproductie;
import persistency.framework.DbQuiz;
import model.Opdracht;
import utils.Arrays;
import utils.Constants;
import utils.Properties;
import com.sun.rowset.CachedRowSetImpl;

/***
 * Deze Template klasse is enkel voor JDBC SQL statements!
 * @author bloemevi
 *
 */
public abstract class DbSqlHandler implements IDatabaseStrategy
{
	
	private CachedRowSet rowSet;
	
	void SaveOpdracht(String query, DbOpdrachtBase o) throws Exception 
	{
	//	InitRowSet();
		rowSet.setCommand(query);
		rowSet.setInt(1, o.getId());
		rowSet.setString(2, o.getVraag());
		rowSet.setString(3, o.getJuisteAntwoord());
		rowSet.setString(4, o.getHint());
		rowSet.setInt(5, o.getMaxAantalPogingen());
		rowSet.setInt(6, o.getMaxAntwoordTijd());
		rowSet.setString(7, o.getCategorie().toString());
		rowSet.setDate(8, new java.sql.Date(o.getDatumRegistratie().getCalendar().getTimeInMillis()));
		rowSet.setString(9, o.getAuteur().name());
		rowSet.setString(10, o.getType().name());
		rowSet.execute();			
	}
	
	void SaveMeerkeuzeOpdracht(String query, DbOpdrachtMeerkeuze o) throws SQLException
	{
		{
			rowSet.setCommand(query);
			rowSet.setInt(1, o.getId());
			rowSet.setString(2, Arrays.Join(";", o.getKeuzen()));
			rowSet.execute();
		}
	}
	
	void SaveOpsomming(String query, DbOpdrachtOpsomming o) throws SQLException
	{
		{
			rowSet.setCommand(query);
			rowSet.setInt(1, o.getId());
			rowSet.setBoolean(2, o.getInJuisteVolgorde());
			rowSet.execute();
		}
	}	
	
	void SaveReproductie(String query, DbOpdrachtReproductie o) throws SQLException
	{
		{
			rowSet.setCommand(query);
			rowSet.setInt(1, o.getId());
			rowSet.setInt(2, o.getMinAantalJuisteTrefwoorden());
			rowSet.execute();
		}
	}		
	
	void SaveQuiz(String query, DbQuiz q) throws SQLException
	{
		{
			rowSet.setCommand(query);
			rowSet.setInt(1, q.getId());
			rowSet.setString(2, q.getOnderwerp());
			rowSet.setString(3, Arrays.Join(";",q.getLeerjaren()));
			rowSet.setString(4, q.getAuteur().name());
			rowSet.setBoolean(5, q.isTest());
			rowSet.setBoolean(6, q.isUniekeDeelname());
			rowSet.setString(7, q.getStatus() == null ? null : q.getStatus().toString());
			rowSet.setDate(8, new java.sql.Date(q.getDatumRegistratie().getCalendar().getTimeInMillis()));
			rowSet.execute();
		}
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

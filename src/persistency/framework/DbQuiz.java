package persistency.framework;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import javax.sql.RowSet;
import model.Leraar;
import model.Quiz;
import model.quizStatus.*;
import utils.Arrays;
import utils.date.gregorian.Datum;

public class DbQuiz {
	int id;
	String onderwerp;
	Datum datumRegistratie;
	Leraar auteur;
	boolean isTest;
	boolean isUniekeDeelname;
	int[] leerjaren;
	Statussen status;
	List<DbQuizOpdracht> quizOpdrachten;
	
	/**
	 * @param quizOpdrachten the quizOpdrachten to set
	 */
	public void setQuizOpdrachten(List<DbQuizOpdracht> quizOpdrachten)
	{
		this.quizOpdrachten = quizOpdrachten;
	}
	/**
	 * Maakt een dbQuizData object van gegevens afkomstig uit een Text database
	 * @param dataRow Een String array als waarden voor de velden in één quiz.
	 */
	public DbQuiz(String[] dataRow, List<DbQuizOpdracht> opdrachten)
	{
		this.id = Integer.parseInt(dataRow[0]);
		this.onderwerp = dataRow[1];		
		setLeerjaren(dataRow[2]);		
		this.isTest = Boolean.parseBoolean(dataRow[3]);
		this.isUniekeDeelname = Boolean.parseBoolean(dataRow[4]);
		this.datumRegistratie = new Datum(dataRow[6]);
		this.auteur = Leraar.valueOf(dataRow[7]);			
		this.status = Statussen.valueOf(dataRow[5]);
		
	}
	public DbQuiz(RowSet dataRow, List<DbQuizOpdracht> opdrachten) throws SQLException
	{
		this(dataRow);
		this.quizOpdrachten = opdrachten;
	}
	
	public DbQuiz(RowSet dataRow) throws SQLException
	{
		this.id = dataRow.getInt("QuizID"); // Integer.parseInt(dataRow[0]);		
		this.onderwerp = dataRow.getString("Onderwerp"); // dataRow[1];		
		setLeerjaren(dataRow.getString("Leerjaren"));// dataRow[2]);		
		this.isTest = dataRow.getBoolean("isTest"); // Boolean.parseBoolean(dataRow[3]);
		this.isUniekeDeelname = dataRow.getBoolean("isuniekeDeelname");// Boolean.parseBoolean(dataRow[4]);
		this.datumRegistratie = new Datum(dataRow.getDate("datumVanCreatie"));
		this.auteur = Leraar.valueOf(dataRow.getString("Auteur"));			
		this.status = Statussen.valueOf(dataRow.getString("Status"));
		//this.quizOpdrachten = opdrachten;
	}
	
	/**
	 * @return the id
	 */
	public int getId()
	{
		return id;
	}

	/**
	 * @return the quizOpdrachten
	 */
	public List<DbQuizOpdracht> getQuizOpdrachten()
	{
		return quizOpdrachten;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id)
	{
		this.id = id;
	}

	public DbQuiz(Quiz q, int id)
	{
		this.id = id;
		this.onderwerp = q.getOnderwerp();
		this.leerjaren = q.getLeerjaren();
		this.isTest = q.isTest();
		this.isUniekeDeelname = q.isUniek();
		this.status = q.getStatus().getEnumType();
		this.datumRegistratie = q.getDatumVanCreatie();
		this.auteur = q.getAuteur();
		this.quizOpdrachten = q.getQuizOpdrachten().stream().map(p -> new DbQuizOpdracht(p)).collect(Collectors.toList());
	}
		
	/**
	 * Maakt een dbQuizData object van gegevens afkomstig uit een MySql database
	 * @param dataRow TODO! probably an incorrect param
	 */
	DbQuiz(Object[] dataRow) {
	
	}
	
	public String getOnderwerp() {
		return onderwerp;
	}
	public Datum getDatumRegistratie() {
		return datumRegistratie;
	}
	public Leraar getAuteur() {
		return auteur;
	}
	public boolean isTest() {
		return isTest;
	}
	public boolean isUniekeDeelname() {
		return isUniekeDeelname;
	}
	public int[] getLeerjaren() {
		return leerjaren;
	}
	public Statussen getStatus() {
		return status;
	}

	public String[] asStringArray()
	{
		String[] data = new String[8];
		data[0] = Integer.toString(id);
		data[1] = getOnderwerp();
		data[2] = Arrays.Join(",", getLeerjaren());
		data[3] = Boolean.toString(isTest());
		data[4] = Boolean.toString(isUniekeDeelname());
		data[5] = getStatus().name();
		data[6] = getDatumRegistratie().getEuropeanFormat();
		data[7] = getAuteur().name();
		return data;
				
	}
	
	private void setLeerjaren(String leerjaren)
	{
		String[] tmpL = leerjaren.split(","); //split param value
		this.leerjaren = new int[tmpL.length]; //allocate array
		for (int i = 0; i < tmpL.length; i++)  //iterate and fill array
		{ this.leerjaren[i] = Integer.parseInt(tmpL[i]); }
	}
	
}

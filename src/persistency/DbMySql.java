package persistency;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import model.Opdracht;
import model.Quiz;
import model.catalogi.Catalogi;
import persistency.framework.DbOpdrachtBase;
import persistency.framework.DbOpdrachtFactory;
import persistency.framework.DbQuiz;
import persistency.framework.DbQuizOpdracht;

/**
 * klasse om objecten naar mysql te lezen en weg te schrijven
 * 
 * @author Natalia Dyubankova <fornnd@gmail.com>
 * @version     1.0                 
 * @since       2014-11-11 
 * @Revisioned Vincent on 10/01/2015:
 */
public class DbMySql extends DbSqlHandler
{

	@Override
	public void leesOpdrachten() throws  Exception
	{
		InitRowSet();
	   List<DbOpdrachtBase> dbo = super.GetOpdrachten();
	   dbo.stream().forEach(p -> Catalogi.getOpdrachten().voegOpdrachtToe(p.getId(), p.CreateOpdracht()));		
	}

	@Override
	public void leesQuizen() throws Exception
	{
		InitRowSet();
		//haal alle quizopdrachten op (ongeacht quiz)
		List<DbQuizOpdracht> dbQo = super.getQuizOpdrachten();
		//Haal alle quizen op
		List<DbQuiz> dbQ = super.GetQuizen();
		//Set QuizOpdrachten in dbQuiz object.
		dbQ.forEach(q -> q.setQuizOpdrachten(dbQo.stream().filter(p -> p.getQuizIndex() == q.getId()).collect(Collectors.toList())));
		//Add alle quizen in cataloog
		dbQ.forEach(q -> Catalogi.getQuizzen().voegQuizToe(q.getId(), new Quiz(q)));		
	}

	@Override
	public void safeOpdrachten() throws Exception
	{
		InitRowSet();
		//init temp db list 
		List<DbOpdrachtBase> dbO = new ArrayList<DbOpdrachtBase>();
		//Fill temp db List
		for(Entry<Integer, Opdracht> opdracht : Catalogi.getOpdrachten()) 
		{ dbO.add(DbOpdrachtFactory.getDbOpdracht(opdracht.getValue())); }
		//Wis voorgaande gegevens
		super.deleteOpdrachten();
		//Bewaar alle Opdrachten (& Meerkeuze of Opsomming data)
		super.SaveOpdrachten(dbO);
		
	}

	@Override
	public void safeQuizen() throws Exception
	{
		InitRowSet();
		List<DbQuiz> dbQ = new ArrayList<>();
		for(Entry<Integer, Quiz> quiz : Catalogi.getQuizzen())
		{ dbQ.add(new DbQuiz(quiz.getValue(), quiz.getKey())); }
		super.deleteQuizzen();
		super.deleteQuizOpdrachten(); //in principe niet nodig. Foreign Key zou zijn werk moeten doen bij verwijderen van Quizen.
		super.SaveQuizzen(dbQ);
	}

}

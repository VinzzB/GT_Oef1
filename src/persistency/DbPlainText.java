package persistency;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.io.FileNotFoundException;
import java.io.IOException;
import utils.Constants;
import utils.Properties;
import model.*;
import persistency.framework.*;
/**
 * Maakt mogelijk txt-bestant lesen en schrijven om QUIZ programma laten runnen
 * 
 * @author Natalia
 * @version     1.0                 
 * @since       2014-11-11
 * @revisioned Vincent on 10/01/2015: - Quizopdrachten gelijktijdig laden met quizen. (conflicteerde anders met Status)
 */
public class DbPlainText extends DbFileHandler
{

	@Override
	public void leesOpdrachten() throws FileNotFoundException, IOException,
			NumberFormatException, Exception
	{
		String filepath = Constants.BESTAND_PATH + Properties.instance().getProperty("opdrachten");
		// Read rows and parse into fields (as array)
		List<String[]> rows = readFileLines(filepath,Constants.DELIMITER);	
		for (String[] row : rows) {		
			//Factory for DbData Object			
			DbOpdrachtBase Dbo = DbOpdrachtFactory.getDbOpdracht(row[1], row);
			//Factory for Opdracht object
			Catalogi.getOpdrachten().voegOpdrachtToe(Dbo.getId(), Dbo.CreateOpdracht());
		}
	}

	@Override
	public void leesQuizen() throws FileNotFoundException, IOException,
			NumberFormatException, Exception
	{
		String filepath = Constants.BESTAND_PATH + Properties.instance().getProperty("quizzen");
		// Read rows and parse into fields (as array)
		List<String[]> rows = readFileLines(filepath,Constants.DELIMITER);
		//Get all Quiz Opdrachten at once
		List<DbQuizOpdracht> quizOpdrachten = leesQuizOpdrachten();
		//Create a new quizen list
		//Map<Integer, Quiz> quizen = new HashMap<Integer, Quiz>();
		for (String[] row : rows) {
			//Parse data
			int id = Integer.parseInt(row[0]);
			//Get opdrachten van huidige quiz (filters quizOpdrachten list)
			List<DbQuizOpdracht> currQuizOpdrachten = quizOpdrachten.stream()
					.filter(p -> p.getQuizIndex() == id)
					.collect(Collectors.toList());
			//create data class (data-framework)
			DbQuiz dataRow = new DbQuiz(row, currQuizOpdrachten);						
			//rebuild Quiz
			Quiz q = new Quiz(dataRow);
			//add to quiz catalog
			Catalogi.getQuizzen().voegQuizToe(id, q);
		}		
	}
	
	private List<DbQuizOpdracht> leesQuizOpdrachten() throws Exception {
		String filepath = Constants.BESTAND_PATH + Properties.instance().getProperty("quizOpdrachten");
		List<String[]> rows = readFileLines(filepath, Constants.DELIMITER);
		List<DbQuizOpdracht> values = new ArrayList<DbQuizOpdracht>();
		for (String[] row : rows) {
			values.add(new DbQuizOpdracht(row));
		}
		return values;
	}

	@Override
	public void safeOpdrachten() throws FileNotFoundException,
			IOException
	{
		String filepath = Constants.BESTAND_PATH + Properties.instance().getProperty("opdrachten");
		//Schrijf opdrachten naar bestand
		List<String[]> opdrData = new ArrayList<String[]>();
		for (Entry<Integer, Opdracht> i : Catalogi.getOpdrachten()) {
			//Fill DbData Object
			DbOpdrachtBase dbDataBase = DbOpdrachtFactory.getDbOpdracht(i.getValue());
			String[] dbRow = dbDataBase.asStringArray(); //fill data array
			dbRow[0] = Integer.toString(i.getKey()); //add key
			opdrData.add(dbRow);			
		}		
		writeFileLines(filepath, opdrData, Constants.DELIMITER);
	}

	@Override
	public void safeQuizen() throws FileNotFoundException,
			IOException
	{
		//Schrijf quizen naar bestand
		List<String[]> quizData = new ArrayList<String[]>();
		//Hold a list with gekoppelde opdrachten
		List<String[]> qoList = new ArrayList<String[]>();
		//iterate through quizen
		for (Entry<Integer, Quiz> i : Catalogi.getQuizzen()) {
			//get quiz data
			DbQuiz dataRow = new DbQuiz(i.getValue(), i.getKey());
			String[] dbRow = dataRow.asStringArray();
			quizData.add(dbRow);			
			//get gekoppelde opdrachten
			//TODO opsplitsen in aparte method??? ( lees/schrijfGekoppeldeQuizOpdrachten() --> Hoe status?) 
			for (QuizOpdracht qo : i.getValue().getQuizOpdrachten()) {
				Opdracht o = qo.getOpdracht();
				int idx = o.getOpdrachtID(); // OR:  Catalogi.getOpdrachten().getIndex(o) 
				qoList.add(new String[] { i.getKey().toString() ,
										Integer.toString(idx) , 
										Integer.toString(qo.getMaxScore()) });
			}			
		}
		//Schrijf quizen naar bestand.
		String quizPath = Constants.BESTAND_PATH + Properties.instance().getProperty("quizzen");
		writeFileLines(quizPath, quizData, Constants.DELIMITER);
		//Schrijf gekoppelde quiz opdrachten naar bestand	
		String quizOpdrachtenPath = Constants.BESTAND_PATH + Properties.instance().getProperty("quizOpdrachten");
		writeFileLines(quizOpdrachtenPath, qoList, Constants.DELIMITER);
	}

}

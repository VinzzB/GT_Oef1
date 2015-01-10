package persistency.framework;

import java.sql.SQLException;
import javax.sql.RowSet;
import model.QuizOpdracht;

public class DbQuizOpdracht {

	private int quizIndex;
	private int opdrachtIndex;
	private int maxScore;
	
	public DbQuizOpdracht(String[] dataRow) {
		this.quizIndex = Integer.parseInt(dataRow[0]);
		this.opdrachtIndex = Integer.parseInt(dataRow[1]);
		this.maxScore = Integer.parseInt(dataRow[2]);		
	}

	public DbQuizOpdracht(RowSet dataRow) throws SQLException {
		this.quizIndex = dataRow.getInt("tblQuizID"); // Integer.parseInt(dataRow[0]);
		this.opdrachtIndex = dataRow.getInt("tblOpdrachtID");// Integer.parseInt(dataRow[1]);
		this.maxScore = dataRow.getInt("MaxScore"); // Integer.parseInt(dataRow[2]);		
	}
	
	public DbQuizOpdracht(QuizOpdracht qo)
	{
		this.quizIndex = qo.getQuiz().getQuizID(); //of: Catalogi.getQuizzen().getIndex(quiz)
		this.opdrachtIndex = qo.getOpdracht().getOpdrachtID(); //of: Catalogi.getOpdrachten().getIndex(opdracht)
		this.maxScore = qo.getMaxScore();
	}
	
	public int getQuizIndex() {
		return quizIndex;
	}

	public int getOpdrachtIndex() {
		return opdrachtIndex;
	}

	public int getMaxScore() {
		return maxScore;
	}
}

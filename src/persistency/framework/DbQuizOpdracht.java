package persistency.framework;

public class DbQuizOpdracht {

	private int quizIndex;
	private int opdrachtIndex;
	private int maxScore;
	
	public DbQuizOpdracht(String[] dataRow) {
		this.quizIndex = Integer.parseInt(dataRow[0]);
		this.opdrachtIndex = Integer.parseInt(dataRow[1]);
		this.maxScore = Integer.parseInt(dataRow[2]);		
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

package model;

public class QuizOpdracht 
{
	private Quiz quiz;
	private Opdracht opdracht;
	private int maxScore;

	
	private QuizOpdracht (Quiz quiz, Opdracht opdracht, int maxScore)
	{
		this.quiz = quiz;
		this.opdracht = opdracht;
		this.maxScore = maxScore;		
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + maxScore;
		result = prime * result
				+ ((opdracht == null) ? 0 : opdracht.hashCode());
		result = prime * result + ((quiz == null) ? 0 : quiz.hashCode());
		return result;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof QuizOpdracht)) {
			return false;
		}
		QuizOpdracht other = (QuizOpdracht) obj;
		if (maxScore != other.maxScore) {
			return false;
		}
		if (opdracht == null) {
			if (other.opdracht != null) {
				return false;
			}
		} else if (!opdracht.equals(other.opdracht)) {
			return false;
		}
		if (quiz == null) {
			if (other.quiz != null) {
				return false;
			}
		} else if (!quiz.equals(other.quiz)) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "QuizOpdracht [maxScore=" + maxScore + "]";
	}

	public int compareTo(QuizOpdracht quizOpdracht)
	{
		return 0;
	}

	public static void koppelOpdrachtAanQuiz(Quiz quiz, Opdracht opdracht, int maxScore)
	{
		QuizOpdracht quizOpdracht = 
		new QuizOpdracht(quiz,opdracht,maxScore);
		quiz.voegQuizOpdrachtToe(quizOpdracht);
		opdracht.voegQuizOpdrachtToe(quizOpdracht);
	}

	public void ontKoppelOpdrachtVanQuiz()
	{
	quiz.verwijderQuizOpdracht(this);
	opdracht.verwijderQuizOpdracht(this);
	}
	
	public Quiz getQuiz() 
	{
	return quiz;
	}
	
	public Opdracht getOpdracht() 
	{
	return opdracht;
	}
}

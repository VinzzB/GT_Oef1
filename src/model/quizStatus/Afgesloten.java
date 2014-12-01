package model.quizStatus;

public class Afgesloten extends QuizStatus
{
    
  private static final Afgesloten statusAfgesloten = new Afgesloten();
  
  public static QuizStatus instance()
  {
      return statusAfgesloten;
  }
}

package model.quizStatus;
/***
 * 
 * @Revisioned bloemevi on 10/01/2015
 *
 */
public class Afgesloten extends QuizStatus
{
    Afgesloten() { /* Singleton via Enumeration! */ }
    
    @Override
    public Statussen getEnumType()
    { return Statussen.Afgesloten; }
}

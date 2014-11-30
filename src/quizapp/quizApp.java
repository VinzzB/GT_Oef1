package quizapp;

import controller.LeerlingController;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Leerling;
import view.LeerlingView;

/**
 *
 * @author Silvia
 */
public class QuizApp
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        LeerlingView leerlingView = new LeerlingView();
        Leerling l = null;
        try
        {
            LeerlingController LeerlingController = new LeerlingController(leerlingView, l);
        }
        catch (Exception ex)
        {
            Logger.getLogger(QuizApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        leerlingView.setVisible(true);
//        System.out.println("Hello World!"); 
    }
    
}

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Leerling;
import view.LeerlingView;

/**
 *
 * @author Silvia
 */
public class LeerlingController
{
    private LeerlingView lView;
    private Leerling leerling;
    
    public LeerlingController(LeerlingView theView, Leerling theLeerling)
    {
        this.lView = theView;
        this.leerling = theLeerling;
        
        this.lView.addLeerlingListener((ActionListener) new LeerlingListener());
    }
    
    class LeerlingListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            int jaar = 0;
            String naam = null;
            
            try
            {
                jaar = lView.gettLeerjaar();
                naam = lView.gettNaamLeerling();
                
                leerling = new Leerling(naam, jaar);
                lView.settControleLeerling("ja");
            }
            catch(Exception ex)
            {
                lView.settControleLeerling("nee");
                lView.displayErrorMessage(ex.getMessage());
            }
        }
    }
}

package view;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author silviamirisola
 */
public class LeerlingView extends JFrame
{
    private JLabel lNaamLeerling = new JLabel("naam leerling: ");
    private JTextField tNaamLeerling = new JTextField(25);
    private JLabel lLeerjaar = new JLabel("leerjaar: ");
    private JTextField tLeerjaar = new JTextField(1);
    private JLabel lControleLeerling = new JLabel("Leerling toegelaten: ");
    private JTextField tControleLeerling = new JTextField(3);
    private JButton voegLeerlingToe = new JButton("Voeg leerling toe");
    
    /**
     *
     */
    public LeerlingView()
    {
        JPanel leerlingPanel = new JPanel();
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 200);
        
        leerlingPanel.add(lNaamLeerling);
        leerlingPanel.add(tNaamLeerling);
        leerlingPanel.add(lLeerjaar);
        leerlingPanel.add(tLeerjaar);
        leerlingPanel.add(voegLeerlingToe);
        leerlingPanel.add(lControleLeerling);
        leerlingPanel.add(tControleLeerling);
        
        this.add(leerlingPanel);
    }

    public String gettNaamLeerling()
    {
        return tNaamLeerling.getText();
    }

    public void settNaamLeerling(String naam)
    {
        this.tNaamLeerling.setText(naam);
    }

    public int gettLeerjaar()
    {
        return Integer.parseInt(tLeerjaar.getText());
    }

    public void settLeerjaar(int jaar)
    {
        this.tLeerjaar.setText(Integer.toString(jaar));
    }
    
    public String gettControleLeerling()
    {
        return tControleLeerling.getText();
    }
    
    public void settControleLeerling(String check)
    {
        this.tControleLeerling.setText(check);
    }
    
    /**
     *
     * @param listenForVoegLeerlingToeButton
     */
    public void addLeerlingListener(ActionListener listenForVoegLeerlingToeButton)
    {
        voegLeerlingToe.addActionListener(listenForVoegLeerlingToeButton);
    }
    
    public void displayErrorMessage(String errorMessage)
    {
        JOptionPane.showMessageDialog(this, errorMessage);
    }
    
}

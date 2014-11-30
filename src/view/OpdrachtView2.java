package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import model.Leraar;
import model.Opdracht;
import model.OpdrachtCategorie;


public class OpdrachtView2 extends JFrame
{

	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = -3998646286756616242L;
	
	private JPanel panelLeft, panelRight;
	private JComboBox<OpdrachtTypen> cbxType;
	private JComboBox<Leraar> cbxLeraar;
	private JComboBox<OpdrachtCategorie> cbxCategorie;
	private JTextField txtVraag, txtAntwoord, txtHint, txtPogingen, txtTijd;
	private JLabel lbType, lbAuteur, lbCategorie, lbVraag, lbAntwoord, 
					lbHint, lbPogingen, lbTijd;
	
	private JButton btnRegistreer;
	
	private Dimension size = new Dimension(700, 400);

	public OpdrachtView2()
	{
		super("Opdracht beheer");
		
		initializeComponents();
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setSize(size);
		this.setVisible(true);
	}
	
	private void initializeComponents()
	{
		// main panel with BorderLayout
		JPanel main = (JPanel) this.getContentPane();
		main.setLayout(new BorderLayout());
		
		//panels
		panelLeft = new JPanel(new GridBagLayout());
		panelRight = new JPanel(new GridBagLayout());
		
		//labels
		lbType = new JLabel("Opdracht Type");
		lbAuteur = new JLabel("Auteur");
		lbCategorie = new JLabel("Opdracht Categorie");
		lbVraag = new JLabel("Vraag");
		lbAntwoord = new JLabel("Antwoord(en)");
		lbHint = new JLabel("Antwoord hint");
		lbPogingen = new JLabel("Maximaal antaal pogingen");
		lbTijd = new JLabel("Maximaal antwoord tijd in sec");
		
		//comboboxes
		cbxType = new JComboBox<OpdrachtTypen>(OpdrachtTypen.values());
		cbxLeraar = new JComboBox<Leraar>(Leraar.values());
		cbxCategorie = new JComboBox<OpdrachtCategorie>(OpdrachtCategorie.values());
		
		//text fields
		txtVraag = new JTextField("Voeg vraag toe", 140);
		txtAntwoord = new JTextField("Voeg antwoord(en) toe", 140);
		txtHint = new JTextField("Voeg antwoord hint toe", 140);
		txtPogingen = new JTextField("Voeg maximaal aantal pogingen toe", 140);
		txtTijd = new JTextField("Voeg maximaal antwoord tijd in seconden toe", 140);
		
		//buttons
		btnRegistreer = new JButton("Registreren");
		
		//set constants for GridBagLayout
		GridBagConstraints c = new GridBagConstraints();
		// for all GridBadPanels:
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(10, 10, 12, 8);
		c.weightx = 1.0;
		c.gridwidth = GridBagConstraints.REMAINDER; // end row
		
		//fill in left panel
		panelLeft.add(lbType, c);
		panelLeft.add(lbAuteur, c);
		panelLeft.add(lbCategorie, c);
		panelLeft.add(lbVraag, c);
		panelLeft.add(lbAntwoord, c);
		panelLeft.add(lbHint, c);
		panelLeft.add(lbPogingen, c);
		panelLeft.add(lbTijd, c);
		
		// fill in right panel
		c.insets = new Insets(10, 10, 5, 5);
		panelRight.add(cbxType, c);
		panelRight.add(cbxLeraar, c);
		panelRight.add(cbxCategorie, c);
		panelRight.add(txtVraag, c);
		panelRight.add(txtAntwoord, c);
		panelRight.add(txtHint, c);
		panelRight.add(txtPogingen, c);
		panelRight.add(txtTijd, c);
		
		main.add(panelLeft, BorderLayout.WEST);
		main.add(panelRight, BorderLayout.CENTER);
		main.add(btnRegistreer, BorderLayout.SOUTH);
	}

	public OpdrachtTypen getOpdrachtType()
	{
		return (OpdrachtTypen)cbxType.getSelectedItem();
	}

	public void setCbxType(OpdrachtTypen opdrachtType)
	{
		this.cbxType.setSelectedItem(cbxType);
	}

	public Leraar getLeraar()
	{
		return (Leraar)cbxLeraar.getSelectedItem();
	}

	public void setLeraar(Leraar auteur)
	{
		this.cbxLeraar.setSelectedItem(cbxLeraar);
	}
	
	public OpdrachtCategorie getCbxCategorie()
	{
		return (OpdrachtCategorie)cbxCategorie.getSelectedItem();
	}

	public void setCategorie(OpdrachtCategorie opdrachtCategorie)
	{
		this.cbxCategorie.setSelectedItem(opdrachtCategorie);
	}	

	public String getVraag()
	{
		return txtVraag.getText();
	}

	public void setTxtVraag(String vraag)
	{
		this.txtVraag.setText(vraag);
	}

	public String getAntwoord()
	{
		return txtAntwoord.getText();
	}

	public void setTxtAntwoord(String antwoord)
	{
		this.txtAntwoord.setText(antwoord);
	}

	public String getHint()
	{
		return txtHint.getText();
	}

	public void setTxtHint(String hint)
	{
		this.txtHint.setText(hint);
	}

	public int getMaxAantalPogingen()
	{
		return Integer.parseInt(txtPogingen.getText());
	}

	public void setTxtPogingen(int maxAantalPogingen)
	{
		this.txtPogingen.setText(Integer.toString(maxAantalPogingen));
	}

	public int getMaxAntwoordTijdInSec()
	{
		return Integer.parseInt(txtTijd.getText());
	}

	public void setTxtTijd(int maxAntwoordTijd)
	{
		this.txtTijd.setText(Integer.toString(maxAntwoordTijd));
	}

	public void addBtnRegistreerListener(ActionListener listenForRegistration)
	{
		btnRegistreer.addActionListener(listenForRegistration);
	}

	public static void main(String[] args)
	{
		OpdrachtView2 ov = new OpdrachtView2();
	}

}

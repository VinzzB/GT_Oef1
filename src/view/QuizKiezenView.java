package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionListener;
import persistency.Catalogi;
import model.Leerling;
import model.Leraar;
import model.Quiz;

@SuppressWarnings("serial")
public class QuizKiezenView extends View
{
	private Leerling leerling;
	private Dimension size = new Dimension(900, 600);
	
	private JPanel main, buttonPanel;
	private JScrollPane paneQuizzen;
	private JList<Quiz> listQuizzen;
	private JButton btnKiez, btnQuit;
	private DefaultListModel<Quiz> model;
	
	public QuizKiezenView()
	{
		super("Kiez de quiz om op te lossen");
		initializeComponents();
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setSize(size);
		this.setVisible(true);
	}
	
	public QuizKiezenView(Leerling leerling)
	{
		super("Kiez de quiz om op te lossen");
		this.leerling = leerling;
		initializeComponents();
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setSize(size);
		this.setVisible(true);
	}
	
	private void initializeComponents()
	{
		// main panel with BorderLayout
		main = (JPanel) this.getContentPane();
		main.setLayout(new BorderLayout());
		main.setBorder(BorderFactory.createTitledBorder("Beschikbare quizzen"));

		//buttons
		btnKiez = new JButton("Neem een deel aan gekozen quiz");
		btnKiez.setEnabled(false);
		btnQuit = new JButton("Sluit");
		
		paneQuizzen = setQuizzenView();
		
		buttonPanel = new JPanel(new FlowLayout());
		buttonPanel.add(btnKiez);
		buttonPanel.add(btnQuit);
		
		main.add(paneQuizzen, BorderLayout.CENTER);
		main.add(buttonPanel, BorderLayout.SOUTH);
	}

	private JScrollPane setQuizzenView()
	{
		model = new DefaultListModel<Quiz>();
		listQuizzen = new JList<Quiz>(model);
		for(Quiz quiz : Catalogi.getQuizzen().getQuizzenPerLeerjaar(leerling.getLeerjaar()))
		{
			model.addElement(quiz);
		}
	
		return new JScrollPane(listQuizzen);
	}
	
	public void setBtnKiez(Boolean enabled)
	{
		this.btnKiez.setEnabled(enabled);
	}
	
	public Quiz getSelectedQuiz()
	{
		if(listQuizzen.getSelectedValue() != null)
			return (Quiz)listQuizzen.getSelectedValue();
		else return null;
	}
	
	//Listeners
	public void addQuizSelectedListener(ListSelectionListener listenForSelectedQuiz)
	{
		listQuizzen.getSelectionModel().addListSelectionListener(listenForSelectedQuiz);
	}
		
	public void addBtnKiezListener(ActionListener listenForBtnKiezListener)
	{
		btnKiez.addActionListener(listenForBtnKiezListener);
	}
	
	public void addBtnQuitListener(ActionListener listenForBtnQuitListener)
	{
		btnQuit.addActionListener(listenForBtnQuitListener);
	}
}

package view;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import utils.Constants;

@SuppressWarnings("serial")
public class InstellingenView extends View
{
	JLabel lbWarning;
	JLabel lbIconWarning;
	
	public InstellingenView()
	{
		super();
		lbWarning = new JLabel("In construction");
		lbIconWarning = new JLabel(new ImageIcon(Constants.IMAGE_PATH + "inconstruction.jpg"));
		this.setLayout(new FlowLayout());
		this.add(lbWarning);
		this.add(lbIconWarning);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setSize(new Dimension(500, 400));
		this.setVisible(true);
	}
}

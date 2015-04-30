package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import util.Complex;
import net.miginfocom.swing.MigLayout;


public class MainFrame extends JFrame {

	int width = 600;
	int height = 600;
	
	JPanel myPanel = new Panel();
	JButton jbt = new JButton("BOOOM");
	public MainFrame(){
		
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		setSize(1280, 800);
	
		myPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		setLayout(new MigLayout("fill")); 
		
		add(myPanel,"grow");
		
	
		
	}
	public JPanel getMyPanel() {
		return myPanel;
	}
	public void setMyPanel(JPanel myPanel) {
		this.myPanel = myPanel;
	}
	
}

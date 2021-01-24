package fr.dauphine.ja.M1MiageApp.projet;

import java.awt.Container;

import javax.swing.JFrame;

/**
 * 
 *This class represent the frame of the GUI
 * @extends : JFrame
 * 
 **/

public class Home extends JFrame{

	private static final long serialVersionUID = 1L;
	private HomePanel panel;

	public Home(){
		Container content = getContentPane();
		panel = new HomePanel();
		content.add(panel);
		setTitle("Home - Morpion Solitaire");
		pack();
		setLocationRelativeTo(null);
	}

	/**
	 * Open the window
	 * 
	 **/
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		JFrame f = new Home();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
	}

}

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class GUI{

	private JFrame frame;
	private BorderLayout layout;

	/**
	 * Menu components
	 */
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenuItem openItem;
	private JMenuItem saveItem;

	
	
	public GUI(Pair[][] schedule) {
		
		frame = new JFrame();
		layout = new BorderLayout();


		//Initialize menu components
		menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		openItem = new JMenuItem("Open");
		saveItem = new JMenuItem("Save");


		//Create menu
		frame.setLayout(layout);
		frame.setJMenuBar(menuBar);
		menuBar.add(fileMenu);
		fileMenu.add(openItem);
		fileMenu.add(saveItem);


		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		frame.pack();
		frame.setVisible(true);
	}	




	
}

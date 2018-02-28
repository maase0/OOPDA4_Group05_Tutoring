import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class GUI extends JFrame{

	private Button quit;
	
	public GUI(Pair[][] schedule) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(0, 6));
		setTitle("Test Title");

		quit = new Button("Quit");
		//quit.addActionListener((ActionEvent ev) -> dispose());
		quit.addActionListener(e -> dispose());
		
		add(quit);
		//add(new Label(""));
		//add(new Button("Quit"));
		add(new Label("Monday"));
		add(new Label("Tuesday"));
		add(new Label("Wednesday"));
		add(new Label("Thursday"));
		add(new Label("Friday"));

		int count = 0;
		for(int i = 1000; i < 1600; i += 100) {
			for(int j = 0; j < 60; j += 15) {
				add(new Label((i + j) + ""));
				for(int x = 0; x < 5; x++) {
					add(new Label(schedule[x][count].getTutor() + ": " + 
					    schedule[x][count].getStudent()));
				}
				count++;
			}
		}
		pack();
		
		
		setVisible(true);
	}	
	
	
}

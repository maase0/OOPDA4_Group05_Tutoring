import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class GUI extends JFrame{

	private JButton quit;
	private JButton switchButtonSV;
	private JButton switchButtonTest;
	private JPanel sv; //the schedule view
	private JPanel test;
	boolean view = true;
	
	
	public GUI(Pair[][] schedule) {
		
		setTitle("Schedule View");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		quit = new JButton("Quit");
		switchButtonSV = new JButton("Switch");
		switchButtonTest = new JButton("Switch");

		quit.addActionListener(e -> dispose());

		switchButtonSV.addActionListener(e -> {setContentPane(test); pack();});
		switchButtonTest.addActionListener(e -> {setContentPane(sv); pack();});


			
		sv = new JPanel(new GridLayout(0, 6));
		sv.add(switchButtonSV);
		sv.add(quit);
	//	addScheduleView(schedule);

		test = new JPanel(new GridLayout(0, 6));
		test.add(switchButtonTest);


		
		setContentPane(sv);
		pack();
		
		
		setVisible(true);
	}	

	private void addScheduleView(Pair[][] schedule) {
		sv.add(quit);
		sv.add(new Label("Monday"));
		sv.add(new Label("Tuesday"));
		sv.add(new Label("Wednesday"));
		sv.add(new Label("Thursday"));
		sv.add(new Label("Friday"));

		int count = 0;
		for(int i = 1000; i < 1600; i += 100) {
			for(int j = 0; j < 60; j += 15) {
				sv.add(new Label((i + j) + ""));
				for(int x = 0; x < 5; x++) {
					sv.add(new Label(schedule[x][count].getTutor() + ": " + 
					    schedule[x][count].getStudent()));
				}
				count++;
			}
		}
	
	}
	
	
}

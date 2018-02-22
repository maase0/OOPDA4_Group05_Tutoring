import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;


public class GUI extends Frame{

	private Label label;
	private Button button;
	
	public GUI() {
		//int c = 6;
		//int r = 33;
		
		setLayout(new GridLayout(0, 6));
		setTitle("Test Title");

		
		add(new Label(""));
		//add(new Button("Quit"));
		add(new Label("Monday"));
		add(new Label("Tuesday"));
		add(new Label("Wednesday"));
		add(new Label("Thursday"));
		add(new Label("Friday"));
		
		for(int i = 1000; i < 1600; i += 100) {
			for(int j = 0; j < 60; j += 15) {
				add(new Label((i + j) + ""));
				for(int x = 0; x < 5; x++) {
					add(new Label("PEOPLE"));
				}
			}
		}
		
		
		setVisible(true);
	}
	
	
	
}

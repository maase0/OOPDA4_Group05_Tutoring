import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;


public class AddTutorPanel extends JPanel
{

	private Scheduler scheduler;
	private ArrayList<Tutor> tutors;

	private JButton existingTutorButton;
	private JPanel newTutorPanel;
	private JPanel existingTutorPanel;


	public AddTutorPanel(Scheduler scheduler, ArrayList<Tutor> tutors)
	{
		this.scheduler = scheduler;
		this.tutors = tutors;

		setLayout(new FlowLayout());

		JButton existingTutorButton = new JButton("TEST");
		add(existingTutorButton);

	}

	

}

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;


public class AddTutorPanel extends JPanel
{

	private Scheduler scheduler;
	private ArrayList<Tutor> tutors;

	private FlowLayout layout;

	private JButton existingTutorButton;
	private JButton newTutorButton;

	private JPanel tutorPrompt;

	private JPanel newTutorPanel;
	private JPanel existingTutorPanel;


	public AddTutorPanel(Scheduler scheduler, ArrayList<Tutor> tutors)
	{
		this.scheduler = scheduler;
		this.tutors = tutors;

		layout = new FlowLayout();
		setLayout(layout);


		tutorPrompt = new JPanel(new FlowLayout());
		newTutorPanel = new JPanel();
		existingTutorPanel = new JPanel();

		JButton existingTutorButton = new JButton("Add an existing tutor");
		tutorPrompt.add(existingTutorButton);


		JButton newTutorButton = new JButton("Add a new tutor");
		tutorPrompt.add(newTutorButton);


		add(tutorPrompt);

	}


	private void switchToNewTutorPanel()
	{
		layout.removeLayoutComponent(tutorPrompt);
		add(newTutorPanel);
		newTutorPanel.repaint();
		validate();
	}

	private void switchToExistingTutorPanel()
	{
		layout.removeLayoutComponent(tutorPrompt);
		add(existingTutorPanel);
		existingTutorPanel.repaint();
		validate();
	}

}

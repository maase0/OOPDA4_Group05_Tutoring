package TutoringScheduler;

/**
 * @author Erich Maas
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public abstract class ScheduleAddFrame extends JFrame implements KeyListener
{
    protected JTextField name;
    protected JTextField year;
    protected JTextField studentID;

    protected JComboBox<String> time;
    protected String[] displayTimes;
    protected int[] times;

    protected JComboBox<String> day;
    protected String[] displayDays;
    protected int[] days;

    protected JButton submit;

    protected GridBagLayout layout;
	protected GridBagConstraints c;
    protected JPanel panel;

	/**
	 * Creates a new ScheduleAddFrame to add students or tutors
	 *
	 * @param day The initial day to schedule the person
	 * @param time The initial time to schedule the person
	 */
    public ScheduleAddFrame(int day, int time)
    {

		addKeyListener(this);
		setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        this.name = new JTextField("Name", 20);
        this.year = new JTextField("Year", 10);
        this.studentID = new JTextField("Student ID", 10);

        this.displayDays = new String[] {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        this.day = new JComboBox<String>(displayDays);
        this.day.setSelectedIndex(day);

        submit = new JButton("Submit");
        submit.addActionListener(e -> schedule());

        layout = new GridBagLayout();

        setLayout(layout);

        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        c.weightx = 1;
        c.weighty = 1.0;

        c.gridx = 0;
        c.gridy = 0;

        add(name, c);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        add(year, c);

        c.gridx = 2;
        add(studentID, c);

        c.gridwidth = 1;

        c.gridy = 2;
        c.gridx = 0;
        c.weightx = .333;
        add(this.day, c);

        

        c.gridy = 4;
        c.gridx = 4;
        c.weightx = 0.25;
        add(submit, c);


        setPreferredSize(new Dimension(500,500));
    }

	/**
	 * Performs no action when a key is typed
	 */
	public void keyTyped(KeyEvent e)
	{
	}

	/**
	 * Closes the frame if escape is released
	 */
	public void keyReleased(KeyEvent e)
	{
		if(e.getKeyCode()== KeyEvent.VK_ESCAPE)	
		{
			dispose();	
		}
	}

	/**
	 * Performs no action when key is pressed
	 */
	public void keyPressed(KeyEvent e)
	{
	}

	/**
	 * Schedules the tutor or student. Must be extended and overloaded.
	 */
	abstract protected void schedule();

}

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public abstract class ScheduleAddFrame extends JFrame
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

    //TODO: Change to separate class, ScheduleFrame
    //extend from innner classes to ScheduleTutorFrame
    //and ScheduleStudentFrame
    public ScheduleAddFrame(int day, int time)
    {
        this.name = new JTextField("Name", 20);
        this.year = new JTextField("Year", 20);
        this.studentID = new JTextField("Student ID", 20);

        //this.displayTimes = new String[] {"10:00", "12:00", "14:00", "16:00"};
        //this.times = new int[] {1000, 1200, 1400, 1600};
        //this.time = new JComboBox<String>(displayTimes);

        //int selected = time == 1200 ? 1 : time == 1400 ? 2 : time == 1600 ? 3 : 0; //lol
        //this.time.setSelectedIndex(selected);

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

        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        add(year, c);

        c.gridx = 1;
        add(studentID, c);

        c.gridwidth = 1;

        c.gridy = 2;
        c.gridx = 0;
        c.weightx = .333;
        add(this.day, c);

        //c.gridx = 2;
        //add(this.time, c);



        c.gridy = 4;
        c.gridx = 3;
        c.weightx = 0.25;
        add(submit, c);


        setPreferredSize(new Dimension(300,300));
    }

	abstract protected void schedule();

}

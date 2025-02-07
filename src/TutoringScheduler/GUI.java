package TutoringScheduler;

/**
 * @author Erich Maas
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.nio.file.Paths;

import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.ArrayList;
import java.util.Optional;


public class GUI {

	/**
	 * Main components of the top level of the GUI
	 */
    private JFrame frame;
    JFrame reportFrame;
    private GridBagLayout layout;

	/**
	 * Components for the top menuBar
	 */
    private JMenuBar menuBar;

    private JMenu fileMenu;
    private JMenuItem openItem;
    private JMenuItem saveItem;
    private JMenuItem saveAsItem;
    private JMenuItem quitItem;
    private JMenuItem reportItem;


    private JMenu helpMenu;
    private JMenuItem aboutItem;
    private JMenuItem helpItem;

	/**
	 * Components for displaying and interacting with the scheduler
	 */
    private JPanel sidePanel;
    private GridBagLayout sidePanelLayout;

    private JButton scheduleViewButton;
    private JButton addTutorButton;
    private JButton addStudentButton;
    private JButton quitButton;

    private PairLabel[][] scheduleLabels;

    /**
     * Logs scheduled event.
     */
    private ScheduleLogger log;
	/**
	 * Stores the scheudeler and added tutors and students
	 */
    private Scheduler scheduler;
    private ArrayList<Student> students; //Maybe use hashmap
    private ArrayList<Tutor> tutors;     //Lookup by student id


	/**
	 * File path information
	 */
    private String fileName;
    private String savePath;



    /**
     * Creates a new scheduler GUI.
     *
     * @param scheduler The scheduler to create a gui for
     */
    private GUI(Scheduler scheduler) {

		students = new ArrayList<Student>();
		tutors = new ArrayList<Tutor>();
		//students.add(new Student("Test Student", "1")); //test can delete

        this.scheduler = scheduler;
		this.log = new ScheduleLogger();

        //Default filename and save path
        fileName = "schedule.sav";
        savePath = "../save/";
        makeSaveDirectory();


        //General frame setup
        frame = new JFrame("Group 5 Tutoring Scheduler - " + fileName);

        layout = new GridBagLayout();
        frame.setLayout(layout);

        makeMenu();

        makeScheduleView();


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setPreferredSize(new Dimension(1200,800));

        frame.pack();
        frame.setVisible(true);
    }

	/**
	 * Creates a new ScheduleTutorFrame to schedule a new tutor
	 *
	 * @param day The initial day to schedule the tutor
	 * @param time The initial time to schedule the tutor
	 */
    private void scheduleTutor(int day, int time)
    {
        time = Scheduler.arrayIndexToTime(time);
        time = Scheduler.timeToBlockStart(time);
        ScheduleTutorFrame test = new ScheduleTutorFrame(day, time);

        test.setVisible(true);
        System.out.println("Schedule Tutor");
    }

	/**
	 * An extension of ScheduleAddFrame to add a tutor
	 */
    private class ScheduleTutorFrame extends ScheduleAddFrame
    {

		/**
		 * Creates a new ScheduleTutorFrame
		 *
		 * @param day The initial day to schedule the tutor
		 * @param time The initial time to schedule the tutor
		 */
		public ScheduleTutorFrame(int day, int time)
		{
			super(day, time);	


			this.displayTimes = new String[] {"10:00", "12:00", "14:00", "16:00"};
			this.times = new int[] {1000, 1200, 1400, 1600};
			this.time = new JComboBox<String>(displayTimes);

			int selected = time == 1200 ? 1 : time == 1400 ? 2 : time == 1600 ? 3 : 0; //lol
			this.time.setSelectedIndex(selected);


			c.gridx = 1;
			c.gridy = 2;
			c.weightx = 0.333;
			c.gridwidth = 1;

			add(this.time, c);

			pack();
		}

		/**
		 * Creates and schedules the tutor based on the information from the input boxes
		 */
        protected void schedule()
        {

            Tutor t = new Tutor(name.getText(),year.getText(),studentID.getText()); 
			//TODO: add tutor to list of tutors if not already
			tutors.add(t);
            scheduler.scheduleTutor(t, day.getSelectedIndex(), times[time.getSelectedIndex()]);
            updateSchedule();
            System.out.println(t.toString());
            dispose();
        }
    }

	/**
	 * Creates a ScheduleStudentFrame to schedule a new student
	 *
	 * @param day The initial day to schedule the student
	 * @param time The initial time to schedule the student
	 */
	private void scheduleStudent(int day, int time)
    {
        time = Scheduler.arrayIndexToTime(time);
        time = Scheduler.timeToBlockStart(time);
        ScheduleStudentFrame studentFrame = new ScheduleStudentFrame(day, time);

        studentFrame.setVisible(true);
		System.out.println("Schedule Student");
		
    }

	/**
	 * An extension of ScheduleAddFrame to schedule a student
	 */
	private class ScheduleStudentFrame extends ScheduleAddFrame
	{
		JComboBox<String> length;
		String[] lengthDisplay;
		int[] lengthNums;

		/**
		 * Creates a new ScheduleStudentFrame to schedule a new student
		 *
		 * @param day The initial day to schedule the student
		 * @param time The initial time to schedule the student
		 */
		public ScheduleStudentFrame(int day, int time)
		{
			super(day, time);

			setTitle("Schedule Student for " + scheduler.getSchedule()[day][Scheduler.timeToArrayIndex(time)].getTutor());

			ArrayList<String> availableTimes = scheduler.checkAvailabilityInBlock(day, time, 30);
			this.time = new JComboBox<String>(availableTimes.toArray(new String[availableTimes.size()]));

			c.gridx = 1;
			c.gridy = 2;
			c.weightx = 0.333;

			add(this.time, c);


			lengthDisplay = new String[] {"30 minutes", "45 minutes", "60 minutes"};
			lengthNums = new int[] {30, 45, 60};

			length = new JComboBox<String>(lengthDisplay);
			
			c.gridy = 3;
			c.gridx = 0;

			c.gridwidth = 1;
			c.weightx = 1;

			add(length, c);


			revalidate();
			repaint();
			pack();

		}

		/**
		 * Creates and schedules a student based on information in the input boxes
		 */
		protected void schedule()
		{
			Student s = new Student(name.getText(),studentID.getText());
			students.add(s);
			//int t = times[time.getSelectedIndex()];
			int t = Integer.parseInt((String) time.getSelectedItem());//fix
			int d = day.getSelectedIndex();
			int l = lengthNums[length.getSelectedIndex()];

			if(scheduler.checkAvailability(d, t, l))
			{
				scheduler.scheduleStudent(s, d, t, l);	
			}
			else
			{
				System.out.println("error, student scheduled"); //handle this	
			}
			
			updateSchedule();
			dispose();
			
			log.log(s,scheduler.getSchedule()[d][Scheduler.timeToArrayIndex(t)].getTutor(),"course",time,day); // TODO: Add option to select a course from student's course list
		}

	}

    

    // Create a popup menu to schedule or remove students or tutors.
    // selecting one of the options would make a popup window to complete
    // whatever task. The day and time would autofill in the popup window,
    // but could be changed in case of a mis-click. Label, x, and y are
    // just used to display the menu in the right location.
	/**
	 * Creates a popup menu to interact with the scheduler
	 *
	 * @param label The label to center the popup menu around
	 * @param x The x value of the given label
	 * @param y The y value of the given label
	 * @param day The initial day to schedule the person on
	 * @param time The initial time to schedule person on
	 */
    private void showScheduleMenu(PairLabel label, int x, int y, int day, int time)
    {
        Pair pair = scheduler.getSchedule()[day][time];
        JPopupMenu menu = new JPopupMenu("Menu");

        if(pair.getTutor() == null)
        {
            JMenuItem t = new JMenuItem("Schedule Tutor");
            t.addActionListener(e -> scheduleTutor(day, time));
            menu.add(t);
        }
        else
        {
            if(pair.getStudent() == null)
            {
                JMenuItem s = new JMenuItem("Schedule Student");
                s.addActionListener(e -> scheduleStudent(day, time));
                menu.add(s);
                menu.add("Remove tutor: " + pair.getTutor().getName());
            }
            else
            {
                menu.add("Remove tutor: " + pair.getTutor().getName());
                menu.add("Remove student: " + pair.getStudent().getName());
            }
        }

        menu.show(label, x, y);
    }

    /**
     * Disposes the main frame, exiting the program.
     */
    private void quit()
    {
        frame.dispose();
    }

    /**
     * Saves the current state of the scheduler to a file
     * named 'fileName' in the 'savePath' directory.
     * Errors are currently printed to standard out.
     */
    private void save()
    {
        try
        {

            File file = new File(savePath + fileName);

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));

            oos.writeObject(scheduler);

            System.out.println("File successfully saved to " + savePath + fileName);
            oos.close();
            frame.setTitle("Group 5 Tutoring Scheduler - " + fileName);
        }
        catch(IOException e)
        {
            System.out.println("Error: Error saving file to " + savePath + fileName);
            System.out.println(e);
        }
    }

    /**
     * Creates a file chooser to select a new fileName and
     * savePath, the calls the save() function.
     */
    private void saveAs()
    {
        try
        {

            JFileChooser chooser = new JFileChooser(savePath);

            //Filters to only show .sav files
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Save files", "sav");
            chooser.setFileFilter(filter);

            int returnValue = chooser.showSaveDialog(frame); //TODO: change to showSaveDialog
            if(returnValue == JFileChooser.APPROVE_OPTION)
            {
                fileName = chooser.getSelectedFile().getName(); //Get name of file
                savePath = chooser.getSelectedFile().getParentFile().getAbsolutePath() + "/"; //get parent directory

                save();
            }
            else
            {
                System.out.println("Error: Invalid file"); //TODO: Add poput
            }
        }
        catch(Exception e)
        {
            System.out.println("Error: Error on save as");
            System.out.println(e);
        }
    }

    /**
     * Creates a file chooser to select a new filename and
     * savePath, then attempts to open the selected file.
     */
    private void open()
    {
        try
        {
            JFileChooser chooser = new JFileChooser(savePath);

            //Only open .sav files
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Save files", "sav");
            chooser.setFileFilter(filter);


            int returnValue = chooser.showOpenDialog(frame);
            if(returnValue == JFileChooser.APPROVE_OPTION)
            {
                fileName = chooser.getSelectedFile().getName(); //Get name of file
                savePath = chooser.getSelectedFile().getParentFile().getAbsolutePath() + "/"; //get parent directory
                System.out.println(savePath + fileName);
                File file = new File(savePath + fileName);

                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                scheduler = (Scheduler) ois.readObject();


                System.out.println("Successfully opened " + savePath + fileName);

                updateSchedule();
                frame.setTitle("Group 5 Tutoring Scheduler - " + fileName);
            }
            else
            {
                System.out.println("Error: Invalid file"); //TODO: Add popup
            }
        }
        catch(ClassNotFoundException e)
        {
            System.out.println("Error: Error on open");
            System.out.println(e);
        }
        catch(IOException e)
        {
            System.out.println("Error: Corrupt or invalid save file!");
            System.out.println(e);
        }

    }

    /**
     * Creates a help popup window with instructions
     * on how to use the program
     */
    private void help()
    {

    }

    /**
     * Creates an about popup window with information
     * about the program
     */
    private void about()
    {

    }

    /**
     * Initailizes the schedule view of the program.
     */
    private void makeScheduleView()
    {
        scheduleLabels = new PairLabel[5][32];

        initializeScheduleView();
        updateSchedule();
    }

    /**
     * Updates the schedule view to the current
     * information in the scheduler
     */
    private void updateSchedule() //could probably be redone to be less bad
    {
        Pair[][] schedule = scheduler.getSchedule();

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;

        for(int x = 0; x < 5; x++) //5 days in a week
        {

            for(int i = 0; i < 4; i++) //4 2-hour tutoring blocks
            {
                if(schedule[x][i*8].getTutor() == null)
                {

                    for(int j = 0; j < 8; j++)
                    {
                        scheduleLabels[x][i * 8 + j].setBackground(Color.WHITE);
                        scheduleLabels[x][i * 8 + j].setTutor(""); // Clear tutors
                        scheduleLabels[x][i * 8 + j].setStudent(""); //Clear students
                    }
                    scheduleLabels[x][i * 8].setTutor("NONE");
                }
                else
                {
                    scheduleLabels[x][i * 8].setTutor(schedule[x][i * 8].getTutor().getName());
                    Student s = schedule[x][i*8].getStudent();
                    if(s != null) //set first block
                    {
                        scheduleLabels[x][i * 8].setStudent(s.getName());
                        scheduleLabels[x][i * 8].setBackground(Color.RED);
                    }
                    else
                    {
                        scheduleLabels[x][i * 8].setStudent("AVAILABLE");
                        scheduleLabels[x][i * 8].setBackground(Color.GREEN);
                    }


                    for(int j = 1; j < 8; j++) //8 15-minute blocks per each 2-hour block, skipping first block
                    {
                        if(schedule[x][i * 8 + j].getStudent() == null)
                        {
                            if(s != null)
                            {
                                scheduleLabels[x][i * 8 + j].setTutor(schedule[x][i * 8 + j].getTutor().getName());
                            }
                            scheduleLabels[x][i * 8 + j].setStudent("AVAILABLE");
                            scheduleLabels[x][i * 8 + j].setBackground(Color.GREEN);
                        }
                        else
                        {
                            if(s != schedule[x][i * 8 + j].getStudent()) {
                                scheduleLabels[x][i * 8 + j].setTutor(schedule[x][i * 8 + j].getTutor().getName());
                            }
                            scheduleLabels[x][i * 8 + j].setStudent(schedule[x][i * 8 + j].getStudent().getName());
                            scheduleLabels[x][i * 8 + j].setBackground(Color.RED);
                        }

                        s = schedule[x][i * 8 + j].getStudent();
                    }
                }
            }
        }
    }

    /**
     * Initailizes all of the labels used
     * to display information in the schedule view
     */
    private void initializeScheduleView()
    {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.0;
        c.weighty = 1.0;

        c.gridx = 0;
        int count = 1;
        for(int i = 10; i < 18; i++)
        {

            for(int j = 0; j < 60; j += 15, count++)
            {
                c.gridy = count;
                frame.add(new JLabel(i + ":" + (j == 0 ? "00" : j + "  ")), c);
            }
        }

        c.weightx = 1.0;

        c.gridy = 0;
        c.gridx = 1;
        frame.add(new JLabel("MONDAY", SwingConstants.CENTER), c);

        c.gridx = 2;
        frame.add(new JLabel("TUESDAY", SwingConstants.CENTER), c);

        c.gridx = 3;
        frame.add(new JLabel("WEDNESDAY", SwingConstants.CENTER), c);

        c.gridx = 4;
        frame.add(new JLabel("THURSDAY", SwingConstants.CENTER), c);

        c.gridx = 5;
        frame.add(new JLabel("FRIDAY", SwingConstants.CENTER), c);



        for(int x = 0; x < 5; x++)
        {
            c.gridx = x+1;
            for(int y = 0; y < 32; y++)
            {
                c.gridy = y+1;
                scheduleLabels[x][y] = new PairLabel();
                scheduleLabels[x][y].setOpaque(true);

                final PairLabel pairLabel = scheduleLabels[x][y]; //  need final for some reason
                final int day = x;								  //  https://stackoverflow.com/questions/
                final int time = y;	  //  13920649/access-local-variable-from-inner-class

                scheduleLabels[x][y].addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e)
                    {
                        showScheduleMenu(pairLabel, e.getX(), e.getY(), day, time);
                    }
                });

                frame.add(scheduleLabels[x][y], c);
            }
        }
    }

    /**
     * Creates and adds components to the menu bar
     */
    private void makeMenu()
    {
        //Initialize menu components
        menuBar = new JMenuBar();

        //file menu
        fileMenu = new JMenu("File");

        openItem = new JMenuItem("Open");
        openItem.addActionListener(e -> open());
        fileMenu.add(openItem);

        saveItem = new JMenuItem("Save");
        saveItem.addActionListener(e -> save());
        fileMenu.add(saveItem);

        saveAsItem = new JMenuItem("Save As");
        saveAsItem.addActionListener(e -> saveAs());
        fileMenu.add(saveAsItem);

        reportItem = new JMenuItem("Generate Student Report");
        reportItem.addActionListener(e -> getStudentID());
        fileMenu.add(reportItem);
        
        quitItem = new JMenuItem("Quit");
        quitItem.addActionListener(e -> quit());
        fileMenu.add(quitItem);
        

        //help menu
        helpMenu = new JMenu("Help");

        helpItem = new JMenuItem("Help");
        helpItem.addActionListener(e -> help());
        helpMenu.add(helpItem);

        aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(e -> about());
        helpMenu.add(aboutItem);


        //Create menu
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);


        frame.setJMenuBar(menuBar);
    }
    /**
     * Prompts user for student ID. Called via a menu item.
     */
    private void getStudentID()
    {
    	reportFrame = new JFrame("Enter Student ID.");
    	
    	JTextField reportField = new JTextField(50);
    	reportField.setEditable(true);
    	JButton reportButton = new JButton("Submit");
    	reportButton.addActionListener(e -> searchStudents(reportField.getText()));
    	
    	reportFrame.add(reportField,BorderLayout.NORTH);
    	reportFrame.add(reportButton,BorderLayout.SOUTH);
    	reportFrame.pack();
    	reportFrame.setVisible(true);
    }
    private void searchStudents(String s)
    {
    	System.out.println(students.get(0).getID());
		Optional<Student> S = students.stream()
        		.filter(student -> student.getID().equals(s))
        		.findFirst();
        	if(S.isPresent())
        	{   
        		generateReport(S.get());
        		String path = Paths.get("").toAbsolutePath().toString();
        		JFrame confirmedFrame = new JFrame("Confirmation");
        		JLabel msg = new JLabel("Report Generated at " + path + "\\" + S.get().getName() + " report file.txt", JLabel.CENTER);
        		confirmedFrame.add(msg,BorderLayout.CENTER);
        		confirmedFrame.pack();
        		confirmedFrame.setVisible(true);
        	}
        	else
        	{
        		JFrame confirmedFrame = new JFrame("Error");
        		JLabel msg = new JLabel("Invalid ID. Student Report not generated", JLabel.CENTER);
        		confirmedFrame.add(msg,BorderLayout.CENTER);
        		confirmedFrame.pack();
        		confirmedFrame.setVisible(true);
        	}
    }
    
    /**
     * Generates report on student s
     */
    private void generateReport(Student s)
    {
    	try {
			Reporter r = new Reporter(s);
			System.out.print("Student File generated at: " + System.getProperty("user.dir") + s.getName() + " report file.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    /**
     * Creates a default save directory if it
     * does not already exist
     */
    private void makeSaveDirectory()
    {
        try
        {
            File dir = new File(savePath);
            if(!dir.exists())
            {
                dir.mkdir();
            }
        }
        catch(SecurityException e)
        {
            System.out.println(e);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }



	/**
	 * Singleton instance
	 */
	private static GUI instance;
	
	/**
	 * Gets the singleton intance of the GUI
	 *
	 * @return The singleton GUI instance
	 */ 
	public static GUI getInstance()
	{
		if(instance == null)
		{
			instance = new GUI(Scheduler.getInstance());	
		}

		return instance;
	}


}

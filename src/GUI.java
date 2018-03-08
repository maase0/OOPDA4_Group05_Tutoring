import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.swing.filechooser.FileNameExtensionFilter; 
import java.util.ArrayList;


public class GUI{

	//Frame components
	private JFrame frame;
	private GridBagLayout layout;	
	//private BorderLayout layout;

	//Menu Components
	private JMenuBar menuBar;
	
	private JMenu fileMenu;
	private JMenuItem openItem;
	private JMenuItem saveItem;
	private JMenuItem saveAsItem;
	private JMenuItem quitItem;

	private JMenu helpMenu;
	private JMenuItem aboutItem;
	private JMenuItem helpItem;

	//Schedule View
	//private JPanel scheduleView;
	//private GridBagLayout scheduleViewLayout;
	private PairLabel[][] scheduleLabels;

	//Scheduling panel
	private JPanel sidePanel;
	private GridBagLayout sidePanelLayout;

	private JButton scheduleViewButton;
	private JButton addTutorButton;
	private JButton addStudentButton;
	private JButton quitButton;

	private AddTutorPanel addTutorPanel;

	//Scheduler, students, and tutors
	private Scheduler scheduler;
	private ArrayList<Student> students; //Maybe use hashmap
	private ArrayList<Tutor> tutors;     //Lookup by student id
	

	//File name and path
	private String fileName;
	private String savePath;

	
	
	/**
	 * Creates a new scheduler GUI.
	 *
	 * @input scheduler The scheduler to create a gui for
	 */
	public GUI(Scheduler scheduler) {
		

		this.scheduler = scheduler;

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

		frame.pack();
		frame.setVisible(true);
	}	


	//private void showScheduleMenu(int x, int y, int day, int time)
	private void showScheduleMenu(PairLabel label, int x, int y)
	{
		JPopupMenu menu = new JPopupMenu("Menu");	
		menu.add("a");
		menu.add("b");
		System.out.println(menu.getWidth());
		menu.show(label, x - 20, y);
		System.out.println(menu.getWidth());
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
	 * Shows all Tutor-Student pairings
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
	private void updateSchedule()
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
							if(s != schedule[x][i * 8 + j].getStudent()){
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

				final PairLabel pairLabel = scheduleLabels[x][y]; //need this for some reason
																  //  https://stackoverflow.com/questions/
																  //  13920649/access-local-variable-from-inner-class

				scheduleLabels[x][y].addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e)
					{
						showScheduleMenu(pairLabel, e.getX(), e.getY());
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
}

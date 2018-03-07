import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.swing.filechooser.FileNameExtensionFilter; 
import java.util.ArrayList;


public class GUI{

	//Frame components
	private JFrame frame;
	private BorderLayout layout;

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
	private JPanel scheduleView;
	private GridBagLayout scheduleViewLayout;
	private JLabel[][] scheduleLabels;

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
		frame = new JFrame("Group 5 Tutoring Scheduler");

		layout = new BorderLayout();
		frame.setLayout(layout);

		addTutorPanel = new AddTutorPanel(scheduler, tutors);

		makeMenu();	

		makeSidePanel();

		makeScheduleView();


		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.pack();
		frame.setVisible(true);
	}	


	private void switchToAddStudent()
	{
		
	}

	private void switchToAddTutor()
	{
		layout.removeLayoutComponent(layout.getLayoutComponent(BorderLayout.CENTER));
		frame.add(addTutorPanel, BorderLayout.CENTER);
		addTutorPanel.repaint();
		frame.validate();
	}
	

	private void switchToScheduleView()
	{
		layout.removeLayoutComponent(layout.getLayoutComponent(BorderLayout.CENTER));
		updateSchedule();
		frame.add(scheduleView, BorderLayout.CENTER);
		scheduleView.repaint();
		frame.validate();
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

			int returnValue = chooser.showOpenDialog(frame); //TODO: change to showSaveDialog
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
				savePath = chooser.getSelectedFile().getParentFile().getAbsolutePath(); //get parent directory
				File file = new File(savePath + fileName);

				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
				scheduler = (Scheduler) ois.readObject();


				System.out.println("Successfully opened " + savePath + fileName);
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
	 * Initailizes all of the components used to schedule
	 * a student or tutor.
	 */
	private void makeSidePanel()
	{
		sidePanel = new JPanel();
		sidePanelLayout = new GridBagLayout();

		sidePanel.setLayout(sidePanelLayout);

		GridBagConstraints c = new GridBagConstraints();

		//c.fill = GridBagConstraints.BOTH;
		//c.weigthx = 1.0;
		//c.weighty = 1.0;
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weighty = 0.5;
		c.weightx = 0.3;


		c.gridx = 0;
		c.gridy = 0;

		scheduleViewButton = new JButton("View Schedule");
		scheduleViewButton.addActionListener(e -> switchToScheduleView());
		sidePanel.add(scheduleViewButton, c);

		c.gridx = 0;
		c.gridy = 1;

		addTutorButton = new JButton("Schedule A Tutor");
		addTutorButton.addActionListener(e -> switchToAddTutor());
		sidePanel.add(addTutorButton, c);

		c.gridx = 0;
		c.gridy = 2;

		addStudentButton = new JButton("Schedule A Student");
		addStudentButton.addActionListener(e -> switchToAddStudent());
		sidePanel.add(addStudentButton, c);

		c.gridx = 0;
		c.gridy = 3;

		quitButton = new JButton("Quit");
		quitButton.addActionListener(e -> quit());
		sidePanel.add(quitButton, c);
		
		
		frame.add(sidePanel, BorderLayout.WEST);
	}	

	/**
	 * Initailizes the schedule view of the program.
	 * Shows all Tutor-Student pairings
	 */
	private void makeScheduleView()
	{
		scheduleView = new JPanel();
		scheduleViewLayout = new GridBagLayout();
		scheduleView.setLayout(scheduleViewLayout);

		
		initializeScheduleView();
		updateSchedule();

		frame.add(scheduleView, BorderLayout.CENTER);
	}

	/**
	 * Updates the schedule view to the current
	 * information in the scheduler
	 */
	private void updateSchedule()
	{
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1.0;
		c.weighty = 1.0;

		for(int x = 1; x < 6; x++)
		{
			c.gridx = x;
			for(int y = 1; y < 33; y++)
			{
				c.gridy = y;
				scheduleLabels[x-1][y-1].setText("NONE");
				scheduleLabels[x-1][y-1].setHorizontalAlignment(SwingConstants.CENTER);
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
				scheduleView.add(new JLabel(i + ":" + (j == 0 ? "00" : ""+j)), c);
			}
		}

		c.weightx = 1.0;

		c.gridy = 0;
		c.gridx = 1;
		scheduleView.add(new JLabel("MONDAY", SwingConstants.CENTER), c);

		c.gridx = 2;
		scheduleView.add(new JLabel("TUESDAY", SwingConstants.CENTER), c);

		c.gridx = 3;
		scheduleView.add(new JLabel("WEDNESDAY", SwingConstants.CENTER), c);

		c.gridx = 4;
		scheduleView.add(new JLabel("THURSDAY", SwingConstants.CENTER), c);

		c.gridx = 5;
		scheduleView.add(new JLabel("FRIDAY", SwingConstants.CENTER), c);


		scheduleLabels = new JLabel[5][32];

		for(int x = 0; x < 5; x++)
		{
			c.gridx = x+1;
			for(int y = 0; y < 32; y++)
			{
				c.gridy = y+1;
				scheduleLabels[x][y] = new JLabel();	
				scheduleView.add(scheduleLabels[x][y], c);
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

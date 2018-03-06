import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.swing.filechooser.FileNameExtensionFilter; 


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
	private JLabel[][] scheduleLabels;


	private Scheduler scheduler;
	

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



		makeMenu();	

		makeScheduleView();


		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.pack();
		frame.setVisible(true);
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
	 * Initailizes the schedule view of the program.
	 * Shows all Tutor-Student pairings
	 */
	private void makeScheduleView()
	{
		scheduleView = new JPanel();
		scheduleView.setLayout(new GridLayout(0,6));
		//scheduleView.setLayout(new FlowLayout());

		
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
		scheduleView.removeAll();
		for(int i = 0; i < 33; i ++)
		{
			for(int j = 0; j < 6; j++)
			{
				scheduleView.add(scheduleLabels[j][i]);
			}
		}
	}

	/**
	 * Initailizes all of the labels used
	 * to display information in the schedule view
	 */
	private void initializeScheduleView()
	{
		scheduleLabels = new JLabel[6][33];	
		for(int i = 0; i < 6; i++)
		{
			for(int j = 0; j < 33; j++)
			{
				scheduleLabels[i][j] = new JLabel(i + ", " + j);	
			}
		}

		scheduleLabels[1][0].setText("Monday");
		scheduleLabels[2][0].setText("Tuesday");
		scheduleLabels[3][0].setText("Wednesday");
		scheduleLabels[4][0].setText("Thursday");
		scheduleLabels[5][0].setText("Friday");

		for(int i = 10, count = 1; i < 18; i+= 1)
		{
			for(int j = 0; j < 60; j+= 15, count++)
			{
				scheduleLabels[0][count].setText(i + ":" + (j == 0 ? "00" : j));
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

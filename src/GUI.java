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

	private Container scheduleView;


	private Scheduler scheduler;
	
	private String fileName;

	
	
	public GUI(Scheduler scheduler) {
		this.scheduler = scheduler;
		fileName = "../save/schedule.sav";
		
		frame = new JFrame("Group 5 Tutoring Scheduler");

		layout = new BorderLayout();

		makeMenu();	

		makeScheduleView();



		frame.setLayout(layout);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}	


	private void quit()
	{
		frame.dispose();	
	}

	private void save()
	{
		try
		{
			File file = new File(fileName);	
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));

			oos.writeObject(scheduler);

			System.out.println("File successfully saved to " + fileName);

			oos.close();
		}
		catch(IOException e)
		{
			System.out.println("Error: Error saving file to " + fileName);
			System.out.println(e);
		}
	}

	private void saveAs()
	{
		try
		{
			JFileChooser chooser = new JFileChooser("../save/");
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Save files", "sav");
			chooser.setFileFilter(filter);
			int returnValue = chooser.showOpenDialog(frame);
			if(returnValue == JFileChooser.APPROVE_OPTION)
			{
				fileName = chooser.getSelectedFile().getAbsolutePath();
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

	private void open()
	{
		try
		{

			JFileChooser chooser = new JFileChooser("../save/");
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Save files", "sav");
			chooser.setFileFilter(filter);
			int returnValue = chooser.showOpenDialog(frame);
			if(returnValue == JFileChooser.APPROVE_OPTION)
			{
				fileName = chooser.getSelectedFile().getAbsolutePath();
				File file = new File(fileName);

				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
				scheduler = (Scheduler) ois.readObject();
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

	private void help()
	{
	
	}

	private void about()
	{
	
	}

	private void makeScheduleView()
	{
		scheduleView = new Container();

		layout.addLayoutComponent(scheduleView, BorderLayout.CENTER);
	}

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


}

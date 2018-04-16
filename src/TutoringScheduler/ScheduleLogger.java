package TutoringScheduler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JComboBox;

public class ScheduleLogger 
{
	Student s;
	Tutor t;
	String l;
	String c;
	JComboBox<String> time;
	JComboBox<String> day;
	/**
	 * Creates new empty log file.
	 */
	public ScheduleLogger()
	{
		File f = new File("ScheduleLog.txt");
		try{f.createNewFile();}
		catch (IOException e){System.err.println("Error: " + e.getMessage());}
	}
	public void log(Student s, Tutor t, String course, JComboBox<String> time, JComboBox<String> day)
	{
		this.s = s;
		this.t = t;
		this.time = time;
		this.day = day;
		c = course;
		if (t.getYear() == "senior")
			l = "30 min";
		else if (t.getYear() == "junior")
			l = "45 min";
		else
			l = "60 min";
		Compile();
	}
	/**
	 * Updates log file with new scheduled session information.
	 * @throws IOException 
	 */
	private void Compile()
	{
		BufferedWriter out = null;
		try
		{
			FileWriter fstream = new FileWriter("ScheduleLog.txt", true);
			out = new BufferedWriter(fstream);
			String appointmentTime = day + " " + time; 
			// Each line in the log contains all necessary information for each scheduled visit in the following format:
			// [STUDENT_ID] : [TUTOR_ID] : [COURSE_HELPED] : [DATE_AND_TIME_OF_APPOINTMENT] : [LENGTH_OF_APPOINTMENT]
		    out.write(s.getID() + " : " + t.getID() + " : " + c + ": " + appointmentTime + " : " + l + "\n");
		}
		catch (IOException e)
		{
		    System.err.println("Error: " + e.getMessage());
		}
		finally // close output stream
		{
		    if(out != null) {
		        try {
					out.close();
				} catch (IOException e) 
		        {
					System.err.println("Error: " + e.getMessage());
				}
		    }
		}
	}
}

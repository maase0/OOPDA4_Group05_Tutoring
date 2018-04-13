package TutoringScheduler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScheduleLogger 
{
	Student s;
	Tutor t;
	String l;
	String c;
	/**
	 * Acquires Student and Tutor from scheduled pair.
	 */
	public ScheduleLogger()
	{
		File f = new File("ScheduleLog.txt");
		try{f.createNewFile();}
		catch (IOException e){System.err.println("Error: " + e.getMessage());}
	}
	public void log(Student s, Tutor t, String course)
	{
		this.s = s;
		this.t = t;
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
	 * Compiles Log file of given student with information of the session compiled and logged.
	 * @throws IOException 
	 */
	private void Compile()
	{
		BufferedWriter out = null;
		try
		{
			FileWriter fstream = new FileWriter("ScheduleLog.txt", true);
			out = new BufferedWriter(fstream);
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			String now = dtf.format(LocalDateTime.now());  
			// Each line in the log contains all necessary information for each scheduled visit in the following format:
			// [STUDENT_ID] : [TUTOR_ID] : [COURSE_HELPED] : [DATE_AND_TIME_OF_APPOINTMENT] : [LENGTH_OF_APPOINTMENT]
		    out.write(s.getID() + " : " + t.getID() + " : " + c + ": " + now + " : " + l + "\n");
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
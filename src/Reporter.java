import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;      

/**
 *  Generates a report for a specified student.
 * @author Tyler
 *
 */
class Reporter
{
	Student stu;
	String STUDENT_REPORT_FILE;
	/**
	 * Initialize
	 * @param s
	 */
	public Reporter(Student s)
	{
		stu = s;
		STUDENT_REPORT_FILE = stu.getName() + " report file.txt";
	}
	@SuppressWarnings("unused")
	/**
	 * Generates the file properly. Unsure if this should be called in the constructor.
	 * @throws IOException
	 */
	private void toFile() throws IOException
	{
		PrintWriter rep = new PrintWriter(new FileWriter(STUDENT_REPORT_FILE)); 
		
		rep.println("Student Report: " + stu.getID());
		rep.println("Name: " + stu.getName()); 
		// Current date and time
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		String now = dtf.format(LocalDateTime.now());  
		rep.println("Date: "+ now);
		rep.println();
		rep.println("Year: " + stu.getYear());
		rep.println("GPA: " + stu.getGPA());
		rep.println("Classes: " + printClasses());
		rep.println("Visits to Tutoring: ");
		BufferedReader reader = null;

		/**
		 * This checks the Log file that has been generated so far and matches lines based on the students ID, which the Logger
		 * class puts at the front of each logged line. From there, it splits the data up into useful parts using a simple
		 * regular expression and compiles it into a more readable format.
		 * 
		 */
		try {
		    reader = new BufferedReader(new FileReader("ScheduleLog.txt"));
		    String text = null;
		    String splitter[] = null;
		    while ((text = reader.readLine()) != null) 
		    {
		        splitter = text.split("\\ : ");
		        if(splitter[0] == stu.getID())
		        	rep.println("Date: " + splitter[3] + "Length: " + splitter[4] + "Course: " + splitter[2] + "Tutor: " + splitter[2]); // TODO: possibly need to get tutor object or name in the future, this works for now
		    }
		} catch (FileNotFoundException e) { // If file doesn't exist
		    e.printStackTrace();
		} catch (IOException e) { // Other exceptions
		    e.printStackTrace();
		} finally { // close reader stream
		    try {
		        if (reader != null) {
		            reader.close();
		        }
		    } catch (IOException e) {
		    }
		}
		rep.close();
	}
	// compiles string of classes being taken by the student
	private String printClasses()
	{
		String classes = "";
		for(int i=0; i<stu.getClasses().size(); i++)
			classes += stu.getClasses().get(i) +", ";
		return classes;
	}
}
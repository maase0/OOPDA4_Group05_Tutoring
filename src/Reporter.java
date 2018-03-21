import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;      

// Generates a report for a specified student.
class Reporter
{
	Student stu;
	String STUDENT_REPORT_FILE;
	public Reporter(Student s)
	{
		stu = s;
		STUDENT_REPORT_FILE = stu.getName() + " report file.txt";
		
	}
	@SuppressWarnings("unused")
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
		rep.println("Number of Visits to Tutoring: " );//TODO: create LOG file which tracks all scheduled 
														//visits as well as visit data
		rep.println("Tutors: ");//TODO: ^^^
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
	
	//TODO: error handling; what if student called isn't a student? what if student called has no CS classes?
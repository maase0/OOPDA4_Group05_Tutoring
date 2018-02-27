import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

class Reporter
{
	Student stu;
	String STUDENT_REPORT_FILE;
	public Reporter(Student s)
	{
		stu = s;
		STUDENT_REPORT_FILE = stu.getName() + "report file.txt";
		
	}
	//TODO: 
	// Create new Student report.
	@SuppressWarnings("unused")
	private void toFile() throws IOException
	{
		PrintWriter rep = new PrintWriter(new FileWriter(STUDENT_REPORT_FILE)); 
		
		rep.println("Student Report: " + stu.getName()); 
		rep.println("Date: "); //TODO: get current date and assign it to a variable
		rep.println();
		//rep.println("Year: " + stu.getYear()); TODO: getYear
		//rep.println("GPA: " + stu.getGPA()); TODO: getGPA
		rep.println("Classes: ");//TODO: get CS classes from Student and put the list here
		rep.println("Number of Visits to Tutoring: " );//TODO: either create a student variable to this or 
														//increment over all days and count visits before this print
		rep.println("Tutors: ");//TODO: increment over days and get all unique tutors, list here
		
		rep.close();
	}
}
	
	//TODO: error handling; what if student called isn't a student? what if student called has no CS classes?
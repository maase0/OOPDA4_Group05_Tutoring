package TutoringScheduler;

/**
 * @author Erich Maas
 */

import java.io.Serializable;
import java.util.ArrayList;

public class Student implements Serializable 
{
	private String name;
	private String year;
	private String GPA;
	private String ID;
	private ArrayList<String> classes;
	
	public Student(String name, String ID) // TODO: delete this constructor, want student to always be filled out completely
	{
		this.name = name;
		this.ID = ID;
		year = "Freshmen";
	}
	
	public Student(String name,String year,String GPA,String ID,ArrayList<String> classes) {
		this.name = name;
		this.year = year;
		this.GPA = GPA;
		this.ID = ID;
		this.classes = classes;
		
	}
	public String getName() 
	{
		return name;
	}
	public String getYear() 
	{
		return year;
	}

	public String getGPA() 
	{
		return GPA;
	}

	public String getID() 
	{
		return ID;
	}

	public ArrayList<String> getClasses() 
	{
		return classes;
	}
}

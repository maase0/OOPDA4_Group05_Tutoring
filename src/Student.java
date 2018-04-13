package TutoringScheduler;

/**
 * @author Erich Maas
 */

import java.io.Serializable;
import java.util.ArrayList;

public class Student implements Serializable {

<<<<<<< HEAD
    private String name;

	/**
	 * Creates a new Student object
	 *
	 * @param name The name of the student
	 */
    public Student(String name) {
        this.name = name;
    }

	/**
	 * Returns the name of the student
	 */
    public String getName() {
        return name;
    }

	/**
	 * Returns a string representation of the student
	 */
	public String toString()
=======
	private String name;
	private String year;
	private String GPA;
	private String ID;
	private ArrayList<String> classes;
	
	public Student(String name,String year,String GPA,String ID,ArrayList<String> classes) {
		this.name = name;
		this.year = year;
		this.GPA = GPA;
		this.ID = ID;
		this.classes = classes;
		
	}
	
	public String getName() 
>>>>>>> reporter
	{
		return name;
	}

<<<<<<< HEAD
=======
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
	
>>>>>>> reporter
}

/**
 * @author Erich Maas
 */

import java.io.Serializable;

public class Student implements Serializable {

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
	public String toString
	{
		return name;
	}

}

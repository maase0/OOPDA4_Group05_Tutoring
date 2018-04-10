/**
 * @author Erich Maas
 */
import java.io.Serializable;

public class Tutor implements Serializable {

    private String name;
    private String year;

	/**
	 * Creates a new tutor object
	 *
	 * @param name The name of the tutor
	 * @param year The year of the tutor
	 */
    public Tutor(String name, String year) {
        this.name = name;
        this.year = year;
    }

	/**
	 * Returns the name of the tutor
	 */
    public String getName() {
        return name;
    }

	/**
	 * Returns the year of the tutor
	 */
    public String getYear() {
        return year;
    }

	/**
	 * Returns a string representation of the tutor
	 */
    public String toString() {
        return name;
    }

}

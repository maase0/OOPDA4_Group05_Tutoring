package TutoringScheduler;

/**
 * @author Erich Maas
 */
import java.io.Serializable;

public class Pair implements Serializable {

    private Tutor tutor;
    private Student student;

	/**
	 * Creates a new Pair object to store a student-tutor pair
	 */
    public Pair() {
        this.tutor = null;
        this.student = null;
    }

	/**
	 * Creates a new Pair object to store a student-tutor pair
	 *	
	 * @param tutor Initial tutor of the pair
	 */
    public Pair(Tutor tutor) {
        this.tutor = tutor;
        this.student = null;
    }

	/**
	 * Creates a new Pair object to store a student-tutor pair
	 *
	 * @param tutor The initial tutor in the pair
	 * @param student The initial student in the pair
	 */
    public Pair(Tutor tutor, Student student) {
        this.tutor = tutor;
        this.student = student;
    }

	/**
	 * Returns the tutor of the pair
	 *
	 * @return The pair's tutor
	 */
    public Tutor getTutor() {
        return tutor;
    }

	/**
	 * Returns the student of the pair
	 *
	 * @return The pair's student
	 */
    public Student getStudent() {
        return student;
    }

	/**
	 * Sets the tutor of the pair
	 *
	 * @param tutor The tutor to store in the pair
	 */
    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

	/**
	 * Sets the student of the pair
	 *
	 * @param student The student to store in the pair
	 */
    public void setStudent(Student student) {
        this.student = student;
    }
}

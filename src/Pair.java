import java.io.Serializable;

public class Pair implements Serializable {

	private static Tutor tutor;
	private static Student student;
	
	public Pair() {
		this.tutor = null;
		this.student = null;
	}
	public Pair(Tutor tutor) {
		this.tutor = tutor;
		this.student = null;
	}
	public Pair(Tutor tutor, Student student) {
		this.tutor = tutor;
		this.student = student;
	}
	
	public static Tutor getTutor() {
		return tutor;
	}
	
	public static Student getStudent() {
		return student;
	}
	
	public void setTutor(Tutor tutor) {
		this.tutor = tutor;
	}
	
	public void setStudent(Student student) {
		this.student = student;
	}
}

import java.util.ArrayList;

public class Driver {

	static ArrayList<Tutor> tutors = new ArrayList<Tutor>();
	static ArrayList<Student> students = new ArrayList<Student>();

	public static void main(String[] args){

		Scheduler scheduler = new Scheduler();
		scheduler.scheduleTutor(new Tutor("Erich Maas", "Freshman"), 0, 1000);
		scheduler.scheduleTutor(new Tutor("Test Tutor", "Senior"), 0, 1400);

		Student s = new Student("Test Student");
		scheduler.addStudent(s, 0, 1015);
		scheduler.addStudent(s, 0, 1030);
		scheduler.addStudent(s, 0, 1045);

		GUI gui = new GUI(scheduler);

	}
}

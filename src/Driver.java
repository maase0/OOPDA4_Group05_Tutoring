import java.util.ArrayList;

public class Driver {

	static ArrayList<Tutor> tutors = new ArrayList<Tutor>();
	static ArrayList<Student> students = new ArrayList<Student>();

	public static void main(String[] args) throws IOException {

		static Scheduler sheduler = new Scheduler();

		GUI gui = new GUI(shceduler);

	}
}

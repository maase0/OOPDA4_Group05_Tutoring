import java.util.ArrayList;

public class Driver {

	static ArrayList<Tutor> tutors = new ArrayList<Tutor>();
	static ArrayList<Student> students = new ArrayList<Student>();

	public static void main(String[] args){

		Scheduler scheduler = new Scheduler();

		GUI gui = new GUI(scheduler);

	}
}

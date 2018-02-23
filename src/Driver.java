import java.util.ArrayList;
import java.io.*;

public class Driver {

	static BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
	static Scheduler sh = new Scheduler();
	static ArrayList<Tutor> tutors = new ArrayList<Tutor>();
	static ArrayList<Student> students = new ArrayList<Student>();

	public static void main(String[] args) throws IOException {

		boolean running = true;
		GUI gui = new GUI();

		while (running) {



			running = false;

		}

	}

	public static void saveSchedule(String filename) throws IOException {
		try{
			File file = new File("schedule.dat");
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file));

			os.writeObject(sh);
			os.writeObject(tutors);
			os.writeObject(students);
	
			os.close();
		} catch(Exception e) {
			System.out.println("NO");	
		}
	}

	@SuppressWarnings("unchecked")
	public static void loadSchedule(String filename) throws IOException{
		try{
			ObjectInputStream is = new ObjectInputStream(new FileInputStream(filename));

			sh = (Scheduler) is.readObject(); 
			tutors = (ArrayList<Tutor>) is.readObject(); 
			students = (ArrayList<Student>) is.readObject(); 
		} catch(Exception e) {
			System.out.println("NO");	
		}
	
	}

	public static void displayTutors() {
		System.out.println("0. Cancel");
		for(int i = 1; i <= tutors.size(); i++) {
			System.out.println(i + ". " + tutors.get(i-1));
		}	
	}

	public static int getInt(){
		int value;
		try {
			value = Integer.parseInt(stdin.readLine());
		} catch (Exception e) {
			value = -1;
		}
		return value;
	}
	
	public static String getString(){
		String value;
		try {
			value = stdin.readLine();
		} catch (Exception e) {
			value = "";
		}
		return value.trim().toUpperCase();
	}

	public static void printOptions() {

		System.out.println("1. Schedule tutor");
		System.out.println("2. Print Schedule");
		System.out.println("0. Exit");

	}
	
}

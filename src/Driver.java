import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Driver {

	static BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args){
		Scheduler sh = new Scheduler();

		boolean running = true;

		printOptions();
		while (running) {

			System.out.print("\nEnter your selection: ");
			int option = getInt();

			switch (option) {

			case 1:
				addTutor(sh);
				break;

			case 2:
				System.out.println(sh.toString());
				break;

			default:
				System.out.println("Something went very wrong ...");

			case 0:
				System.out.println("Exiting program ...");
				running = false;
				break;
			}
		}

	}

	public static void addTutor(Scheduler scheduler) {
		System.out.println("When do you want to add the tutor?");
		System.out.println("1. Monday");
		System.out.println("2. Tuesday");
		System.out.println("3. Wednesday");
		System.out.println("4. Thursday");
		System.out.println("5. Friday");
		System.out.println("0. Nevermind");
		System.out.print("    Day:");
		int day = getInt();
		System.out.println("\n What time do you want to start?");
		System.out.println("1. 10:00");
		System.out.println("2. 12:00");
		System.out.println("3. 14:00");
		System.out.println("4. 16:00");
		System.out.println("0. Nevermind");
		System.out.print("    Time: ");
		int time = getInt();
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

	public static void printOptions() {

		System.out.println("1. Add tutor");
		System.out.println("2. Print Schedule");
		System.out.println("0. Exit");

	}

}

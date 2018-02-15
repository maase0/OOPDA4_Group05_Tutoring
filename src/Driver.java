import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Driver {

	static BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException {
		Scheduler sh = new Scheduler();

		boolean running = true;

		printOptions();
		while (running) {

			System.out.print("Enter your selection: ");
			int option = -1;
			try {
				option = getInt();
			} catch (Exception e) {
				System.out.println("Error: Invalid Input");
				option = -1;
			}

			switch (option) {

			case 1:
				System.out.println("You chose option 1");
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

	public static int getInt() throws IOException {
		return Integer.parseInt(stdin.readLine());
	}

	public static void printOptions() {

		System.out.println("1. Add tutor");
		System.out.println("2. Print Schedule");
		System.out.println("0. Exit");

	}


}

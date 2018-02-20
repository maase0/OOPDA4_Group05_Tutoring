import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Driver {

	static BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

	static ArrayList<Tutor> tutors = new ArrayList<Tutor>();
	static ArrayList<Student> students = new ArrayList<Student>();

	public static void main(String[] args){
		Scheduler sh = new Scheduler();

		boolean running = true;

		printOptions();
		while (running) {

			System.out.print("\nEnter your selection: ");
			int option = getInt();

			switch (option) {

			case 1:
				scheduleTutor(sh);
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

		System.out.print("Current Tutors: ");
		if(tutors.isEmpty()){
			System.out.println("None");	
		} else {
			for(Tutor t : tutors) {
				System.out.print(t + ", ");	
			}	
		}

		}

	}

	public static void scheduleTutor(Scheduler scheduler) {
		int day;
		int time;
		
		System.out.println("When do you want to schedule the tutor?");
		System.out.println("1. Monday");
		System.out.println("2. Tuesday");
		System.out.println("3. Wednesday");
		System.out.println("4. Thursday");
		System.out.println("5. Friday");
		System.out.println("0. Nevermind");
		System.out.print("    Day:");
		day = getInt();
		
		if(day == 0) {
			System.out.println("\nCancelling 'Schedule tutor'");
			return;
		}
		
		System.out.println("\n What time do you want to start?");
		System.out.println("1. 10:00");
		System.out.println("2. 12:00");
		System.out.println("3. 14:00");
		System.out.println("4. 16:00");
		System.out.println("0. Nevermind");
		System.out.print("    Time: ");
		time = getInt();
		
		if(time == 0) {
			System.out.println("\nCancelling 'Schedule tutor'");
			return;
		}	

		if(day >= 1 && day <= 5 && time >= 1 && time <= 4) {
			switch(time) {
				case 1: time = 1000; break;
				case 2: time = 1200; break;
				case 3: time = 1400; break;
				case 4: time = 1600; break;
				default: time = -1; break; //not needed
			}

			

			//TODO: Add existing tutor or make new tutor

			if(scheduler.checkBlockAvailablity(intToDay(day), time)) {


				System.out.print("Do you want to add a previously added tutor? (y/N) ");
				if(getString().trim().toUpperCase().equals("Y")){
					System.out.println("Please select the tutor");
					displayTutors();
					System.out.print("Enter your selection: ");
					int option = getInt();
					if(option == 0) {
						System.out.println("Cancelling 'Schedule tutor'");	
					} else if(option > 0 && option <= tutors.size()) {
						scheduler.scheduleTutor(tutors.get(option - 1), intToDay(day), time);
					} else {
						System.out.println("Error: Invalid option");	
					}

				} else {
					System.out.println("Adding a new tutor.");
					System.out.print("Enter tutor's name: ");
					String name = getString();
	
					System.out.print("Enter tutor's year: ");
					String year = getString();

					Tutor t = new Tutor(name, year);
					tutors.add(t);
					scheduler.scheduleTutor(t, intToDay(day), time);
				}
			} else {
				System.out.println("Block not available");
			}
		} else {
			System.out.println("Invalid time or day! Tutor not added!");
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
	
	public static Day intToDay(int day) {
		Day d;
		switch(day) {
		case 1: d = Day.MONDAY; break;
		case 2: d = Day.TUESDAY; break;
		case 3: d = Day.WEDNESDAY; break;
		case 4: d = Day.THURSDAY; break;
		case 5: d = Day.FRIDAY; break;
		default: d = Day.INVALID; break;
		}
		
		return d;
	}

}

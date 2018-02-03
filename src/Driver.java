import java.util.Arrays;

public class Driver {

	public static void main(String[] args) {
		Scheduler s = new Scheduler();
		
		printSchedule(s);
	}
	
	public static void printSchedule(Scheduler s) {
		for(String row : s.convertToStringArray()) {
			System.out.println(row);
		}
	}
	
}

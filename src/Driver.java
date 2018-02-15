import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Driver {

	public static void main(String[] args) {
		Scheduler sh = new Scheduler();
		static BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		
		
	}
	
	public static void printSchedule(Scheduler s) {
		int count = 0;
		for(String row : s.convertToStringArray()) {
			System.out.println(count + "   " + row);
			count++;
		}
	}
	
}

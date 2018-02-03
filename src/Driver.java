public class Driver {

	public static void main(String[] args) {
		Scheduler s = new Scheduler();
		Tutor t = new Tutor("Guy Person", "senior");
		
		s.addTutor(Day.MONDAY, 1500, t);
		
		printSchedule(s);
	}
	
	public static void printSchedule(Scheduler s) {
		for(String row : s.convertToStringArray()) {
			System.out.println(row);
		}
	}
	
}

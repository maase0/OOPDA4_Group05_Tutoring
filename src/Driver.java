public class Driver {

	public static void main(String[] args) {
		/*Scheduler sh = new Scheduler();
		Tutor t = new Tutor("Guy Person", "senior");
		Student s = new Student("Somebody");
		
		sh.addTutor(Day.MONDAY, 1000, t);
		sh.addTutor(Day.MONDAY, 1015, t);
		sh.addTutor(Day.MONDAY, 1030, t);
		sh.addTutor(Day.MONDAY, 1045, t);
		sh.addTutor(Day.MONDAY, 1500, t);
		sh.addTutor(Day.MONDAY, 1515, t);
		sh.addTutor(Day.MONDAY, 1530, t);
		sh.addTutor(Day.MONDAY, 1545, t);
		sh.addTutor(Day.MONDAY, 1600, t);
		sh.addTutor(Day.MONDAY, 1615, t);

		sh.addStudent(Day.MONDAY, 1015, s);
		sh.addStudent(Day.MONDAY, 1500, s);
		
		printSchedule(sh);
		System.out.println(sh.checkAvailabilityByTutor(t, Day.MONDAY, 30));*/
		
		GUI gui = new GUI();
	}
	
	public static void printSchedule(Scheduler s) {
		int count = 0;
		for(String row : s.convertToStringArray()) {
			System.out.println(count + "   " + row);
			count++;
		}
	}
	
}

import java.util.HashMap;

public class Scheduler {

	HashMap<Day, HashMap<Integer, Pair>> schedule;
	
	public Scheduler() {
		schedule = new HashMap<Day, HashMap<Integer, Pair>>();
		initializeSchedule();
	}
	
	/**
	 * Adds a tutor to a given time and day only if there is not already a tutor
	 * at that time and day
	 * @param day The day to schedule the tutor
	 * @param time The time to schedule the tutor
	 * @param tutor The tutor to schedule
	 */
	public void addTutor(Day day, int time, Tutor tutor) {
		if(schedule.get(day).get(time) == null) {
			schedule.get(day).put(time, new Pair(tutor));
		}
	}
	
	/**
	 * Sets the student of an existing tutor-student pair on the given day and time
	 * Currently allow overwriting a current student
	 * @param day The day to add the student
	 * @param time The time to add the student
	 * @param student The student to be added
	 * @return true if the student was successfully added
	 */
	public boolean addStudent(Day day, int time, Student student) {
		boolean set = false;
		HashMap<Integer, Pair> timetable = schedule.get(day);
		if(timetable != null && timetable.get(time) != null) {
			timetable.get(time).setStudent(student);
			set = true;
		}
		return set;
	}
	
	/**
	 * Initializes the schedule of all possible days
	 */
	private void initializeSchedule() {
		schedule.put(Day.MONDAY, new HashMap<Integer, Pair>());
		schedule.put(Day.TUESDAY, new HashMap<Integer, Pair>());
		schedule.put(Day.WEDNESDAY, new HashMap<Integer, Pair>());
		schedule.put(Day.THURSDAY, new HashMap<Integer, Pair>());
		schedule.put(Day.FRIDAY, new HashMap<Integer, Pair>());
	}
	
	
}

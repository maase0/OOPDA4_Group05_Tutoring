import java.util.HashMap;
import java.util.ArrayList;

public class Scheduler {

	HashMap<Day, HashMap<Integer, Pair>> schedule;
	
	public Scheduler() {
		schedule = new HashMap<Day, HashMap<Integer, Pair>>();
		initializeSchedule();
	}
	
	/**
	 * Returns the entire schedule
	 * @return The schedule
	 */
	public HashMap<Day, HashMap<Integer, Pair>> getSchedule() {
		return schedule;
	}
	
	public void getScheduleAsArray() {
		Pair[][] scheduleArray = new Pair[5][(1745 - 1000) % 15];
		
		int dayCount = 0;
		for(Day day : Day.values()) {
			int timeCount = 0;
			for(Integer i : schedule.get(day).keySet()) {
				scheduleArray[dayCount][timeCount] = schedule.get(day).get(timeCount + 1000);
				
			}
		}
	}
	
	/**
	 * Returns the timetable for a given day
	 * @param day The day to get the timetable for
	 * @return The time-pair hashmap timetable for a given day
	 */
	public HashMap<Integer, Pair> getTimetable(Day day) {
		return schedule.get(day);
	}
	
	/**
	 * Checks if any tutor available for a given time, day, and duration.
	 * @param day The day to check
	 * @param startTime The start time to check
	 * @param duration The duration of the appointment
	 * @return true if there is a tutor available
	 */
	public boolean checkAvailability(Day day, int startTime, int duration) {
		boolean available = false;
		HashMap<Integer, Pair> timetable = schedule.get(day);
		
		if(timetable.get(startTime) != null) {
			available = true;
			
			Tutor t = timetable.get(startTime).getTutor();
			for(int i = startTime + 15; i < startTime + duration; i += 15) {
				if(!(timetable.get(i) != null && timetable.get(i).getTutor().equals(t))) {
					available = false;
				}
			}
		}
		
		return available;
	}
	
	public boolean findAvailability(Tutor tutor, int duration) {
		boolean availability = false;
		ArrayList<>
		
		
		return availability;
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
		for(Day day : Day.values()) {
			schedule.put(day, new HashMap<Integer, Pair>());
		}
	}
	
	
}

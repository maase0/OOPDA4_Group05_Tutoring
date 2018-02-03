public class Scheduler {

	public static final int DAYS = 5;
	public static final int BLOCK_LENGTH = 15;
	public static final int NUM_BLOCKS = 32; // 1000 to 1800: 8 hours * 4 fifteen min blocks per hour
	
	private Pair[][] schedule;
	
	public Scheduler() {
		schedule = new Pair[DAYS][NUM_BLOCKS];
		initializeSchedule();
	}
	
	/**
	 * Returns the entire schedule
	 * @return The schedule
	 */
	private Pair[][] getSchedule() {
		return schedule;
	}
	
	
	
	/**
	 * Checks if any tutor available for a given time, day, and duration.
	 * @param day The day to check
	 * @param startTime The start time to check
	 * @param duration The duration of the appointment
	 * @return true if there is a tutor available for the given time and duration
	 */
	public boolean checkAvailability(Day day, int startTime, int duration) {
		boolean available = false;
		
		Tutor t = schedule[day.value()][timeToArrayIndex(startTime)].getTutor();
		if(t != null) {
			available = true;
			for(int i = 15; i < duration; i += 15) {
				if(! t.equals((schedule[day.value()][timeToArrayIndex(startTime)].getTutor())) && available) {
					available = false;
				}
			}
		}
		return available;
	}
	
	
	
	/**
	 * Adds a tutor to a given time and day only if there is not already a tutor
	 * at that time and day
	 * @param day The day to schedule the tutor
	 * @param time The time to schedule the tutor
	 * @param tutor The tutor to schedule
	 */
	public void addTutor(Day day, int time, Tutor tutor) {
		if(schedule[day.value()][timeToArrayIndex(time)].getTutor() == null) {
			schedule[day.value()][timeToArrayIndex(time)].setTutor(tutor);
		}
	}
	
	/**
	 * Sets the student of an existing tutor-student pair on the given day and time
	 * Currently allow overwriting a current student
	 * @param day The day to add the student
	 * @param time The time to add the student
	 * @param student The student to be added
	 */
	public void addStudent(Day day, int time, Student student) {
		schedule[day.value()][timeToArrayIndex(time)].setStudent(student);
	}
	
	/**
	 * Converts the time from 24 hour HHMM format to a value from 0 to 31
	 * @param time The time to convert
	 * @return An array index for the schedule array
	 */
	private int timeToArrayIndex(int time) {
		return (((int) time / 100) - 10) * 4 + ((time % 100) / 15);
	}
	
	/**
	 * Initializes the schedule to all null
	 * Maybe not needed
	 */
	private void initializeSchedule() {
		for(int i = 0; i < DAYS; i++) {
			for(int j = 0; j < NUM_BLOCKS; j++) {
				schedule[i][j] = new Pair();
			}
		}
	}
	
	public String[] convertToStringArray() {
		String[] rows = new String[Scheduler.NUM_BLOCKS];
		Arrays.fill(rows, "");
		
		for(int i = 0; i < Scheduler.DAYS; i++) {
			for(int j = 0; j < Scheduler.NUM_BLOCKS; j++) {
				rows[j] += schedule[i][j].getTutor() == null ? "NONE" : schedule[i][j].getTutor().getName();
				rows[j] += ": ";
				rows[j] += schedule[i][j].getStudent() == null ? "NONE" : schedule[i][j].getStudent().getName();
				rows[j] += "   ";
			}
		}
		
		return rows;
	}
	
	
}

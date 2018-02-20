import java.util.Arrays;
import java.util.ArrayList;

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
	 * 
	 * @return The schedule
	 */
	private Pair[][] getSchedule() {
		return schedule;
	}

	/**
	 * Returns an array list of starting times a given tutor is available for an
	 * appointment of a given length on a given day
	 * 
	 * @param tutor
	 *            The tutor to check
	 * @param day
	 *            The day of the appointment
	 * @param duration
	 *            The duration of the appointment
	 * @return An array list of valid starting times
	 */
	public ArrayList<Integer> checkAvailabilityByTutor(Tutor tutor, Day day, int duration) {
		ArrayList<Integer> availableTimes = new ArrayList<Integer>();
		int blockOffset = duration / BLOCK_LENGTH;

		for (int time = 0; time < NUM_BLOCKS - blockOffset; time++) {
			boolean available = true;
			for (int i = time; (i - time) < blockOffset && available; i++) {
				if (!tutor.equals(schedule[day.value()][i].getTutor())
						|| schedule[day.value()][i].getStudent() != null) {
					available = false;
				}
			}
			if (available) {
				availableTimes.add(time);
			}
		}

		return availableTimes;
	}

	/**
	 * Checks if a two hour block is available at the given day and starting time.
	 * Only checks if there is no tutor.
	 * @param day The day to check
	 * @param startTime The start of the block
	 * @return True if 8 consecutive 15 minute blocks are free
	 */
	public boolean checkBlockAvailablity(Day day, int startTime) {
		boolean available = true;
		int index = timeToArrayIndex(startTime);
		if(index <= NUM_BLOCKS - 8) {
			for(int i = index; i < index + 8 && available; i++) {
				if(schedule[day.value()][i].getTutor() != null) {
					available = false;
				}
			}
		} else {
			available = false;
		}
		
		return available;
	}
	
	/**
	 * Adds a tutor to a two hour block of time on given day and start time
	 * @param t The tutor to schedule
	 * @param day The day to schedule the tutor
	 * @param startTime The beginning of the tutors scheduled block
	 * @return true if the tutor was successfully scheduled
	 */
	public boolean scheduleTutor(Tutor t, Day day, int startTime) {
		boolean successful = true;
		int index = timeToArrayIndex(startTime);
		if(index <= NUM_BLOCKS - 8) {
			for(int i = index; i < index + 8; i++) {
				schedule[day.value()][i].setTutor(t);
			}
		} else {
			successful = false;
		}
		
		return successful;
	}
	
	/**
	 * Checks if any tutor available for a given time, day, and duration.
	 * 
	 * @param day
	 *            The day to check
	 * @param startTime
	 *            The start time to check
	 * @param duration
	 *            The duration of the appointment
	 * @return true if there is a tutor available for the given time and duration
	 */
	public boolean checkAvailability(Day day, int startTime, int duration) {
		boolean available = false;

		Tutor t = schedule[day.value()][timeToArrayIndex(startTime)].getTutor();
		if (t != null) {
			available = true;
			for (int i = 15; i < duration; i += 15) {
				if (!t.equals((schedule[day.value()][timeToArrayIndex(startTime)].getTutor())) && available) {
					available = false;
				}
			}
		}
		return available;
	}

	/**
	 * Adds a tutor to a given time and day only if there is not already a tutor at
	 * that time and day
	 * 
	 * @param day
	 *            The day to schedule the tutor
	 * @param time
	 *            The time to schedule the tutor
	 * @param tutor
	 *            The tutor to schedule
	 */
	public void addTutor(Day day, int time, Tutor tutor) {
		if (schedule[day.value()][timeToArrayIndex(time)].getTutor() == null) {
			schedule[day.value()][timeToArrayIndex(time)].setTutor(tutor);
		}
	}

	/**
	 * Sets the student of an existing tutor-student pair on the given day and time
	 * Currently allow overwriting a current student
	 * 
	 * @param day
	 *            The day to add the student
	 * @param time
	 *            The time to add the student
	 * @param student
	 *            The student to be added
	 */
	public void addStudent(Day day, int time, Student student) {
		schedule[day.value()][timeToArrayIndex(time)].setStudent(student);
	}

	/**
	 * Converts the time from 24 hour HHMM format to a value from 0 to 31
	 * 
	 * @param time
	 *            The time to convert
	 * @return An array index for the schedule array
	 */
	private int timeToArrayIndex(int time) {
		return (((int) time / 100) - 10) * 4 + ((time % 100) / 15);
	}

	private int arrayIndexToTime(int index) {
		return 0;
	}

	/**
	 * Initializes the schedule to all null Maybe not needed
	 */
	private void initializeSchedule() {
		for (int i = 0; i < DAYS; i++) {
			for (int j = 0; j < NUM_BLOCKS; j++) {
				schedule[i][j] = new Pair();
			}
		}
	}

	/**
	 * Converts the schedule to an array of strings where each row is an element of
	 * the array and columns are separated by a space
	 */
	public String toString() {

		String returnString = "";
		returnString += String.format("%6s|", "");
		returnString += String.format("%20s|", "MONDAY");
		returnString += String.format("%20s|", "TUESDAY");
		returnString += String.format("%20s|", "WEDNESDAY");
		returnString += String.format("%20s|", "THURSDAY");
		returnString += String.format("%20s|", "FRIDAY");
		returnString += "\n---------------------------------------------------------"
				+ "-------------------------------------------------------\n";
		for (int i = 1000; i < 1800; i += 100) {
			for (int j = 0; j < 60; j += 15) {
				returnString += String.format("%6d|", (i+j));
				for (int day = 0; day < 5; day++) {
					returnString += String.format("%10s%10s|", schedule[day][timeToArrayIndex(i+j)].getTutor(),
							schedule[day][timeToArrayIndex(i+j)].getStudent());
				}
				returnString += "\n";
			}
		}
		
		return returnString;
	}

}

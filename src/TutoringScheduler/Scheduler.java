package TutoringScheduler;

/**
 * @author Erich Maas
 */

import java.util.Arrays;
import java.util.ArrayList;
import java.io.Serializable;

public class Scheduler implements Serializable {

	/**
	 * Properties of the scheduler
	 */
    public static final int DAYS = 5;
    public static final int BLOCK_LENGTH = 15;
    public static final int NUM_BLOCKS = 32; // 1000 to 1800: 8 hours * 4 fifteen min blocks per hour

    private Pair[][] schedule;

	/**
	 * Creates a new scheduler object.
	 *
	 * Creates an array of size DAYS by NUM_BLOCKS
	 * and initializes it
	 */
    public Scheduler() {
        schedule = new Pair[DAYS][NUM_BLOCKS];
        initializeSchedule();
    }

    /**
     * Returns the entire schedule
     *
     * @return The schedule
     */
    public Pair[][] getSchedule() {
        return schedule;
    }

    /**
     * Returns an array list of starting times a given tutor is available for an
     * appointment of a given length on a given day
     *
     * @param day The day of the appointment
	 * @param startTime The starting time of the appointment
     * @param duration The duration of the appointment
     * @return An array list of valid starting times
     */
    public ArrayList<String> checkAvailabilityInBlock(int day, int startTime, int duration) {
        ArrayList<String> availableTimes = new ArrayList<String>();
        int blockOffset = duration / BLOCK_LENGTH;

        for (int time = timeToArrayIndex(startTime); time < NUM_BLOCKS - blockOffset; time++) {
            boolean available = true;
            for (int i = time; (i - time) < blockOffset && available; i++) {
                if (schedule[day][i].getTutor() == null  || schedule[day][i].getStudent() != null) {
                    available = false;
                }
            }
            if (available) {
                availableTimes.add("" + arrayIndexToTime(time));
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
    public boolean checkBlockAvailablity(int day, int startTime) {
        boolean available = true;
        int index = timeToArrayIndex(startTime);
        if(index <= NUM_BLOCKS - 8) {
            for(int i = index; i < index + 8 && available; i++) {
                if(schedule[day][i].getTutor() != null) {
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
    
    public boolean scheduleTutor(Tutor t,int day, int startTime) {
        boolean successful = true;
        int index = timeToArrayIndex(startTime);
        if(index <= NUM_BLOCKS - 8) {
            for(int i = index; i < index + 8; i++) {
                schedule[day][i].setTutor(t);
            }
        } else {
            successful = false;
        }

        return successful;
    }

	/**
	 * Schedules a student on a given time and day for a given appointment length
	 *
	 * @param s The student object to schedule
	 * @param day The day to schedule the student
	 * @param startTime The beginning of the appointment
	 * @param length How long the appointment will last
	 *
	 * @return True if the student was successfully scheduled
	 */
	public boolean scheduleStudent(Student s, int day, int startTime, int length)
	{
		boolean successful = true;
		int index = timeToArrayIndex(startTime);
		if(index <= NUM_BLOCKS - (length / 15))
		{
			for(int i = index; i < index + (length / 15); i++)
			{
				schedule[day][i].setStudent(s);	
			}
		}
		else
		{
			successful = false;	
		}

		return successful;
	}

    /**
     * Checks if any tutor available for a given time, day, and duration.
     *
     * @param day The day to check
     * @param startTime The start time to check
     * @param duration The duration of the appointment
     * @return true if there is a tutor available for the given time and duration
     */
    public boolean checkAvailability(int day, int startTime, int duration) {
        boolean available = false;

        Tutor t = schedule[day][timeToArrayIndex(startTime)].getTutor();
        if (t != null) {
            available = true;
            for (int i = 15; i < duration; i += 15) {
                if (!t.equals((schedule[day][timeToArrayIndex(startTime)].getTutor())) && available) {
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
     * @param day The day to schedule the tutor
     * @param time The time to schedule the tutor
     * @param tutor The tutor to schedule
     */
    public void addTutor(int day, int time, Tutor tutor) {
        if (schedule[day][timeToArrayIndex(time)].getTutor() == null) {
            schedule[day][timeToArrayIndex(time)].setTutor(tutor);
        }
    }

    /**
     * Sets the student of an existing tutor-student pair on the given day and time
     * Currently allow overwriting a current student
     *
     * @param student The student to be added
     * @param day The day to add the student
     * @param time The time to add the student
     */
    public void addStudent(Student student, int day, int time) {
        schedule[day][timeToArrayIndex(time)].setStudent(student);
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
	
	/**
     * Converts the time from 24 hour HHMM format to a value from 0 to 31
     *
     * @param time The time to convert
     * @return An array index for the schedule array
     */
    public static int timeToArrayIndex(int time) {
        return (((int) time / 100) - 10) * 4 + ((time % 100) / 15);
    }


	/**
	 * Converts a valid array index to its corresponding time in the schedule
	 *
	 * @param index The index of the time slot
	 *
	 * @return The time represented by the index
	 */
    public static int arrayIndexToTime(int index)
    {
        return 1000 + ((index / 4) * 100) + 15 * (index % 4);
    }

	/**
	 * Converts a valid DDHH time to its corresponding tutoring block in the schedule
	 *
	 * @param time The time to convert
	 *
	 * @return The beginning of the tutoring block containing the given time
	 */
    public static int timeToBlockStart(int time)
    {
        if(time >= 1000 && time < 1200)
        {
            return 1000;
        }
        else if(time < 1400)
        {
            return 1200;
        }
        else if(time < 1600)
        {
            return 1400;
        }
        else if(time < 1800)
        {
            return 1600;
        }
        else
        {
            return 0;
        }
    }


	/**
	 * Singleton object
	 */
	private static Scheduler instance;

	/**
	 * @return The instance of the singleton Scheduler object
	 */
	public static Scheduler getInstance()
	{
		if(instance == null)
		{
			instance = new Scheduler();
		}

		return instance;
	}

}

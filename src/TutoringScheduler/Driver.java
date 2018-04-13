package TutoringScheduler;

import java.util.ArrayList;

public class Driver {

    static ArrayList<Tutor> tutors = new ArrayList<Tutor>();
    static ArrayList<Student> students = new ArrayList<Student>();

    public static void main(String[] args) {

        Scheduler scheduler = new Scheduler();

        /*
        scheduler.scheduleTutor(new Tutor("Erich Maas", "Freshman"), 0, 1000);
        scheduler.scheduleTutor(new Tutor("Test Tutor", "Senior"), 0, 1400);
        scheduler.scheduleTutor(new Tutor("Guy Person", "Senior"), 3, 1600);
        scheduler.scheduleTutor(new Tutor("Tutor Test", "Sophomore"), 2, 1200);
        scheduler.scheduleTutor(new Tutor("Somebody", "Senior"), 4, 1400);
        scheduler.scheduleTutor(new Tutor("TTUUTTOORR", "Junior"), 1, 1600);
*/
        Student s = new Student("Test Student", "1");
        scheduler.addStudent(s, 0, 1015);
        scheduler.addStudent(s, 0, 1030);
        scheduler.addStudent(s, 0, 1045);

        Student s2 = new Student("Test Student 2", "2");
        scheduler.addStudent(s2, 0, 1100);
        scheduler.addStudent(s2, 0, 1115);
        scheduler.addStudent(s2, 0, 1130);
        

        //GUI gui = new GUI(scheduler);

		GUI.getInstance();

    }
}

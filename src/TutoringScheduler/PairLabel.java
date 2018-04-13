package TutoringScheduler;

/**
 * @author Erich Maas
 */
import javax.swing.*;
import java.awt.*;

public class PairLabel extends JPanel
{

    private JLabel student;
    private JLabel tutor;
    private BorderLayout layout;
    private String offset;

	/**
	 * Creates a new PairLabel object to display a student-tutor pair
	 */
    public PairLabel()
    {
        offset = "    ";
        setLayout(new BorderLayout());

        student = new JLabel();
        student.setHorizontalAlignment(SwingConstants.RIGHT);

        tutor = new JLabel();
        tutor.setHorizontalAlignment(SwingConstants.LEFT);

        add(tutor, BorderLayout.WEST);
        add(student, BorderLayout.EAST);
    }


	/**
	 * Sets the name of the student in the PairLabel
	 *
	 * @param studentName The name of the student
	 */
    public void setStudent(String studentName)
    {
        student.setText(studentName + offset);
    }

	/**
	 * Sets the name of the tutor in the PairLabel
	 *
	 * @param tutorName The name of the tutor
	 */
    public void setTutor(String tutorName)
    {
        tutor.setText(offset + tutorName);
    }
}

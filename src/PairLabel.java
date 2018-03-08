import javax.swing.*;
import java.awt.*;

public class PairLabel extends JPanel
{

	private JLabel student;
	private JLabel tutor;
	private BorderLayout layout;
	private String offset;

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


	public void setStudent(String studentName)
	{
		student.setText(studentName + offset);
	}

	public void setTutor(String tutorName)
	{
		tutor.setText(offset + tutorName);	
	}
}

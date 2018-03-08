import javax.swing.*;
import java.awt.*;

public class PairLabel extends JPanel
{

	private JLabel student;
	private JLabel tutor;
	private BorderLayout layout;

	public PairLabel()
	{
		//layout = new BorderLayout();
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
		student.setText(studentName);
	}

	public void setTutor(String tutorName)
	{
		student.setText(tutorName);	
	}
}

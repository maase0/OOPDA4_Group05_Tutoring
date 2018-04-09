import java.io.Serializable;

public class Tutor implements Serializable {
	
	private String name;
	private String year;
	private String ID;
	
	public Tutor(String name, String year, String ID) {
		this.name = name;
		this.year = year;
		this.ID = ID;
	}
	
	public String getName() {
		return name;
	}
	
	public String getYear() {
		return year;
	}
	public String getID() {
		return ID;
	}
	
	public String toString() {
		return ID + " " + name + " " + year;
	}

}

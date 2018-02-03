
public enum Day {

	MONDAY(0), TUESDAY(1), WEDNESDAY(2), THURSDAY(3), FRIDAY(4);
	
	
	private final int value;
	private Day(int value) {
		this.value = value;
	}
	
	public int value() {
		return value;
	}
	
}

package hworg;

public class Date {
	private int month;
	private int day;
	private int year;
	private String errorMessage = "You have entered an invalid date."
								+ "\nThe date will be set to 1/1/18 by default."
								+ "\nCall the setDate()	method to try again.";
	
	
	//This is a constructor for the Date Class.
	public Date(int month, int day, int year){
		setDate(month, day, year);
	}
	
	
	/**
	 * Setters Begin here
	 */
	//This method sets the date. It has integrated validity checks for each value.
	public void setDate(int month, int day, int year){
		setMonth(month);
		setDay(day);
		setYear(year);
	}
	//This is a default date that is set in case an invalid date is entered.
	private void errorDate(){
		this.day = 1;
		this.month = 1;
		this.year = 2018;
	}
	//This is a complicated method to check if the day is valid and to set the day value.
	private void setDay(int day){
		if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12){
			if(1 <= day && day <= 31){
				this.day = day;
			}
		}
		if(month == 4 || month == 6 || month == 9 || month == 11){
			if (1 <= day && day <= 30){
				this.day = day;
			}
		}
		if(month == 2){
			if((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0)){
				if(1 <= day && day <= 29){
					this.day = day;
				}
			}
			else{
				if(1 <= day && day <= 28){
					this.day = day;
				}
			}
		}
		else{
			System.out.println(errorMessage);
			errorDate();
		}
	}
	//This method sets the month. It contains a validity check.
	private void setMonth(int month){
		if(1 <= month && month <= 12){
			this.month = month;
		}
		else{
			System.out.println(errorMessage);
			errorDate();
		}
	}
	//This method sets the year and contains a simple validity check.
	private void setYear(int year){
		if(year >= 0){
			this.year = year;
		}
		else{
			System.out.println(errorMessage);
			errorDate();
		}
	}
	
	/**
	 * Getters begin here
	 */
	
	public void printDate(){
		System.out.println("Due Date: " + month + "/" + day + "/" + year);
	}
}

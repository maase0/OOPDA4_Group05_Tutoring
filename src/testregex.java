import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class testregex 
{
	public static void main(String[] s)
	{
		// String to be scanned to find the pattern.
	      String line = "41234125 : 51235125 : CS2345 : DATE : LENGTH";
	      String[] stuff = line.split("\\ : ");
	      
	      System.out.println(stuff[0]);
	      System.out.println(stuff[1]);
	      System.out.println(stuff[2]);
	      System.out.println(stuff[3]);
	      System.out.println(stuff[4]);
	      /*
	      // Create a Pattern object
	      Pattern r = Pattern.compile(pattern);

	      // Now create matcher object.
	      Matcher m = r.matcher(line);
	      String nLine = "";
	      if(m.matches())
	      {
	    	  nLine = m.group(1);
	      }
	      /*String npattern = ":(.*):";
	      Pattern nr = Pattern.compile(pattern);
	      Matcher nm = nr.matcher(nLine);
	      
	      if (m.find( )) {
	    	  System.out.print(m.group());
	         /*System.out.println("Found value: " + m.group(0) );
	         System.out.println("Found value: " + m.group(1) );
	      }else {
	         System.out.println("NO MATCH");
	      }*/
	}
}

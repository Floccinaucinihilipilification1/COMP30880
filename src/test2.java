import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class test2 {
    public static void main(String[] args) {
       String file = "src/Miskatonic Staff Members.csv";
       String delimiter = ",";
       String line;
       List<List<String>> lines = new ArrayList<List<String>>();
        try (BufferedReader br =
                     new BufferedReader(new FileReader(file))) {
            while((line = br.readLine()) != null){
            	line = replaceCommas(line);
                List<String> values = Arrays.asList(line.split(delimiter));
                lines.add(values);
            }
            lines.forEach(l -> System.out.println(l));
        } catch (Exception e){
            System.out.println(e);
        }
    }
    
    
    //replaces any ',' enclosed in '"' with ';' then removes all '"' (Could possibly use regular expression instead?)
    public static String replaceCommas(String myString) {
    	boolean inQuotes = false;
		for (int i=0; i < myString.length(); i++) {
    		
    		if ((myString.charAt(i) == '\"') && (!inQuotes) ) inQuotes = true;    		
    		else if (myString.charAt(i) == '\"') inQuotes = false;
    		
    		if (inQuotes && myString.charAt(i) == ',') {
    			myString = myString.substring(0,i) + ';' + myString.substring(i+1);
    		}
    	}
		myString = myString.replace("\"", "");
		return myString;
    }
    
    //can be used to convert columns 2 and 3 into array lists
    public static List<String> toArrayList(String myString) {
		String delimiter = ";";
    	return Arrays.asList(myString.split(delimiter));
    }
    
}

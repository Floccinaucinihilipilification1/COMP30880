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
       List<StaffMember> staffMembers = new ArrayList();
        try (BufferedReader br =
                     new BufferedReader(new FileReader(file))) {
        	br.readLine(); //initial readline() so not to include first line (column names) 
            while((line = br.readLine()) != null){
            	line = replaceCommas(line);
                List<String> columns = Arrays.asList(line.split(delimiter, -1));           
                
                String name = columns.get(0);
                List<String> researchActivity = toArrayList(columns.get(1));
                List<String> researchArea = toArrayList(columns.get(2));
                String specialFocus = columns.get(3);
                
                StaffMember staffMember = new StaffMember(name, researchActivity, 
                		researchArea, specialFocus);
                staffMembers.add(staffMember);
            }
            staffMembers.forEach(staffMember -> System.out.println(staffMember.toString()));
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
    
    //used to convert columns 2 and 3 into array lists
    public static List<String> toArrayList(String myString) {
		String delimiter = ";";
    	return Arrays.asList(myString.split(delimiter));
    }
    
}

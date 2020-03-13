import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class test2 {
    public static void main(String[] args) {
       String file = "src/Miskatonic Staff Members.csv";
       String delimiter = ",";
       String line;
       List<StaffMember> staffMembers = new LinkedList<StaffMember>();
        try (BufferedReader br =
                     new BufferedReader(new FileReader(file))) {
        	br.readLine(); //initial readline() so not to include first line (column names) 
            while((line = br.readLine()) != null){
            	line = replaceCommas(line);
                List<String> columns = Arrays.asList(line.split(delimiter, -1));           
                
                String name = columns.get(0);
                List<String> researchActivity = toLinkedList(columns.get(1));
                List<String> researchArea = toLinkedList(columns.get(2));
                String specialFocus = columns.get(3);
                
                StaffMember staffMember = new StaffMember(name, researchActivity, 
                		researchArea, specialFocus);
                staffMembers.add(staffMember);
            }
            for (int i=0; i < staffMembers.size()-1; i++) {
            	System.out.println(staffMembers.get(i).toString());
            	System.out.println(staffMembers.get(i).randomActivity());
            	System.out.println(staffMembers.get(i).toString());

            }
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
    public static List<String> toLinkedList(String myString) {
		String delimiter = ";";
		List<String>  myList = new LinkedList<String>();
		myList.addAll(Arrays.asList(myString.split(delimiter)));
		return myList;
    }
     
}

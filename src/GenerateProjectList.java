import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
public class GenerateProjectList {
    public static void main(String[] args) {
       String file = "src/Miskatonic Staff Members.csv";
       String delimiter = ",";
       String line;
       List<StaffMember> staffMembers = new LinkedList<StaffMember>();
        try (BufferedReader br =
                     new BufferedReader(new FileReader(file))) {
        	br.readLine(); //initial readLine() so not to include first line (column names) in loop 
            while((line = br.readLine()) != null){
            	line = line.replace(", ", ","); //removes leading whitespace between columns
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
        } catch (Exception e){
            System.out.println(e);
        }
        
        file = ("src/projectList.csv");
        try (BufferedWriter bw =
                new BufferedWriter(new FileWriter(file))) {
        	int listLength = 90; //number of lines in projectList
            Random r = new Random();
            for (int i=0; i<listLength; i++ ) {
            	int index = r.nextInt(staffMembers.size());
            	StaffMember staffMember = staffMembers.get(index);
            	if (!staffMember.getReseachActivity().isEmpty()) {
            		
            		String name = staffMember.getName();
            		String activity = staffMember.randomActivity();
            		String audience = null;
            		
            		if (staffMember.getSpecialFocus().equals("Dagon Studies")) audience = "CS + DS";
            		else audience = "CS";  
            		bw.write(name + "," + activity + "," + audience);
            		bw.newLine();
            	}
            	else i--; //decrements and tries again if researchActivity is empty
            }
            System.out.println("projectList.csv successfully created");
   	   
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
    		else if (inQuotes && myString.charAt(i) == ',') {
    			myString = myString.substring(0,i) + ';' + myString.substring(i+1); //changes string so "," at i is ";"
    		}
    		
    	}
		myString = myString.replace("\"", ""); //removes quotation marks as no longer necessary
		return myString;
    }
    
    //used to convert columns 2 and 3 into linked lists with delimiter ";"
    public static List<String> toLinkedList(String myString) {
		String delimiter = ";";
		List<String>  myList = new LinkedList<String>();
		myList.addAll(Arrays.asList(myString.split(delimiter)));
		
		//removes null element from each list
		for (int i=0; i < myList.size(); i++) {
			if (myList.get(i).equals("")) myList.remove(i);
		}
		
		return myList;
    }
     
}

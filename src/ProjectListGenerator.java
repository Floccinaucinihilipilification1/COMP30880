import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
public class ProjectListGenerator {
    
	String inputFile;
	String outputFile;
	int numSupervisors;

	List<StaffMember> staffMembers;
	
	ProjectListGenerator(String inputFile) {
		this.inputFile = inputFile;
		generateStaffMembers();
	}
	
	//Generates file from inputFile to outputFile with numSupervisors supervisors and 3 times as many projects
	public void generateProjectList(String output, int numSupervisors) {
				this.outputFile = output;
	        	List<StaffMember> supervisors = new LinkedList<StaffMember>();
	        	
	        	int numProjects;
	        	int iterations = 0;
				List<Project> projects = new LinkedList<Project>();
				
				/*adds numSupervisors Projects to projects w/ unique supervisors where 
				* between all supervisors there are enough projects for 3:1 ratio of 
				* projects to supervisors 
				*/
				Random r = new Random();
				do {
					projects = new LinkedList<Project>();
					generateStaffMembers();
					
					//removes 10*LoopIterations staffMembers who have less than 3 research activities to prevent loop taking too long
					for (int i=0; i < iterations*10; i++) {
						for (int j=0; j < staffMembers.size(); j++) {
							int size = staffMembers.get(j).getReseachActivity().size();
							if (size < 3) {
								staffMembers.remove(j);
								break;
							}
						}
					}
					
					
					numProjects = 0; //keeps track of the number of available projects left between all staff members
					for (int i=0; i<numSupervisors; i++ ) {
	            	
						int index = r.nextInt(staffMembers.size());
						StaffMember staffMember = staffMembers.get(index);
						if ((!staffMember.getReseachActivity().isEmpty())
								&& (!supervisors.contains(staffMember))) {
	            		
							supervisors.add(staffMember);
							String supervisor = staffMember.getName();
							String project = staffMember.randomActivity();
							String audience = null;
							numProjects += staffMember.getReseachActivity().size();
							List<String> audienceList = new LinkedList<String>();
							audienceList.add("DS");
							audienceList.add("CS");
							audienceList.add("CS + DS");
							
							int audienceIndex = 0; 
							if (!staffMember.getSpecialFocus().equals("Dagon Studies")) audienceIndex = r.nextInt(2)+1; //randomly assigns "CS" or "CS + DS" if focus isn't Dagon Studies
							audience = audienceList.get(audienceIndex);
							
							projects.add(new Project(supervisor, project, audience));
	            	}
	            	else i--; //decrements and tries again if researchActivity is empty or staffMember is already included
	            	
	            }
	            iterations ++;
	        	} while (numProjects < numSupervisors*2); //we only check for possible 2:1 ratio since numSupervisors amount of projects already added
            	
				//adds numSupervisors*2 projects where project must be from existing supervisor
				for (int i=0; i<numSupervisors*2; i++ ) {
	            	
					int index = r.nextInt(staffMembers.size());
					StaffMember staffMember = staffMembers.get(index);
					if ((!staffMember.getReseachActivity().isEmpty())
							&& (supervisors.contains(staffMember))) {
            		
						supervisors.add(staffMember);
						String supervisor = staffMember.getName();
						String project = staffMember.randomActivity();
						String audience;
						if (staffMember.getSpecialFocus().equals("Dagon Studies")) audience = "DS";
						else audience = "CS";  
						projects.add(new Project(supervisor, project, audience));
					}
					else i--;
				}
				 Collections.shuffle(projects); //shuffles to prevent observable pattern in list order
				 
				 //writes projects to outputFile
				 try (BufferedWriter bw =
			                new BufferedWriter(new FileWriter(outputFile))) {
					 for (int i=0; i<projects.size(); i++) {
						 String supervisor = projects.get(i).getSupervisor();
						 String project = projects.get(i).getProject();
						 String audience = projects.get(i).getAudience();
						 String toWrite = supervisor + "," + project + "," + audience;
						 bw.write(toWrite);
						 bw.newLine();
					 }
				 } catch (IOException e) {
					e.printStackTrace();
				}
				System.out.println(outputFile + " successfully created");
				
				
	}

    
	
    //replaces any ',' enclosed in '"' with ';' then removes all '"' (Could possibly use regular expression instead?)
    private String replaceCommas(String myString) {
    	boolean inQuotes = false;
		for (int i=0; i < myString.length(); i++) {
    		
    		if ((myString.charAt(i) == '\"') && (!inQuotes) ) inQuotes = true;    		
    		else if (myString.charAt(i) == '\"') inQuotes = false;
    		else if (inQuotes && myString.charAt(i) == ',') {
    			myString = myString.substring(0,i) + ';' + myString.substring(i+1); //changes myString so "," at i is ";"
    		}
    		
    	}
		myString = myString.replace("\"", ""); //removes quotation marks as no longer necessary
		return myString;
    }
    
    //used to convert columns 2 and 3 into linked lists with delimiter ";"
    private List<String> toLinkedList(String myString) {
    	
 
    	
    	
		String delimiter = ";";
		List<String>  myList = new LinkedList<String>();
		myList.addAll(Arrays.asList(myString.split(delimiter)));
		
		//removes null element from each list
		for (int i=0; i < myList.size(); i++) {
			if (myList.get(i).equals("")) myList.remove(i);
		}	
		return myList;
    }

    private void  generateStaffMembers() {
    	
    	String x = "";
    	String y = "csv";
    	String s = inputFile;
    	String fileType = s.substring(s.length() - 3);
    	if(fileType.equals(y)) 
    	{ x = "," ;   }
    	
    	else {x ="/t";}
    	
    	
    	String delimiter = x;
		String line;
		staffMembers = new LinkedList<StaffMember>();
		BufferedReader br = null;
        try {
        	br = new BufferedReader(new FileReader(inputFile));
        	br.readLine(); //initial readLine() so not to include first line (column names) in loop 
            while((line = br.readLine()) != null){
            	line = line.replace(x + " ", x); //removes leading whitespace between columns
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
        } finally {
            
        	try {
				br.close();
			} catch (IOException e) {
				System.out.println(e);
				e.printStackTrace();
			}

        }
    }
       
}

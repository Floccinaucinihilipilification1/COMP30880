import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class CandidateSolutionGenerator {
	List<Student> students = new LinkedList<Student>();
	
	//constructor
	CandidateSolutionGenerator(String filename) {
		generateStudents(filename);
	}
	

	CandidateSolution generate() {
		Random rand = new Random();
		List<Integer> solutions = new LinkedList<Integer>();
		List<String> allProjects = new LinkedList<String>(); //projects that have been used for solutions (check to prevent duplicates)
		for (int i=0; i<students.size(); i++) {
			List<String> projects = students.get(i).getProjects();
			List<String> usableProjects = new LinkedList<String>(); //projects for student i that have not yet been used 
			usableProjects.addAll(projects);
			//removes already used solutions from usableProjects
			for (int j=0; j<projects.size(); j++) {
				if (allProjects.contains(projects.get(j))) usableProjects.remove(projects.get(j)); 
			}
			
			int projectRank;
			if (usableProjects.isEmpty()) projectRank = 0;
			else {
				String project = usableProjects.get(rand.nextInt(usableProjects.size()));			
				projectRank = projects.indexOf(project); //assigns rank as index of project which was randomly selected from usable projects.
				allProjects.add(project);
			}
			solutions.add(projectRank);
		}
		return new CandidateSolution(students, solutions);
	}
	
	
	void generateStudents(String filename) {
		
		//first creates randomPorjects list from RandomProjects.csv, to be used whenever a student needs additional projects
		List<String> randomProjects = new LinkedList<String>();
		try(BufferedReader br = new BufferedReader(new FileReader("RandomProjects.csv"))) {
			String line;
   		 	while ((line = br.readLine()) != null) {
   		 		randomProjects.add(line);
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//System.out.println(randomProjects.toString());
		
		//Generates students list
		try(BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line;
   		 	br.readLine(); //initial readLine with headers
			while ((line = br.readLine()) != null) {
				line = replaceCommas(line);				
				List<String> projects = new LinkedList<String>();

				List<String>columns = new LinkedList<String>();
				
				columns.addAll(Arrays.asList(line.split(",", -1)));
   			
				//remove all empty entries from columns
				for (int i=0; i<columns.size(); i++) columns.removeAll(Collections.singleton(""));
				
				
				if (columns.size() != 0) { 
					//if a student has less than 10 preferences we append more random preferences until they have 10
					Random rand = new Random();
					List<String> usableProjects = new LinkedList<String>();
					usableProjects.addAll(randomProjects);
				
					while (columns.size() < 14) {	
						//remove already used projects
						for (int i=0; i<usableProjects.size(); i++) {
							if (columns.contains(usableProjects.get(i))) usableProjects.remove(usableProjects.get(i)); 
						}
						int randIndex = rand.nextInt(usableProjects.size());
						columns.add(usableProjects.get(randIndex));
					}
   			
					for (int i=4; i<14; i++) {
						projects.add(columns.get(i));
					}
				
					students.add(new Student(columns.get(0), columns.get(1), Double.parseDouble(columns.get(2)), projects, columns.get(3)));
				}
			}
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
   	 
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
}
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
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
			
			String project = usableProjects.get(rand.nextInt(usableProjects.size()));			
			int projectRank = projects.indexOf(project); //assigns rank as index of project which was randomly selected from usable projects.
			allProjects.add(project);
			solutions.add(projectRank);
		}
		return new CandidateSolution(students, solutions);
	}
	
	
	void generateStudents(String filename) {
   	 try(BufferedReader br = new BufferedReader(new FileReader(filename))) {
   		 String line;
			while ((line = br.readLine()) != null) {
			List<String> projects = new LinkedList<String>();
   			List<String>columns = Arrays.asList(line.split(",", -1));
   			for (int i=3; i<13; i++) {
   				projects.add(columns.get(i));
   			}
   			students.add(new Student(columns.get(0), columns.get(1), projects, columns.get(2)));
   		 }
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
   	 
    }
}

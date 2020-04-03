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
		for (int i=0; i<students.size(); i++) {
			solutions.add(rand.nextInt(10));
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

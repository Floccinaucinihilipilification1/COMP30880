import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CandidateSolution implements Comparable<CandidateSolution> {
	
	//student in students index i has solution in solutions index i
	List<Student> students;	
	List<Integer> solutions; //stored as integer corresponding with an index in students projects list
	Double fitness;
	//constructor
	CandidateSolution(List<Student> students, List<Integer> solutions) {
		this.students = students;
		this.solutions = solutions;
	}
	
	//getters
	public List<Student> getStudents() { return students; }
	//public Double getFitness() {return fitness;}
	public List<Integer> getSolutions() { return solutions; }
	public Student getStudentAt(int index) { return students.get(index); }
	public String getSolutionAt(int index) { return students.get(index).getProjects().get(solutions.get(index)); }
	public int getSolutionRankAt(int index) { return solutions.get(index); }
	public int getSize() { return solutions.size(); }
	public String toString() {
		String myString = "";
		for (int i=0; i<students.size(); i++) {
			myString += getStudentAt(i).getName() + " : " + getSolutionAt(i) + "\n";
		}
		return myString;
	}
	
	
	//setters
	public void setSolutionRankAt(int index, int rank) { solutions.set(index, rank); }
	public void setFitness(Double fitness) { this.fitness = fitness; }

	@Override
	public int compareTo(CandidateSolution o) {
	
		int compare = fitness.compareTo(o.fitness);
		return compare;
	}
	
	
	//saves solution to directory specified in dir
	public void saveSolution(String dir) {
		try (BufferedWriter bw =
                new BufferedWriter(new FileWriter(dir))) {
		 for (int i=0; i<students.size(); i++) {
			 bw.write(students.get(i).getName() + ", " + students.get(i).getProjects().get(solutions.get(i)));
			 bw.newLine();
		 }
	 } catch (IOException e) {
		e.printStackTrace();
	}
		
	}
}

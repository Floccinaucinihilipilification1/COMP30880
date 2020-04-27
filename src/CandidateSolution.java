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
		this.fitness = 0.0;
		
	}
	
	//getters
	public List<Student> getStudents() { return students; }
	public Double getFitness() {return fitness;}
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
}

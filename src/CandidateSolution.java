import java.util.List;

public class CandidateSolution {
	
	//student in students index i has solution in solutions index i
	List<Student> students;	
	List<Integer> solutions; //stored as integer corresponding with an index in students projects list
	
	//constructor
	CandidateSolution(List<Student> students, List<Integer> solutions) {
		this.students = students;
		this.solutions = solutions;
	}
	
	//getters
	public Student getStudentAt(int index) { return students.get(index); }
	public String getSolutionAt(int index) { return students.get(index).getProjects().get(solutions.get(index)); }
	public int getSolutionRankAt(int index) { return solutions.get(index); }
	public String toString() {
		String myString = "";
		for (int i=0; i<students.size(); i++) {
			myString += getStudentAt(i).getName() + " : " + getSolutionAt(i) + "\n";
		}
		return myString;
	}
		
	//setters
	public void setSolutionRankAt(int index, int rank) { solutions.set(index, rank); }
}

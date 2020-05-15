import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class SolutionChanger {
	public static double Fitness(CandidateSolution candidateSolution) {
 
		double fitness = 0;
		List<String> projects = new LinkedList<String>();
		for (int i=0; i<candidateSolution.getSize(); i++ ) {
			String project = candidateSolution.getSolutionAt(i);
			int projectRank = candidateSolution.getSolutionRankAt(i);
			//hard constraints (return 0 for hard constraints)
			//With the way the project is designed it is impossible for a student to have more than 1 assigned project so the hard constraint for more than one project is unnecessary. 
			if (projectRank < 0 || 9 < projectRank) return 0; //hard constraint if students projectRank is greater than their 10th choice
			if (projects.contains(project)) return fitness -= -100;  //hard constraint if 2 students have same project
			projects.add(project);
			
			//soft constraints
			fitness -= 10-projectRank * (candidateSolution.getStudentAt(i).GPA/4.2); //soft constraint where fitness increases based on how high (numerically low) a projectRank is
			
		} 
		
		return fitness;
	}
	
	public static double Energy(CandidateSolution candidateSolution) {
		if (Fitness(candidateSolution) == 0) return Double.POSITIVE_INFINITY;
		else return Fitness(candidateSolution)*-1;
	}
	
	public static String changeRandom(CandidateSolution candidateSolution) {
		Random rand = new Random();
		int studentIndex = rand.nextInt(candidateSolution.getSize());
		int newRank = rand.nextInt(10);
		int oldRank = candidateSolution.getSolutionRankAt(studentIndex);
		
		candidateSolution.setSolutionRankAt(studentIndex, newRank);
		System.out.println("Student: " + candidateSolution.getStudentAt(studentIndex).getName() + ", Rank changed from: " + oldRank + " to: " + newRank + "\n");
		GUI.ja.append("Student: " + candidateSolution.getStudentAt(studentIndex).getName() + ", Rank changed from: " + oldRank + " to: " + newRank + "\n");
		return ("\nStudent: " + candidateSolution.getStudentAt(studentIndex).getName() + ", Rank changed from: " + oldRank + " to: " + newRank);
	}

	
}

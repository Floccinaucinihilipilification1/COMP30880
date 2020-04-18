import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class SimulatedAnnealing {
	public static double Fitness(CandidateSolution candidateSolution) {
		
	/*
	for(long i = 0; i < projectRank; i++){
	double t = (double)i / projectRank = (1 - t)*1000; //set initialtemp as 1000
	}
	
	 private static double boltzmann(double x) {
		if (x < -1022)  // Underflow
			return 0;
		if (x >= 1024)  // Overflow
			return Double.POSITIVE_INFINITY;
		double y = Math.floor(x);
		double z = x - y;  // In the range [0.0, 1.0)
		double u = Double.longBitsToDouble((long)((int)y + 1023) << 52);  // Equal to 2^floor(x)
		double v = ((0.07901988694851840505 * z + 0.22412622970387342355) * z + 0.69683883597650776993) * z + 0.99981190792895544660;
		return u * v;
	}

	*/
		double fitness = 0;
		List<String> projects = new LinkedList<String>();
		for (int i=0; i<candidateSolution.getSize(); i++ ) {
			String project = candidateSolution.getSolutionAt(i);
			int projectRank = candidateSolution.getSolutionRankAt(i);
			//hard constraints (return 0 for hard constraints)
			//With the way the project is designed it is impossible for a student to have more than 1 assigned project so the hard constraint for more than one project is unnecessary. 
			if (projectRank < 0 || 9 < projectRank) return 0; //hard constraint if students projectRank is greater than their 10th choice
			if (projects.contains(project)) return 0;  //hard constraint if 2 students have same project
			projects.add(project);
			
			//soft constraints
			fitness -= 10-projectRank; //soft constraint where fitness increases based on how high (numerically low) a projectRank is
			
		} 
		
		return fitness;
	}
	
	public static double Energy(CandidateSolution candidateSolution) {
		if (Fitness(candidateSolution) == 0) return Double.POSITIVE_INFINITY;
		else return Fitness(candidateSolution)*-1;
	}
	
	public static void changeRandom(CandidateSolution candidateSolution) {
		Random rand = new Random();
		int studentIndex = rand.nextInt(candidateSolution.getSize());
		int newRank = rand.nextInt(10);
		int oldRank = candidateSolution.getSolutionRankAt(studentIndex);
		
		candidateSolution.setSolutionRankAt(studentIndex, newRank);
		
		System.out.println("Student: " + candidateSolution.getStudentAt(studentIndex).getName() + ", Rank changed from: " + oldRank + " to: " + newRank + "\n");
		
	}

	
}

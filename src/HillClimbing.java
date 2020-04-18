import java.util.Random;

public class HillClimbing {

	public static void climbing(CandidateSolution candidateSolution)
	{
	int x = 0;
		
	while (x < 100)  // run until 100 worse results are obtained
	{	
		
		double y = SimulatedAnnealing.Energy(candidateSolution);
		System.out.println("\n" + "The current fitness of the solution is " + SimulatedAnnealing.Fitness(candidateSolution));
		System.out.println("\n" + "The current energy of the solution is" + SimulatedAnnealing.Energy(candidateSolution));
		Random rand = new Random();
		int studentIndex = rand.nextInt(candidateSolution.getSize());
		int newRank = rand.nextInt(10);
		int oldRank = candidateSolution.getSolutionRankAt(studentIndex);
		candidateSolution.setSolutionRankAt(studentIndex, newRank);
		double z = SimulatedAnnealing.Energy(candidateSolution);
		
		
		if(z < y) {x =0;}
		
		if(z >= y) 
		{
			candidateSolution.setSolutionRankAt(studentIndex, oldRank); // revert worse choices.	
			x++;
			System.out.println(x);
		}
		
		
		System.out.println("Student: " + candidateSolution.getStudentAt(studentIndex).getName() + ", Rank changed from: " + oldRank + " to: " + newRank + "\n");
		
	
		
		
		
		
		

	
	}
	
	
	}
	
	
		

	

	
	
}

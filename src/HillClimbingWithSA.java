import java.util.Random;
public class HillClimbingWithSA {

	public static CandidateSolution climbing(CandidateSolution candidateSolution)
	{
	int x = 0;
	double temp = 100;
	double cooling = 10;
	while (x < 1000)  // run until 1000 worse results are obtained in a row
	{	
		
		double y = SolutionChanger.Energy(candidateSolution);
		System.out.println("\n" + "The current fitness of the solution is " + SolutionChanger.Fitness(candidateSolution));
		System.out.println("\n" + "The current energy of the solution is" + SolutionChanger.Energy(candidateSolution));
		Random rand = new Random();
		int studentIndex = rand.nextInt(candidateSolution.getSize());
		int newRank = rand.nextInt(10);
		int oldRank = candidateSolution.getSolutionRankAt(studentIndex);
		candidateSolution.setSolutionRankAt(studentIndex, newRank);
		double z = SolutionChanger.Energy(candidateSolution);
				
		if(z < y) {
			x = 0;
			if (1 < temp) temp = temp - cooling;
		} 
			
		else if (y < z)
		{

			double energyIncrease = z - y;
			double boltzmannProb = 1 / Math.pow(Math.E,(energyIncrease/temp));
			if ( rand.nextDouble() < boltzmannProb) {
				//System.out.println(temp); for testing
				//System.out.println(cooling); for testing
			
				x = 0;
				if (1 < temp) temp = temp - cooling;
			}
			else {
				candidateSolution.setSolutionRankAt(studentIndex, oldRank); // revert worse choices.
				//System.out.println(x); for testing
				x++;
			}
		}
		
		
		System.out.println("Student: " + candidateSolution.getStudentAt(studentIndex).getName() + ", Rank changed from: " + oldRank + " to: " + newRank + "\n");
		
		
		
		
		
		

	
	}
	return candidateSolution;
	
	}
	
	
		

	

	
	
}

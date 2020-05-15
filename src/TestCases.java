
public class TestCases {
	
	public static void main(String args[]) {
		
		CandidateSolutionGenerator candGen = new CandidateSolutionGenerator("sample preference matrix.csv");
		CandidateSolution cand = null;
		
		/*SimulatedAnnealing testing
			SimulatedAnnealing is tested by performing the HillClimbingWithSA.climb() method with the same
			input file but with different cooling, the most optimal cooling is then used in the application.
			We take an average fitness of 10 for each different cooling.
			
			Note: we test no less than cooling 10 as a cooling any smaller would not run in an acceptable time
			
			RESULTS:
			cooling10 : 77.94357142857143
			cooling25 : 77.83357142857142
			cooling50 :76.1407142857143
			cooling100 :76.03071428571428
			cooling200 :79.63642857142857
			cooling250 :75.23928571428571
			
			From this we conclude that 200 is the optimal value for the cooling rate
		*/
		
		//where average fitness is stored for different cooling schedules
		double cooling10 = 0;
		double cooling25 = 0;
		double cooling50 = 0;
		double cooling100 = 0;
		double cooling200 = 0;
		double cooling250 = 0;
		
		for (int i=0; i<10; i++) {
			cand = candGen.generate();
			cooling10 += SolutionChanger.Fitness(HillClimbingWithSA.climbing(cand, 10) ) / 10;
			cooling25 += SolutionChanger.Fitness(HillClimbingWithSA.climbing(cand, 25) ) / 10;
			cooling50 += SolutionChanger.Fitness(HillClimbingWithSA.climbing(cand, 50) ) / 10;
			cooling100 += SolutionChanger.Fitness(HillClimbingWithSA.climbing(cand, 100) ) / 10;
			cooling200 +=  SolutionChanger.Fitness(HillClimbingWithSA.climbing(cand, 200) ) / 10;
			cooling250 += SolutionChanger.Fitness(HillClimbingWithSA.climbing(cand, 250) ) / 10;
		}
		
		System.out.println("cooling10 : " + cooling10);
		System.out.println("cooling25 : " + cooling25);
		System.out.println("cooling50 :" + cooling50);
		System.out.println("cooling100 :" + cooling100);
		System.out.println("cooling200 :" + cooling200);
		System.out.println("cooling250 :" + cooling250);
	
	
		/*Genetic Algorithm Testing
			GeneticAlgorithm is tested by performing the GeneticAlgorithm.geneticAlgorithmGeneration() method with the same
			input file but with different populations, the most optimal population is then used in the application.
			
			Note: we test no greater than population 250 as a population any larger would not run in an acceptable time
			RESULTS:
			pop10 : 48.53333333333334
			pop50 :48.53333333333334
			pop100 :50.73333333333334
			pop250 :50.73333333333334
			From this we conclude 100 is the optimal population
		 */
		
		double pop10 = 0;
		double pop50 = 0;
		double pop100 = 0;
		double pop250 = 0;

		pop10 += SolutionChanger.Fitness(GeneticAlgorithm.GeneticAlgorithmGeneration(candGen, 10) );
		pop50 += SolutionChanger.Fitness(GeneticAlgorithm.GeneticAlgorithmGeneration(candGen, 50) );
		pop100 += SolutionChanger.Fitness(GeneticAlgorithm.GeneticAlgorithmGeneration(candGen, 100) );
		pop250 += SolutionChanger.Fitness(GeneticAlgorithm.GeneticAlgorithmGeneration(candGen, 250) );
		
		System.out.println("pop10 : " + pop10);
		System.out.println("pop50 :" + pop50);
		System.out.println("pop100 :" + pop100);
		System.out.println("pop250 :" + pop250);

	}
	
}

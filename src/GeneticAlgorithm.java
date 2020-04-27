import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class GeneticAlgorithm {
 

	static List<CandidateSolution> population = new LinkedList<CandidateSolution>();
	
	public static void GeneticAlgorithmGeneration(CandidateSolutionGenerator cand) {
	Random rand = new Random();
	int i ;
	double y =0;
	int x = 0;
	CandidateSolution solution = null;

	
	for(i = 0 ; i < 3; i++ ) {
	solution = cand.generate();
	y = SimulatedAnnealing.Fitness(solution);
	solution.setFitness(y);
	population.add(solution);
	

	x++;
	
	
	
	

	}
	

	 Collections.sort(population);
	 Collections.reverse(population);

	
	int h = 0;	
	while (h < 1000) {
		
	int size = population.size();
	double e = size * 10 / 100; 
	int r = (int) Math.round(x);
	if (r < 1) {r = 1; }
		
	int p = 0;
	
	while( p < r) {
	CandidateSolution father = population.get(rand.nextInt(r));
	CandidateSolution mother = population.get(rand.nextInt(r));
	CandidateSolution offspring = GAMate.mate(father, mother);
	
	Random ran = new Random();
	int o = ran.nextInt(100);
	
	if (o >= 95) 
	{
	SimulatedAnnealing.changeRandom(offspring);
	}
	
	population.add(offspring);
	double z = SimulatedAnnealing.Fitness(offspring);
	offspring.setFitness(z);
	z = 0;
	p++;
	father = null;
	mother = null;
	}
	

	 Collections.sort(population);
	 Collections.reverse(population);
	
    Culling.culling(population);
     h++;
 
	}
	
	 Collections.sort(population);
	 Collections.reverse(population);
	 
	 double  l = SimulatedAnnealing.Fitness(population.get(0));
	 System.out.println("\n" + "The optimum solution is" + "\n" + population.get(0));
	 System.out.println("\n" + "The fitness of this solution is" + "\n" + l);
	 
	
	
	
	
	}


	
	

}

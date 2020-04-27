import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class GeneticAlgorithm {
 

	static List<CandidateSolution> population = new LinkedList<CandidateSolution>();
	
	public static void GeneticAlgorithmGeneration(CandidateSolutionGenerator cand) {
	int i ;
	double y =0;
	int x = 0;
	int o;
	CandidateSolution solution = null;
	Double n;
	// int size= 0;
	
	for(i = 0 ; i < 3; i++ ) {
	solution = cand.generate();
	y = SimulatedAnnealing.Fitness(solution);
	solution.setFitness(y);
	population.add(solution);
	
	//System.out.println("\n" + population.get(x));
	x++;
	
	
	
	n = solution.getFitness();
	//size = population.size();
	//System.out.println(size + "\n");
	//System.out.println(n + "\n");
	}
	
	
//	System.out.println("\n Before Sorting");
	
	
	 Collections.sort(population);
	 Collections.reverse(population);
	//for(o = 0 ; o < population.size(); o++ ) 
	//{
	// System.out.println("\n" + population.get(o));
	//}
	
	int h = 0;	
	while (h < 1000) {
	CandidateSolution father = population.get(0);
	CandidateSolution mother = population.get(1);
	
	
	CandidateSolution offspring = GAMate.mate(father, mother);
	
	//System.out.println("\n The Offspring");
	
	//System.out.println("\n" + offspring);
	
	population.add(offspring);
	double z = SimulatedAnnealing.Fitness(offspring);
	offspring.setFitness(z);
	z = 0;
	
	//System.out.println("\n Population with offspring");
	
	//for(o = 0 ; o < population.size(); o++ ) 
	//{
	// System.out.println("\n" + population.get(o));
	//}
	
	 Collections.sort(population);
	 Collections.reverse(population);
	
	//System.out.println("\n Before culling");
    Culling.culling(population);
    //System.out.print(h);
     h++;
    father = null;
 	mother = null;
	}
	
	 Collections.sort(population);
	 Collections.reverse(population);
	 
	 System.out.println("\n" + "The optimum solution is" + "\n" + population.get(0));
	
	
	
	
	
	}


	
	

}

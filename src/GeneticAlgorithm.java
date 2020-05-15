import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class GeneticAlgorithm {
 
	static List<CandidateSolution> population = new LinkedList<CandidateSolution>();

	public static CandidateSolution GeneticAlgorithmGeneration(CandidateSolutionGenerator cand) {
		Random rand = new Random();
		CandidateSolution solution = null;
		int populationSize = 100;
		for(int i = 0 ; i < populationSize; i++ ) {
			solution = cand.generate();
			double solutionFitness = SolutionChanger.Fitness(solution);
			solution.setFitness(solutionFitness);
			population.add(solution);
		}
	

		Collections.sort(population);
		Collections.reverse(population);

	
	int h = 0;	
	while (h < 1000) {
	 
		double  m = SolutionChanger.Fitness(population.get(0));	;	
		
	int size = population.size();
	double e = size * 10 / 100; 
	int r = (int) Math.round(populationSize);
	if (r < 1) {r = 1; }
		
	int p = 0;
	
	while( p < r) {
		CandidateSolution father = null;
		CandidateSolution mother = null;
		
		Random ra = new Random();
		int t = ra.nextInt(100);
		
		if(t >= 98) {father = population.get(rand.nextInt(population.size()));}
		else {	
	father = population.get(rand.nextInt(r));
		     }
		
		
		t = ra.nextInt(100);
	if(t >= 98) {mother = population.get(rand.nextInt(r));}
		else{
	mother = population.get(rand.nextInt(r));
		}
	CandidateSolution offspring = mate(father, mother);
	
	Random ran = new Random();
	int o = ran.nextInt(100);
	
	if (o >= 95) 
	{
	GUI.ja.append(SolutionChanger.changeRandom(offspring));
	}
	
	population.add(offspring);
	double z = SolutionChanger.Fitness(offspring);
	offspring.setFitness(z);
	z = 0;
	p++;
	father = null;
	mother = null;
	}
	

	 Collections.sort(population);
	 Collections.reverse(population);
	
    culling(population);
     
    
    double  u = SolutionChanger.Fitness(population.get(0));	
    
    if(m == u) {
    h++;
     } 
    
    if(u > m) { h = 0;}
    
     }
	
	 Collections.sort(population);
	 Collections.reverse(population);
	 
	 double  l = SolutionChanger.Fitness(population.get(0));	
	 System.out.println("\n" + "The optimum solution is" + "\n" + population.get(0));
	 System.out.println("\n" + "The fitness of this solution is" + "\n" + l);
	 
	 return population.get(0);
	 
	
	
	
	}

	public static CandidateSolution mate(CandidateSolution father, CandidateSolution mother) {
		List<String> allProjects = new LinkedList<String>(); //projects that have been used for solutions (check to prevent duplicates)
		List<Student> students = father.students; //we can assume father & mother have same students in same order
		List<Integer> solutions = new LinkedList<Integer>();
		
		boolean useFather = true; //used to alternate between adding element from mother and father to offspring
		CandidateSolution solutionToAdd; //assigned to either mother or father depending on use parent
		
		Random rand = new Random();
		
		//since we can assume mother & father are same size and have same students in same order we only need iterate through father
		for (int i = 0; i < father.getSize(); i++) {
			
			if (useFather) solutionToAdd = father;
			else solutionToAdd = mother;
			
			List<String> projects = solutionToAdd.students.get(i).getProjects();
			List<String> usableProjects = new LinkedList<String>(); //projects for student i that have not yet been used 
			
			usableProjects.addAll(projects);
			
			//removes already used solutions from usableProjects
			for (int j=0; j<projects.size(); j++) {
				if (allProjects.contains(projects.get(j))) usableProjects.remove(projects.get(j)); 
			}
			
			//first checks if the solutionToAdds current rank is usable and if so will use that.
			if (usableProjects.contains(solutionToAdd.getSolutionAt(i))){
				allProjects.add(solutionToAdd.getSolutionAt(i));
				solutions.add(solutionToAdd.getSolutionRankAt(i));
			}
			
			//next it checks if there are any usable projects and if so will use a a random one of those
			else if (0 < usableProjects.size()) {
				String project = usableProjects.get(rand.nextInt(usableProjects.size()));			
				int projectRank = projects.indexOf(project); //assigns rank as index of project which was randomly selected from usable projects.
				allProjects.add(project);
				solutions.add(projectRank);
			}
			
			//otherwise will use a random rank (solution will have fitness of 0)
			else solutions.add(rand.nextInt(10));
			
			useFather = !useFather; //toggles use father so other parent is used in next iteration of loop
		}
		
		CandidateSolution offSpring = new CandidateSolution(students, solutions);
		
		return offSpring;
		
		
		
	}
	
	public static void culling(List<CandidateSolution> population) {
		int size = population.size();
		double x = size * 10 / 100; // I put the percentage as 10% at this case.
		int y = (int) Math.round(x); // change Round double value in to int 
		if (y < 1) {y = 1; }
		int a = size - y;
	    population.subList(a, size).clear();
		 
		//for(int o = 0 ; o < population.size(); o++ ) 
		//{
		// System.out.println("\n" + population.get(o));
		//}
		
		}

	

}


public class SimulatedAnnealing {
	
	//Calculate how change can be accepted (probability)
    public static double acceptance(int energy, int newEnergy, double fitness) {
        //If we get the random solution and when it is better than original one, accept it.
        if (newEnergy < energy) {
            return 1.0;
        }
        //If the random solution is worse than original solution, calculate an acceptance probability
        return Math.exp((energy - newEnergy) / fitness);
    }

    public static void main(String[] args) {
        //TODO: Call the GPA, Project, Student
       /*
        * 
        */

        //Set initial fitness
        double fitness = 10000;

        //Cooling rate
        double coolingRate = 0.003;

        //Initialize intial solution
        Project currentSolution = new Project();
        currentSolution.generateIndividual();
        
        System.out.println("Current solution for student getting projects : " + currentSolution.getConstraints());

        //Set as current project distribution
        project best = new project(currentSolution.getproject());
        
        //Loop until system has cooled
        while (fitness > 1) {
        	
            //Create new neighbour project
            project newSolution = new project(currentSolution.getproject());

            //Get a random positions in the project
            int projectPos1 = (int) (newSolution.projectSize() * Math.random());
            int projectPos2 = (int) (newSolution.projectSize() * Math.random());

            //Determine how students are distributed at selected positions in the project
            Student StudentSwap1 = newSolution.getStudent(projectPos1);
            Student StudentSwap2 = newSolution.getStudent(projectPos2);

            //Swap them
            newSolution.setStudent(projectPos2, StudentSwap1);
            newSolution.setStudent(projectPos1, StudentSwap2);
            
            //Get energy of solutions
            int currentEnergy = currentSolution.getConstraints();
            int neighbourEnergy = newSolution.getConstraints();

            //Decide if we should accept the new solution
            if (acceptance(currentEnergy, neighbourEnergy, fitness) > Math.random()) {
                currentSolution = new project(newSolution.getproject());
            }

            //Keep track of the best solution found
            if (currentSolution.getConstraints() < best.getConstraints()) {
                best = new project(currentSolution.getproject());
            }
            
            //Cool system
            fitness *= 1-coolingRate;
        }

        System.out.println("For constraints algorithm, better solution is: " + best.getConstraints());
        System.out.println("After run, The best solution for distribution of project is : " + best);
    }

}

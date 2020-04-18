import java.util.Scanner;
import java.*;
import java.util.*;


public class Main {
	static CandidateSolutionGenerator cand = new CandidateSolutionGenerator("src/PreferenceList60.csv");

		
		public static void main(String[] args) {
			
			
			ProjectListGenerator Proj = new ProjectListGenerator("src/Miskatonic Staff Members.csv");
			StudentGenerator Pref = new StudentGenerator("src/names.csv");
			
			Proj.generateProjectList("src/ProjectList60.csv", 30);
			Pref.generateStudentPrefList("src/PreferenceList60.csv", "src/ProjectList60.csv", 60);
			
			Proj.generateProjectList("src/ProjectList120.csv", 60);
			Pref.generateStudentPrefList("src/PreferenceList120.csv", "src/ProjectList120.csv", 120);
			
			Proj.generateProjectList("src/ProjectList240.csv", 120);
			Pref.generateStudentPrefList("src/PreferenceList240.csv", "src/ProjectList240.csv", 240);
			
			Proj.generateProjectList("src/ProjectList500.csv", 250);
			Pref.generateStudentPrefList("src/PreferenceList500.csv", "src/ProjectList500.csv", 500);

			
			CandidateSolution solution = null;
			 System.out.println("Initial candidate solution generated \n");
			 
			 solution = cand.generate();
			
			
		 menu(solution);
		
		}
		
		
		
		
		
		 public static void menu(CandidateSolution solution) 
		 {   
			 

			 
			 
			 Scanner choice = new Scanner(System.in);
			 
			 			 int x = 0;
			 System.out.println("Please choose an option from the menu \n"
					 +	"Enter 1 to generate a new candidate solution \n"
					 +   "Enter 2 to print out the candidate solution \n"
					 +   "Enter 3 to calculate the fitness and energy of that solution \n"
					 +    "Enter 4 to make a random change to the solution \n"
					 +    "Enter 5 to use hill climbing to improve the solution \n"
					 +    "Enter 0 exit \n"
					 );
		
	
		            try {
						x = choice.nextInt();
					} catch (Exception e) {
					 System.out.println("Incorrect input detected. Please enter the correct input");
						
					}
			 
			 
			 switch(x) 
			 {
			 
			 case 1: 
					
				   solution = cand.generate();
					menu(solution);
					break;
				
			 case 2:
					
					System.out.println("\n" + solution.toString());	
					menu(solution);
					break;
				
				
			case 3:
					
					System.out.println("\n" + "the Fitness of the solution is " + SimulatedAnnealing.Fitness(solution));
					System.out.println("\n" + "the Energy of the solution is " + SimulatedAnnealing.Energy(solution));
					menu(solution);
					break;
				
			case 4:

					
					System.out.println("\n" + "the Energy of the solution is " + SimulatedAnnealing.Energy(solution));
					SimulatedAnnealing.changeRandom(solution);
					menu(solution);
                    break;
                    
			case 5: 
			        HillClimbing.climbing(solution);
			        menu(solution);
				    break;
				
		 case 0:
				{
					
				System.exit(0);	
				}
				
				default: 
				System.out.println("Please choose a valid option from the menu \n");
				menu(solution);
				
				
				}
			 
			 
			 
			 
			 
			 
			 
				
			











	
	
		 
}}

  
 
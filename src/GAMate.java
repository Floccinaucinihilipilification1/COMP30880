import java.util.LinkedList;
import java.util.List;
import java.util.Random;
public class GAMate {
	
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
}


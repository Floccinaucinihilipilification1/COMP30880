public class Main {
	
	public static void main(String[] args) {		
		CandidateSolutionGenerator cand = new CandidateSolutionGenerator("src/PreferenceList60.csv");
		CandidateSolution solution = cand.generate();
		
		System.out.println(solution.toString());
	}
}

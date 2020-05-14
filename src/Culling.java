import java.util.List;

public class Culling {
	
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

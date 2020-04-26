import java.util.ArrayList;

Public static class Culling extends ArrayList<String> {
	public static void main(String[] args)
	
	//after the population list made
	
	System.out.println("Contents of the Array List: \n"+list); // testing before culling
	int size = list.size();
	double x = size * 10 / 100; // I put the percentage as 10% at this case.
	int y = (int) Math.round(x); // change Round double value in to int 
	int a = size - y;
	list.removeRange(a,size);
	/*
	 * list.subList(a, size).clear();
	 * Use this method if you used ArrayList<String> list = new ArrayList<String>(); instead of extends ArrayList<String>
	 */
	System.out.println("Contents of the Array List after culling: \n"+list); // testing after culling


	

}

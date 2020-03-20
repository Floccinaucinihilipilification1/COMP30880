import java.util.*;



//piece of code that has to be inserted at Student.java
//making it as separate class before putting codes in
// I assumed this code is working on 60 student first.
public class bellcurve {

	public static void main(String... aArgs){
	    bellcurve gaussian = new bellcurve();
	    double MEAN = 45.5f; 
	    double VARIANCE = 60.0f;
	    for (int idx = 1; idx <= 10; ++idx){
	      log("60 students involved at this project. They are distributed as : " + gaussian.getGaussian(MEAN, VARIANCE));
	    }
	  }

	  private Random fRandom = new Random();

	  private double getGaussian(double aMean, double aVariance){
	    return aMean + fRandom.nextGaussian() * aVariance;
	  }

	  private static void log(Object aMsg){
	    System.out.println(String.valueOf(aMsg));
	  }
}

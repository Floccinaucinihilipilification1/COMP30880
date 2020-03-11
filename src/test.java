import java.io.*;
import java.util.Arrays; 


public class test {

	public static void main(String[] args) throws Exception {
	//	FileReader fr = new FileReader("src/MiskatonicStaffMembers.csv");
	//	BufferedReader br = new BufferedReader(fr);
		
	//	int i;
	//((i=br.read())!=-1){  
	//        System.out.print((char)i);  
	 //       }  
	 //        br.close();    
	 //       fr.close(); 


	          
	         BufferedReader in = new BufferedReader(new FileReader("src/Miskatonic Staff Members.csv"));
	         String line;
	         while ((line = in.readLine()) != null) {
	              String[] fields = line.split(",");
	              System.out.println(Arrays.toString(fields)); 
	             
	          }
	         
	      
	          
	          
	          in.close();
	          
	          
	}

}
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class test3 {

	public static void main(String[] args) {
		 String file = "src/names.csv";
	        String file2 = "src/projectList.csv";
	        String delimiter = ",";
	        String line;
	        String line2;
	        List<List<String>> lines = new ArrayList<List<String>>();
	         
	        try (BufferedReader br =
	                      new BufferedReader(new FileReader(file))) {
	             while((line = br.readLine()) != null){
	                 List<String> values = Arrays.asList(line.split(delimiter));
	                 lines.add(values);
	             }
	             lines.forEach(l -> System.out.println(l));
	         } catch (Exception e){
	             System.out.println(e);
	         }
	     
	        try (BufferedReader br =
                    new BufferedReader(new FileReader(file2))) {
           while((line = br.readLine()) != null){
               List<String> values = Arrays.asList(line.split(delimiter));
               lines.add(values);
           }
           lines.forEach(l -> System.out.println(l));
       } catch (Exception e){
           System.out.println(e);
       }
	
	
	
	
	
	
	
	}

	
	
	
	
	
	}



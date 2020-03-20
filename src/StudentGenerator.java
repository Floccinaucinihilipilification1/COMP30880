import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class StudentGenerator {
    
	String inputFile;
	String inputFile2;


	
	StudentGenerator(String inputFile, String inputFile2 ) {
		this.inputFile = inputFile;
		this.inputFile = inputFile2;
		StudentPrefsGenerator(inputFile, inputFile2);
	}


public void StudentPrefsGenerator(String inputFile, String inputFile2) {

    	String[] field = {"CS", "DS"};
        String file = inputFile;
        String file1 = inputFile2;
        String delimiter = ",";
        String line;
        String line2;
        List<Student> Student = new LinkedList<Student>();
         try (
        		 BufferedReader br =  new BufferedReader(new FileReader(file));
        		 BufferedReader br1 =  new BufferedReader(new FileReader(file1))
        		 
        	 ) {
         	
             while((line = br.readLine()) != null && (line2 = br1.readLine()) != null ){

                 
             	
             	List<String> columns = Arrays.asList(line.split(delimiter, -1));           
             	List<String> columns1 = Arrays.asList(line2.split(delimiter, -1));                 
             	
             	
             	
                 String name = columns.get(0);
                 String StudentNumber = columns.get(1);
                 List<String> projectStream = toLinkedList(columns1.get(2));
                 List<String> projects = toLinkedList(columns1.get(1));
                 Random z = new Random(); 
                 int x = 0;
             	 x = z.nextInt(9);
                 
                 if (x <= 3) 
          		{
          			x = 1;
          		}
          		
          		else {
          			x = 0;
          			}
                 String Stream = field[x];
                  
                 Student student = new Student(name, StudentNumber, 
                		 projects, Stream, projectStream);
                 Student.add(student);
             }
         } catch (Exception e){
             System.out.println(e);
         }
         
         file = ("src/PreferenceList.csv");
         try (BufferedWriter bw =
                 new BufferedWriter(new FileWriter(file))) {
         	int listLength = 500; //number of lines in the List
             Random r = new Random();
             int s = 0;
             
             for (int i=0; i<listLength; i++ ) {
            	
             	int index = r.nextInt(Student.size());
             	Student student = Student.get(index);
             	if (!student.getProjects().isEmpty()) {
             		String name = student.getName();
             		String StudentNumber = student.getStudentNumber();
             		String stream = student.getStream();
             	    String project = student.randomProject(stream);
             			
             		
             		
             		
             		bw.write(name + "," + StudentNumber + "," + stream + "," + project  );
             		bw.newLine();
             	}
             	else i--; //decrements and tries again if projects is empty
             }
             System.out.println("PreferenceList.csv successfully created");
    	   
         } catch (Exception e){
         	System.out.println(e);
         }

     }
     
     //replaces any ',' enclosed in '"' with ';' then removes all '"' (Could possibly use regular expression instead?)

     
     //used to convert columns 2 and 3 into linked lists with delimiter ";"
     public static List<String> toLinkedList(String myString) {
 		String delimiter = ";";
 		List<String>  myList = new LinkedList<String>();
 		myList.addAll(Arrays.asList(myString.split(delimiter)));
 		
 		//removes null element from each list
 		for (int i=0; i < myList.size(); i++) {
 			if (myList.get(i).equals("")) myList.remove(i);
 		}
 		
 		return myList;
     }
      
 }
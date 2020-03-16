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

    public static void main(String[] args) {
    	String[] field = {"CS", "DS"};
        String file = "src/names.csv";
        String file1 = "src/projectList.csv";
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
                 List<String> projects = toLinkedList(columns1.get(1));
                 Random z = new Random(); 
                 int x = 0;
             	 x = z.nextInt(10);
                 
                 if (x <= 3) 
          		{
          			x = 1;
          		}
          		
          		else {
          			x = 0;
          			}
                 String Stream = field[x];
                  
                 Student student = new Student(name, StudentNumber, 
                		 projects, Stream);
                 Student.add(student);
             }
         } catch (Exception e){
             System.out.println(e);
         }
         
         file = ("src/PreferenceList.csv");
         try (BufferedWriter bw =
                 new BufferedWriter(new FileWriter(file))) {
         	int listLength = 500; //number of lines in projectList
             Random r = new Random();
          
             for (int i=0; i<listLength; i++ ) {
            	
             	int index = r.nextInt(Student.size());
             	Student student = Student.get(index);
             	if (!student.getProjects().isEmpty()) {
             		String name = student.getName();
             		String StudentNumber = student.getStudentNumber();
             		String project = student.randomProject();
             		String stream = student.getStream();
             		
             		
             		bw.write(name + "," + StudentNumber + "," + stream + "," + project  );
             		bw.newLine();
             	}
             	else i--; //decrements and tries again if researchActivity is empty
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
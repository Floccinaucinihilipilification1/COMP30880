import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Collections;
public class StudentGenerator {
    
	;
	List<Project> csProjects;
	List<Project> dsProjects;
	List<Name> names;
	String nameFile;
	
	StudentGenerator(String nameFile) {
		this.nameFile = nameFile;
	}


	public void generateStudentPrefList(String outputFile, String projectFile, int studentNum ) {
		generateNames();
		generateCSProjects(projectFile);
		generateDSProjects(projectFile);
		Collections.shuffle(csProjects); //we shuffle the two list so our normal distribution is random for each preference list
		Collections.shuffle(dsProjects);
		List<Project> workingProjectList;

        List<Student> students = new LinkedList<Student>();
        String stream;
        Random r = new Random();
        
        for (int i=0; i<studentNum; i++) {
        	if (r.nextInt(9) <= 3) {
        		stream = "DS"; 
        		workingProjectList = dsProjects.subList(0, dsProjects.size());
        	}
            else {
            	stream = "CS"; 
        		workingProjectList = csProjects.subList(0, csProjects.size());
            }
        	List<String> studentProjects = selectProjects(workingProjectList);
        	int nameIndex = r.nextInt(names.size());
        	String name = names.get(nameIndex).getName();
        	String ID = names.get(nameIndex).getID();
        	students.add(new Student(name, ID, studentProjects, stream));
        }
              
        try (BufferedWriter bw =
                new BufferedWriter(new FileWriter(outputFile))) {
		 for (int i=0; i<students.size(); i++) {
			 bw.write(students.get(i).toString());
			 bw.newLine();
		 }
        } catch (IOException e) {
		e.printStackTrace();
        }
        System.out.println(outputFile + " successfully created");
	}
	

     void generateNames() {
    	 names = new LinkedList<Name>();
    	 try(BufferedReader br = new BufferedReader(new FileReader(nameFile))) {
    		 String line;
			while ((line = br.readLine()) != null) {
    			 List<String>columns = Arrays.asList(line.split(",", -1));
    			 names.add(new Name(columns.get(0), columns.get(1)));
    		 }
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
     }
     
     void generateCSProjects(String projectFile) {
    	 csProjects = new LinkedList<Project>();
    	 try(BufferedReader br = new BufferedReader(new FileReader(projectFile))) {
    		 String line;
			while ((line = br.readLine()) != null) {
    			 List<String>columns = Arrays.asList(line.split(",", -1));
    			 if (!columns.get(2).equals("DS")) csProjects.add(new Project(columns.get(0), columns.get(1), columns.get(2)));
    		 }
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
     }
     
     void generateDSProjects(String projectFile) {
    	 dsProjects = new LinkedList<Project>();
    	 try(BufferedReader br = new BufferedReader(new FileReader(projectFile))) {
    		 String line;
			while ((line = br.readLine()) != null) {
    			 List<String>columns = Arrays.asList(line.split(",", -1));
    			 if (!columns.get(2).equals("CS")) dsProjects.add(new Project(columns.get(0), columns.get(1), columns.get(2)));
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
     }
     
     List<String> selectProjects(List<Project> projects) {
    	 Random r = new Random();
    	 List<String> myProjects = new LinkedList<String>();
    	 int deviations = projects.size()/3;
    	 for (int i=0; i<10; i++) {
    		 int deviation = r.nextInt(99);
    		 if  (deviation < 68) {
    			 int index = r.nextInt(deviations);
    			 if(!myProjects.contains(projects.get(index).getProject())) myProjects.add(projects.get(index).getProject());
    			 else i--;
    		 }
    		 if  (67 < deviation && deviation < 95) {
    			 int index = r.nextInt(deviations)+deviations;
    			 if(!myProjects.contains(projects.get(index).getProject())) myProjects.add(projects.get(index).getProject());
    			 else i--;
    		 } 
    		 if  (94 < deviation) {
    			 int index = r.nextInt(deviations)+deviations*2;
    			 if(!myProjects.contains(projects.get(index).getProject())) myProjects.add(projects.get(index).getProject());
    			 else i--;
    		 } 
    	 }
    	 return myProjects; 
     }
      
 }
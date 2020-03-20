
import java.util.List;
import java.util.Random;

public class Student {
	
	String name;
	String StudentNumber;
	List<String> projects;
	String Stream;
	List<String> projectStream;
	StudentGenerator list = new StudentGenerator();
	//constructor
	Student(String name,String StudentNumber, List<String> projects,  String Stream, List<String> projectStream) {
		this.name = name;
		this.StudentNumber = StudentNumber;
		this.projects = projects;
		this.Stream = Stream;
		this.projectStream = projectStream;
	}
	
	//Gets a random entry from projects list and it's stream and returns it
	public String randomProject(String stream2) {
		int n = 0;
		String project = "";
		String projectstream;
		String studStream = stream2;
		Random r = new Random();
		
		while (n < 10) {
		int i = 0;	
		i = r.nextInt(projects.size());
		 projectstream = projectStream.get(i);
		if(studStream != projectstream || projectstream != "CS + DS" ) {
			project += projects.get(i);
		    project += ",";
		}
		else {
		 
		    
		}
		n++;
		}
		return project;
	}

	//getters
	public String getName() { return name; }
	public String getStudentNumber() {return StudentNumber;}
	public List<String> getProjects() { return projects; }
	public String getStream() { return Stream; }
	public List<String> getprojectStream() { return projectStream; }
	
	//setters
	public void setName(String name) { this.name = name; }
	public void setStudentNumber(String StudentNumber) {this.StudentNumber = StudentNumber;}
	public void setProjects(List<String> projects) { this.projects = projects; }
	public void setStream(String Stream) { this.Stream = Stream; }
	public void setprojectStream(String Stream) { this.projectStream = projectStream; }
	
	//to string method
	public String toString() {
		return name + ", " + StudentNumber + ", " + Stream + ", " + projects.toString() + ", "
				  ;
		
	}
	
	
	
}

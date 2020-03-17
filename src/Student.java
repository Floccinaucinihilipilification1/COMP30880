import java.util.List;
import java.util.Random;

public class Student {
	
	String name;
	String StudentNumber;
	List<String> projects;
	String Stream;
	StudentGenerator list = new StudentGenerator();
	//constructor
	Student(String name,String StudentNumber, List<String> projects,  String Stream) {
		this.name = name;
		this.StudentNumber = StudentNumber;
		this.projects = projects;
		this.Stream = Stream;
		
	}
	
	//Gets a random entry from projects list and returns it
	public String randomProject() {
		int n = 0;
		String project = "";
		while (n < 10) {
		int i = 0;	
		Random r = new Random();
		i = r.nextInt(projects.size());
		project += projects.get(i);
		project += ",";
		n++;
		
		}
		return project;
	}

	//getters
	public String getName() { return name; }
	public String getStudentNumber() {return StudentNumber;}
	public List<String> getProjects() { return projects; }
	public String getStream() { return Stream; }
	
	//setters
	public void setName(String name) { this.name = name; }
	public void setStudentNumber(String StudentNumber) {this.StudentNumber = StudentNumber;}
	public void setProjects(List<String> projects) { this.projects = projects; }
	public void setStream(String Stream) { this.Stream = Stream; }
	
	//to string method
	public String toString() {
		return name + ", " + StudentNumber + ", " + Stream + ", " + projects.toString() + ", "
				  ;
		
	}
	
	
	
}

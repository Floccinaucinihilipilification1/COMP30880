
import java.util.List;

public class Student {
	
	String name;
	String StudentNumber;
	List<Project> projects;
	String Stream;

	//constructor
	Student(String name,String StudentNumber, List<Project> projects,  String Stream) {
		this.name = name;
		this.StudentNumber = StudentNumber;
		this.projects = projects;
		this.Stream = Stream;
	}
	
	//Gets a random entry from projects list and it's stream and returns it


	//getters
	public String getName() { return name; }
	public String getStudentNumber() {return StudentNumber;}
	public List<Project> getProjects() { return projects; }
	public String getStream() { return Stream; }
	
	//setters
	public void setName(String name) { this.name = name; }
	public void setStudentNumber(String StudentNumber) {this.StudentNumber = StudentNumber;}
	public void setProjects(List<Project> projects) { this.projects = projects; }
	public void setStream(String Stream) { this.Stream = Stream; }
	
	private String projectsString() {
		String myString = "";
		for (int i=0; i<projects.size(); i++) {
			myString = myString + "," + projects.get(i).getProject();
		}
		return myString.substring(1);
	}
	
	//to string method
	public String toString() {
		return name + ", " + StudentNumber + ", " + Stream + ", " + projectsString();
		
	}
	
	
	
}

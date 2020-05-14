
public class Project {
	String supervisor;
	String project;
	String audience;
	Project(String supervisor, String project,String audience) {
		this.supervisor = supervisor;
		this.project = project;
		this.audience = audience;
	}
	
	public String getSupervisor() {return supervisor;}
	public String getProject() {return project;}
	public String getAudience() {return audience;}
	
	public String toString() { return supervisor + "," + project + "," + audience;}
}

public class Main {
	
	public static void main(String[] args) {
		ProjectListGenerator Proj = new ProjectListGenerator("src/Miskatonic Staff Members.txt");
		StudentGenerator Pref = new StudentGenerator("src/names.csv");
		
		Proj.generateProjectList("src/ProjectList60.csv", 30);
		Pref.generateStudentPrefList("src/PreferenceList60.csv", "src/ProjectList60.csv", 60);
		
		Proj.generateProjectList("src/ProjectList120.csv", 60);
		Pref.generateStudentPrefList("src/PreferenceList120.csv", "src/ProjectList120.csv", 120);
		
		Proj.generateProjectList("src/ProjectList240.csv", 120);
		Pref.generateStudentPrefList("src/PreferenceList240.csv", "src/ProjectList240.csv", 240);
		
		Proj.generateProjectList("src/ProjectList500.csv", 250);
		Pref.generateStudentPrefList("src/PreferenceList500.csv", "src/ProjectList500.csv", 500);
	}
}

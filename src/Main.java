public class Main {
	
	public static void main(String[] args) {
		ProjectListGenerator generatePL = new ProjectListGenerator("src/Miskatonic Staff Members.csv");
		generatePL.generateProjectList("src/projectList.csv", 250);
		StudentGenerator generatePrefList = new StudentGenerator("src/names.csv","src/projectList.csv");
		generatePrefList.StudentPrefsGenerator("src/names.csv","src/projectList.csv");
		
	}
}

public class Main {
	
	public static void main(String[] args) {
		ProjectListGenerator generatePL = new ProjectListGenerator("src/Miskatonic Staff Members.csv");
		ProjectListGenerator generatePrefList = new ProjectListGenerator("src/src/names.csv","src/projectList.csv" );
		generatePL.generateProjectList("src/projectList60.csv", 30);
		generatePL.generateProjectList("src/projectList120.csv", 60);
		generatePL.generateProjectList("src/projectList240.csv", 120);
		generatePL.generateProjectList("src/projectList500.csv", 250);
		
	}
}

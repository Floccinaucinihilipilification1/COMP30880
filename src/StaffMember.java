import java.util.List;
import java.util.Random;
public class StaffMember {
	String name;
	List<String> researchActivity;
	List<String> researchArea;
	String specialFocus;
	//constructor
	StaffMember(String name, List<String> researchActivity, 
			List<String> researchArea, String specialFocus) {
		this.name = name;
		this.researchActivity = researchActivity;
		this.researchArea = researchArea;
		this.specialFocus = specialFocus;
		
	}
	
	//removes random entry from research activity list and returns it
	public String randomActivity() {
		Random r = new Random();
		int i = r.nextInt(researchActivity.size());
		String activity = researchActivity.get(i);
		researchActivity.remove(i);
		return activity;
	}
	
	
	//getters
	public String getName() { return name; }
	public List<String> getReseachActivity() { return researchActivity; }
	public List<String> getReseachArea() { return researchActivity; }
	public String getSpecialFocus() { return specialFocus; }
	
	//setters
	public void setName(String name) { this.name = name; }
	public void setReseachActivity(List<String> researchActivity) { this.researchActivity = researchActivity; }
	public void setReseachArea(List<String> researchArea) {  this.researchArea = researchArea; }
	public void setSpecialFocus(String specialFocus) { this.specialFocus = specialFocus; }
	
	//to string method
	public String toString() {
		return name + ", " + researchActivity.toString() + ", "
				+ researchArea.toString() + ", " + specialFocus;
		
	}
}

import java.util.List;

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
	
	public String toString() {
		return name + ", " + researchActivity.toString() + ", "
				+ researchArea.toString() + ", " + specialFocus;
		
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
}

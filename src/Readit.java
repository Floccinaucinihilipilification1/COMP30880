import java.io.*;
import java.util.Arrays; 
import java.util.Scanner;

public class Readit {
	
	
	//prints the file list from the path so user can see the list of files
	
	public static void filelist(String a[]) {
		File file = new File("/path");
		String[] fileList =file.list();
		for(String name:fileList) {
			System.out.println("This is the file list for your folder" + name);
		}
	}
	
	public static void main(String[] args) throws Exception {  
				//scan the filename 
				Scanner sc = new Scanner(System.in);
				System.out.print("Enter the CSV/TSV file name you want to read. :");
				char input = sc.next().charAt(0);
				//now have to read the file name if it is CSV/TSV
		
				
				//if statement for csv
		         BufferedReader inCSV = new BufferedReader(new FileReader("src/Filename.csv"));
		         String lineCSV;
		         while ((lineCSV = inCSV.readLine()) != null) {
		              String[] fields = lineCSV.split(",");
		           System.out.println(Arrays.toString(fields));
		           
		          }
		         
		         //else statement for tsv
		         BufferedReader inTSV = new BufferedReader(new FileReader("src/Filename.tsv"));
		         String lineTSV;
		         while ((lineTSV = inTSV.readLine()) != null) {
		              String[] fields = lineTSV.split("\t");
		           System.out.println(Arrays.toString(fields));
		           
		          }
   		          
		          inCSV.close();
		          inTSV.close();
		          
		}

}

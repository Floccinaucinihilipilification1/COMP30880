import javax.swing.*;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class GUI extends JFrame{
	static String y = null;
	static CandidateSolutionGenerator cand;
	static CandidateSolution solution = null;
	static int x = 0;
	//set variables for Menubar
	private ImageIcon iconOpen;
	private ImageIcon iconSave;
	private ImageIcon iconExit;
	private JMenu fileMenu;
	private JMenuItem openMenu;
	private JMenuItem saveMenu;
	private JMenuItem exitMenu;
	static JPanel jp = new JPanel(new BorderLayout(20,20));
	static JTextField jt = new JTextField("Enter your choice here", 60);
	static JTextArea ja = new JTextArea(10, 10);
	static File file;
	static String input;

	
	public GUI() {
 
        initUI();
    }
	 private void initUI() {
		 
	        MenuBar();
	        setTitle("Call of Cthulu Project Management System");
	        setSize(800, 800);
	        setLocationRelativeTo(null);
	        setDefaultCloseOperation(EXIT_ON_CLOSE);
	        text(solution);
	        menuDisplay();
	        add(jp);
	    }

	    private static void text(CandidateSolution solution) {
	    jp.add(jt, BorderLayout.SOUTH);
	    
	    jt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) 
			{
			     input = null;
				input = jt.getText();
				//ja.append(input + "\n");
		        menuChoice();
			}

	    	
	    });
	    
	    
	    	
		
	}
		private void MenuBar() {
	    	

	        JMenuBar menuBar = new JMenuBar();
	        iconOpen = new ImageIcon("src/open.png");
	     //   iconSave = new ImageIcon("src/save.png");
	        iconExit = new ImageIcon("src/exit.png");
	        JFileChooser fileChooser = new JFileChooser(); //This method will search all type of files, not only CSV/TSV
	        jp.add(ja, BorderLayout.NORTH); //This adds text area to GUI
	        JScrollPane scrollPane1 = new JScrollPane(ja);
	        scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	        ja.setEditable(false);
	        jp.add(scrollPane1);
	        
	        
	        fileMenu = new JMenu("File");
	        fileMenu.setMnemonic(KeyEvent.VK_F);

	        openMenu = new JMenuItem(new MenuItemAction("Open file", iconOpen,
	                KeyEvent.VK_O));
	        openMenu.addActionListener(ev -> {
	        	int value = fileChooser.showOpenDialog(openMenu);
	        	if (value == JFileChooser.APPROVE_OPTION) {
	        		file = fileChooser.getSelectedFile();
	        		GUI.y = file.getAbsolutePath();
	        		String n = "csv";
	        		String m = "tsv";
	        		String fileType = y.substring(y.length() - 3);
	        		if(fileType.equals(n) || fileType.equals(m) ) {
	        		    
	        				        			
	        		
	        		try {
	        			int lines = 0;
	        			BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
	        			String line = input.readLine();
	        			while (line != null)
	        			{
	        				lines ++;
	        				ja.setText(ja.getText() + "\n" + line);
		        		    line = input.readLine();
	        			}
	        			ja.append("\n" + "The following preference list has been loaded" + file.getAbsolutePath() + "\n");
	        			ja.append("\n" + "The number of lines in the preference file loaded is " + lines + "\n");
	        			GUI.cand = new CandidateSolutionGenerator(y);
	        			input.close();
	        			menuDisplay();
	        		}
	        		catch (Exception e) {
	        			e.printStackTrace();
	        		}}
	        	
	        		else {
		        		ja.append("Opening failed" + "\n");
        		}
	        		 if(!fileType.equals(n) && !fileType.equals(m) ) {ja.append("\n" + "The file loaded was not a tsv or a csv file. Please load a tsv or csv file "+ "\n");
	        		 menuDisplay();
	        		 }
	        }}
	       
	
	        
	        );

	      //  saveMenu = new JMenuItem(new MenuItemAction("Save Result", iconSave,
	      //          KeyEvent.VK_S));
	     //  saveMenu.addActionListener((event) -> {
	    //	   CandidateSolution mySolution;
	    //	   mySolution.saveSolution(GUI.file);});

	        exitMenu = new JMenuItem("Exit Program", iconExit);
	        exitMenu.setMnemonic(KeyEvent.VK_C);
	        exitMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));
	        exitMenu.addActionListener((event) -> System.exit(0));

	        fileMenu.add(openMenu);
	  //      fileMenu.add(saveMenu);
	        fileMenu.addSeparator();
	        fileMenu.add(exitMenu);

	        menuBar.add(fileMenu);

	        setJMenuBar(menuBar);
	    }

	    private class MenuItemAction extends AbstractAction {
	        public MenuItemAction(String text, ImageIcon icon, Integer keyinput) {
	            super(text);
	            putValue(SMALL_ICON, icon);
	            putValue(MNEMONIC_KEY, keyinput);
	        }

	        @Override
	        public void actionPerformed(ActionEvent e) {
	            System.out.println(e.getActionCommand());
	        }
	    }

	    public static void menuChoice() 
		 {   
	    	
	    	x = 0;
			 
		
			 
			 try {
					x = Integer.parseInt(input);
				} catch (Exception e) {
					ja.append("\n Please enter a valid input \n");
				}
		 
		 
		 switch(x) 
		 {
		 
		 case 1: 
			 if(GUI.y == null) {ja.append("\n No preference list loaded. Please load a Preference list \n"); break;}
			 else { 
			 GUI.solution = cand.generate();
			   ja.append("\n Solution Generated \n");
			   ja.append("\n The size of the solution is" + GUI.solution.getSize() + "\n");
			   menuDisplay();
               
			   break; }
			
		 case 2:
			 if(GUI.solution == null) {ja.append("\n No solution generated. Please generate a solution first \n"); break;}
			 else {
				 ja.append("\n" + GUI.solution.toString());	
			 menuDisplay();
				break; }
			
			
		case 3:
			if(GUI.solution == null) {ja.append("\n No solution generated. Please generate a solution first \n"); break;}
			 else {
			ja.append("\n" + "the Fitness of the solution is " + SolutionChanger.Fitness(GUI.solution));
			ja.append("\n" + "the Energy of the solution is " + SolutionChanger.Energy(GUI.solution));
			
			menuDisplay();
				break; }
			
		case 4:
			if(GUI.solution == null) {ja.append("\n No solution generated. Please generate a solution first \n"); break;}
			 else {
			SolutionChanger.changeRandom(GUI.solution);
			ja.append("\n" + "the Energy of the solution is " + SolutionChanger.Energy(GUI.solution));

			menuDisplay();
             break; }
          
		case 5:
			if(GUI.solution == null) {ja.append("\n No solution generated. Please generate a solution first \n"); break;}
			 else {
			HillClimbingWithSA.climbing(GUI.solution);
			ja.append("\n" + "the Energy of the solution is " + SolutionChanger.Energy(GUI.solution));
			menuDisplay();
             break; }
			
			
		case 6: 
			if(GUI.solution == null) {ja.append("\n No solution generated. Please generate a solution first \n"); break;}
			 else {
				 GUI.solution = HillClimbingWithSA.climbing(GUI.solution);
				 GUI.solution.saveSolution("C:\\Users\\Public\\OptimumSolution.csv");
	        ja.append("\n" + "the Energy of the solution is " + SolutionChanger.Energy(GUI.solution));
			menuDisplay(); 
			    break; }
			
		case 7:
			if(GUI.solution == null) {ja.append("\n No solution generated. Please generate a solution first \n"); break;}
			 else {
				 GUI.solution = GeneticAlgorithm.GeneticAlgorithmGeneration(GUI.cand);
				 GUI.solution.saveSolution("C:\\Users\\Public\\OptimumSolution.csv");
			menuDisplay();
			    break; }
	    
		case 8:
			if(GUI.file == null) {ja.append("\n" + "No files have been loaded"  + "\n"); break;}
			 else {
			ja.append("\n" + "The following preference list has been loaded" + GUI.file.getAbsolutePath() + "\n");	 
			menuDisplay();
			    break; }
			
		case 9:
			if(GUI.file == null) {ja.append("\n" + "No files have been loaded"  + "\n"); break;}
			 else {
				 GUI.solution.saveSolution("C:\\Users\\Public\\OptimumSolution.csv"); 
				 ja.append("\n" + "File Saved"  + "\n");
				 ja.append("\n" + "The file has been saved to the following location C:\\Users\\Public\\OptimumSolution.csv "  + "\n");
			menuDisplay();
			    break; }
			
			
			default: 
				ja.append("\n Please choose a valid option from the menu \n");
				menuDisplay();
               
			
			}
			 
			 
			 
			 

		 }
	    
	    public static void menuDisplay() 
	    {
	    	ja.append("\nPlease select a preference list and then choose an option from the menu \n"
					 +	"Enter 1 to generate a new candidate solution \n"
					 +   "Enter 2 to print out the candidate solution \n"
					 +   "Enter 3 to calculate the fitness and energy of that solution \n"
					 +    "Enter 4 to make a random change to the solution \n"
					 +    "Enter 5 to use hill climbing to improve the solution \n"
					 +    "Enter 6 to use Simulated Annealing to improve the solution \n"
					 +    "Enter 7 to use Genetic Algorithms to improve the solution \n"
					 +    "Enter 8 To see which files you currently have loaded \n"
					 +    "Enter 9 To save your file \n"
					 );
	    
	    }
	    
	    
	    
	    }
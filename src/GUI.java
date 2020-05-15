import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.awt.event.ItemEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Random;



public class GUI extends JFrame{
	static String y = null;
	static CandidateSolutionGenerator cand;
	static CandidateSolution solution = null;
	static int x = 0;
	//set variables for Menubar
	private ImageIcon iconOpen;
	private ImageIcon iconExit;
	private JMenu fileMenu;
	private JMenuItem openMenu;
	private JMenuItem exitMenu;
	private JLabel statusBar;
	private JButton reg;
	private JButton reg2;
	private JButton reg3;
	private JButton reg4;
	private JButton reg5;
	private JButton reg6;
	private JButton reg7;
	private JButton reg8;
	private JButton reg9;
	static JPanel jp = new JPanel(new BorderLayout(20,20));
	static JPanel jp2 = new JPanel(new BorderLayout(20,20));
	static JTextArea ja = new JTextArea(10, 10);
	static File file;
	static String input;
	

	//TODO: put some frame color(Menubar, buttons).

	
	public GUI() {
 
        initUI();
    }
	 private void initUI() {
		 
	        MenuBar();
	        setTitle("Call of Cthulu Project Management System");
	        setSize(1700, 1000);
	        setLocationRelativeTo(null);
	        setDefaultCloseOperation(EXIT_ON_CLOSE);
	        menuDisplay();
	        buttons();
	        add(jp);
	        statusBar = new JLabel("Choose a button");
	        statusBar.setBorder(BorderFactory.createEtchedBorder());
	        add(statusBar, BorderLayout.SOUTH);
	    }


	    
	    
	    	
		
	    
	    private void buttons() 
	    {	
	     reg = new JButton("Generate Solution");
	     reg.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				 if(GUI.y == null) {ja.append("\n No preference list loaded. Please load a Preference list \n"); }
				 else { 
				 GUI.solution = cand.generate();
				   ja.append("\n You choosed Generate Solution. Solution has been generated. \n");
				   ja.append("The size of the solution is" + GUI.solution.getSize() + "\n");
				
			}}});
	     
	     reg2 = new JButton("Print Candidate solution");
	     reg2.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if(GUI.solution == null) {ja.append("\n No solution generated. Please generate a solution first \n"); }
					 else {
						 ja.append("\n" + GUI.solution.toString());
						 ja.append("\n You choosed print Solution. Solution printed. \n");
					
				}}});
	     
	     reg3 = new JButton("Calculate Fitness and Energy");
	     reg3.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if(GUI.solution == null) {ja.append("\n No solution generated. Please generate a solution first \n"); }
					 else {
					ja.append("\n You Choosed calculate Fitness and Energy.\n");
					ja.append("The Fitness of the solution is " + SolutionChanger.Fitness(GUI.solution));
					ja.append("\n" + "The Energy of the solution is " + SolutionChanger.Energy(GUI.solution)+ "\n");
					
				}}});
	     
	     reg4 = new JButton("Make a Random Change");
	     reg4.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if(GUI.solution == null) {ja.append("\n No solution generated. Please generate a solution first \n");}
					 else {
					SolutionChanger.changeRandom(GUI.solution);
					ja.append("\n You choosed make a Random Change. Random change applied to solution. \n");
					ja.append("The Energy of the solution is: " + SolutionChanger.Energy(GUI.solution)+ "\n");

					
				}}});
	     
	     reg5 = new JButton("Use Hill Climbing to improve the solution");
	     reg5.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if(GUI.solution == null) {ja.append("\n No solution generated. Please generate a solution first \n"); }
					 else {
					HillClimbingWithSA.climbing(GUI.solution);
					GUI.solution.saveSolution("C:\\Users\\Public\\OptimumSolutionHillClimbing.csv");
					ja.append("\n You choosed Hill Climbing. Hill Climbing implemented to solution. \n");
					ja.append("Hill Climbing solution is saved at C:\\Users\\Public\\OptimumSolutionHillClimbing.csv \n");
					ja.append("The Energy of the solution is " + SolutionChanger.Energy(GUI.solution)+ "\n");
					
				}}});
	     
	     reg6 = new JButton("Use Simulated Annealing to improve the solution");
	     reg6.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if(GUI.solution == null) {ja.append("\n No solution generated. Please generate a solution first \n"); }
					 else {
						 GUI.solution = HillClimbingWithSA.climbing(GUI.solution);
						 GUI.solution.saveSolution("C:\\Users\\Public\\OptimumSolutionSA.csv");
						 ja.append("\n You choosed Simulated Annealing. Simulated Annealing implemented to solution. \n");
						 ja.append("SA solution is saved at C:\\Users\\Public\\OptimumSolutionSA.csv"+ "\n");
						 ja.append("The Energy of the solution is " + SolutionChanger.Energy(GUI.solution)+ "\n");
					
				}}});
	     
	     reg7 = new JButton("Use Genetic Algorithms to improve the solution");
	     reg7.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if(GUI.solution == null) {ja.append("\n No solution generated. Please generate a solution first \n");}
					 else {
						 GUI.solution = GeneticAlgorithm.GeneticAlgorithmGeneration(GUI.cand);
						 GUI.solution.saveSolution("C:\\Users\\Public\\OptimumSolutionGA.csv");
						 ja.append("\n You choose Genetic Algorithms. Genetic Algorithms implemented to solution. \n");
						 ja.append("GA solution is saved at C:\\Users\\Public\\OptimumSolutionGA.csv"+ "\n");
						 ja.append("The fitness of the solution is " + SolutionChanger.Fitness(GUI.solution)+ "\n");
					
				}}});
	     
	     reg8 = new JButton("View current loaded file");
	     reg8.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if(GUI.file == null) {ja.append("\n" + "No files have been loaded"  + "\n"); }
					 else {
					ja.append("\n" + "The following preference list has been loaded" + GUI.file.getAbsolutePath() + "\n");	 
					
				}}});
	     
	     reg9 = new JButton("Save File");
	     reg9.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if(GUI.file == null) {ja.append("\n" + "No files have been loaded"  + "\n");}
					 else {
						 GUI.solution.saveSolution("C:\\Users\\Public\\OptimumSolution.csv"); 
						 ja.append("\n" + "File Saved"  + "\n");
						 ja.append("\n" + "The file has been saved to the following location C:\\Users\\Public\\OptimumSolution.csv "  + "\n");
					
				}}});
	    	
	     jp2.setLayout(new GridLayout(1,9));
	     jp2.setPreferredSize(new Dimension(200, 100));
	     jp2.setBackground(Color.black);
	     
	     
	   	
	        jp2.add(reg);
	        jp2.add(reg2);
	        jp2.add(reg3);
	        jp2.add(reg4);
	        jp2.add(reg5);
	        jp2.add(reg6);
	        jp2.add(reg7);
	        jp2.add(reg8);
	        jp2.add(reg9);
	    	
	    	
	    }
	    
	    
		private void MenuBar() {
	    	

	        JMenuBar menuBar = new JMenuBar();
	        iconOpen = new ImageIcon("src/open.png");
	        iconExit = new ImageIcon("src/exit.png");
	        JFileChooser fileChooser = new JFileChooser(); //This method will search all type of files, not only CSV/TSV
	        jp.add(ja, BorderLayout.WEST); //This adds text area to GUI
	        jp.add(jp2,BorderLayout.NORTH);
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
	        		String fileType = y.substring(y.length() - 3);
	        		if(fileType.equals(n)) {
	        		    
	        				        			
	        		
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
	        			ja.append("\n\n\n" + "The following preference list has been loaded: " + file.getAbsolutePath() + "\n");
	        			ja.append("The number of lines in the preference file loaded is " + lines + "\n");
	        			GUI.cand = new CandidateSolutionGenerator(y);
	        			input.close();
	        		}
	        		catch (Exception e) {
	        			e.printStackTrace();
	        		}}
	        	
	        		else {
		        		ja.append("Opening failed" + "\n");
        		}
	        		 if(!fileType.equals(n)) {ja.append("\n" + "The file loaded was not a csv file. Please load a csv file "+ "\n");
	        		 }
	        }}
	       
	
	        
	        );
	        

	        setJMenuBar(menuBar);//this will put show statusbar option at menu
	        JCheckBoxMenuItem showStatusBarMenuItem = new JCheckBoxMenuItem("StatusBar");
	        showStatusBarMenuItem.setMnemonic(KeyEvent.VK_S);
	        showStatusBarMenuItem.setDisplayedMnemonicIndex(5);
	        showStatusBarMenuItem.setSelected(true);

	        showStatusBarMenuItem.addItemListener((e) -> {
	            if (e.getStateChange() == ItemEvent.SELECTED) {
	                statusBar.setVisible(true);
	            } else {
	                statusBar.setVisible(false);
	            }
	        });

	        exitMenu = new JMenuItem("Exit Program", iconExit);
	        exitMenu.setMnemonic(KeyEvent.VK_C);
	        exitMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));
	        exitMenu.addActionListener((event) -> System.exit(0));

	        fileMenu.add(openMenu);
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

	    
			 
			 
			 
			 

	    
	    public static void menuDisplay() 
	    {

	    	ja.append("Please load a preference list and then choose a button from the menu above \n"
	    			+ "The preference list loaded should be in the csv format \n" );

	    
	    }
	    
	    
	    
	    }
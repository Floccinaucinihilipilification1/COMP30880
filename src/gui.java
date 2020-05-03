import javax.swing.*;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class gui extends JFrame{
	//set variables for Menubar
	private ImageIcon iconOpen;
	private ImageIcon iconSave;
	private ImageIcon iconExit;
	private JMenu fileMenu;
	private JMenuItem openMenu;
	private JMenuItem saveMenu;
	private JMenuItem exitMenu;
	
	public gui() {

        initUI();
    }
	 private void initUI() {

	        MenuBar();
	        setTitle("Call of Cthulu Project Management System");
	        setSize(600, 600);
	        setLocationRelativeTo(null);
	        setDefaultCloseOperation(EXIT_ON_CLOSE);
	        
	    }

	    private void MenuBar() {
	    	

	        JMenuBar menuBar = new JMenuBar();
	        iconOpen = new ImageIcon("src/open.png");
	        iconSave = new ImageIcon("src/save.png");
	        iconExit = new ImageIcon("src/exit.png");
	        JFileChooser fileChooser = new JFileChooser(); //This method will search all type of files, not only CSV/TSV
	        JTextArea text = new JTextArea(10, 10);
	        getContentPane().add(text,BorderLayout.CENTER); //This adds text area to GUI
	        JScrollPane scrollPane1 = new JScrollPane(text);
	        scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	        add(scrollPane1);
	        
	        
	        fileMenu = new JMenu("File");
	        fileMenu.setMnemonic(KeyEvent.VK_F);

	        openMenu = new JMenuItem(new MenuItemAction("Open file", iconOpen,
	                KeyEvent.VK_O));
	        openMenu.addActionListener(ev -> {
	        	int value = fileChooser.showOpenDialog(openMenu);
	        	if (value == JFileChooser.APPROVE_OPTION) {
	        		File file = fileChooser.getSelectedFile();
	        		try {
	        			BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
	        			text.read(input, "Read file");
	        		}
	        		catch (Exception e) {
	        			e.printStackTrace();
	        		}
	        	}
	        	else {
        			System.out.println("Opening Failed.");
        		}
	        }
	        );

	        saveMenu = new JMenuItem(new MenuItemAction("Save Result", iconSave,
	                KeyEvent.VK_S));
	        //TODO: use JFileChooser method to save data file OR just make auto-save when we press save button.

	        exitMenu = new JMenuItem("Exit Program", iconExit);
	        exitMenu.setMnemonic(KeyEvent.VK_C);
	        exitMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));
	        exitMenu.addActionListener((event) -> System.exit(0));

	        fileMenu.add(openMenu);
	        fileMenu.add(saveMenu);
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

	    public static void main(String[] args) {
	    	//control swing objects with invokeLater
	        EventQueue.invokeLater(() -> {
	            JFrame UI = new gui();
	            UI.setVisible(true);
	        });
	    }

}

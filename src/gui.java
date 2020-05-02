import javax.swing.*;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class gui extends JFrame{
	//set variables for Menubar
	private ImageIcon iconOpen;
	private ImageIcon iconSave;
	private ImageIcon iconExit;
	private JMenu fileMenu;
	private JMenuItem openMenuItem;
	private JMenuItem saveMenuItem;
	private JMenuItem exitMenuItem;
	
	public gui() {

        initUI();
    }
	 private void initUI() {

	        MenuBar();
	        setTitle("Call of Cthulu Project Management system.");
	        setSize(600, 600);
	        setLocationRelativeTo(null);
	        setDefaultCloseOperation(EXIT_ON_CLOSE);
	    }

	    private void MenuBar() {
	    	

	        JMenuBar menuBar = new JMenuBar();
	        iconOpen = new ImageIcon("src/open.png");
	        iconSave = new ImageIcon("src/save.png");
	        iconExit = new ImageIcon("src/exit.png");

	        fileMenu = new JMenu("File");
	        fileMenu.setMnemonic(KeyEvent.VK_F);

	        openMenuItem = new JMenuItem(new MenuItemAction("Open file", iconOpen,
	                KeyEvent.VK_O));
	        //TODO: use JfileChooser method to choose data file for open data

	        saveMenuItem = new JMenuItem(new MenuItemAction("Save Result", iconSave,
	                KeyEvent.VK_S));
	        //TODO: use JFileChooser method to save data file

	        exitMenuItem = new JMenuItem("Exit Program", iconExit);
	        exitMenuItem.setMnemonic(KeyEvent.VK_C);
	        exitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));
	        exitMenuItem.addActionListener((event) -> System.exit(0));

	        fileMenu.add(openMenuItem);
	        fileMenu.add(saveMenuItem);
	        fileMenu.addSeparator();
	        fileMenu.add(exitMenuItem);

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

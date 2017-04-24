import javax.swing.SwingUtilities;

public class Main {

	public static void main(String[] args) {
		
		GameFacade gameFacade; 
		
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		         new LauncherGUI();
		    }
		});
	}
}


import javax.swing.JButton;

public class Tile {
	private JButton btn;
	private String id;
	public Tile(String id, JButton btn){
		this.id = id;
		this.btn = btn;
	}
	
	public void setId(String id){
		this.id = id;
	}
	
	public String getId(){
		return id;
	}

	public boolean compareButton(JButton jbutton)
	{
		if(jbutton.equals(btn))
			return true;
		else 
			return false;
		
	}
	
	public void setTileText(String str){
		btn.setText(str);
	}
	
	public void disableTile(){
		btn.setEnabled(false);
	}
	
	public void update(String msg, String id) {

		
	}

}

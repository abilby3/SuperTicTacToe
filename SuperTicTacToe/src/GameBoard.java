import java.util.ArrayList;

import javax.swing.JButton;

public class GameBoard {

	private ArrayList<JButton> tiles;
	
	public GameBoard(){
		tiles = new ArrayList<>();
	}
	
	public ArrayList<JButton> getTiles(){
		return tiles;
	}
	
	public boolean validMove(int i){
		if(tiles.get(i).getText().equals(""))
			return false;
		else
			return true;
	}
	
	public void addTile(JButton tile){
		tiles.add(tile);
	}
	
	
	public void placeMove(int moveIndex, int turn){
		if(GameGUI.turn % 2 == 0)
			tiles.get(moveIndex).setText("O");
		else
			tiles.get(moveIndex).setText("X");
		tiles.get(moveIndex).setEnabled(false);	
	}
	
	public void placeMove(JButton tile, int turn){
		int index = tiles.indexOf(tile);
		if(GameGUI.turn % 2 == 0)
			tiles.get(index).setText("O");
		else
			tiles.get(index).setText("X");
		tiles.get(index).setEnabled(false);	
	}
	
}

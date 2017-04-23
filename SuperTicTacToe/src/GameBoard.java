import java.util.ArrayList;

import javax.swing.JButton;

public class GameBoard {

	private ArrayList<JButton> tiles;
	final int MATRIX = 5;
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
	
	public GameBoard clone(){
		GameBoard newGameBoard = new GameBoard();
		for(int i = 0; i < tiles.size(); i++)
			newGameBoard.getTiles().add(new JButton(tiles.get(i).getText()));
		return newGameBoard;	
	}
	
	public String getWinner(){
		
		int oCount = 0;
		int xCount = 0;
		int tmpCount = 1; 
		
		//Check for winner across rows
		for(int i = 0; i < MATRIX * MATRIX; i++)
		{ 
			if(tiles.get(i).getText().equals("X"))
				xCount++;
			else if(tiles.get(i).getText().equals("O"))
				oCount++;
			
			if(tmpCount % MATRIX == 0)
			{
				tmpCount = 0;
				if(oCount == MATRIX)
					return "O";
				else if(xCount == MATRIX)
					return "X";
				oCount = 0;
				xCount = 0;
			}
			tmpCount++;
		} 
		
		oCount = 0;
		xCount = 0;
		tmpCount = 1;
		int index = 0;
		
		//Check for winnder in cols
		for(int i = 0; i < MATRIX; i++)
		{
			index = i;
			while(tmpCount <= MATRIX)
			{
				if(tiles.get(index).getText().equals("X"))
					xCount++;
				else if(tiles.get(index).getText().equals("O"))
					oCount++;
				
				index += MATRIX; 
				tmpCount++;
			}
			
			if(oCount == MATRIX)
				return "O";
			else if(xCount == MATRIX)
				return "X";
			oCount = 0;
			xCount = 0;
			tmpCount = 1;
		}
		
		oCount = 0;
		xCount = 0;			
		index = 0;
		//left to right diagonal
		for(int i = 0; i < MATRIX; i++)
		{
			
			if(tiles.get(index).getText().equals("X"))
				xCount++;
			else if(tiles.get(index).getText().equals("O"))
				oCount++;
			index += MATRIX + 1;
		}
		if(oCount == MATRIX)
			return "O";
		else if(xCount == MATRIX)
			return "X";
		
		oCount = 0;
		xCount = 0;			
		index = MATRIX - 1;
		//right to left diagonal
		for(int i = 0; i < MATRIX; i++)
		{
			
			if(tiles.get(index).getText().equals("X"))
				xCount++;
			else if(tiles.get(index).getText().equals("O"))
				oCount++;
			index += MATRIX - 1;
		}
		if(oCount == MATRIX)
			return "O";
		else if(xCount == MATRIX)
			return "X";
		
		for(int i = 0; i < tiles.size(); i++)
		{
			if(tiles.get(i).getText().equals(""))
				return "";
		}
		
		return "Draw";
	}
	

}

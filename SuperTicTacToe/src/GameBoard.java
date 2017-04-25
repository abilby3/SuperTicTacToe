import java.util.ArrayList;

import javax.swing.JButton;

public class GameBoard {

	private ArrayList<JButton> tiles;
	final int MATRIX = 5;
	private int lastMove = 0;
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
		setLastMove(moveIndex);
		if(turn % 2 == 0)
			tiles.get(moveIndex).setText("O");
		else
			tiles.get(moveIndex).setText("X");
		tiles.get(moveIndex).setEnabled(false);	
	}
	
	public void placeMove(JButton tile, int turn){
		int index = tiles.indexOf(tile);
		setLastMove(index);
		if(turn % 2 == 0)
			tiles.get(index).setText("O");
		else
			tiles.get(index).setText("X");
		tiles.get(index).setEnabled(false);	
	}
	
	public void setLastMove(int lastMove){
		this.lastMove = lastMove;
	}
	public int getLastMove(){
		return lastMove;
	}
	
	public GameBoard clone(){
		GameBoard newGameBoard = new GameBoard();
		for(int i = 0; i < tiles.size(); i++)
			newGameBoard.getTiles().add(new JButton(tiles.get(i).getText()));
		newGameBoard.setLastMove(lastMove);
		return newGameBoard;	
	}
	
	public void disableGameBoard(){
		for(JButton tile : tiles){
			tile.setEnabled(false);
		}
	}
	
	public void resetGameBoard(){
		for(JButton tile : tiles){
			tile.setText("");
			tile.setEnabled(true);
			setLastMove(0);
		}
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
	
	public void printBoard(){
		int count = 1;
		for(int i = 0; i < tiles.size(); i++)
		{
			if(count == 6)
			{
				System.out.println("");
				count = 1;
			}
			if(tiles.get(i ).getText().equals(""))
				System.out.print("-");
			else
				System.out.print(tiles.get(i).getText());
			count++;
		}
		System.out.println("\n");
	}
	
	public int evaluate(){
		int score = 0;
		
		score += evaluateLine( 0, 1, 2, 3, 4);
		score += evaluateLine( 5, 6, 7, 8, 9);
		score += evaluateLine( 10, 11, 12, 13, 14);
		score += evaluateLine( 15, 16, 17, 18, 19);
		score += evaluateLine( 20, 21, 22, 23, 24);
		score += evaluateLine( 0, 5, 10, 15, 20);
		score += evaluateLine( 1, 6, 11, 16, 21);
		score += evaluateLine( 2, 7, 12, 17, 22);
		score += evaluateLine( 3, 8, 13, 18, 23);
		score += evaluateLine( 4, 9, 14, 19, 24);
		score += evaluateLine( 0, 6, 12, 18, 24);
		score += evaluateLine( 4, 8, 12, 16, 20);
		
		
		return score;
	}
	
	public int evaluateLine(int pos1, int pos2, int pos3, int pos4, int pos5){
		int score =0;
		/*
		if(getWinner().equals("O")){
			score = -100000;
		} else if(getWinner().equals("X")){
			score = 100000;
		}
		*/
		
		String aiChar = LauncherGUI.aiChar;
		String oppChar = LauncherGUI.oppChar;
		
		if(tiles.get(pos1).getText().equals(aiChar)){
			score = 1;
		} else if (tiles.get(pos1).getText().equals(oppChar)){
			score = -1;
		}
		
		if(tiles.get(pos2).getText().equals(aiChar)){
			if(score == 1){
				score = 10;
			} else if(score == -1){
				return 0;
			} else if(score == 0){
				score = 1;
			}
		} else if (tiles.get(pos2).getText().equals(oppChar)){
			if(score == -1){
				score = -10;
			} else if(score == 1){
				return 0;
			} else if(score == 0){
				score = -1;
			}
		}
		
		if(tiles.get(pos3).getText().equals(aiChar)){
			if(score > 0){
				score *= 10;
			} else if(score < 0){
				return 0;
			} else if(score == 0){
				score = 1;
			} 
		} else if (tiles.get(pos3).getText().equals(oppChar)){
			if(score < 0){
				score *= 10;
			} else if(score > 0){
				return 0;
			} else if(score == 0){
				score = -1;
			}
		}
		
		if(tiles.get(pos4).getText().equals(aiChar)){
			if(score > 0){
				score *= 10;
			} else if(score < 0){
				return 0;
			} else if(score == 0){
				score = 1;
			} 
		} else if (tiles.get(pos4).getText().equals(oppChar)){
			if(score < 0){
				score *= 10;
			} else if(score > 0){
				return 0;
			} else if(score == 0){
				score = -1;
			}
		}
		
		if(tiles.get(pos5).getText().equals(aiChar)){
			if(score > 0){
				score *= 10;
			} else if(score < 0){
				return 0;
			} else if(score == 0){
				score = 1;
			} 
		} else if (tiles.get(pos5).getText().equals(oppChar)){
			if(score < 0){
				score *= 10;
			} else if(score > 0){
				return 0;
			} else if(score == 0){
				score = -1;
			}
		}
		
		return score;
	}
	

}

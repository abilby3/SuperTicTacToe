import java.util.ArrayList;

import javax.swing.JButton;

public class Node {

	GameBoard gameBoard;
	ArrayList<Node> children;
	
	public Node(GameBoard gameBoard)
	{
		this.gameBoard = gameBoard.clone();
		children = new ArrayList<>();
	}

	public GameBoard getGameBoard(){
		return gameBoard;
	}
	
	public ArrayList<Node> getChildren(){
		return children;
	}
	
	public void setChildren(ArrayList<Node> children){
		this.children = children;
	}
	
	public Node clone(){
		Node newNode = new Node(gameBoard);
		newNode.setChildren(this.children);
		
		return newNode;
	}
}

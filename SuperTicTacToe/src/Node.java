import java.util.ArrayList;

import javax.swing.JButton;

public class Node {

	GameBoard gameBoard;
	ArrayList<Node> children;
	private int score;
	
	public Node(){
		
	}
	
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
		newNode.setScore(score);
		
		return newNode;
	}
	
	public void copy(Node node){
		this.gameBoard = gameBoard.clone();
		//Node newNode = new Node(gameBoard);
		//newNode.setChildren(this.children); Could be an issue
		setChildren(node.getChildren());
		//newNode.setScore(score);
		setScore(node.getScore());
		
		//return newNode;
	}
	
	public void setScore(int score){
		this.score = score;
	}
	
	public int getScore(){
		return this.score;
	}
}

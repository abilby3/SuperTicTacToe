import java.util.ArrayList;

public class Node {

	GameBoard gameBoard;
	ArrayList<Node> children;
	
	public Node(GameBoard gameBoard)
	{
		this.gameBoard = gameBoard;
		children = new ArrayList<>();
	}

	public GameBoard getGameBoard(){
		return gameBoard;
	}
	
	public ArrayList<Node> getChildren(){
		return children;
	}
}

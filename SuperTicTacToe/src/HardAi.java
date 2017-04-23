
public class HardAi extends AI {

	private static int counter = 0;

	@Override
	public int makeMove(GameBoard gameBoard, int turn) {
		
		//generate tree based on gameBoard
		
		Node rootNode = new Node(gameBoard);
		int depth = 0;
		populateTree(rootNode, turn, depth);
		//apply minimax to said tree
		
		// return move that passes through the minimax
		
		return 0;
	}
	
	public void populateTree(Node node, int turn, int depth){
		GameBoard gameBoard = node.getGameBoard();
		//counter++;
		//System.out.println(counter);
		
		//Check Base Case
		if(depth == 3)
			return;
		else if(gameBoard.getWinner().equals("Draw") || gameBoard.getWinner().equals("X") || gameBoard.getWinner().equals("O"))
			return;
		
		//Find empty spaces
		for(int i = 0; i < gameBoard.getTiles().size(); i++)
			if(gameBoard.getTiles().get(i).getText().equals(""))
			{
				GameBoard newGameBoard = gameBoard.clone();
				newGameBoard.placeMove(i, turn);
				node.getChildren().add(new Node(newGameBoard));
			}
		
		turn++;
		depth++;
		for(int i = 0; i < node.getChildren().size(); i++)
			populateTree(node.getChildren().get(i), turn, depth);
	 
	}
}

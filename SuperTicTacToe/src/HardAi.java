//import java.util.Random;

public class HardAi extends AI {

	//private Random rand = new Random();
	
	private static int counter = 0;

	@Override
	public int makeMove(GameBoard gameBoard, int turn) {
		
		//generate tree based on gameBoard
		
		Node rootNode = new Node(gameBoard);
		int depth = 0;
		populateTree(rootNode, turn, depth);
		
		Node bestNode = minimax(rootNode, 3, true);
		int move = 0;
		
		for(int i = 0; i < 25; i++){
			//if(!(bestNode.getGameBoard().getTiles().get(i).getText().equals(gameBoard.getTiles().get(i).getText()))){
			String gameString = gameBoard.getTiles().get(i).getText();
			String bestString = bestNode.getGameBoard().getTiles().get(i).getText();
			
			if(!gameString.equals(bestString)){	
				
				move = i;
				break;
			}
		}
		System.out.println(gameBoard.evaluate());
		
		
		return move;
		
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
		for(int i = 0; i < gameBoard.getTiles().size(); i++){
			if(gameBoard.getTiles().get(i).getText().equals(""))
			{
				GameBoard newGameBoard = gameBoard.clone();
				newGameBoard.placeMove(i, turn);
				node.getChildren().add(new Node(newGameBoard));
			}
		}
		
		turn++;
		depth++;
		for(int i = 0; i < node.getChildren().size(); i++){
			populateTree(node.getChildren().get(i), turn, depth);
		}
	 
	}
	
	public Node minimax(Node startNode, int depth, boolean isAi){
		Node bestNode = startNode.clone();
		
		if(depth == 0 || startNode.getChildren().isEmpty()){
			return bestNode;
		}else{
			for(Node child : startNode.getChildren()){
				if(isAi){
					bestNode = minimax(bestNode, depth-1, !isAi);
					if (bestNode.getGameBoard().evaluate() > child.getGameBoard().evaluate()){
						bestNode = child;
					}
				}else{
					bestNode = minimax(bestNode, depth-1, !isAi);
					if (bestNode.getGameBoard().evaluate() < child.getGameBoard().evaluate()){
						bestNode = child;
					}
				}
			}
		}
		
		
		return bestNode;
	}
}

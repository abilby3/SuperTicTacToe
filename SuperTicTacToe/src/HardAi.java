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
		
		Node bestNode = minimaxStart(rootNode, 3,true);
		int move = 0;
		
		for(int i = 0; i < 25; i++){
			String gameString = gameBoard.getTiles().get(i).getText();
			String bestString = bestNode.getGameBoard().getTiles().get(i).getText();
			//System.out.println(gameString+ " " + bestString + " " + i);
			
			if(!(gameString.equals(bestString))){	
				//System.out.println("HI");
				move = i;
				
			}
		}
		//System.out.println(gameBoard.evaluate());
		
		
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
	
	public Node minimaxStart(Node startNode, int depth, boolean isAi){
		Node bestNode = startNode.clone();
		bestNode.setScore(bestNode.getGameBoard().evaluate());
		
		for(Node child : startNode.getChildren()){
			minimax(child, depth, !isAi);
			if(child.getScore() > bestNode.getScore()){
				bestNode = child.clone();
				System.out.println("HI");
			}
		}
		return bestNode;
	}
	
	public void minimax(Node startNode, int depth,boolean isAi){
		if(depth == 0 || startNode.getChildren().isEmpty()){
			startNode.setScore(startNode.getGameBoard().evaluate());
			return;
		} else{
			for(Node child : startNode.getChildren()){
				
								
				if(isAi){
					minimax(child, depth-1, !isAi);
					if(child.getGameBoard().evaluate() > startNode.getGameBoard().evaluate()){
						startNode.setScore(child.getGameBoard().evaluate());
						//i think this is where im messin up
					}
				}else{
					minimax(child, depth-1, !isAi);
					if(child.getGameBoard().evaluate() < startNode.getGameBoard().evaluate()){
						startNode.setScore(child.getGameBoard().evaluate());
						//as well as here
					}
				}
			}
		}
	}
}

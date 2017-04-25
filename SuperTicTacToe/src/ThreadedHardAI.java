
public class ThreadedHardAI extends AI {
	//private Random rand = new Random();
	private final static int MAX_DEPTH = 3;
	private static int counter = 0;

	@Override
	public int makeMove(GameBoard gameBoard, int turn) {
		
		//generate tree based on gameBoard
		Node rootNode = new Node(gameBoard);
		int depth = 0;
		populateTree(rootNode, turn, depth);
		System.out.println(counter);
		counter = 0;
		
		
		Node bestNode = minimaxStart(rootNode, 3,true);
		
		System.out.println(counter);
		counter = 0;
		int move = bestNode.getGameBoard().getLastMove();
		bestNode.getGameBoard().printBoard();
		return move;
	}
	
	public void populateTree(Node node, int turn, int depth){
		GameBoard gameBoard = node.getGameBoard();
		counter++;
		 
		//Check Base Case
		if(depth == MAX_DEPTH)
		{ 
			return;
		}
		else if(gameBoard.getWinner().equals("Draw") || gameBoard.getWinner().equals("X") || gameBoard.getWinner().equals("O"))
		{ 
			return;	
		}
		
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
		//Init node should not have a score because a move has to be made.
		bestNode.setScore(-100000);
		
		for(Node child : startNode.getChildren()){
			counter++;
			minimax(child, depth, !isAi);
			if(child.getScore() > bestNode.getScore() || bestNode.getScore() == -100000){
				bestNode = child.clone();
			}
		}
		return bestNode;
	}
	
	public void minimax(Node startNode, int depth,boolean isAi){
		if(depth == 0 || startNode.getChildren().isEmpty()){
			startNode.setScore(startNode.getGameBoard().evaluate());
			return;
		} else{
			counter++;
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

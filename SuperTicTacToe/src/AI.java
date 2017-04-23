import java.util.ArrayList;

public abstract class AI {
 
	protected ArrayList<GameBoard> gameboards;
	
	public abstract int makeMove(GameBoard gameBoard);
}

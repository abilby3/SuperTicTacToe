import java.util.Random;

public class EasyAi extends AI{
	private Random rand = new Random();
	
	
	@Override
	public int makeMove(GameBoard gameBoard) {
		
		int  move = rand.nextInt(24);
		while(gameBoard.validMove(move))
		{
			move = rand.nextInt(24);
		}
		
		return move;
	}
}

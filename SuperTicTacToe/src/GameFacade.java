
public class GameFacade {
	private GameGUI gameGui;
	private GameBoard gameBoard;
	private NetworkHandler networkHandler;
    public String condition; 
	
	
	public GameFacade(GameGUI gameGUI, GameBoard gameBoard, NetworkHandler networkHandler)
	{
		this.gameBoard = gameBoard;
		this.gameGui = gameGUI;
		this.networkHandler = networkHandler;
		
		
		
		
	}
  
	 
	public boolean localTurn()
	{
		if(gameGui.turn == 0)
		{
			return true; 
		}
		
		return false; 
	}
	
	public int localMove(int m)
	{
		return 0;
	}
	
	public void recieveMove(int move)
	{
		
	}
	
}


public class GameFacade {
	private GameGUI gameGui;
	private GameBoard gameBoard;
	private NetworkHandler networkHandler;
    public String condition; 
	public String test;
	
	public GameFacade(GameGUI gameGUI, GameBoard gameBoard, NetworkHandler networkHandler)
	{
	 
		this.gameGui = gameGUI;
		this.networkHandler = networkHandler;
	    this.gameBoard = gameBoard; 
		
		
		
	}
  
    public void sendMove(int m)
    {
    	networkHandler.localMove(m);
    } 
	
	public boolean localTurn()
	{
		if(GameGUI.turn == 0)
		{
			return true; 
		}
		
		return false; 
	}
	
	
	public void recieveMove(int move)
	{
		this.gameBoard.placeMove(move, GameGUI.turn);
	}
	
}


public class GameFacade {
	private GameGUI gameGui;
	private GameBoard gameBoard;
	private NetworkHandler networkHandler;
    public String condition;
	
	public GameFacade(GameGUI gameGUI,  NetworkHandler networkHandler)
	{
	 
		this.gameGui = gameGUI;
		this.networkHandler = networkHandler;
	   
		
		
		
	}
  
    public void sendMove(int m)
    {
    	networkHandler.localMove(m);
    } 
	
    public int getMove()
    {
    	return gameGui.getMove(); 
   
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
		gameGui.enemyeMove(move); 
		sendMove(gameGui.getMove());
		
	}
	
}

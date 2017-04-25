
public class GameFacade {
	private GameGUI gameGui;
	private GameBoard gameBoard;
	private NetworkHandler networkHandler;
    public String condition;
    private boolean localFirst;
	public static boolean gameOver;
	public boolean localTurn;
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
    
	
	public void setTurn()
	{
		GameGUI.turn++;
		localFirst = false;
	}

	public void setLocalFirst(boolean localFirst)
	{
		this.localFirst = localFirst;
	}
	
	public void setLocalTurn(boolean localTurn)
	{
		this.localTurn = localTurn;
	}

	public boolean getLocalTurn()
	{
		return localTurn;
	}
	
	public void recieveMove(int move)
	{
		gameGui.enemyeMove(move); 
		GameGUI.turn++;
		
		sendMove(gameGui.getMove());
		setLocalTurn(true);
		
	}
	
}

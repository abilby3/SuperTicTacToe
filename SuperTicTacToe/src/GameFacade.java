
public class GameFacade {
	private GameGUI gameGui;
	private GameBoard gameBoard;
	private NetworkHandler networkHandler;
    public String condition;
    private boolean localFirst;
	
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
    
	
	
	public void recieveMove(int move)
	{
		gameGui.enemyeMove(move); 
		GameGUI.turn++;
		sendMove(gameGui.getMove());
		
		
	}
	
}

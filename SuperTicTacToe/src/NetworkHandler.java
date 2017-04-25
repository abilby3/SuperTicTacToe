import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

public class NetworkHandler implements Runnable {
 
		private int port = 9001;
		private final String myIp = "localhost";
		private String ip = "localhost";
		private Socket socket;
		private DataOutputStream dos;
		private DataInputStream dis; 
		private ServerSocket serverSocket; 
		private boolean accepted; 
		private boolean unableToCommunicate;
		private GameFacade gameFacade; 
		private int errors; 
		private Random random;
		private int roll; 
		private boolean hasDice = false; 
		
		public NetworkHandler(int port, String ip)
		{
			 this.port = port;
			 this.ip = ip;
			 random = new Random();
			 this.roll = random.nextInt(100);
			 
		    if(!connect())
		    {
		    	initializeServer();
		    }
		}

		public void setGameFacade(GameFacade gameFacade)
		{
			this.gameFacade = gameFacade;
			
		}
		
		private void listenForRequest()
		{
		    Socket socket = null;
			try {
				socket = serverSocket.accept();
				dos = new DataOutputStream(socket.getOutputStream());
				dis = new DataInputStream(socket.getInputStream());
				accepted = true;
				sendDiceRoll(roll);
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				 
			
		}
		
		private void initializeServer() {
			try {
				serverSocket = new ServerSocket();
				serverSocket = new ServerSocket(port, 8,InetAddress.getLocalHost());
			} catch (Exception e) {
				e.printStackTrace();
			}
			 
		}

		private boolean connect() {
			try {
				socket = new Socket(InetAddress.getLocalHost(), port);
				dos = new DataOutputStream(socket.getOutputStream());
				dis = new DataInputStream(socket.getInputStream());
				accepted = true;
				sendDiceRoll(roll);
			} catch (IOException e) {
				System.out.println("Unable to connect to the address: " + ip + ":" + port + " | Starting a server");
				return false;
			}
			System.out.println("Successfully connected to the server.");
			return true;
		}
		
		public void update()
		{
			
			if(errors > 10)
			{
				unableToCommunicate = true; 
				
			}
			
			if(hasDice == false && dis != null)
			{
				
				//test if d100
				
				
				
				 try {
					    System.out.println("We have dice!");
						int dice = dis.readInt();
						System.out.println("Dice:" + dice);
					
						if(dice < 0 || dice > 100)
						{
							gameFacade.condition = "win";
							
						}
						
						if(dice < roll)
						{
						   localMove(gameFacade.getMove());
						   System.out.println("I won the dice roll!");
							gameFacade.setLocalFirst(true);
							gameFacade.setLocalTurn(true);
						}
						else
						{
							System.out.println("I lost the dice roll!");
							gameFacade.setTurn();
							gameFacade.setLocalFirst(false);
							gameFacade.setLocalTurn(false);
						}
						
						//Test if within range
						
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
				
				
				
				hasDice = true; 
				return;
			}
			
			if(unableToCommunicate == false && dis != null)
			{
				 try {
					 
					int move = dis.readInt();
					System.out.println("Move:" + move);
					 
					
					//Test if within range
					
					gameFacade.recieveMove(move);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					errors++;
				} 
			}
			
			if(unableToCommunicate == true)
			{
				
				gameFacade.condition = "win";
			}
		}
		
		@Override
		public void run() {
			
			while(true)
			{
				if(GameFacade.gameOver)
				{
					break;
				}
				
				update();
				if(!accepted)
				{
					listenForRequest();
				}
			
				if(unableToCommunicate)
				{
					break;
				}
				
			}
			
		}

		public void sendPlayer(){
			
		}
		
		public void sendDiceRoll(int d)
		{
			
			try {
				 
				dos.writeInt(d);
				dos.flush();
			} catch (IOException e1) {
				errors++;
				e1.printStackTrace();
			}
		}
		
		public void localMove(int m)
		{ 
			try {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				dos.writeInt(m);
				dos.flush();
			} catch (IOException e1) {
				errors++;
				e1.printStackTrace();
			}
		} 
}

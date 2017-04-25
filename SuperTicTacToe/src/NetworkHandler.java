import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class NetworkHandler implements Runnable {
 
		private int port = 9001;
		private String ip = "localhost";
		private Socket socket;
		private DataOutputStream dos;
		private DataInputStream dis; 
		private ServerSocket serverSocket; 
		private boolean accepted; 
		private boolean unableToCommunicate;
		private GameFacade gameFacade; 
		private int errors; 
		
		
		public NetworkHandler(int port, String ip, GameFacade gameFacade)
		{
			 this.port = port;
			 this.ip = ip;
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
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				 
			
		}
		
		private boolean Connect(String ip, int port)
		{
			try {
				socket = new Socket(ip, port);
				dos = new DataOutputStream(socket.getOutputStream());
				dis = new DataInputStream(socket.getInputStream());
				accepted = true;
			} catch (UnknownHostException e) {
				
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false; 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			
			return true; 
				
		}
		
		public void update()
		{
			
			if(errors > 10)
			{
				unableToCommunicate = true; 
				
			}
			
			if(gameFacade.localTurn() == false && unableToCommunicate == false)
			{
				 try {
					int move = dis.readInt();
					gameFacade.recieveMove(move);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
				update();
				if(this.gameFacade.localTurn() == false && !accepted)
				{
					listenForRequest();
				}
			
				
			}
			
		}


		public void localMove(int m)
		{
			try {
				dos.writeInt(m);
				dos.flush();
			} catch (IOException e1) {
				errors++;
				e1.printStackTrace();
			}
		} 
}

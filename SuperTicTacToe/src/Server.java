import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	
	public void getMove(int v) throws IOException
	{
		ServerSocket server = new ServerSocket(9001);
		Socket client = server.accept();
		BufferedReader fromClient = new BufferedReader
				(
						new InputStreamReader(client.getInputStream()));
			System.out.println(fromClient.readLine());			
	}
}

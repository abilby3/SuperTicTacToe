import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	
	
	public void test(int v) throws UnknownHostException, IOException
	{
		Socket server = new Socket("", 1234);
		DataOutputStream toServer = new DataOutputStream(server.getOutputStream());
	    toServer.writeByte(v);
		server.close();
		
	}
}

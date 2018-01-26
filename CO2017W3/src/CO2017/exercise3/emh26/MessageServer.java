/*
 	The class that is used to help us to make the application multi threaded.
 */
package CO2017.exercise3.emh26;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class MessageServer {

	

	public MessageServer() {

	}
	//The main method where we enable multiple users to connect by creating objects of the MessageServerHandler class
	public static void main(String[] args) {
		String port = args[0];
		System.out.println("Starting Message server on port "+port);
		try (ServerSocket server = new ServerSocket(Integer.parseInt(port))) {
			ThreadPoolExecutor e = (ThreadPoolExecutor) Executors.newCachedThreadPool();
			MessageBoard msb = new MessageBoard();
			while (true) {
				
				Socket client = server.accept();
				System.out.println("connection : " + client.getInetAddress());
				MessageServerHandler temp = new MessageServerHandler(msb, client);
				Thread the = new Thread(temp);
				e.execute(the);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}
}

/*
 	This class would implement the client side of this application
 	and would be the class with which the user would interact, this
 	in turn would send the user input to the server handler.
 	This class creates an id that will be incremented as the user sends the data.
 	This will enable the option to not let the user select the ID which will be saved
 	in the message header.
*/

package CO2017.exercise3.emh26;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.net.UnknownHostException;

public class MessageClient {

	//Creating buffered reader to read the data that is sent by the server.
	static BufferedReader in;
	//Creating the writer object to send data to the server.
	static Writer out;
	
	
	public MessageClient() {
		
	}

	//The main method for the user to interact with the server.
	public static void main(String[] args) {
		String servername = args[0];
		String port = args[1];
		String clientRequestString;
		String result;
		int id = 0;
		try (Socket server = new Socket(servername, Integer.parseInt(port))) {

			in = new BufferedReader(new InputStreamReader(server.getInputStream(), "UTF-8"));
			out = new OutputStreamWriter(server.getOutputStream());
			BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
			do {
				//Conditions that would be used to send and receive data from the server accordingly. 
				System.out.print("? ");
				clientRequestString = stdin.readLine();
				if (clientRequestString.startsWith("SEND")) {
					String[] parts = clientRequestString.split(":");
					id += 1;
					out.write(String.format("%s%n", "SEND" + ":" + id + ":" + parts[1]));
					out.flush();
				} else if (clientRequestString.startsWith("BYE")) {
					out.write(String.format("%s%n", clientRequestString));
					out.flush();
				} else if (clientRequestString.equals("LIST")) {
					out.write(String.format("%s%n", clientRequestString));
					out.flush();
					while (true) {
						result = in.readLine();
						if (result.equals(".")) {
							break;
						}
						System.out.println(result);
					}
					
				} else if (clientRequestString.startsWith("GET")) {
					out.write(String.format("%s%n", clientRequestString));
					out.flush();
					result = in.readLine();
					System.out.println(result);
				} else {
					System.out.println("You have entered the wrong choice of inputs.");
				}
				
				
			} while (!clientRequestString.equals("BYE"));
			server.close();
		//Handling exceptions thrown by the code.
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Server closed connection.");
		} catch (Exception e) {
			System.out.println("Server closed connection.");
		}

	}

}

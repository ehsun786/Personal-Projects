/*
 * The class where the server will process the commands sent by the user
 * and send some data back to the client too.
 */
package CO2017.exercise3.emh26;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.Socket;
import java.util.Set;

public class MessageServerHandler implements Runnable {

	MessageBoard messageBoard;
	Socket socket;
	String request;
	Writer out;
	BufferedReader in;
	int id = 0;
	char autoGeneratedCharacter;
	static char nextAutoGeneratedCharacter = 'A';
	
	//The constructor where we initialise variables declared above.
	public MessageServerHandler(MessageBoard b, Socket cl) {

		try {
			out = new OutputStreamWriter(cl.getOutputStream());
			in = new BufferedReader(new InputStreamReader(cl.getInputStream(), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.messageBoard = b;
		this.socket = cl;
		autoGeneratedCharacter = nextAutoGeneratedCharacter;
		nextAutoGeneratedCharacter += 1;
	}

	//The run method where the conditions are defined which will be used to send data to the user
	//and receive the data too.
	@Override
	public void run() {
		try {
			do {
				request = in.readLine();
				if (request.equals("LIST")) {
					System.out.println("LIST");
					Set<MessageHeader> set = messageBoard.ListHeaders();
					for (MessageHeader msgHead : set) {
						out.write(String.format("%s%n",msgHead));
						out.flush();
					}
					out.write(String.format("%s%n", "."));
					out.flush();
				} else if (request.startsWith("GET:")) {
					String[] parts = request.split(":");
					String head = parts[1];
					Set<MessageHeader> mshSet = messageBoard.ListHeaders();
					MessageHeader msh = null;
					for (MessageHeader temp : mshSet) {
						if (temp.toString().equals(head)) {
							msh = temp;
							break;
						}
					}
					if (!(msh == null)) {
						System.out.println(request);
						System.out.println(head + "=" + messageBoard.hashMap.get(msh));
						System.out.println("OK");
						out.write(String.format("%s%n", messageBoard.hashMap.get(msh)));
						out.flush();
					} else {
						System.out.println(request);
						System.out.println(head + "=" + "null");
						System.out.println("ERR");
						out.write(String.format("%s%n", "No such message"));
						out.flush();
					}
				} else if (request.startsWith("SEND:")) {
					String[] parts = request.split(":");
					String body = parts[2];
					MessageHeader msh = new MessageHeader(autoGeneratedCharacter, Integer.parseInt(parts[1]));
					System.out.println("SEND:" + parts[1] + ":" + body);
					messageBoard.SaveMessage(msh, body);
				} else {
					out.write(String.format("%s%n", "ERR"));
					out.flush();
				}

				
			} while (!request.equals("BYE"));
			System.out.println("BYE");
			out.close();
			in.close();
			socket.close();
		//Exception Handling to make the application fault tolerant.
		} catch (UnsupportedEncodingException e) {
			System.err.print("There has been problem with the format of the client input.");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.print("There has been problem reading the client input.");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Connection dropped unexpectedly.");
		}
	}

}
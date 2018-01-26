/*
 * The class which will help us to store the data sent by the user thus enabling the user
 * to retrieve the data too.
 */
package CO2017.exercise3.emh26;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class MessageBoard {
	
	Map<MessageHeader, String> hashMap;
	
	//The hash map is concurrent so that there are no deadlocks between any of the clients.
	public MessageBoard() {
		hashMap = new ConcurrentHashMap<MessageHeader, String>();
	}
	
	String GetMessage(MessageHeader mh) {
		return hashMap.get(mh);
	}
	
	synchronized Set<MessageHeader> ListHeaders() {
		return hashMap.keySet();
	}
	
	synchronized void SaveMessage(MessageHeader mh, String msg) {
		if(!hashMap.containsKey(mh)) {
			hashMap.put(mh, msg);
		}
	}
	
}

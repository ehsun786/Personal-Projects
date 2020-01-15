
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/*A node can be described with a name, a LinkedList in reference to the shortestPath, 
* a distance from the source, and an adjacency list named adjacentNodes:*/

public class Node {

	private String ndName;

	private LinkedList<Node> smallestPath = new LinkedList<>();

	private Integer dist = Integer.MAX_VALUE;

	private Map<Node, Integer> adjcntNodes = new HashMap<>();

	public Node(String name) {
		this.ndName = name;
	}

	public void addDest(Node dest, int dist) {
		adjcntNodes.put(dest, dist);
	}

	public String getNodeName() {
		return ndName;
	}

	public void setNodeName(String name) {
		this.ndName = name;
	}

	public Map<Node, Integer> getAdjacencyNodes() {
		return adjcntNodes;
	}

	public void setAdjacencyNodes(Map<Node, Integer> adjcntNodes) {
		this.adjcntNodes = adjcntNodes;
	}

	public Integer getDist() {
		return dist;
	}

	public void setDist(Integer distance) {
		this.dist = distance;
	}

	public List<Node> getSmallestPath() {
		return smallestPath;
	}

	public void setSmallestPath(LinkedList<Node> smallestPath) {
		this.smallestPath = smallestPath;
	}

}

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Set;

public class Dijkstra {

	/*
	 * The adjacentNodes attribute is used to associate immediate neighbours with
	 * edge length. This is a simplified implementation of an adjacency list, which
	 * is more suitable for the Dijkstra algorithm than the adjacency matrix.
	 */
	public static Graph smallestPathFromSrc(Graph graph, Node source) {
		source.setDist(0);
		Set<Node> setNodes = new HashSet<>();
		Set<Node> unsetNodes = new HashSet<>();
		unsetNodes.add(source);
		while (unsetNodes.size() != 0) {
			Node curntNode = smallestDistNode(unsetNodes);
			unsetNodes.remove(curntNode);
			for (Entry<Node, Integer> adjacenctPair : curntNode.getAdjacencyNodes().entrySet()) {
				Node adjcntNode = adjacenctPair.getKey();
				Integer eWeight = adjacenctPair.getValue();
				if (!setNodes.contains(adjcntNode)) {
					minDistance(adjcntNode, eWeight, curntNode);
					unsetNodes.add(adjcntNode);
				}
			}
			setNodes.add(curntNode);
		}
		return graph;
	}

	/*
	 * The calculateMinimumDistance() method compares the actual distance with the
	 * newly calculated one while following the newly explored path:
	 */
	private static void minDistance(Node evalNode, Integer edgeWeight, Node srcNode) {
		Integer srcDistance = srcNode.getDist();
		if (srcDistance + edgeWeight < evalNode.getDist()) {
			evalNode.setDist(srcDistance + edgeWeight);
			LinkedList<Node> shortestPath = new LinkedList<>(srcNode.getSmallestPath());
			shortestPath.add(srcNode);
			evalNode.setSmallestPath(shortestPath);
		}
	}

	/*
	 * The getLowestDistanceNode() method, returns the node with the lowest distance
	 * from the unsettled nodes set.
	 */
	private static Node smallestDistNode(Set<Node> unsettledNodes) {
		Node smallestDistNode = null;
		int lowestDistance = Integer.MAX_VALUE;
		for (Node node : unsettledNodes) {
			int nodeDistance = node.getDist();
			if (nodeDistance < lowestDistance) {
				lowestDistance = nodeDistance;
				smallestDistNode = node;
			}
		}
		return smallestDistNode;
	}
}
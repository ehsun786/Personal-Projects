public class DijkstraRunner {

	public static void main(String... argz) {
		Node nodeG = new Node("G");
		Node nodeH = new Node("H");
		Node nodeI = new Node("I");
		Node nodeJ = new Node("J");
		Node nodeK = new Node("K");
		Node nodeL = new Node("L");

		nodeG.addDest(nodeH, 10);
		nodeG.addDest(nodeI, 15);

		nodeH.addDest(nodeJ, 12);
		nodeH.addDest(nodeL, 15);

		nodeI.addDest(nodeK, 10);

		nodeJ.addDest(nodeK, 2);
		nodeJ.addDest(nodeL, 1);

		nodeL.addDest(nodeK, 5);

		Graph grph = new Graph();

		grph.addNode(nodeG);
		grph.addNode(nodeH);
		grph.addNode(nodeI);
		grph.addNode(nodeJ);
		grph.addNode(nodeK);
		grph.addNode(nodeL);

		grph = Dijkstra.smallestPathFromSrc(grph, nodeG);
		for (Node nd : grph.getNodes()) {
			System.out.println("Name: " + nd.getNodeName());
		}

	}

}

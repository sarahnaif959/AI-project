import java.util.*;

class Graph {
    List<Node> nodes;
    List<Edge> edges;

    Graph() {
        nodes = new ArrayList<>();
        edges = new ArrayList<>();
    }

    void addNode(String name, int weight) {
        nodes.add(new Node(name, weight));
    }

    void addEdge(String from, String to, int weight) {
        Node nodeFrom = getNodeByName(from);
        Node nodeTo = getNodeByName(to);
        if (nodeFrom != null && nodeTo != null) {
            edges.add(new Edge(nodeFrom, nodeTo, weight));
        }
    }

    Node getNodeByName(String name) {
        for (Node node : nodes) {
            if (node.name.equals(name)) {
                return node;
            }
        }
        return null;
    }
}

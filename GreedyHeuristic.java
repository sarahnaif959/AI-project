import java.util.*;

class GreedyHeuristic {
    public List<List<Node>> partitionGraph(Graph graph, int clusterCount, int lowerBound, int upperBound) {
        List<List<Node>> clusters = new ArrayList<>();
        for (int i = 0; i < clusterCount; i++) {
            clusters.add(new ArrayList<>());
        }
        // فرز العقد بناءً على الوزن
        graph.nodes.sort((n1, n2) -> n2.weight - n1.weight);
// توزيع العقد باستخدام الخوارزمية الجشعة
        for (Node node : graph.nodes) {
            for (List<Node> cluster : clusters) {
                int clusterWeight = cluster.stream().mapToInt(n -> n.weight).sum();
                if (clusterWeight + node.weight <= upperBound) {
                    cluster.add(node);
                    break;
                }
            }
        }
        // **تحقق من الحد الأدنى للوزن بعد توزيع العقد**
        for (List<Node> cluster : clusters) {
            int clusterWeight = cluster.stream().mapToInt(n -> n.weight).sum();
            // إذا كان وزن المجموعة أقل من الحد الأدنى، أضف عقدة إلى هذه المجموعة
            if (clusterWeight < lowerBound) {
                for (Node node : graph.nodes) {
                    if (!cluster.contains(node)) {
                        if (clusterWeight + node.weight <= upperBound) {
                            cluster.add(node);
                            break;
                        }
                    }
                }
            }
        }

        return clusters;
    }
}

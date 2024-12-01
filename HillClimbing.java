import java.util.*;

class HillClimbing {
    public List<List<Node>> optimize(Graph graph, List<List<Node>> initialClusters, int lowerBound, int upperBound) {
        List<List<Node>> bestSolution = new ArrayList<>(initialClusters);
        int bestScore = calculateScore(graph, bestSolution);

        for (int i = 0; i < 10; i++) {
            List<List<Node>> newSolution = new ArrayList<>(initialClusters);
            swapNodes(newSolution);// استدعاء تبديل العقد
            int newScore = calculateScore(graph, newSolution);

            if (newScore > bestScore) {
                bestSolution = newSolution;
                bestScore = newScore;
            }
        }
        return bestSolution;
    }

    // **منطق تبديل العقد بين المجموعات**
    private void swapNodes(List<List<Node>> clusters) {
        Random rand = new Random();

        // اختيار عشوائي لمجموعة وعقدة
        int clusterIndex1 = rand.nextInt(clusters.size());
        List<Node> cluster1 = clusters.get(clusterIndex1);

        if (cluster1.size() > 0) {
            // اختيار عقدة عشوائية من المجموعة
            Node nodeToMove = cluster1.get(rand.nextInt(cluster1.size()));

            // اختيار مجموعة أخرى
            int clusterIndex2 = (clusterIndex1 == 0) ? 1 : 0;
            List<Node> cluster2 = clusters.get(clusterIndex2);

            // نقل العقدة من المجموعة الأولى إلى الثانية
            cluster1.remove(nodeToMove);
            cluster2.add(nodeToMove);
        }
    }
    // حساب نتيجة المجموعات
    public int calculateScore(Graph graph, List<List<Node>> clusters) {
        int score = 0;
        for (List<Node> cluster : clusters) {
            for (Node node : cluster) {
                for (Edge edge : graph.edges) {
                    if (cluster.contains(edge.from) && cluster.contains(edge.to)) {
                        score += edge.weight;
                    }
                }
            }
        }
        return score;
    }
}

import java.util.*;

public class GraphOptimization {

    public static void main(String[] args) {
        Graph graph = new Graph();

        graph.addNode("A", 3);
        graph.addNode("B", 4);
        graph.addNode("C", 5);
        graph.addNode("D", 2);
        graph.addNode("E", 6);

        graph.addEdge("A", "B", 10);
        graph.addEdge("A", "C", 15);
        graph.addEdge("B", "D", 12);
        graph.addEdge("C", "E", 20);
        graph.addEdge("D", "E", 8);

        GreedyHeuristic gh = new GreedyHeuristic();
        List<List<Node>> initialClusters = gh.partitionGraph(graph, 2, 5, 11);

        HillClimbing hc = new HillClimbing();
        // تخزين النتائج من التشغيلات المتعددة
        List<Integer> scores = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            List<List<Node>> optimizedClusters = hc.optimize(graph, initialClusters, 5, 11);
            int score = hc.calculateScore(graph, optimizedClusters);
            scores.add(score);
            System.out.println("Run " + (i + 1) + ": Score = " + score);
        }

        // حساب المتوسط والانحراف المعياري
        double mean = calculateMean(scores);
        double stdDev = calculateStdDev(scores, mean);

        System.out.println("Average Score: " + mean);
        System.out.println("Standard Deviation: " + stdDev);
    }

    // **دوال حساب المتوسط والانحراف المعياري**
    public static double calculateMean(List<Integer> scores) {
        int sum = 0;
        for (int score : scores) {
            sum += score;
        }
        return (double) sum / scores.size();
    }

    public static double calculateStdDev(List<Integer> scores, double mean) {
        double sum = 0;
        for (int score : scores) {
            sum += Math.pow(score - mean, 2);
        }
        return Math.sqrt(sum / scores.size());

    }
}

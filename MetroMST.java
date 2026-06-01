import java.util.*;

class Edge implements Comparable<Edge> {
    char src, dest;
    int cost;

    Edge(char src, char dest, int cost) {
        this.src = src;
        this.dest = dest;
        this.cost = cost;
    }

    public int compareTo(Edge e) {
        return this.cost - e.cost;
    }
}

public class MetroMST {

    static Map<Character, Character> parent = new HashMap<>();

    static char find(char node) {
        if (parent.get(node) == node)
            return node;

        char root = find(parent.get(node));
        parent.put(node, root);
        return root;
    }

    static void union(char a, char b) {
        char rootA = find(a);
        char rootB = find(b);

        if (rootA != rootB)
            parent.put(rootA, rootB);
    }

    public static void main(String[] args) {

        Edge[] edges = {
            new Edge('Y', 'H', 4),
            new Edge('M', 'Y', 5),
            new Edge('K', 'W', 6),
            new Edge('S', 'E', 7),
            new Edge('M', 'K', 8),
            new Edge('W', 'S', 8),
            new Edge('E', 'Y', 9),
            new Edge('K', 'S', 9),
            new Edge('M', 'W', 10),
            new Edge('M', 'S', 11),
            new Edge('W', 'E', 12),
            new Edge('Y', 'W', 14)
        };

        char[] stations = {'M', 'K', 'W', 'S', 'E', 'Y', 'H'};

        for (char station : stations) {
            parent.put(station, station);
        }

        Arrays.sort(edges);

        int totalCost = 0;

        System.out.println("=================================");
        System.out.println(" Bangalore Metro Phase-3 MST");
        System.out.println("=================================\n");

        System.out.println("Selected Edges:");

        for (Edge edge : edges) {

            char root1 = find(edge.src);
            char root2 = find(edge.dest);

            if (root1 != root2) {

                union(edge.src, edge.dest);

                System.out.println(edge.src + " - " +
                        edge.dest + " : ₹" +
                        edge.cost + " crore");

                totalCost += edge.cost;
            }
        }

        System.out.println("\nTotal MST Cost = ₹" +
                totalCost + " crore");
    }
}
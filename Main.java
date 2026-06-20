import java.util.Arrays;

public class Main {

    static final int V = 7;

    // Find vertex with minimum distance
    static int minDistance(int dist[], boolean visited[]) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int v = 0; v < V; v++) {
            if (!visited[v] && dist[v] < min) {
                min = dist[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    // Dijkstra Algorithm
    static void dijkstra(int graph[][], int src) {

        int dist[] = new int[V];
        boolean visited[] = new boolean[V];

        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(visited, false);

        dist[src] = 0;

        for (int count = 0; count < V - 1; count++) {

            int u = minDistance(dist, visited);

            visited[u] = true;

            for (int v = 0; v < V; v++) {

                if (!visited[v]
                        && graph[u][v] != 0
                        && dist[u] != Integer.MAX_VALUE
                        && dist[u] + graph[u][v] < dist[v]) {

                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }

        String[] locations = {
                "CK",
                "R1",
                "R2",
                "D1",
                "D2",
                "C1",
                "C2"
        };

        System.out.println("\n========================================");
        System.out.println(" FOODEXPRESS SHORTEST DELIVERY ROUTES");
        System.out.println("========================================");

        System.out.printf("%-10s %-15s\n", "Location", "Time(min)");
        System.out.println("------------------------------");

        for (int i = 0; i < V; i++) {
            System.out.printf("%-10s %-15d\n",
                    locations[i], dist[i]);
        }
    }

    public static void main(String[] args) {

        int graph[][] = {

                // CK R1 R2 D1 D2 C1 C2
                {0, 5, 8, 0, 0, 0, 0}, // CK
                {0, 0, 0, 4,10, 0, 0}, // R1
                {0, 0, 0, 6, 0, 0, 0}, // R2
                {0, 0, 0, 0, 0, 6, 0}, // D1
                {0, 0, 0, 0, 0, 0, 3}, // D2
                {0, 0, 0, 0, 0, 0, 3}, // C1
                {0, 0, 0, 0, 0, 0, 0}  // C2
        };

        dijkstra(graph, 0); // Source = CK
    }
}
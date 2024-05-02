import java.util.*;

public class PrimsAlgorithm {

    static class Edge {
        String source;
        String destination;
        int distance;

        public Edge(String source, String destination, int distance) {
            this.source = source;
            this.destination = destination;
            this.distance = distance;
        }
    }

    static class Graph {
        int vertices;
        LinkedList<Edge>[] adjacencyList;
        Map<String, Integer> cityIndices;

        public Graph(int vertices) {
            this.vertices = vertices;
            adjacencyList = new LinkedList[vertices];
            cityIndices = new HashMap<>();
            for (int i = 0; i < vertices; i++) {
                adjacencyList[i] = new LinkedList<>();
            }
        }

        public void addEdge(String source, String destination, int distance) {
            if (!cityIndices.containsKey(source)) {
                cityIndices.put(source, cityIndices.size());
            }
            if (!cityIndices.containsKey(destination)) {
                cityIndices.put(destination, cityIndices.size());
            }
            int sourceIndex = cityIndices.get(source);
            int destinationIndex = cityIndices.get(destination);
            Edge edge = new Edge(source, destination, distance);
            adjacencyList[sourceIndex].addFirst(edge);

            edge = new Edge(destination, source, distance);
            adjacencyList[destinationIndex].addFirst(edge);
        }

        public int primMST() {
            boolean[] mst = new boolean[vertices];
            Edge[] edges = new Edge[vertices];
            int[] key = new int[vertices];
            Arrays.fill(key, Integer.MAX_VALUE);
            key[0] = 0;
            edges[0] = new Edge(null, null, 0);

            for (int i = 0; i < vertices - 1; i++) {
                int vertex = minKey(key, mst);
                mst[vertex] = true;
                for (Edge edge : adjacencyList[vertex]) {
                    String adjVertex = edge.destination;
                    int distance = edge.distance;
                    int adjVertexIndex = cityIndices.get(adjVertex);
                    if (!mst[adjVertexIndex] && distance < key[adjVertexIndex]) {
                        key[adjVertexIndex] = distance;
                        edges[adjVertexIndex] = new Edge(edge.source, adjVertex, distance);
                    }
                }
            }
            return printMST(edges);
        }

        private int minKey(int[] key, boolean[] mst) {
            int min = Integer.MAX_VALUE;
            int minIndex = -1;

            for (int i = 0; i < vertices; i++) {
                if (!mst[i] && key[i] < min) {
                    min = key[i];
                    minIndex = i;
                }
            }

            return minIndex;
        }

        private int printMST(Edge[] edges) {
            int totalDistance = 0;
            System.out.println("Minimum Spanning Tree using Prim's Algorithm:");
            for (int i = 1; i < vertices; i++) {
                System.out.println("Edge: " + edges[i].source + " - " + edges[i].destination + " | Distance: " + edges[i].distance);
                totalDistance += edges[i].distance;
            }
            return totalDistance;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of cities: ");
        int V = scanner.nextInt();

        Graph graph = new Graph(V);

        System.out.print("Enter the number of connections between cities: ");
        int E = scanner.nextInt();

        System.out.println("Enter the connections (source, destination, distance):");
        for (int i = 0; i < E; i++) {
            String source = scanner.next();
            String destination = scanner.next();
            int distance = scanner.nextInt();
            graph.addEdge(source, destination, distance);
        }

        scanner.close();

        int totalDistance = graph.primMST();
        System.out.println("Total distance of Minimum Spanning Tree: " + totalDistance);
    }
}

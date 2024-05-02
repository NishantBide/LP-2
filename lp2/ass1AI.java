import java.util.*;

public class Main {
    static final int SIZE = 6;
    static int[][] adjMat = {
        {0, 1, 1, 0, 0, 0},
        {1, 0, 0, 1, 0, 0},
        {1, 0, 0, 0, 1, 0},
        {0, 1, 0, 0, 0, 1},
        {0, 0, 1, 0, 0, 0},
        {0, 0, 0, 1, 0, 0}
    };
    static boolean[] visited = new boolean[SIZE];

    public static void main(String[] args) {
        String[] vertices = {"A", "B", "C", "D", "E", "F"};
        int startVertex = 1;
        int goalNode = 5;

        dfs(startVertex, vertices, goalNode);
        bfs(startVertex, vertices, goalNode);
    }

    static void dfs(int start, String[] arr, int goal) {
        Stack<Integer> s = new Stack<>();
        s.push(start);
        visited[start] = true;

        System.out.print("DFS Traversal starting from vertex " + arr[start] + ": ");

        while (!s.isEmpty()) {
            int currentVertex = s.pop();

            System.out.print(arr[currentVertex] + " ");

            if (currentVertex == goal) {
                System.out.println("\nGoal node " + arr[goal] + " reached!");
                return;
            }

            for (int i = 0; i < SIZE; i++) {
                if (adjMat[currentVertex][i] == 1 && !visited[i]) {
                    s.push(i);
                    visited[i] = true;
                }
            }
        }

        Arrays.fill(visited, false);
        System.out.println();
    }

    static void bfs(int start, String[] arr, int goal) {
        Queue<Integer> q = new LinkedList<>();
        Arrays.fill(visited, false);

        if (!visited[start]) {
            System.out.print("BFS Traversal starting from vertex " + arr[start] + ": ");
            q.add(start);
            visited[start] = true;

            while (!q.isEmpty()) {
                int v = q.poll();

                System.out.print(arr[v] + " ");

                if (v == goal) {
                    System.out.println("\nGoal node " + arr[goal] + " reached!");
                    return;
                }

                for (int i = 0; i < SIZE; i++) {
                    if (adjMat[v][i] == 1 && !visited[i]) {
                        q.add(i);
                        visited[i] = true;
                    }
                }
            }
        }

        Arrays.fill(visited, false);
        System.out.println();
    }
}

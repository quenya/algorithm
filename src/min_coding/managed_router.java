package min_coding;

import java.util.*;
import java.util.stream.IntStream;

public class managed_router {
    static int N;
    static int T;
    static int[][] map;
    static int[][] distMap;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        T = sc.nextInt();
        map = new int[N][N];
        distMap = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
                distMap[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int i = 0; i < N; i++) {
            BFS(i);
        }
        int min = Integer.MAX_VALUE;
        if (T == 1) {
            for (int i = 0; i < N; i++) {
                int max = getMax(i);
                if (max < min)
                    min = max;
            }
        }
        if (T == 2) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (i == j) continue;
                    int max = getMax(i, j);
                    if (max < min)
                        min = max;
                }
            }
        }
        if (T == 3) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (i == j) continue;
                    for (int k = 0; k < N; k++) {
                        if (j == k || k == i) continue;
                        int max = getMax(i, j, k);
                        if (max < min)
                            min = max;
                    }
                }
            }
        }

        System.out.println(min + " us");
        sc.close();
    }

    private static int getMax(int a, int b, int c) {
        int[] dist = new int[N];
        IntStream.range(0, N).filter(i -> i != a && i != b && i != c).forEach(i -> {
            HashMap<Integer, Integer> d = new HashMap<>();
            d.put(a, distMap[i][a]);
            d.put(b, distMap[i][b]);
            d.put(c, distMap[i][c]);
            Map.Entry<Integer, Integer> minEntry = d.entrySet().stream().min(Comparator.comparingInt(Map.Entry::getValue)).orElse(null);
            Integer minNode = minEntry.getKey();
            if (dist[minNode] < minEntry.getValue())
                dist[minNode] = minEntry.getValue();
        });
        int max = 0;
        if (max < dist[a]) max = dist[a];
        if (max < dist[b]) max = dist[b];
        if (max < dist[c]) max = dist[c];
        return max;
    }

    private static int getMax(int a, int b) {
        int[] dist = new int[N];
        IntStream.range(0, N).filter(i -> i != a && i != b).forEach(i -> {
            if (distMap[i][a] < distMap[i][b]) {
                if (dist[a] < distMap[i][a])
                    dist[a] = distMap[i][a];
            } else {
                if (dist[b] < distMap[i][b])
                    dist[b] = distMap[i][b];
            }
        });
        int max = 0;
        if (max < dist[a]) max = dist[a];
        if (max < dist[b]) max = dist[b];
        return max;
    }

    private static int getMax(int a) {
        return IntStream.range(0, N).filter(i -> i != a).map(i -> distMap[i][a]).filter(i -> i >= 0).max().orElse(0);
    }

    private static void BFS(int start) {
        Queue<Integer> nextQueue = new LinkedList<>();
        nextQueue.add(start);
        int currentDist = 0;
        while (!nextQueue.isEmpty()) {
            Integer node = nextQueue.poll();
            if (node != start)
                currentDist = distMap[start][node];
            for (int i = 0; i < N; i++) {
                if (i == node || i == start) continue;
                if (map[i][node] > 0 && currentDist + map[i][node] < distMap[start][i]) {
                    distMap[start][i] = currentDist + map[i][node];
                    nextQueue.add(i);
                }
            }
        }
    }
}

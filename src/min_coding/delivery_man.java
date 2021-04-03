package min_coding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class delivery_man {
    static int H;
    static int W;
    static int N;
    static int[][] map;
    static int[][] tour;
    static HashMap<Integer, List<int[]>> destMap;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] s = bf.readLine().split(" ");
        H = Integer.parseInt(s[0]);
        W = Integer.parseInt(s[1]);
        map = new int[H][W];
        N = Integer.parseInt(s[2]);
        destMap = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            destMap.put(i, new ArrayList<>());
        }
        for (int i = 0; i < H; i++) {
            String readLine = bf.readLine();
            map[i] = Arrays.asList(readLine.split("")).stream()
                    .mapToInt(str -> str.equals("#") ? -1 : Integer.parseInt(str)).toArray();
            for (int j = 0; j < readLine.length(); j++) {
                int v = map[i][j];
                if (v > 0) {
                    List<int[]> pairs = destMap.get(v);
                    int[] ints = {i, j};
                    pairs.add(ints);
                    destMap.put(v, pairs);
                }
            }
        }
        bf.close();

        int sum = 0;
        int sx = 0;
        int sy = 0;
        for (int i = 1; i <= N; i++) {
            initTour(sx, sy);
            BFS(sx, sy);
            int[] minArr = getMinArray(i);
            sy = minArr[0];
            sx = minArr[1];
            sum += tour[sy][sx];
        }

        System.out.println(sum + "회");
    }

    static int[] getMinArray(int i) {
        List<int[]> pairs = destMap.get(i);
        int min = Integer.MAX_VALUE;
        int[] minArr = null;
        for (int[] pair : pairs) {
            int y = pair[0];
            int x = pair[1];
            int dist = tour[y][x];
            if (dist < min) {
                min = dist;
                minArr = pair;
            }
        }
        return minArr;
    }
    private static void initTour(int sx, int sy) {
        tour = new int[H][W];
        for (int j = 0; j < H; j++) {
            for (int k = 0; k < W; k++) {
                tour[j][k] = Integer.MAX_VALUE;
            }
        }
        tour[sy][sx] = 0;
    }

    static void BFS(int sx, int sy) {
        LinkedList<int[]> queue = new LinkedList<>();
        int[] xy = {sx, sy};
        queue.add(xy);
        while (!queue.isEmpty()) {
            xy = queue.poll();
            int x = xy[0];
            int y = xy[1];
            int currentValue = (x == sx && y == sy) ? 0 : tour[y][x];
            int next = x + 1;
            checkAndProcess(queue, currentValue, y, next, next < W);
            next = x - 1;
            checkAndProcess(queue, currentValue, y, next, next >= 0);
            next = y + 1;
            checkAndProcess(queue, currentValue, next, x, next < H);
            next = y - 1;
            checkAndProcess(queue, currentValue, next, x, next >= 0);
        }
    }

    private static void checkAndProcess(LinkedList<int[]> queue, int currentValue, int y, int next, boolean b) {
        if (b && map[y][next] != -1 && currentValue + 1 < tour[y][next])
            refreshAndAdd(queue, currentValue, next, y);
    }

    private static void refreshAndAdd(LinkedList<int[]> queue, int currentValue, int x, int y) {
        tour[y][x] = currentValue + 1;
        int[] xy = {x, y};
        queue.add(xy);
    }
}

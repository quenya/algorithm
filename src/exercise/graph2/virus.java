package exercise.graph2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.stream.IntStream;

public class virus {
    static int N;
    static int S;
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        visited = new boolean[N + 1];
        S = sc.nextInt();
        sc.close();

        BFS(S, 0);

        int count = (int) IntStream.range(1, N + 1).filter(i -> !visited[i]).count();
        System.out.println(count);
    }

    static void BFS(int start, int cnt) {
        Queue<int[]> q = new LinkedList<>();
        int[] data = {start, cnt};
        q.add(data);
        visited[data[0]] = true;

        while (!q.isEmpty()) {
            int[] current = q.poll();

            int[] mul = {current[0] * 2, current[1] + 1};
            int[] div = {current[0] / 3, current[1] + 1};

            if (mul[0] <= N && !visited[mul[0]]) {
                q.add(mul);
                visited[mul[0]] = true;
            }
            if (div[0] <= N && !visited[div[0]]) {
                q.add(div);
                visited[div[0]] = true;
            }
        }
    }

}

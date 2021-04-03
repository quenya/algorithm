package exercise.graph3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class dijkstra {
    static int N;
    static int M;
    static List<Integer>[] map;
    static int[] D;
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        map = new List[N];
        D = new int[N];
        visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            map[i] = new ArrayList<>();
            D[i] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < M; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            map[a].add(b);
            map[b].add(a);
        }
        sc.close();

        dijk(0, N - 1);

        System.out.println(D[N - 1]);
    }

    static void dijk(int s, int e) {
        D[s] = 0;
        visited[s] = true;

        for (int i = 0; i < N; i++) {
            int index = 0;
            int min = Integer.MAX_VALUE;

            for (int j = 0; j < N; j++) {
                if (!visited[j] && min > D[j]) {
                    index = j;
                    min = D[j];
                }
            }

            visited[index] = true;

            for (int j = 0; j < map[index].size(); j++) {
                Integer node = map[index].get(j);

                if (D[node] > D[index] + 1)
                    D[node] = D[index] + 1;
            }
        }
    }
}

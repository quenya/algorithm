package exercise.graph3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class party {
    static int N;
    static int M;
    static int S;
    static List<Integer>[] map;
    static List<Integer>[] cost;
    static int[] D;
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        S = sc.nextInt();
        map = new List[N+1];
        cost = new List[N+1];
        D = new int[N+1];
        visited = new boolean[N+1];
        for (int i = 1; i <= N; i++) {
            map[i] = new ArrayList<>();
            cost[i] = new ArrayList<>();
            D[i] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < M; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            map[a].add(b);
            cost[a].add(c);
        }
        sc.close();

        int sum = 0;
        for (int i = 1; i <= N; i++) {
            if (i != S)
                sum += dijk(S, i);
        }
        for (int i = 1; i <= N; i++) {
            if (i != S)
                sum += dijk(i, S);
        }

        System.out.println(sum);
    }

    static int dijk(int s, int e) {
        init();
        D[s] = 0;
        visited[s] = true;

        int index = s;
        for (int i = 1; i <= N; i++) {
            int min = Integer.MAX_VALUE;

            for (int j = 1; j <= N; j++) {
                if (!visited[j] && min > D[j]) {
                    index = j;
                    min = D[j];
                }
            }

            visited[index] = true;

            for (int j = 0; j < map[index].size(); j++) {
                Integer node = map[index].get(j);
                Integer cost1 = cost[index].get(j);

                if (D[node] > D[index] + cost1)
                    D[node] = D[index] + cost1;
            }
        }
        return D[e];
    }

    static void init() {
        for (int i = 1; i <= N; i++) {
            D[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }
    }
}

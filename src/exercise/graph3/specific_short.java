package exercise.graph3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class specific_short {
    static int N;
    static int M;
    static int A;
    static int B;
    static List<Integer>[] map;
    static List<Integer>[] cost;
    static int[] D;
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
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
            map[b].add(a);
            cost[a].add(c);
            cost[b].add(c);
        }
        A = sc.nextInt();
        B = sc.nextInt();
        sc.close();

        int a1 = dijk(1, A);
        for (int i = 1; i <= N; i++) {
            D[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }
        int a2 = dijk(A, B);
        for (int i = 1; i <= N; i++) {
            D[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }
        int a3 = dijk(B, N);

        System.out.println(a1 + a2 + a3);
    }

    static int dijk(int s, int e) {
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
            if (index == e) break;

            for (int j = 0; j < map[index].size(); j++) {
                Integer node = map[index].get(j);
                Integer cost1 = cost[index].get(j);

                if (D[node] > D[index] + cost1)
                    D[node] = D[index] + cost1;
            }
        }
        return D[e];
    }
}

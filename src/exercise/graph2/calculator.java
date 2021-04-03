package exercise.graph2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class calculator {
    static int N;
    static int result;
    static boolean[] visited;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        visited = new boolean[99999];
        result = 10000;
        sc.close();

        if (N == 0)
            System.out.println(1);
        else if (N == 1)
            System.out.println(0);
        else
        {
            BFS(2, 1, N);
            System.out.println(result);
        }
    }

    static void BFS(int start, int cnt, int goal) {
        Queue<int[]> q = new LinkedList<>();
        int[] data = {start, cnt};
        q.add(data);

        while (!q.isEmpty()) {
            int[] current = q.poll();

            if (current[0] == goal) {
                result = current[1];
                return;
            }

            int[] mul = {current[0]*2, current[1]+1};
            int[] div = {current[0]/3, current[1]+1};

            if (mul[1] > result || div[1] > result) continue;

            if (mul[0] <= 99999 && !visited[mul[0]]) {
                q.add(mul);
                visited[mul[0]] = true;
            }
            if (div[0] <= 99999 && !visited[div[0]]) {
                q.add(div);
                visited[div[0]] = true;
            }
        }
    }
}

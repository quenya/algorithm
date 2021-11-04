package boj.problems;

import java.io.*;
import java.util.*;

public class Main_2056_1 {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./src/boj/problems/Main_2056.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        List<List<Integer>> arr = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            arr.add(new ArrayList<>());
        }
        int[] indegree = new int[N + 1];
        int[] time = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            int count = Integer.parseInt(st.nextToken());

            for (int j = 0; j < count; j++) {
                int next = Integer.parseInt(st.nextToken());
                arr.get(next).add(i);
                indegree[i]++;
            }
        }

        bw.write(topologicalSort(N, arr, indegree, time) + "");
        br.close();
        bw.close();
    }

    private static int topologicalSort(int N, List<List<Integer>> arr, int[] indegree, int[] time) {
        Queue<Integer> q = new LinkedList<>();
        int[] result = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            result[i] = time[i];
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }
        while (!q.isEmpty()) {
            int curr = q.poll();

            for (int next : arr.get(curr)) {
                indegree[next]--;
                result[next] = Math.max(result[next], result[curr] + time[next]);
                if (indegree[next] == 0) {
                    q.add(next);
                }
            }
        }

        int ans = 0;
        for (int i = 1; i <= N; i++) {
            ans = Math.max(ans, result[i]);
        }
        return ans;
    }
}

package blog_study;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_2188 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[] cowArr;
    static int[] areaArr;
    static boolean[] visited;
    static List<Integer>[] aList;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        cowArr = new int[N+1];
        visited = new boolean[N+1];
        int M = Integer.parseInt(st.nextToken());
        areaArr = new int[M+1];
        aList = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            aList[i] = new ArrayList<>();
        }
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            for (int j = 0; j < S; j++) {
                aList[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        int ans = 0;
        for (int i = 1; i <= N; i++) {
            if (cowArr[i] == 0)
            {
                visited = new boolean[N+1];
                if (binaryMatch(i)) ans++;
            }
        }
        bw.write(ans + "");
        bw.close();
        br.close();
    }
    static boolean binaryMatch(int a) {
        visited[a] = true;
        for (int b : aList[a]) {
            if (areaArr[b] == 0 || !visited[areaArr[b]] && binaryMatch(areaArr[b])) {
                cowArr[a] = b;
                areaArr[b] = a;
                return true;
            }
        }
        return false;
    }
}

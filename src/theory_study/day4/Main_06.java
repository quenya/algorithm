package theory_study.day4;

import java.io.*;
import java.util.StringTokenizer;

// 인디언 합창단
public class Main_06 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[] root;
    static int[] num;
    static int cnt_team = 0;
    static int cnt_person = 26;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine().trim());
        root = new int[27];
        num = new int[27];
        for (int i = 0; i < 26; i++) {
            root[i] = i;
            num[i] = 1;
        }
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            char a = st.nextToken().charAt(0);
            char b = st.nextToken().charAt(0);
            union(a - 'A' + 1, b - 'A' + 1);
        }
        bw.write(cnt_team + "\n" + cnt_person);
        bw.close();
        br.close();
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x==y) return;
        // 1. team + team : team -1
        if (num[x] >= 2 && num[y] >= 2)
            cnt_team--;
            // 2. team + person : person -1
        else if (num[x] >= 2 || num[y] >= 2)
            cnt_person--;
            // 3. person + persion : team +1, person -2
        else {
            cnt_team++;
            cnt_person -= 2;
        }
        root[y] = x;
        num[x] += num[y];
    }

    static int find(int x) {
        if (root[x] == x)
            return x;
        return find(root[x]);
    }
}

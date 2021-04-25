package theory_study.day4;

import java.io.*;
import java.util.Objects;
import java.util.StringTokenizer;

// 동전 던지기
public class Main_08_1 {
    static class Pos {
        int y;
        int x;

        public Pos(int y, int x) {
            this.y = y;
            this.x = x;
        }

        public boolean isParent() {
            Pos parent = root[this.y][this.x];
            return this == parent;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pos pos = (Pos) o;
            return y == pos.y &&
                    x == pos.x;
        }

        @Override
        public int hashCode() {
            return Objects.hash(y, x);
        }
    }

    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static boolean[][] map;
    static Pos[][] root;
    static Pos[][] parents;
    static int[][] sum_group;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int height = Integer.parseInt(st.nextToken());
        int width = Integer.parseInt(st.nextToken());
        map = new boolean[height][width];
        for (int i = 0; i < height; i++) {
            String line = br.readLine();
            for (int j = 0; j < width; j++) {
                map[i][j] = line.charAt(j) == '0' ? true : false;
            }
        }
        parents = new Pos[height][width];
        // 점수별 group 생성
        int ans = 0;
        int Q = Integer.parseInt(br.readLine().trim());
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int a = Integer.parseInt(st.nextToken());
            if (a == 1) {
                // add coin
                int y = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());
                map[y][x] = true;
            } else if (a == 2) {
                // how many k area
                int k = Integer.parseInt(st.nextToken());
                makeSumGroup(height, width);
                int[] sum = getSumArr(height, width);
                ans += sum[k];
            }
        }
        bw.write(ans + "");
        bw.close();
        br.close();
    }

    private static void makeSumGroup(int height, int width) {
        // 초기화
        root = new Pos[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                root[i][j] = new Pos(i, j);
            }
        }
        sum_group = new int[height][width];
        // union
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                boolean check = false;
                for (int k = 0; k < 4; k++) {
                    int ny = i + dy[k];
                    int nx = j + dx[k];
                    if (ny >= 0 && ny < height && nx >= 0 && nx < width && map[i][j] == map[ny][nx]) {
                        union(i, j, ny, nx);
                        check = true;
                    }
                }
                if (!check)
                    union(i, j, i, j);
            }
        }
    }

    private static int[] getSumArr(int height, int width) {
        int[] sum = new int[height*width+1];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                sum[sum_group[i][j]]++;
            }
        }
        return sum;
    }

    static boolean union(int y1, int x1, int y2, int x2) {
        if (y1==y2&&x1==x2) {
            root[y1][x1] = new Pos(y1, x1);
            sum_group[y1][x1] = getScore(y1, x1);
            return true;
        }
        Pos p1 = find(y1, x1);
        Pos p2 = find(y2, x2);
        if (p1 == p2)
            return false;
        // 1. team+team : merge sum
        if (sum_group[p1.y][p1.x] >= 2 && sum_group[p2.y][p2.x] >= 2)
            sum_group[p1.y][p1.x] += sum_group[p2.y][p2.x];
            // 2. person+team : add person(coin/no coin)
        else if (sum_group[p1.y][p1.x] >= 2 || sum_group[p2.y][p2.x] >= 2)
            sum_group[p1.y][p1.x] += getScore(p2.y, p2.x);
            // 3. person+person : merge person and create team
        else {
            sum_group[p1.y][p1.x] = (getScore(p1.y, p1.x)) + (getScore(p2.y, p2.x));
        }
        root[y2][x2] = p1;
        return true;
    }

    private static int getScore(int y, int x) {
        return map[y][x] ? 2 : 1;
    }

    static Pos find(int y, int x) {
        if (parents[y][x] != null) return parents[y][x];
        if (root[y][x].isParent())
            return root[y][x];
        return parents[y][x] = find(root[y][x]);
    }

    static Pos find(Pos p) {
        return find(p.y, p.x);
    }
}

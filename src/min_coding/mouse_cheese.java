package min_coding;

import java.util.Scanner;

public class mouse_cheese {
    static int[][] map;
    static int y;
    static int x;
    static int n;
    static int m;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        y = scanner.nextInt();
        x = scanner.nextInt();
        n = scanner.nextInt();
        m = scanner.nextInt();
        System.out.println(get_dist(0, 0, y, x) + get_dist(y, x, n, m));

        scanner.close();
    }

    static int get_dist(int sy, int sx, int ey, int ex) {
        map = new int[][]{
                {0, 0, 0, 0, -1},
                {-1, 0, -1, 0, 0},
                {0, 0, 0, 0, -1}
        };

        travel(map, sy, sx, ey, ex);
        return map[ey][ex];
    }

    private static void travel(int[][] m, int cy, int cx, int dy, int dx) {
        if (cy == dy && cx == dx) return;

        if (cy -1 >= 0 && m[cy-1][cx] == 0) {
            m[cy-1][cx] = m[cy][cx] + 1;
            travel(m, cy-1, cx, dy, dx);
        }
        if (cy+1 <= 2 && m[cy+1][cx] == 0) {
            m[cy+1][cx] = m[cy][cx] + 1;
            travel(m, cy+1, cx, dy, dx);
        }
        if (cx -1 >= 0 && m[cy][cx-1] == 0) {
            m[cy][cx-1] = m[cy][cx] + 1;
            travel(m, cy, cx-1, dy, dx);
        }
        if (cx +1 <= 4 && m[cy][cx+1] == 0) {
            m[cy][cx+1] = m[cy][cx] + 1;
            travel(m, cy, cx+1, dy, dx);
        }
    }
}

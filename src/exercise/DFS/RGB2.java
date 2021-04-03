package exercise.DFS;

import java.util.Scanner;

class RGB2 {
    static char[][] array = new char[101][101];
    static char[][] visited = new char[101][101];
    static int[] x_dir = {-1, 1, 0, 0};
    static int[] y_dir = {0, 0, -1, 1};
    static char[] rgb = {'R', 'G', 'B'};
    static int N;
    static int count = 0;
    static int count2 = 0;

    public static void main(String[] args) throws java.lang.Exception {
        Scanner scan = new Scanner(System.in);

        N = scan.nextInt();
        String str;

        int i, j;
        for (i = 0; i < N; i++) {
            str = scan.next();
            for (j = 0; j < N; j++) {
                array[i][j] = str.charAt(j);
            }
        }
        int k;
        for (k = 0; k < 3; k++) {
            for (i = 0; i < N; i++) {
                for (j = 0; j < N; j++) {
                    if (visited[i][j] == 0 && array[i][j] == rgb[k]) {
                        DFS(i, j, rgb[k]);
                        count++;
                    }
                }
            }
        }
        for (i = 0; i < N; i++) {
            for (j = 0; j < N; j++) {
                if (array[i][j] == 'G') array[i][j] = 'R';
                visited[i][j] = 0;
            }
        }
        for (k = 0; k < 3; k++) {
            for (i = 0; i < N; i++) {
                for (j = 0; j < N; j++) {
                    if (visited[i][j] == 0 && array[i][j] == rgb[k]) {
                        DFS(i, j, rgb[k]);
                        count2++;
                    }
                }
            }
        }

        System.out.println(count + " " + count2);

    }

    static void DFS(int x, int y, char c) {
        visited[x][y] = 1;
        int i;
        int next_x, next_y;

        for (i = 0; i < 4; i++) {
            next_x = x + x_dir[i];
            next_y = y + y_dir[i];

            if (next_x >= 0 && next_x < N && next_y >= 0 && next_y < N) {
                if (visited[next_x][next_y] == 0 && array[next_x][next_y] == c) {
                    DFS(next_x, next_y, c);
                }
            }
        }
    }
}
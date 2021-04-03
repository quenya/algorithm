package exercise.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Vilage {
    static int N;
    static int map[][];
    static List<Integer> countList;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextLine().charAt(0) - 48;
        map = new int[N+1][N+1];
        countList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String line = scanner.nextLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = line.charAt(j) - 48;
            }
        }
        scanner.close();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] > 0) {
                    int count = DFS(i, j, 0);
                    countList.add(count);
                }
            }
        }

        System.out.println(countList.size());
        countList.stream().sorted().forEach(System.out::println);
    }

    static int DFS(int x, int y, int count) {
        count++;
        map[x][y] = 0;

        int i = x-1;
        int j = y;
        if (i>=0 && map[i][j] > 0) count = DFS(i, j, count);
        i = x;
        j = y-1;
        if (j>=0 && map[i][j] > 0) count = DFS(i, j, count);
        i = x+1;
        j = y;
        if (i<N && map[i][j] > 0) count = DFS(i, j, count);
        i=x;
        j=y+1;
        if (j<N && map[i][j] > 0) count = DFS(i, j, count);

        return count;
    }
}

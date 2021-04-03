package exercise.graph;

import java.util.*;

public class twoColor {
    static int V;
    static int E;
    static List<Integer> map[];
    static boolean visited[];
    static int colors[];
    static boolean avail = true;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        V = sc.nextInt();
        E = sc.nextInt();
        map = new List[V + 1];
        colors = new int[V+1];
        for (int i = 0; i < V; i++) {
            map[i] = new ArrayList<>();
        }
        visited = new boolean[V + 1];
        for (int i = 0; i < E; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            map[a].add(b);
            map[b].add(a);
        }
        sc.close();

        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        BFS(queue, 1);

        System.out.println(avail? "YES" : "NO");
    }

    static void BFS(Queue<Integer> parent, int color) {
        if (avail == false)
            return;

        Queue<Integer> children = new LinkedList<>();

        while (parent.isEmpty() == false) {
            Integer pop = parent.poll();
            if (colors[pop] != 0 && colors[pop] != color) {
                avail = false;
                return;
            }
            colors[pop] = color;
            for (int i = 0; i < map[pop].size(); i++) {
                if (colors[map[pop].get(i)] == 0)
                    children.offer(map[pop].get(i));
                else if (colors[map[pop].get(i)] == color) {
                    avail = false;
                    return;
                }
            }
        }

        if (children.isEmpty() == false)
            BFS(children, color == 1? 2 : 1);

        if (avail == false)
            return;
    }

}

package min_coding.G3.lv22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class bgtk {
    static class Node {
        char name;
        int depth;
        Node b;
        Node g;
        Node t;
        Node k;
    }

    static int level;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        level = Integer.parseInt(br.readLine().trim());
        br.close();
        Node node = addNode('0', 0);
        search(node, new ArrayList<>());
    }

    static void search(Node node, ArrayList<String> path) {
        if (node.depth >= level) {
            path.forEach(s -> System.out.printf("%s", s));
            System.out.printf("%s", node.name);
            System.out.println();
            return;
        }
        if (node.depth > 0)
            path.add(String.valueOf(node.name));
        search(node.b, path);
        search(node.g, path);
        search(node.t, path);
        search(node.k, path);
        if (node.depth > 0)
            path.remove(path.size() - 1);
    }

    static Node addNode(char name, int depth) {
        Node node = new Node();
        node.name = name;
        node.depth = depth;
        if (depth >= level)
            return node;
        node.b = addNode('B', depth + 1);
        node.g = addNode('G', depth + 1);
        node.t = addNode('T', depth + 1);
        node.k = addNode('K', depth + 1);
        return node;
    }
}

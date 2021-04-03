package min_coding.G3.lv22;

import java.util.LinkedList;

public class dfs {
    static class Node {
        char name;
        int depth;
        Node left;
        Node mid;
        Node right;
    }
    public static void main(String[] args) {
        Node node = addNode('0', 0);
        search(node, new LinkedList<>());
    }
    static void search(Node node, LinkedList<String> path) {
        if (node.depth == 2) {
            System.out.printf("%s%s\n", path.peek(), node.name);
            return;
        }
        path.push(String.valueOf(node.name));
        search(node.left, path);
        search(node.mid, path);
        search(node.right, path);
        path.poll();
    }
    static Node addNode(char name, int depth) {
        Node node = new Node();
        node.name = name;
        node.depth = depth;
        if (depth >= 2)
            return node;
        node.left = addNode('A', depth+1);
        node.mid = addNode('B', depth+1);
        node.right = addNode('C', depth+1);
        return node;
    }
}

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int[] tree;
    static int NN;
    static int LIMIT = 1_000_001;
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        // machine index
        int[] machineIdx = new int[LIMIT];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            machineIdx[Integer.parseInt(st.nextToken())] = i;
        // init tree
        NN = 1;
        while (NN < LIMIT) NN *= 2;
        tree = new int[NN*2];
        // update index tree & calculate
        st = new StringTokenizer(br.readLine());
        long ans = 0;
        for (int i = 0; i < N; i++) {
            int machine = Integer.parseInt(st.nextToken());
            updateTree(NN+machineIdx[machine], 1);
            if (machineIdx[machine] + 2 > NN) continue;
            int query = query(1, 1, NN, machineIdx[machine] + 2, NN);
            ans += query;
        }
        bw.write(ans + "");

        br.close();
        bw.close();
    }
    static void updateTree(int node, int value) {
        tree[node] += value;
        while (node > 1) {
            node /= 2;
            tree[node] = tree[node*2] + tree[node*2+1];
        }
    }
    static int query(int node, int l, int r, int ql, int qr) {
        if (r < ql || qr < l) return 0;
        if (ql <= l && r <= qr) return tree[node];
        int mid = (l + r)/2;
        return query(node*2, l, mid, ql, qr) + query(node*2+1, mid+1, r, ql, qr);
    }
}

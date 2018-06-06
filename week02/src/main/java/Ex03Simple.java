import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Ex03Simple{

    private int [] parent;
    private int [] rank;
    private int maxRank = 0;

    private void makeSet(int x, int initialSize) {
        parent[x] = x;
        rank[x] = initialSize;
    }

    private int findSet(int x) {
        if (x == parent[x]) return parent[x];
        return parent[x] = findSet(parent[x]);
    }

    private void unionSets(int dest, int source) {
        dest = findSet(dest);
        source = findSet(source);
        if (dest != source) {
            parent[source] = dest;
            rank[dest] += rank[source];
            rank[source] = 0;
            maxRank = Integer.max(maxRank, rank[dest]);
        }
    }

    void solve() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(tokenizer.nextToken());
        int m = Integer.parseInt(tokenizer.nextToken());

        parent = new int[n];
        rank = new int[n];

        tokenizer = new StringTokenizer(in.readLine());
        for (int i = 0; i < n ; i++) {
            int initialSize = Integer.parseInt(tokenizer.nextToken());
            makeSet(i, initialSize);
            maxRank = Integer.max(maxRank, initialSize);
        }

        for (int i = 0; i < m; i++) {
            tokenizer = new StringTokenizer(in.readLine());
            int dest = Integer.parseInt(tokenizer.nextToken()) - 1;
            int source = Integer.parseInt(tokenizer.nextToken()) - 1;
            unionSets(dest, source);
            System.out.println(maxRank);
        }
    }

    public static void main(String[] args) throws IOException {
        new Ex03Simple().solve();
    }
}

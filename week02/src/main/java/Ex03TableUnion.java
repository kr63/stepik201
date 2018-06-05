import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Ex03TableUnion {
    public static void main(String[] args) throws IOException {
        new Ex03TableUnion().run();
    }

    void run() throws IOException {
        // read number of table & operations
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk1 = new StringTokenizer(in.readLine());
        int numTable = Integer.parseInt(stk1.nextToken());
        int numOperation = Integer.parseInt(stk1.nextToken());

        // read table initial sizes and find maximum one
        StringTokenizer stk2 = new StringTokenizer(in.readLine());
        int[] tableSizes = new int[numTable];
        int maxSize = Integer.MIN_VALUE;
        for (int i = 0; i < numTable; i++) {
            int value = Integer.parseInt(stk2.nextToken());
            if (value > maxSize) maxSize = value;
            tableSizes[i] = value;
        }

        Table table = new Table(maxSize, tableSizes);
        table.makeSet(numTable);

        for (int i = 0; i < numOperation; i++) {
            StringTokenizer stk3 = new StringTokenizer(in.readLine());
            int destination = Integer.parseInt(stk3.nextToken());
            int source = Integer.parseInt(stk3.nextToken());

            table.union(destination - 1, source - 1);
            System.out.println(table.maxSize);
        }
    }
}

class Table {
    int maxSize;
    int[] parent;
    int[] rank;
    int[] tableSizes;

    Table(int maxSize, int[] tableSizes) {
        this.maxSize = maxSize;
        this.tableSizes = tableSizes;
    }

    void union(int i, int j) {
        int i_id = find(i);
        int j_id = find(j);
        if (i_id == j_id) return;
        if (rank[j_id] > rank[i_id]) {
            parent[i_id] = j_id;
            tableSizes[j_id] +=  tableSizes[i_id];
            tableSizes[i_id] = 0;
            maxSize = Math.max(maxSize, tableSizes[j_id]);
        } else {
            parent[j_id] = i_id;
            if (rank[j_id] == rank[i_id]) rank[i_id] += 1;
            tableSizes[i_id] +=  tableSizes[j_id];
            tableSizes[j_id] = 0;
            maxSize = Math.max(maxSize, tableSizes[i_id]);
        }
    }

    void makeSet(int numTable) {
        int[] parent = new int[numTable];
        int[] rank = new int[numTable];
        for (int i = 0; i < numTable; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
        this.parent = parent;
        this.rank = rank;
    }

    int find(int i) {
//        while (i != parent[i]) i = parent[i];
//        return i;
        if (i != parent[i]) {
            parent[i] = find(parent[i]);
        }
        return parent[i];
    }
}

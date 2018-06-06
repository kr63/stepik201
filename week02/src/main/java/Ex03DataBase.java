import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Ex03DataBase {

    int maxRows = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        new Ex03DataBase().run();
    }

    void run() throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());
        int tableCount = Integer.parseInt(tokenizer.nextToken());
        int operationsCount = Integer.parseInt(tokenizer.nextToken());

        // create array of tables
        Table[] tables = new Table[tableCount];

        // find the maximum rows number & create tables
        tokenizer = new StringTokenizer(in.readLine());
        for (int i = 0; i < tableCount; i++) {
            int rowsNumber = Integer.parseInt(tokenizer.nextToken());
            maxRows = Integer.max(maxRows, rowsNumber);
            tables[i] = new Table(rowsNumber);
        }

        for (int i = 0; i < operationsCount; i++) {
            tokenizer = new StringTokenizer(in.readLine());
            int destination = Integer.parseInt(tokenizer.nextToken());
            int source = Integer.parseInt(tokenizer.nextToken());
            union(tables[destination - 1], tables[source - 1]);
            System.out.println(maxRows);
        }
    }

    void union(Table t1, Table t2) {
        Table rootT1 = t1.find();
        Table rootT2 = t2.find();
        if (rootT1 == rootT2) return;
        if (rootT2.rank > rootT1.rank) { // high rank --> parent
            rootT1.parent = rootT2;
            rootT2.rowsNumber += rootT1.rowsNumber;
            rootT1.rowsNumber = 0;
            maxRows = Math.max(maxRows, rootT2.rowsNumber);
        } else { // low rank --> child
            rootT2.parent = rootT1;
            if (rootT2.rank == rootT1.rank) rootT1.rank += 1;
            rootT1.rowsNumber += rootT2.rowsNumber;
            rootT2.rowsNumber = 0;
            maxRows = Math.max(maxRows, rootT1.rowsNumber);
        }
    }

}

class Table {
    int rowsNumber;
    Table parent;
    int rank;

    Table(int rowsNumber) {
        this.rowsNumber = rowsNumber;
        rank = 0;
        parent = this;
    }

    Table find() {
        Table root = this;
        while (root != root.parent) {
            root = root.parent;
        }
        return root;
    }
}

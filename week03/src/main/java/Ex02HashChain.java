import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

class Ex02HashChain {
    public static void main(String[] args) throws IOException {
        new Ex02HashChain().run();
    }

    void run() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(in.readLine());
        int baskets = Integer.parseInt(stk.nextToken());
        HashTable hashTable = new HashTable(baskets);

        stk = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(stk.nextToken());
        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(in.readLine());
            String command = stk.nextToken();
            switch (command) {
                case "add":
                    hashTable.add(stk.nextToken());
                    break;
                case "find":
//                    if (hashTable.find(stk.nextToken())) System.out.println("yes");
//                    else System.out.println("no");
                    hashTable.find(stk.nextToken());
                    break;
                case "del":
                    hashTable.del(stk.nextToken());
                    break;
                case "check":
                    hashTable.check(Integer.parseInt(stk.nextToken()));
                    break;
            }
        }
    }
}

class HashTable {
    private int baskets;
    private List<List<String>> hashTable = new ArrayList<>();

    HashTable(int baskets) {
        this.baskets = baskets;
        for (int i = 0; i < baskets; i++) {
            hashTable.add(new LinkedList<>());
        }
    }

    int hash(String s) {
        double hash = 0;
        byte[] bytes = s.getBytes(StandardCharsets.US_ASCII);
        for (int i = 0; i < s.length(); i++) hash += (bytes[i] * Math.pow(263, i));
        return (int) (hash % 1_000_000_007) % baskets;
    }

    void add(String s) {
        int hash = hash(s);
        if (!find(s)) hashTable.get(hash).add(s);
    }

    void del(String s) {
        if (find(s)) hashTable.get(hash(s)).remove(0);
    }

    boolean find(String s) {
        return hashTable.get(hash(s)).contains(s);
    }

    void check(int i) {
        StringBuilder sb = new StringBuilder();
        for (String s : hashTable.get(i)) {
            sb.insert(0, s + " ");
        }
        System.out.println(sb.toString().trim());
    }
}

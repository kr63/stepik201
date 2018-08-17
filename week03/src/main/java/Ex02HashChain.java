import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
        long hash = 0;
        int prime = 1_000_000_007;
        int multiplier = 263;
        for (int i = s.length() - 1; i >= 0; --i)
            hash = (hash * multiplier + s.charAt(i)) % prime;
        return (int)hash % baskets;
    }

    void add(String s) {
        List<String> list = hashTable.get(hash(s));
        if (!list.contains(s)) list.add(0, s);
    }

    void del(String s) {
        List<String> list = hashTable.get(hash(s));
        list.remove(s);
    }

    void find(String s) {
        List<String> list = hashTable.get(hash(s));
        if (list.contains(s)) System.out.println("yes");
        else System.out.println("no");
    }

    void check(int i) {
        StringBuilder sb = new StringBuilder();
        for (String s : hashTable.get(i)) {
            sb.append(s).append(" ");
        }
        System.out.println(sb.toString().trim());
    }
}

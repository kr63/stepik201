import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Ex04 {
    public static void main(String[] args) throws IOException {
        System.out.println(new Ex04().run());
    }

    int run() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk1 = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(stk1.nextToken());
        int e = Integer.parseInt(stk1.nextToken());
        int d = Integer.parseInt(stk1.nextToken());

        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < e; i++) {
            StringTokenizer stk2 = new StringTokenizer(in.readLine());
            int e_i = Integer.parseInt(stk2.nextToken());
            int e_j = Integer.parseInt(stk2.nextToken());
            parent[e_j - 1] = parent[e_i - 1];
        }

        for (int i = 0; i < d; i++) {
            StringTokenizer stk3 = new StringTokenizer(in.readLine());
            int d_i = Integer.parseInt(stk3.nextToken());
            int d_j = Integer.parseInt(stk3.nextToken());
            if (parent[d_i - 1] == parent[d_j - 1]) {
                return 0;
            }
        }
        return 1;
    }
}

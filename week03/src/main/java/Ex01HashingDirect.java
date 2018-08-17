import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Ex01HashingDirect {

    // TODO: 6/9/18 ArrayIndexOutOfBoundsException: 911
    // write hash function
    private String[] directMap = new String[10_000_000];

    public static void main(String[] args) throws IOException {
        new Ex01HashingDirect().run();
    }

    void run() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(stk.nextToken());
        directMap = new String[n];
        int key;
        String value;
        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(in.readLine());
            String s = stk.nextToken();
            switch (s) {
                case "add":
                    key = Integer.parseInt(stk.nextToken());
                    value = stk.nextToken();
                    directMap[key] = value;
                    break;
                case "find":
                    key = Integer.parseInt(stk.nextToken());
                    value = directMap[key];
                    if (value != null) System.out.println(value);
                    else System.out.println("not found");
                    break;
                case "del":
                    key = Integer.parseInt(stk.nextToken());
                    directMap[key] = null;
                    break;
            }
        }
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

class Ex01HashingSimple {

    private Map<Integer, String> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        new Ex01HashingSimple().run();
    }

    void run() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(stk.nextToken());
        int key;
        String value;
        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(in.readLine());
            String s = stk.nextToken();
            switch (s) {
                case "add":
                    key = Integer.parseInt(stk.nextToken());
                    value = stk.nextToken();
                    map.put(key, value);
                    break;
                case "find":
                    key = Integer.parseInt(stk.nextToken());
                    value = map.get(key);
                    if (value != null) System.out.println(value);
                    else System.out.println("not found");
                    break;
                case "del":
                    key = Integer.parseInt(stk.nextToken());
                    map.remove(key);
                    break;
            }
        }
    }
}

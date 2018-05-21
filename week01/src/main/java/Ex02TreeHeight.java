import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Ex02TreeHeight {

    private Map<Integer, Node> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        in.readLine();
        String input = in.readLine();
        new Ex02TreeHeight().run(input);
    }

    void run(String in) {

        String[] tokens = in.split(" ");

        for (int key = 0; key < tokens.length; key++) {
            int parentKey = Integer.parseInt(tokens[key]);
            Node child = new Node();
            child.setKey(key);

            Node parent = map.getOrDefault(parentKey, new Node());
            parent.setKey(parentKey);
            parent.getChildes().add(child);
            map.put(parentKey, parent);
        }
        System.out.println(getHeight(map.get(-1)));
    }

    private int getHeight(Node root) {
        int height = 1;

        for (Node child : root.getChildes()) {
            height = Math.max(height, getHeight(child) + 1);
        }
        return height;
    }

    class Node {
        private Integer key;
        private List<Node> childes = new ArrayList<>();

        public Integer getKey() {
            return key;
        }

        void setKey(Integer key) {
            this.key = key;
        }

        List<Node> getChildes() {
            return childes;
        }

        public void setChildes(List<Node> childes) {
            this.childes = childes;
        }
    }
}

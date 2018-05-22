import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Ex02TreeHeight {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        in.readLine();
        String input = in.readLine();
        new Ex02TreeHeight().run(input);
    }

    void run(String in) {
        Tree tree = new Tree();
        String[] tokens = in.split(" ");
        for (int i = 0; i < tokens.length; i++) {
            int key = Integer.parseInt(tokens[i]);
            Node node = tree.nodeMap.getOrDefault(i, new Node(i));
            if (key != -1) {
                tree.nodeMap.putIfAbsent(i, node);
                Node parent = tree.nodeMap.getOrDefault(key, new Node(key));
                tree.nodeMap.putIfAbsent(key, parent);
                parent.getChildes().add(node);
            } else {
                tree.root = node;
                tree.nodeMap.put(i, node);
            }
        }
        System.out.println(tree.getHeight(tree.root));
    }

    class Tree {
        Node root;
        Map<Integer, Node> nodeMap = new HashMap<>();

        /**
         * Return height of the subtree with root at node
         * @param node root of the subtree
         * @return the height of the subtree
         */
        int getHeight(Node node) {
            int height = 1;
            for (Node child : node.getChildes()) {
                height = Math.max(height, 1 + getHeight(child));
            }
            return height;
        }
    }

    class Node {
        private int key;
        private List<Node> childes = new ArrayList<>();

        Node(int key) {
            this.key = key;
        }

        List<Node> getChildes() {
            return childes;
        }
    }
}

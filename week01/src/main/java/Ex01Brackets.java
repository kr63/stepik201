import java.util.*;

class Ex01Brackets {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        new Ex01Brackets().run(input);
    }

    void run(String input) {
        Deque<Character> value = new ArrayDeque<>();
        Deque<Integer> index = new ArrayDeque<>();
        Map<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');
        char inputChar;

        for (int i = 0; i < input.length(); i++) {
            inputChar = input.charAt(i);
            if (map.containsKey(inputChar)) {
                value.push(inputChar);
                index.push(i);
                continue;
            }
            if (map.containsValue(inputChar)) {
                if (value.isEmpty()) {
                    index.push(i);
                    break;
                }
                if (map.get(value.pop()) == inputChar) index.pop();
                else {
                    index.push(i);
                    break;
                }
            }
        }

        if (value.isEmpty() && index.isEmpty()) System.out.println("Success");
        else System.out.println(index.pop() + 1);
    }
}

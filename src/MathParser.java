import java.util.*;

public class MathParser {
    final List<String> OPERATIONS = Arrays.asList("^", "/", "*", "-", "+", "(", ")");
    final List<String> FUNCTIONS = Arrays.asList("sin", "cos", "tg", "ctg", "sqrt", "abs", "lg", "ln", "log");

    private String transformString(String str) {
        str = str.toLowerCase();
        if (str.charAt(0) == '-')
            str = "0" + str;
        str = str.replaceAll("\\(-", "(0-").replaceAll(",", " ");
        for (String func: FUNCTIONS) {
            str = str.replaceAll(func, " " + func + " " );
        }
        for (String op: OPERATIONS) {
            str = str.replaceAll(String.format("\\%s", op), " " + op + " " );
        }
        str = str.replaceAll("e", " " + Math.E + " ").replaceAll("pi", " " + Math.PI + " ");
        str = str.replaceAll("\\s+", " ").trim();
        return str;
    }

    private Queue<String> infixToPostfix(String str) {
        String[] data = str.split("\\s");
        Queue<String> output = new ArrayDeque<>();
        Stack<String> stack = new Stack<>();
        Queue<String> input = new ArrayDeque<>(Arrays.asList(data));

        while (!input.isEmpty()) {
            String cur = input.poll();
            if (!(OPERATIONS.contains(cur) || FUNCTIONS.contains(cur))) {
                output.add(cur);
            } else if (stack.isEmpty() || cur.equals("(") || FUNCTIONS.contains(cur) || OPERATIONS.indexOf(stack.peek()) > OPERATIONS.indexOf(cur)) {
                stack.push(cur);
            } else {
                if (cur.equals(")")) {
                    while (!stack.peek().equals("(")) {
                        output.add(stack.pop());
                    }
                    stack.pop();
                    if (!stack.isEmpty() && FUNCTIONS.contains(stack.peek())) {
                        output.add(stack.pop());
                    }
                    continue;
                }
                while (!stack.isEmpty() && OPERATIONS.indexOf(stack.peek()) <= OPERATIONS.indexOf(cur)) {
                    output.add(stack.pop());
                }
                stack.push(cur);
            }
        }
        while (!stack.isEmpty()) {
            output.add(stack.pop());
        }
        return output;
    }

    public double calculate(String str) {
        Queue<String> queue = infixToPostfix(transformString(str));
        Stack<String> stack = new Stack<>();
        while (!queue.isEmpty()) {
            String cur = queue.poll();
            if (!(OPERATIONS.contains(cur) || FUNCTIONS.contains(cur))) {
                stack.push(cur);
            } else if (OPERATIONS.contains(cur)){
                String result = null;
                double b = Double.parseDouble(stack.pop());
                double a = Double.parseDouble(stack.pop());
                switch (cur) {
                    case "+":
                        result = String.valueOf(a + b);
                        break;
                    case "-":
                        result = String.valueOf(a - b);
                        break;
                    case "*":
                        result = String.valueOf(a * b);
                        break;
                    case "/":
                        result = String.valueOf(a / b);
                        break;
                    case "^":
                        result = String.valueOf(Math.round(Math.pow(a,b)));
                        break;
                }
                stack.push(result);
            } else if (FUNCTIONS.contains(cur)){
                String result = null;
                double arg = Double.parseDouble(stack.pop());
                switch (cur) {
                    case "sin":
                        result  = String.valueOf(Math.sin(arg));
                        break;
                    case "cos":
                        result  = String.valueOf(Math.cos(arg));
                        break;
                    case "tg":
                        result  = String.valueOf(Math.tan(arg));
                        break;
                    case "ctg":
                        result  = String.valueOf(1.0/Math.tan(arg));
                        break;
                    case "sqrt":
                        result  = String.valueOf(Math.sqrt(arg));
                        break;
                    case "abs":
                        result  = String.valueOf(Math.abs(arg));
                        break;
                    case "lg":
                        result  = String.valueOf(Math.log10(arg));
                        break;
                    case "ln":
                        result  = String.valueOf(Math.log(arg));
                        break;
                    case "log":
                        double base = Double.parseDouble(stack.pop());
                        result  = String.valueOf(Math.log(arg) / Math.log(base));
                        break;
                }
                stack.push(result);
            } else {
                System.out.println("Incorrect input");
            }
        }
        return Double.parseDouble(stack.pop());
    }
}

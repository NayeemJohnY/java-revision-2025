package com.java_revision;

import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Stack;

class MinStack {

    Stack<Integer> mainStack;
    Stack<Integer> minStack;

    public MinStack() {
        mainStack = new Stack<>();
        minStack = new Stack<>();
    }

    public Integer push(int item) {
        if (minStack.isEmpty() || item <= minStack.peek())
            minStack.push(item);
        return mainStack.push(item);
    }

    public Integer pop() {
        int removed = mainStack.pop();
        if (!minStack.isEmpty() && minStack.peek() == removed)
            minStack.pop();
        return removed;
    }

    public Integer getMin() {
        if (minStack.isEmpty()) throw new EmptyStackException();
        return minStack.peek();
    }

    public Integer peek() {
        return mainStack.peek();
    }

    @Override
    public synchronized String toString() {
        return "Actual Stack: " + mainStack.toString() + " Min Stack: " + minStack.toString();
    }

}

class UndoRedoFeature {
    Stack<String> stack;
    Stack<String> redoStack;

    public UndoRedoFeature() {
        stack = new Stack<>();
        redoStack = new Stack<>();
    }

    public void typing(String s) {
        stack.push(s);
    }

    public String undo() {
        if (!stack.isEmpty()) {
            String value = stack.pop();
            redoStack.push(value);
            return value;
        }
        return "Nothing to Undo";
    }

    public String redo() {
        if (!redoStack.isEmpty()) {
            String value = redoStack.pop();
            stack.push(value);
            return value;
        }
        return "Nothing to Redo";
    }

    public String currentText() {
        return String.join(" ", stack);
    }

    @Override
    public String toString() {
        return "Undo Stack: " + stack.toString() + " Redo Stack: " + redoStack.toString();
    }
}

public class CollectionsStack {

    public boolean balancedParentheses(String expression) {
        Stack<Character> stack = new Stack<>();
        expression = expression.replaceAll("[^()\\[\\]{}]", "");
        HashMap<Character, Character> mappings = new HashMap<>() {
            {
                put('}', '{');
                put(')', '(');
                put(']', '[');
            }
        };

        for (Character character : expression.toCharArray()) {
            if (mappings.containsValue(character)) {
                stack.add(character);
            } else if (stack.isEmpty() || stack.pop() != mappings.get(character)) {
                return false;
            }
        }
        return stack.isEmpty();
    }

    public void evaluvatePostfixExpression(String expression) {
        Stack<Double> stack = new Stack<>();
        for (String s : expression.split("\\s+")) {
            if (s.matches("\\d+")) {
                stack.add(Double.parseDouble(s));
            } else {
                if (stack.size() < 2) {
                    throw new IllegalArgumentException("Invalid expression: not enough operands for operator " + s);
                }                
                double b = stack.pop();
                double a = stack.pop();
                double result = 0;
                switch (s) {
                    case "+":
                        result = a + b;
                        break;
                    case "-":
                        result = a - b;
                        break;
                    case "*":
                        result = a * b;
                        break;
                    case "/":
                        result = a / b;
                        break;
                    case "%":
                        result = a % b;
                        break;
                    default:
                        throw new IllegalArgumentException("Unsupported operator: " + s);
                }
                stack.add(result);
            }
        }
        System.out.println(stack);
    }

    public void reverseStringUsingStack(String str){
        Stack<Character> stack = new Stack<>();
        for (Character character : str.toCharArray()) {
            stack.push(character);
        }
        StringBuilder builder = new StringBuilder();
        while (!stack.isEmpty())
            builder.append(stack.pop());
        System.out.println(builder.toString());
    }

    public static void main(String[] args) {

        // Problem 1
        CollectionsStack collectionsStack = new CollectionsStack();
        boolean isValid = collectionsStack.balancedParentheses("x * (a + b) - {y / [z - 3]}");
        System.out.println(isValid);
        isValid = collectionsStack.balancedParentheses("x * (a + b)) - {y / [z - 3]}");
        System.out.println(isValid);
        isValid = collectionsStack.balancedParentheses("(x * (a + b) - {y / [z - 3]}");
        System.out.println(isValid);
        isValid = collectionsStack.balancedParentheses("a^2 + b^2 = c^2");
        System.out.println(isValid);

        // Problem 2
        collectionsStack.evaluvatePostfixExpression("1 4 5 + *");
        collectionsStack.evaluvatePostfixExpression("5 3 +");
        collectionsStack.evaluvatePostfixExpression("10 2 8 * +");
        collectionsStack.evaluvatePostfixExpression("4 2 5 * + 1 3 2 * + /");
        collectionsStack.evaluvatePostfixExpression("6 2 / 3 - 4 2 * +");
        collectionsStack.evaluvatePostfixExpression("7 8 + 3 2 + /");
        collectionsStack.evaluvatePostfixExpression("15 7 1 1 + - / 3 * 2 1 1 + + -");

        // Problem 3
        MinStack testMinStack = new MinStack();
        testMinStack.push(10);
        testMinStack.push(20);
        testMinStack.push(30);
        testMinStack.push(1);
        testMinStack.push(-1);
        testMinStack.push(-3);
        testMinStack.push(12);
        testMinStack.push(3);
        System.out.println(testMinStack);
        System.out.println(testMinStack.pop());
        System.out.println(testMinStack.pop());
        System.out.println(testMinStack.pop());
        System.out.println(testMinStack);

        // Problem 4 - Reverse a string using a stack.
        collectionsStack.reverseStringUsingStack( "Java programming test");

        // Problem 5: Undo feature using stack (simulate typing and undo).
        UndoRedoFeature undoRedoFeature = new UndoRedoFeature();
        undoRedoFeature.typing("Hello");
        undoRedoFeature.typing("Welcome");
        undoRedoFeature.typing("Good");
        System.out.println(undoRedoFeature.currentText());
        System.out.println(undoRedoFeature.toString());
        System.out.println(undoRedoFeature.undo());
        System.out.println(undoRedoFeature.toString());
        System.out.println(undoRedoFeature.undo());
        System.out.println(undoRedoFeature.toString());
        System.out.println(undoRedoFeature.currentText());
        System.out.println(undoRedoFeature.redo());
        System.out.println(undoRedoFeature.toString());
        System.out.println(undoRedoFeature.undo());
        System.out.println(undoRedoFeature.toString());
        System.out.println(undoRedoFeature.redo());
        System.out.println(undoRedoFeature.currentText());
        System.out.println(undoRedoFeature.toString());
        System.out.println(undoRedoFeature.redo());
        System.out.println(undoRedoFeature.toString());
    }

}

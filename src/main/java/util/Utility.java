package util;

import domain.LinkedStack;
import domain.Stack;
import domain.StackException;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.Random;
import java.time.LocalDate;

public class Utility {
    private static Random random;

    //constructor estatico, inicializador estatico
    static {
        // semilla para el random
        long seed = System.currentTimeMillis();
        random = new Random(seed);

    }

    public static int random(int bound){
        //return (int)Math.floor(Math.random()*bound); //forma 1
        return 1+random.nextInt(bound);
    }

    public static void fill(int[] a) {
        for (int i = 0; i < a.length; i++) {
            a[i] = random(99);
        }
    }

    public static String format(long n) {
        return new DecimalFormat("###,###,###.##").format(n);
    }

    public static int min(int x, int y) {
        return x<y ? x : y;
    }

    public static int max(int x, int y) {
        return x>y ? x : y;
    }

    public static String show(int[] a) {
        String result="";
        for(int item : a){
            if(item == 0) break; //si es cero es xq no hay mas elementos
            result+=item + " ";
        }
        return result;
    }

    public static int compare(Object a, Object b) {
        switch(instanceOf(a, b)){
            case "Integer":
                Integer int1 = (Integer)a; Integer int2 = (Integer)b;
                return int1 < int2 ? -1 : int1 > int2 ? 1 : 0;

            case "String":
                String str1 = (String)a; String str2 = (String)b;
                return str1.compareTo(str2) < 0 ? -1 : str1.compareTo(str2) > 0 ? 1 : 0;

            case "Character":
                Character ch1 = (Character) a; Character ch2 = (Character) b;
                return ch1.compareTo(ch2) < 0 ? -1 : ch1.compareTo(ch2) > 0 ? 1 : 0;

        }
        return 2; //Unknown
    }

    public static String instanceOf(Object a, Object b){
        if(a instanceof Integer && b instanceof Integer) return "Integer";
        if(a instanceof String && b instanceof String) return "String";
        if(a instanceof Character && b instanceof Character) return "Character";

        return "Unknown";
    }

    public static String dateFormat(Date value) {
        return new SimpleDateFormat("dd/MM/yyyy").format(value);
    }

    public static int getAge(Date birthDay) {
        LocalDate birth = birthDay.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate now = LocalDate.now();
        return Period.between(birth, now).getYears();
    }

    public static String infixToPostfixConverter(String exp) throws StackException {
        LinkedStack stack = new LinkedStack();
        String expPostFix = "";
        for(char c : exp.toCharArray()) {
            if (Character.isLetterOrDigit(c))
                expPostFix += c; //lo agregamos a la exp postfija
            else if (c == '(')
                stack.push(c);
            else if (c == ')') {
                while (!stack.isEmpty() && compare(stack.peek(), '(') != 0)
                    expPostFix += stack.pop();
                if (!stack.isEmpty() && compare(stack.top(), '(') != 0)
                    return "Invalid expression";
                else if (!stack.isEmpty())
                    stack.pop();
            } else { //es un operador
                while (!stack.isEmpty() && getPriority(c) <= getPriority((char) stack.peek()))
                    expPostFix += stack.pop();
                stack.push(c);
            }
        }
        while(!stack.isEmpty())
            expPostFix+=stack.pop();
        return expPostFix;
    }

    private static int getPriority(char operator) {
        switch (operator){
            case '+': case '-': return 1; //prioridad más baja
            case '*': case '/': return 2;
            case '^': return 3; //prioridad más alta
        }
        return -1;
    }

    public static Boolean isBalanced(Stack stack, String expression) throws StackException{

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if(c=='('||c=='{'||c=='['){
                stack.push(c);
            } else if (c==')'||c=='}'||c==']') {
                if(stack.isEmpty()){
                    return false;
                }
                char c1 = (char) stack.pop();

                if ((c == ')' && c1 != '(') ||
                        (c == '}' && c1 != '{') ||
                        (c == ']' && c1 != '[')) {
                    return false;
                }
            }

        }

        return stack.isEmpty();
    }

    //Conversor de Decimal a Binario
    public static String decimalToBinary(int number) throws StackException {
        if (number == 0) {
            return "0";
        }
        LinkedStack stack = new LinkedStack();

        while (number > 0) {
            int remainder = number % 2;
            stack.push(remainder);
            number = number / 2;
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop().toString());
        }
        return sb.toString();
    }

    //Conversor de Decimal a Octal
    public static String decimalToOctal(int number) throws StackException {
        if (number == 0) {
            return "0";
        }
        LinkedStack stack = new LinkedStack();

        while (number > 0) {
            int remainder = number % 8;
            stack.push(remainder);
            number = number / 8;
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop().toString());
        }
        return sb.toString();
    }

    //Conversor de Decimal a Hexadecimal
    public static String decimalToHexadecimal(int number) throws StackException {
        if (number == 0) {
            return "0";
        }
        LinkedStack stack = new LinkedStack();

        while (number > 0) {
            int remainder = number % 16;
            if (remainder < 10) {
                stack.push(remainder);
            } else {
                stack.push((char) ('A' + (remainder - 10)));
            }
            number = number / 16;
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            Object item = stack.pop();
            if (item instanceof Integer) {
                sb.append(item.toString());
            } else if (item instanceof Character) {
                sb.append(item);
            }
        }
        return sb.toString();
    }
}

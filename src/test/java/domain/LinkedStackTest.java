package domain;

import org.junit.jupiter.api.Test;
import util.Utility;

import static org.junit.jupiter.api.Assertions.*;

class LinkedStackTest {

    @Test
    void test(){

        try {
            System.out.println("\nIs Balanced:");
            System.out.println(this.isBalanced("({[]})"));
            System.out.println(this.isBalanced("([])"));
            System.out.println(this.isBalanced("([)]"));
            System.out.println(this.isBalanced("((()))"));
            System.out.println(this.isBalanced("{[}"));
            System.out.println(this.isBalanced("]"));
            System.out.println(this.isBalanced(""));
        } catch (StackException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testBaseConverter() throws StackException {
        int[] numbers = {0, 3, 4, 5, 6, 7, 9, 10, 15, 17, 23, 32, 255, 1023, 1025, 4192, 8586};
        System.out.println("\nDecimal to Binary Conversion:");
        for (int value : numbers)
            System.out.println("Decimal: " + value + " → Binary: " + util.Utility.decimalToBinary(value));

        System.out.println("\nDecimal to Octal Conversion:");
        for (int value : numbers)
            System.out.println("Decimal: " + value + " → Octal: " + util.Utility.decimalToOctal(value));

        System.out.println("\nDecimal to Hexadecimal Conversion:");
        for (int value : numbers)
            System.out.println("Decimal: " + value + " → Hexadecimal: " + util.Utility.decimalToHexadecimal(value));
    }

    private boolean isBalanced(String expression) throws StackException {
        return Utility.isBalanced(new LinkedStack(), expression);
    }
}
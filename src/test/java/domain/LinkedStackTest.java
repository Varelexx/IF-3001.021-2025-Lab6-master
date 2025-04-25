package domain;

import org.junit.jupiter.api.Test;
import util.Utility;

import static org.junit.jupiter.api.Assertions.*;

class LinkedStackTest {

    @Test
    void test(){

        try {
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
    void testDecimalToBinary() throws StackException {
        int[] numbers = {0, 3, 4, 5, 6, 7, 9, 10, 15, 17, 23, 32, 255, 1023, 1025, 4192, 8586};
        System.out.println("Decimal to Binary Conversion:");
        for (int value : numbers)
            System.out.println("Decimal: " + value + " → Binary: " + util.Utility.decimalToBinary(value));
    }

    private boolean isBalanced(String expression) throws StackException {
        return Utility.isBalanced(new LinkedStack(), expression);
    }
}
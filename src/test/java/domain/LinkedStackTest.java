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

    private boolean isBalanced(String expression) throws StackException {
        return Utility.isBalanced(new LinkedStack(), expression);
    }
}
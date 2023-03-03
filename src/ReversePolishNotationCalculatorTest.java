import java.util.ArrayDeque;
import java.util.Deque;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ReversePolishNotationCalculatorTest {

    ReversePolishNotationCalculator calculator = new ReversePolishNotationCalculator();
    @Test
    public void shouldCalculateAddition() {
        int result = calculator.calculatePolishNotation("1 2 +");
        assertEquals(3,result);
    }

    @Test
    public void shouldCalculateDecreasing() {
        int result = calculator.calculatePolishNotation("1 2 -");
        assertEquals(-1,result);
    }

    @Test
    public void shouldCalculateMultiplication() {
        int result = calculator.calculatePolishNotation("1 2 *");
        assertEquals(2,result);
    }

}

class ReversePolishNotationCalculator {

    public int calculatePolishNotation(String str) {
        String[] parts = str.split(" ");
        Deque<Integer> numbers = new ArrayDeque<>();
        int index = 0;

        while (index != parts.length) {

            if (parts[index].isBlank()) {
                index++;
                continue;
            }

            if (isOperation(parts[index])) {
                int operandOne = numbers.pop();
                int operandTwo = numbers.pop();

                switch (parts[index]) {
                    case "+":
                        numbers.push(operandOne + operandTwo);
                        break;
                    case "-":
                        numbers.push(operandTwo - operandOne);
                        break;
                    case "*":
                        numbers.push(operandOne * operandTwo);
                        break;
                }

            } else {
                numbers.push(Integer.parseInt(parts[index]));
            }

            index++;
        }

        return numbers.pop();
    }

    private boolean isOperation(String part) {
        if (part.equals("+")
                || part.equals("-")
                || part.equals("*")) {
            return true;
        }

        return false;
    }
}

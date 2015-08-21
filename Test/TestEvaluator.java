/**
 * Created by stshakun on 09.08.15.
 */

import net.sourceforge.jeval.EvaluationException;
import net.sourceforge.jeval.Evaluator;
import junit.framework.TestCase;
//import org.junit.*;



public class TestEvaluator extends TestCase {
    static Evaluator calculator = new Evaluator();

    //@Test
    public static void testExceptions() throws EvaluationException {
        try {
            calculator.evaluate("7/0");
        } catch (EvaluationException e) {
            assertEquals("Divide by zero exception", "Infinite", e.getMessage());
        }

        try {
            calculator.evaluate("power(1,2)");
        } catch (EvaluationException e) {
            assertEquals("A function is not defined (index=5).", e.getMessage());
        }

        try {
            calculator.evaluate(")");
        } catch (EvaluationException e) {
            assertEquals("Expression is invalid.", e.getMessage());
        }

    }

    //@Test
    public static void testCorrectExceptions() throws EvaluationException {
        assertEquals("4.0", calculator.evaluate("4"));
        assertEquals("-4.0", calculator.evaluate("-4"));
        assertEquals("5.0", calculator.evaluate("4 + 1"));
        assertEquals("3.0", calculator.evaluate("4 + -1"));
        assertEquals("0.2", calculator.evaluate("0.2"));
        assertEquals("1.6", calculator.evaluate("1.2 + 0.4"));
        assertEquals("1.6", calculator.evaluate("1.2 + .4"));
        assertEquals("-0.2", calculator.evaluate("0.2 - 0.4"));
        assertEquals("6.0", calculator.evaluate("2 - -4"));
        assertEquals("-3.0", calculator.evaluate("-4 + 1"));
        assertEquals("-5.0", calculator.evaluate("-4 + -1"));
        assertEquals("3.0", calculator.evaluate("4 - 1"));
        assertEquals("-3.0", calculator.evaluate("1 - 4"));
        assertEquals("12.0", calculator.evaluate("4 * 3"));
        assertEquals("-12.0", calculator.evaluate("4 * -3"));
        assertEquals("12.0", calculator.evaluate("-4 * -3"));
        assertEquals("2.0", calculator.evaluate("4 / 2"));
        assertEquals("0.5", calculator.evaluate("2 / 4"));
        assertEquals("-2.0", calculator.evaluate("4 / -2"));
        assertEquals("1.0", calculator.evaluate("7 % 2"));
        assertEquals("1.0", calculator.evaluate("7 % -2"));
        assertEquals("14.0", calculator.evaluate("4 * 3 + 2"));
        assertEquals("10.0", calculator.evaluate("4 + 3 * 2"));
        assertEquals("16.0", calculator.evaluate("4 / 2 * 8"));
        assertEquals("4.0", calculator.evaluate("(4)"));
        assertEquals("-4.0", calculator.evaluate("(-4)"));
        assertEquals("-4.0", calculator.evaluate("-(4)"));
        assertEquals("4.0", calculator.evaluate("-(-4)"));
        assertEquals("4.0", calculator.evaluate("-(-(4))"));
        assertEquals("7.0", calculator.evaluate("(4 + 3)"));
        assertEquals("-6.0", calculator.evaluate("-(3 + 3)"));
        assertEquals("4.0", calculator.evaluate("(3) + 1"));
        assertEquals("2.0", calculator.evaluate("(3) - 1"));
        assertEquals("14.0", calculator.evaluate("(4 + 3) * 2"));
        assertEquals("13.0", calculator
                .evaluate("4 + (3 + 1) + (3 + 1) + 1"));
        assertEquals("14.0", calculator.evaluate("((4 + 3) * 2)"));
        assertEquals("42.0", calculator.evaluate("((4 + 3) * 2) * 3"));
        assertEquals("-42.0", calculator.evaluate("((4 + 3) * -2) * 3"));
        assertEquals("-2.0", calculator.evaluate("((4 + 3) * 2) / -7"));
        assertEquals("16.0", calculator.evaluate("(4 / 2) * 8"));
        assertEquals("0.25", calculator.evaluate("4 / (2 * 8)"));
        assertEquals("1.0", calculator.evaluate("(4 * 2) / 8"));
        assertEquals("1.0", calculator.evaluate("4 * (2 / 8)"));
        assertEquals("16.0", calculator.evaluate("(4 / (2) * 8)"));
        assertEquals("-4.0", calculator.evaluate("-(3 + -(3 - 4))"));
        assertEquals("9.0", calculator.evaluate("pow(3,2)"));
        assertEquals("6.0", calculator.evaluate("min(min(9,8),min(7,6))"));

    }


}

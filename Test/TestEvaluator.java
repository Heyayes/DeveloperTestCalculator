/**
 * Created by stshakun on 09.08.15.
 */

import net.sourceforge.jeval.EvaluationException;
import net.sourceforge.jeval.Evaluator;
import junit.framework.TestCase;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.*;


public class TestEvaluator extends TestCase {
    static Evaluator evaluator = new Evaluator();

    @Test
    public static void testEvaluator() throws EvaluationException {

        assertEquals("4.0", evaluator.evaluate("4"));
        assertEquals("-4.0", evaluator.evaluate("-4"));
        assertEquals("5.0", evaluator.evaluate("4 + 1"));
        assertEquals("3.0", evaluator.evaluate("4 + -1"));
        assertEquals("0.2", evaluator.evaluate("0.2"));
        assertEquals("1.6", evaluator.evaluate("1.2 + 0.4"));
        assertEquals("1.6", evaluator.evaluate("1.2 + .4"));
        assertEquals("0.6000000000000001", evaluator
                .evaluate("0.2 + 0.4"));
        assertEquals("-0.2", evaluator.evaluate("0.2 - 0.4"));
        assertEquals("6.0", evaluator.evaluate("2 - -4"));
        assertEquals("-3.0", evaluator.evaluate("-4 + 1"));
        assertEquals("-5.0", evaluator.evaluate("-4 + -1"));
        assertEquals("3.0", evaluator.evaluate("4 - 1"));
        assertEquals("-3.0", evaluator.evaluate("1 - 4"));
        assertEquals("12.0", evaluator.evaluate("4 * 3"));
        assertEquals("-12.0", evaluator.evaluate("4 * -3"));
        assertEquals("12.0", evaluator.evaluate("-4 * -3"));
        assertEquals("2.0", evaluator.evaluate("4 / 2"));
        assertEquals("0.5", evaluator.evaluate("2 / 4"));
        assertEquals("-2.0", evaluator.evaluate("4 / -2"));
        assertEquals("1.0", evaluator.evaluate("7 % 2"));
        assertEquals("1.0", evaluator.evaluate("7 % -2"));
        assertEquals("14.0", evaluator.evaluate("4 * 3 + 2"));
        assertEquals("10.0", evaluator.evaluate("4 + 3 * 2"));
        assertEquals("16.0", evaluator.evaluate("4 / 2 * 8"));
        assertEquals("4.0", evaluator.evaluate("(4)"));
        assertEquals("-4.0", evaluator.evaluate("(-4)"));
        assertEquals("-4.0", evaluator.evaluate("-(4)"));
        assertEquals("4.0", evaluator.evaluate("-(-4)"));
        assertEquals("4.0", evaluator.evaluate("-(-(4))"));
        assertEquals("7.0", evaluator.evaluate("(4 + 3)"));
        assertEquals("-6.0", evaluator.evaluate("-(3 + 3)"));
        assertEquals("4.0", evaluator.evaluate("(3) + 1"));
        assertEquals("2.0", evaluator.evaluate("(3) - 1"));
        assertEquals("14.0", evaluator.evaluate("(4 + 3) * 2"));
        assertEquals("13.0", evaluator
                .evaluate("4 + (3 + 1) + (3 + 1) + 1"));
        assertEquals("14.0", evaluator.evaluate("((4 + 3) * 2)"));
        assertEquals("42.0", evaluator.evaluate("((4 + 3) * 2) * 3"));
        assertEquals("-42.0", evaluator.evaluate("((4 + 3) * -2) * 3"));
        assertEquals("-2.0", evaluator.evaluate("((4 + 3) * 2) / -7"));
        assertEquals("16.0", evaluator.evaluate("(4 / 2) * 8"));
        assertEquals("0.25", evaluator.evaluate("4 / (2 * 8)"));
        assertEquals("1.0", evaluator.evaluate("(4 * 2) / 8"));
        assertEquals("1.0", evaluator.evaluate("4 * (2 / 8)"));
        assertEquals("16.0", evaluator.evaluate("(4 / (2) * 8)"));
        assertEquals("-4.0", evaluator.evaluate("-(3 + -(3 - 4))"));
    }


    @Test(expected = ArithmeticException.class)
    public void divisionWithException() {
        int i = 1 / 0;
    }

/*
    public void testEvaluationExceptionTest() throws EvaluationException{

            evaluator.evaluate("4+");

           /* evaluator.evaluate("4 + ");
            evaluator.evaluate("4 - ");
            evaluator.evaluate("4 + -");
            evaluator.evaluate("--4");
            evaluator.evaluate("4 * / 3");
            evaluator.evaluate("* 3");
            evaluator.evaluate("((4");
            evaluator.evaluate("4 (");
            evaluator.evaluate("(4))");
            evaluator.evaluate("((4 + 3)) * 2)");
            evaluator.evaluate("4 ()");
            evaluator.evaluate("4 (+) 3");

    }*/
}

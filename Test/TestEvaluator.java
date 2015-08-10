/**
 * Created by stshakun on 09.08.15.
 */

import org.junit.*;
import org.junit.Assert.*;
import net.sourceforge.jeval.Evaluator;
import net.sourceforge.jeval.EvaluationException;

public class TestEvaluator extends org.junit.Assert {
    static Evaluator eva = new Evaluator();

    @Test
    public void testEvaluator() {

        try {
            String result = (eva.evaluate("4+3"));
            assertTrue("Resul is " + result + " not equals", result.equals("7.0"));
        } catch (EvaluationException e) {
            e.printStackTrace();
        }
    }


}

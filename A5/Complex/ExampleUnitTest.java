package com.example.pritish.scicalc;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    List<String> exp;
    MainActivity calc;

    @Test
    public void addition_isCorrect() throws Exception {
        exp = new ArrayList<>();
        calc = new MainActivity();
        exp.add(0, "5");
        exp.add(1, "+");
        exp.add(2, "2");
        exp.add(3, "*");
        exp.add(4, "3");
        assertEquals(11.0, Double.parseDouble(calc.evaluate_exp(exp).get(0)), 0.0);
    }
}
package com.httpexample.nakate;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(2.0,MainActivity.getsqrt(4.0),1);
    }

    @Test
    public void sin() throws Exception {
        assertEquals(0.0,MainActivity.getsin(90.0),1);
    }

    @Test
    public void add() throws Exception {
        assertEquals(9.0,MainActivity.getadd(45,45),1);
    }
}
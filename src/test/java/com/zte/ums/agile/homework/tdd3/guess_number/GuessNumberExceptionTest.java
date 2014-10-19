package com.zte.ums.agile.homework.tdd3.guess_number;

import static org.junit.Assert.*;

import java.util.IllegalFormatException;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
@Deprecated
public class GuessNumberExceptionTest {
    private GuessNumber guessNumber;

    @Before
    public void setUp() {
        this.guessNumber =  new GuessNumber();
    }

    @Test(expected = IllegalFormatException.class)
    public void test_guess_should_throws_IllegalFormatException() {
        this.guessNumber.guess("1123");
    }

    @Test(expected = IllegalFormatException.class)
    public void test_guess_should_throws_IllegalFormatException_when_input_nonnumeric() {
        this.guessNumber.guess("abcd");
    }

    @Test(expected = IllegalFormatException.class)
    public void test_guess_should_throws_IllegalFormatException_when_input_null() {
        this.guessNumber.guess(null);
    }

}

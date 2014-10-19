package com.zte.ums.agile.homework.tdd3.guess_number;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.IllegalFormatException;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;

public class GuessNumberSingleTest {
    private GuessNumber guessNumber;

    @Before
    public void setUp() {
        this.guessNumber =  new GuessNumber();
        this.guessNumber.start();

        // 无法同时使用@RunWith Parameterized和PowerMockRunner，这里只有通过反射来修改成员变量以到达mock的目的
        Class clazz = this.guessNumber.getClass();  
        try {  
            Field countField = clazz.getDeclaredField("bingoNumberList");  
            countField.setAccessible(true);//设置该字段可见  
            List<String> bingoNumberList = Lists.newArrayList();
            bingoNumberList.add("6");
            bingoNumberList.add("7");
            bingoNumberList.add("8");
            bingoNumberList.add("9");
            countField.set(this.guessNumber, bingoNumberList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * It is not necessary to test a random method.
     */
    @Ignore
    @Test
    public void test_generate_bingoNumber_should_return_difference_number_when_generate_twice() {
        Class class1 = this.guessNumber.getClass();
        try {
            Method format = class1.getDeclaredMethod("generateBingoNumber");
            format.setAccessible(true);// 设为可见
            List<String> bingoNumber1 = (List) format.invoke(this.guessNumber);
            Thread.sleep(1);
            List<String> bingoNumber2 = (List) format.invoke(this.guessNumber);
            assertThat(Objects.equal(bingoNumber1, bingoNumber2), Matchers.is(false));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_generate_bingoNumber_should_return_same_result_when_guess_twice() {
        assertEquals(this.guessNumber.guess("6789"), this.guessNumber.guess("6789"));
        assertThat(this.guessNumber.guess("6789"), Matchers.is("4A0B"));
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
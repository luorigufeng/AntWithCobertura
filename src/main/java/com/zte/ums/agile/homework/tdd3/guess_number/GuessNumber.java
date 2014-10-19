package com.zte.ums.agile.homework.tdd3.guess_number;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UnknownFormatConversionException;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class GuessNumber {
    private static final String REGEX = "[0-9]{4}";
    private static final String INVAILD_INPUT_TIPS = "输入不正确，请重新输入";
    private List<String> bingoNumberList;

    public String guess(String input) {
        valid(input);

        int countB = countB(input, bingoNumberList);
        int countA = countA(input, bingoNumberList);
        return printResult(countB, countA);
    }

    private void valid(String input) {
        if (input == null) {
            throw new UnknownFormatConversionException(INVAILD_INPUT_TIPS);
        }
        if (input.matches(REGEX) == false) {
            throw new UnknownFormatConversionException(INVAILD_INPUT_TIPS);
        }
        Set<String> inputSet = convertInputString2Set(input);
        if (inputSet.size() != 4) {
            throw new UnknownFormatConversionException(INVAILD_INPUT_TIPS);
        }
    }

    private Set<String> convertInputString2Set(String input) {
        Set<String> inputSet = Sets.newHashSet();
        for (int i = 0; i < input.length(); i++) {
            inputSet.add(String.valueOf(input.charAt(i)));
        }
        return inputSet;
    }

    private int countB(String input, List<String> bingoNumberList) {
        Set<String> inputSet = convertInputString2Set(input);
        for (Iterator<String> iterator = inputSet.iterator(); iterator.hasNext();) {
            String intputOne = iterator.next();
            if (bingoNumberList.contains(intputOne)) {
                iterator.remove();
            }
        }
        int BCount = 4 - inputSet.size();
        return BCount;
    }
    
    private int countA(String input, List<String> bingoNumberList) {
        int countA = 0;
        for (int i = 0; i < bingoNumberList.size(); i++) {
            String bingoOne = bingoNumberList.get(i);
            String inputOnt = String.valueOf(input.charAt(i));
            if (inputOnt.equals(bingoOne)) {
                countA++;
            }
        }
        return countA;
    }

    private String printResult(int countB, int countA) {
        return countA+ "A" + (countB-countA) + "B";
    }

    public void start() {
        bingoNumberList = generateBingoNumber();
    }

    private List<String> generateBingoNumber() {
        Random random = new Random(System.currentTimeMillis());
        List<String> bingoNumberList = Lists.newArrayList();
        while (bingoNumberList.size() != 4) {
            String bingoNumberOne = String.valueOf(Math.abs(random.nextInt() % 10));
            if (bingoNumberList.contains(bingoNumberOne) == false) {
                bingoNumberList.add(bingoNumberOne);
            }
        }
        return bingoNumberList;
    }
}
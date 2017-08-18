package org.sjs;

import org.sjs.test.ISjsKotlin;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by jianshunsun on 2017/7/16.
 */

public class Generator {

    public static void main(String[] args) {
        System.out.println("Hello!");
//        func1();
//        func2();
        func3();
    }

    private static void func1() {
        ISjsKotlin kotlin = new ISjsKotlin();

        kotlin.getSjsName();
        kotlin.setSjsName(null);
    }

    private static void func2() {
        List<Integer> integers = new LinkedList<>();
        for (int index = 0; index < 10; ++index) {
            integers.add(index);
        }

        for (Iterator<Integer> iterator = integers.iterator(); iterator.hasNext(); ) {
            Integer integer = iterator.next();
            if (integer.equals(5)) {
                iterator.remove();
            }
        }

        for (Integer integer : integers) {
            System.out.println(integer + ", ");
        }
    }

    private static void func3() {
        System.out.print(roundUpToPowerOfTwo(4));
    }

    public static int roundUpToPowerOfTwo(int i) {
        i--; // If input is a power of two, shift its high-order bit right.

        // "Smear" the high-order bit all the way to the right.
        i |= i >>>  1;
        i |= i >>>  2;
        i |= i >>>  4;
        i |= i >>>  8;
        i |= i >>> 16;

        return i + 1;
    }
}

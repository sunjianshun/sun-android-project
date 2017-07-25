package org.sjs;

import org.sjs.test.ISjs;
import org.sjs.test.ISjsKotlin;

/**
 * Created by jianshunsun on 2017/7/16.
 */

public class Generator {

    public static void main(String[] args) {
        System.out.println("Hello!");

        ISjsKotlin kotlin = new ISjsKotlin();

        kotlin.getSjsName();
        kotlin.setSjsName(null);
    }
}

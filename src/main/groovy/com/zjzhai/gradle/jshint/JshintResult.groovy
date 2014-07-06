package com.zjzhai.gradle.jshint


/**
 * Created by zjzhai on 7/1/14.
 */
class JshintResult {

    String line;
    String character;
    String reason;
    String evidence;
    String raw;
    String a;
    String b;
    String c;
    String d


    @Override
    public String toString() {
        return "JshintResult{" +
                "line=" + line +
                ", character=" + character +
                ", reason=" + reason +
                ", evidence=" + evidence +
                ", raw=" + raw +
                ", a=" + a +
                ", b=" + b +
                ", c=" + c +
                ", d=" + d +
                '}';
    }
}

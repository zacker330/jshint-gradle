package com.zjzhai.gradle.jshint

public interface Jshint {
    JshintResults verify(String javascriptSource, String option)
}
package com.zjzhai.gradle.jshint

/**
 * Created by zjzhai on 7/1/14.
 */
class JshintResults {

    private List<JshintResult> jshintResultList = new ArrayList<>()

    JshintResults add(JshintResult aResult) {
        jshintResultList.add(aResult)
        return this
    }

    boolean isEmpty() {
        jshintResultList.isEmpty()
    }


    List all() {
        Collections.unmodifiableList(jshintResultList)
    }

    int size() {
        jshintResultList.size()
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder()
        all().each {
            builder.append(String.format("{%s}", it.toString()))
        }
        return builder.toString()
    }
}

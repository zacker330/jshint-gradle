package com.zjzhai.gradle.jshint.internal

import com.zjzhai.gradle.jshint.Jshint
import com.zjzhai.gradle.jshint.JshintResult
import com.zjzhai.gradle.jshint.JshintResults
import com.zjzhai.gradle.jshint.JshintVersion
import groovy.json.JsonSlurper
import org.mozilla.javascript.NativeArray
import org.mozilla.javascript.NativeObject


class JshintRhino implements Jshint {

    private Rhino rhino = new Rhino();

    def JshintRhino(JshintVersion version) {
        rhino.eval(loadJshintFileContent(version.value()))
    }

    def JshintRhino(String jshintjs) {
        // TODO verify the jshintjs
        rhino.eval(jshintjs)
    }


    @Override
    JshintResults verify(String javascriptSource, String option) {
        boolean success = rhino.call("JSHINT", javascriptSource, convertStrOptionToNativeObject(option))
        if (success) {
            return new JshintResults();
        }
        NativeArray errors = rhino.eval("JSHINT.errors");
        return convertBy(errors);
    }

    private JshintResults convertBy(NativeArray errors) {
        JshintResults results = new JshintResults();
        errors.each {
            if (it != null) {
                results.add(convertBy(it))
            }
        }
        return results
    }


    private JshintResult convertBy(Object objResult) {
        NativeObject nativeObject = (NativeObject) objResult;

        JshintResult jshintResult = new JshintResult()

        jshintResult.reason = nativeObject.get("reason")
        jshintResult.line = nativeObject.get("line")
        jshintResult.a = nativeObject.get("a")
        jshintResult.b = nativeObject.get("b")
        jshintResult.c = nativeObject.get("c")
        jshintResult.d = nativeObject.get("d")
        jshintResult.character = nativeObject.get("character")
        jshintResult.evidence = nativeObject.get("evidence")
        jshintResult.raw = nativeObject.get("raw")

        return jshintResult

    }


    private NativeObject convertStrOptionToNativeObject(String option) {
        option = option ? option : "{}"
        Map configJson = new JsonSlurper().parseText(option)
        NativeObject nativeOptions = new NativeObject();
        configJson.entrySet().each {
            nativeOptions.defineProperty(it.key, it.value, NativeObject.READONLY)
        }
        return nativeOptions;

    }


    private String loadJshintFileContent(String version) {
        return getClass().getResourceAsStream("/jshint/jshint-${version.trim()}.js").text
    }
}

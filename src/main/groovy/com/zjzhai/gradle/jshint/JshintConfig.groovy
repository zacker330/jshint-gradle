package com.zjzhai.gradle.jshint

import org.gradle.api.Project

/**
 * Created by zhai on 6/29/14.
 */
class JshintConfig {

    static final DEFAULT_VERSION = "2.4.3"

    def inputDirs

    def includes

    def excludes

    def version = DEFAULT_VERSION

    def getVersion() {
        version = version ? version : DEFAULT_VERSION
        return JshintVersion.get(version)
    }

    static def getOptions(final Project project) {
        def file = project.file("src/main/resources/jshintConfig.json")
        if (!file.exists()) {
            throw new RuntimeException("Not found jshintConfig.json")
        }
        return file.text

    }


}

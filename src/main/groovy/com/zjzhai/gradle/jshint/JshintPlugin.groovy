package com.zjzhai.gradle.jshint

import com.zjzhai.gradle.jshint.internal.JshintRhino
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.logging.Logger


class JshintPlugin implements Plugin<Project> {

    private Project _project
    private Logger logger

    void apply(Project project) {
        project.extensions.create("jshint", JshintConfig)
        logger = project.logger
        _project = project
        def jshintConfig = _project.jshint

        project.task('jshint') << {
            boolean hasError = false
            getPendingJshintFilesByConfig(jshintConfig).each { File file ->
                Jshint jshint = new JshintRhino(jshintConfig.version)
                JshintResults results = jshint.verify(file.text, JshintConfig.getOptions(project))
                println getLoggerText(file.absolutePath, results)
                if (!results.isEmpty() && !hasError) {
                    hasError = true
                }
            }
            if (hasError) {
                throw new RuntimeException("Jshint Failure!")
            }

        }
    }

    private String getLoggerText(String rawFilePath, JshintResults results) {
        StringBuilder builder = new StringBuilder()
        builder.append("File: $rawFilePath \n ------ \n")
        results.all().each {
            builder.append("line:${it.line}, character:${it.character}, reason:${it.reason} \n")
        }
        return builder.toString() + "\n"
    }

    private List getPendingJshintFilesByConfig(JshintConfig config) {
        List pendingJshintFiles = new ArrayList()

        def ant = new AntBuilder()

        def scanner = ant.fileScanner {
            fileset(dir: config.inputDirs) {
                config.includes?.each {
                    include(name: it)
                }
                config.excludes?.each {
                    exclude(name: it)
                }
            }
        }

        for (file in scanner) {
            pendingJshintFiles.add(file)
        }

        return pendingJshintFiles
    }

}
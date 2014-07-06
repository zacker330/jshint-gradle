package com.zjzhai.gradle.jshint
/**
 * Created by zhai on 7/1/14.
 */
public enum JshintVersion {

    V2_4_3("2.4.3"),V2_4_1("2.4.1"),V2_1_9("2.1.9");

    private String version;

    private JshintVersion(String version){
        this.version = version
    }


    static JshintVersion get(String version){
        for(JshintVersion each : JshintVersion.values()) {
            if (each.version == version) {
                return each
            }
        }
    }

     String value(){
        return version
    }

}
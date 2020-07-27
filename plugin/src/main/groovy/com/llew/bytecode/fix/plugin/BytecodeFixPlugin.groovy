package com.llew.bytecode.fix.plugin

import com.android.build.gradle.AppExtension
import com.llew.bytecode.fix.transform.AsmInjectTrans;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class BytecodeFixPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        println "this is a gradle plugin, (*^__^*)……"
        println "getAndroid Success"
        def android = project.extensions.getByType(AppExtension)
        println "getAndroid Success"
        android.registerTransform(new AsmInjectTrans())
    }
}
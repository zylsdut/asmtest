package com.llew.bytecode.fix.classvisitor;

import com.android.ddmlib.Log;
import com.llew.bytecode.fix.transform.AsmMethodVisitor;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class AsmClassVisitor extends ClassVisitor {

    public AsmClassVisitor(int i) {
        super(i);
    }

    public AsmClassVisitor(int i, ClassVisitor classVisitor) {
        super(i, classVisitor);
    }

    @Override
    public MethodVisitor visitMethod(int i, String s, String s1, String s2, String[] strings) {
        System.out.println("==== directoryInput file name == "+ "visitMethod");
        MethodVisitor mv = cv.visitMethod(i,s,s1,s2,strings);
        AsmMethodVisitor asmClassVisitor = new AsmMethodVisitor(Opcodes.ASM5, mv, i, s, s1);
        return asmClassVisitor;
    }

}
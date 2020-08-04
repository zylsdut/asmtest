package com.llew.bytecode.fix.classvisitor;

import com.android.ddmlib.Log;
import com.llew.bytecode.fix.transform.AsmMethodVisitor;


import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.util.Arrays;

public class AsmClassVisitor extends ClassVisitor {

    private String mClassName;
    private String mSuperName;
    private String[] mInterfaces;
    private int mVersion;

    public AsmClassVisitor(int i) {
        super(i);
    }

    public AsmClassVisitor(int i, ClassVisitor classVisitor) {
        super(i, classVisitor);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        mClassName = name;
        mInterfaces = interfaces;
        mSuperName = superName;
        mVersion = version;
        super.visit(version, access, name, signature, superName, interfaces);
    }


    @Override
    public void visitInnerClass(String name, String outerName, String innerName, int access) {
        super.visitInnerClass(name, outerName, innerName, access);
    }

    @Override
    public String toString() {
        return "AsmClassVisitor{" +
                "mClassName='" + mClassName + '\'' +
                ", mSuperName='" + mSuperName + '\'' +
                ", mInterfaces=" + Arrays.toString(mInterfaces) +
                ", mVersion=" + mVersion +
                '}';
    }

    @Override
    public MethodVisitor visitMethod(int i, String s, String s1, String s2, String[] strings) {
        System.out.println("--------------------MethodVisitor = " + Arrays.toString(strings));
        MethodVisitor mv = cv.visitMethod(i,s,s1,s2,strings);
        AsmMethodVisitor asmClassVisitor = new AsmMethodVisitor(Opcodes.ASM5, mv, i, s, s1, mInterfaces);
        return asmClassVisitor;
    }

}
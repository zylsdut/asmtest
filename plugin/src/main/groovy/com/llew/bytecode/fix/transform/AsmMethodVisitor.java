package com.llew.bytecode.fix.transform;

import org.apache.http.util.TextUtils;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Handle;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.AdviceAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.TextUI;

public class AsmMethodVisitor extends AdviceAdapter {

    private String methodName;
    private String methodDes;
    private String[] classInterfaces; // method owmer class interface
    private static List<String> lambdaListenerList = new ArrayList<>();
    private static int count = 0;

    public AsmMethodVisitor(int i, MethodVisitor methodVisitor, int i1, String s, String s1, String[] interfaces) {
        super(i, methodVisitor, i1, s, s1);
        methodName = s;
        methodDes = s1;
        this.classInterfaces = interfaces;
        count++;
        System.out.println("---------------------count = " + count);
    }

    @Override
    public void visitInvokeDynamicInsn(String name, String desc, Handle bsm, Object... bsmArgs) {
        Object[] restArgs = bsmArgs;
        if (restArgs == null || restArgs.length < 2 || !(restArgs[1] instanceof Handle)) {
            super.visitInvokeDynamicInsn(name, desc, bsm, bsmArgs);
        } else {
            Handle handle = (Handle) restArgs[1];
            if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(desc) && !TextUtils.isEmpty(handle.getName()) && !TextUtils.isEmpty(handle.getDesc())) {
                if ("onClick".equals(name) && desc.contains("Landroid/view/View$OnClickListener")) {
                    lambdaListenerList.add(handle.getName());
                }
            }
            super.visitInvokeDynamicInsn(name, desc, bsm, bsmArgs);
        }
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
        super.visitMethodInsn(opcode, owner, name, desc, itf);
    }

    @Override
    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
        System.out.println("visitAnnotation----------------- desc = " + desc);
        return super.visitAnnotation(desc, visible);
    }


    @Override
    public void visitCode() {
        super.visitCode();
    }

    @Override
    protected void onMethodEnter() {
        boolean isClickMethod;
        if (methodName.contains("lambda")) {
            isClickMethod = isLambdaClickFunction();
        } else {
            isClickMethod = isCommonClickFunction();
        }
        if (isClickMethod) {
            //将引用变量推送到栈顶
            mv.visitVarInsn(ALOAD,1);
            //添加方法
            mv.visitMethodInsn(Opcodes.INVOKESTATIC,"com/dafasoft/asmtest/ReClickHelper","clickEnable","()Z",false);
            int clickable = newLocal(Type.BOOLEAN_TYPE);
            mv.visitVarInsn(LSTORE, clickable);
            mv.visitVarInsn(ILOAD, clickable);
            Label l2 = new Label();
            mv.visitJumpInsn(IFEQ, l2);
            mv.visitInsn(RETURN);
            mv.visitLabel(l2);
        }
    }

    private boolean isCommonClickFunction() {
        if (!"onClick".equals(methodName)) {
            return false;
        }
        if (!"(Landroid/view/View;)V".equals(methodDes)) {
            return false;
        }
        if (classInterfaces == null || classInterfaces.length <= 0) {
            return false;
        }
        for (String str : classInterfaces) {
            if (str.equals("android/view/View$OnClickListener")) {
                return true;
            }
        }
        return false;
    }

    private boolean isLambdaClickFunction() {
        int index = -1;
        int size = lambdaListenerList.size();
        if (size <= 0) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (lambdaListenerList.get(i).equals(methodName)) {
                index = i;
            }
        }
        if (index > -1) {
            lambdaListenerList.remove(index);
            return true;
        }
        return false;
    }
}
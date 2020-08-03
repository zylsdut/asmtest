package com.llew.bytecode.fix.transform;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Handle;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.AdviceAdapter;

public class AsmMethodVisitor extends AdviceAdapter {

    private String methodName;
    private String methodDes;
    private String classInfo;

    private static int count = 0;

    public AsmMethodVisitor(int i, MethodVisitor methodVisitor, int i1, String s, String s1, String classInfo) {
        super(i, methodVisitor, i1, s, s1);
        methodName = s;
        methodDes = s1;
        this.classInfo = classInfo;
        count++;
        System.out.println("---------------------count = " + count);
    }

    @Override
    public void visitInvokeDynamicInsn(String name, String desc, Handle bsm, Object... bsmArgs) {
        System.out.println(String.format("----------------visitInvokeDynamicInsn  = %s    %s", name, desc));
        super.visitInvokeDynamicInsn(name, desc, bsm, bsmArgs);
    }

    @Override
    public void invokeDynamic(String name, String desc, Handle bsm, Object... bsmArgs) {
        System.out.println(String.format("----------------invokeDynamic  = %s    %s", name, desc));
        super.invokeDynamic(name, desc, bsm, bsmArgs);
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
        super.visitMethodInsn(opcode, owner, name, desc, itf);
    }



    @Override
    public AnnotationVisitor visitAnnotationDefault() {
        return super.visitAnnotationDefault();
    }

    @Override
    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
        System.out.println("----------------- desc = " + desc);
        return super.visitAnnotation(desc, visible);
    }

    @Override
    protected void onMethodEnter() {
        System.out.println(String.format("onMethodEnter----------- methodName = %s    methodDes = %s   classInfo = %s", methodName, methodDes, classInfo));
        if ("onClick".equals(methodName)&&"(Landroid/view/View;)V".equals(methodDes)){
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
}
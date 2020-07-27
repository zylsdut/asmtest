package com.llew.bytecode.fix.transform;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.AdviceAdapter;

public class AsmMethodVisitor extends AdviceAdapter {

    private String methodName;
    private String methodDes;

    public AsmMethodVisitor(int i, MethodVisitor methodVisitor, int i1, String s, String s1) {
        super(i, methodVisitor, i1, s, s1);
        methodName = s;
        methodDes = s1;
    }

    @Override
    protected void onMethodEnter() {
        System.out.println("------------------------------------onMethodEnter");
        if ("onClick".equals(methodName)&&"(Landroid/view/View;)V".equals(methodDes)){
          //将引用变量推送到栈顶
            mv.visitVarInsn(ALOAD,1);
         //添加方法
            System.out.println("------------------------------------onMethodadd");
            mv.visitMethodInsn(Opcodes.INVOKESTATIC,"com/dafasoft/asmtest/ReClickHelper","clickEnable","()Z",false);
        }
    }
}
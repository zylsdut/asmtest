package com.llew.bytecode.fix.transform;

import org.objectweb.asm.Handle;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
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
    public void visitInvokeDynamicInsn(String name, String desc, Handle bsm, Object... bsmArgs) {
        System.out.println(String.format("visitInvokeDynamicInsn = %s    %s", name, desc));
        super.visitInvokeDynamicInsn(name, desc, bsm, bsmArgs);
    }

    @Override
    protected void onMethodEnter() {
        System.out.println(String.format("methodName = %s methodDesc = %s", methodName, methodDes));
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
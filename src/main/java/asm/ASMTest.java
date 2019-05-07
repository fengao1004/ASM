package asm;

import org.objectweb.asm.*;
import org.objectweb.asm.util.ASMifier;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.objectweb.asm.Opcodes.*;

public class ASMTest {
    public static void main(String[] args) throws Exception {
//        MyClassLoader myClassLoader = new MyClassLoader();
//        Class classByBytes = myClassLoader.getClassByBytes(createNewClass());
//        Object o = classByBytes.newInstance();
//        Field field = classByBytes.getField("age");
//        Object o1 = field.get(o);
//        Method method = classByBytes.getMethod("getAge");
//        Object o2 = method.invoke(o);
//        System.out.println("Field age:  " + o1);
//        System.out.println("Method method :  " + o2);
//        ASMifier.main(new String[]{"asm.Student"});
        ClassReader classReader = new ClassReader(createNewClass());
        ClassWriter classWriter = new ClassWriter(classReader, 0);
        ClassVisitor cv = new MyClassVisitor(classWriter);
        classReader.accept(cv,0);
        MyClassLoader myClassLoader = new MyClassLoader();
        Class classByBytes = myClassLoader.getClassByBytes(classWriter.toByteArray());
        Object o = classByBytes.newInstance();
        Field field = classByBytes.getField("age");
        Object o1 = field.get(o);
        Method method = classByBytes.getMethod("getAge");
        Object o2 = method.invoke(o);
        System.out.println("Field age:  " + o1);
        System.out.println("Method method :  " + o2);
    }

    public static byte[] createNewClass() {
        //����ClassWriter ����������ĺ������Ƿ��Զ�����ջ֡��������ջ���ֲ�������Ĵ�С
        //0����ȫ�ֶ����� ���ֶ�����visitFrame��visitMaxs��ȫ��Ч
        //ClassWriter.COMPUTE_MAXS=1����Ҫ�Լ�����ջ֡��С�������ر�������������Զ�����ã���ȻҲ���Ե���visitMaxs������ֻ�����������ã������ᱻ���ԣ�
        //ClassWriter.COMPUTE_FRAMES=2��ջ֡���ر����Ͳ�����ջ���Զ����㣬����Ҫ����visitFrame��visitMaxs��������ʹ����Ҳ�ᱻ���ԡ�
        //��Щѡ��ǳ����㣬������һ���Ŀ�����ʹ��COMPUTE_MAXS����10%��ʹ��COMPUTE_FRAMES����2����
        ClassWriter cw = new ClassWriter(0);
        //������ͷ����Ϣ��jdk�汾�����η�����ȫ����ǩ����Ϣ�����࣬�ӿڼ�
        cw.visit(Opcodes.V1_8, ACC_PUBLIC, "asm/Student", null, "java/lang/Object", null);
        //�����ֶ�arg�����η��������������ͣ�ǩ����Ϣ����ʼֵ����һ���������ú����˵����
        cw.visitField(ACC_PUBLIC, "age", "I", null, new Integer(11))
                .visitEnd();
        //�������������η��������������ͣ�����������������ͣ���ǩ����Ϣ���׳��쳣����
        // �������߼�ȫ��ʹ��jvmָ������д�ıȽϻ�ɬ���ż��ϸߣ��������ܼ򵥵ķ���
        MethodVisitor mv = cw.visitMethod(ACC_PUBLIC, "getAge", "()I", null, null);
        // ����������һ��
        mv.visitCode();
        // ������Ϊ #0 �ı��ر����б�ӵ�������ջ�¡�#0 �����ı��ر����б���Զ�� this ����ǰ��ʵ�������á�
        mv.visitVarInsn(ALOAD, 0);
        // ��ȡ������ֵ��
        mv.visitFieldInsn(GETFIELD, "asm/Student", "age", "I");
        // ����age
        mv.visitInsn(IRETURN);
        // ���ò�����ջ�ͱ��ر�����Ĵ�С
        mv.visitMaxs(1, 1);
        //������������
        mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
        mv.visitCode();
        mv.visitVarInsn(ALOAD, 0);
        mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
        mv.visitVarInsn(ALOAD, 0);
        mv.visitIntInsn(BIPUSH, 10);
        mv.visitFieldInsn(PUTFIELD, "asm/Student", "age", "I");
        mv.visitInsn(RETURN);
        mv.visitMaxs(2, 1);
        mv.visitEnd();
        //����������
        cw.visitEnd();
        //����class��byte[]����
        return cw.toByteArray();
    }
}

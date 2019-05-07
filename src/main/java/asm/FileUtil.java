package asm;


import java.io.UnsupportedEncodingException;

public class FileUtil {
    public static void main1(String[] args) {
        String str = "package asm;\n" +
                "\n" +
                "import jdk.internal.org.objectweb.asm.util.ASMifier;\n" +
                "import org.objectweb.asm.ClassWriter;\n" +
                "import org.objectweb.asm.MethodVisitor;\n" +
                "import org.objectweb.asm.Opcodes;\n" +
                "\n" +
                "import static org.objectweb.asm.Opcodes.*;\n" +
                "\n" +
                "public class ASMTest {\n" +
                "    public static void main(String[] args) throws Exception {\n" +
                "//        MyClassLoader myClassLoader = new MyClassLoader();\n" +
                "//        Class classByBytes = myClassLoader.getClassByBytes(createNewClass());\n" +
                "//        Object o = classByBytes.newInstance();\n" +
                "//        Field field = classByBytes.getField(\"age\");\n" +
                "//        Object o1 = field.get(o);\n" +
                "//        Method method = classByBytes.getMethod(\"getAge\");\n" +
                "//        Object o2 = method.invoke(o);\n" +
                "//        System.out.println(\"Field age:  \" + o1);\n" +
                "//        System.out.println(\"Method method :  \" + o2);\n" +
                "        ASMifier.main(new String[]{\"asm.Student.java\"});\n" +
                "    }\n" +
                "\n" +
                "    public static byte[] createNewClass() {\n" +
                "        //����ClassWriter ����������ĺ������Ƿ��Զ�����ջ֡��������ջ���ֲ�������Ĵ�С\n" +
                "        //0����ȫ�ֶ����� ���ֶ�����visitFrame��visitMaxs��ȫ��Ч\n" +
                "        //ClassWriter.COMPUTE_MAXS=1����Ҫ�Լ�����ջ֡��С�������ر�������������Զ�����ã���ȻҲ���Ե���visitMaxs������ֻ�����������ã������ᱻ���ԣ�\n" +
                "        //ClassWriter.COMPUTE_FRAMES=2��ջ֡���ر����Ͳ�����ջ���Զ����㣬����Ҫ����visitFrame��visitMaxs��������ʹ����Ҳ�ᱻ���ԡ�\n" +
                "        //��Щѡ��ǳ����㣬������һ���Ŀ�����ʹ��COMPUTE_MAXS����10%��ʹ��COMPUTE_FRAMES����2����\n" +
                "        ClassWriter cw = new ClassWriter(0);\n" +
                "        //������ͷ����Ϣ��jdk�汾�����η�����ȫ����ǩ����Ϣ�����࣬�ӿڼ�\n" +
                "        cw.visit(Opcodes.V1_8, ACC_PUBLIC, \"asm/Student\", null, \"java/lang/Object\", null);\n" +
                "        //�����ֶ�arg�����η��������������ͣ�ǩ����Ϣ����ʼֵ����һ���������ú����˵����\n" +
                "        cw.visitField(ACC_PUBLIC, \"arg\", \"I\", null, new Integer(11))\n" +
                "                .visitEnd();\n" +
                "        //�������������η��������������ͣ�����������������ͣ���ǩ����Ϣ���׳��쳣����\n" +
                "        // �������߼�ȫ��ʹ��jvmָ������д�ıȽϻ�ɬ���ż��ϸߣ��������ܼ򵥵ķ���\n" +
                "        MethodVisitor mv = cw.visitMethod(ACC_PUBLIC, \"getAge\", \"()I\", null, null);\n" +
                "        // ����������һ��\n" +
                "        mv.visitCode();\n" +
                "        // ������Ϊ #0 �ı��ر����б�ӵ�������ջ�¡�#0 �����ı��ر����б���Զ�� this ����ǰ��ʵ�������á�\n" +
                "        mv.visitVarInsn(ALOAD, 0);\n" +
                "        // ��ȡ������ֵ��\n" +
                "        mv.visitFieldInsn(GETFIELD, \"asm/Student\", \"age\", \"I\");\n" +
                "        // ����age\n" +
                "        mv.visitInsn(IRETURN);\n" +
                "        // ���ò�����ջ�ͱ��ر�����Ĵ�С\n" +
                "        mv.visitMaxs(1, 1);\n" +
                "        //������������\n" +
                "        mv.visitEnd();\n" +
                "        //����������\n" +
                "        cw.visitEnd();\n" +
                "        //����class��byte[]����\n" +
                "        return cw.toByteArray();\n" +
                "    }\n" +
                "}\n";
        String charset ="GBK"; //�ٶ������ʽ
        String str1 = "����";
//        try {
//
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
        System.out.println(str);


    }
}

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
                "        //创建ClassWriter ，构造参数的含义是是否自动计算栈帧，操作数栈及局部变量表的大小\n" +
                "        //0：完全手动计算 即手动调用visitFrame和visitMaxs完全生效\n" +
                "        //ClassWriter.COMPUTE_MAXS=1：需要自己计算栈帧大小，但本地变量与操作数已自动计算好，当然也可以调用visitMaxs方法，只不过不起作用，参数会被忽略；\n" +
                "        //ClassWriter.COMPUTE_FRAMES=2：栈帧本地变量和操作数栈都自动计算，不需要调用visitFrame和visitMaxs方法，即使调用也会被忽略。\n" +
                "        //这些选项非常方便，但会有一定的开销，使用COMPUTE_MAXS会慢10%，使用COMPUTE_FRAMES会慢2倍。\n" +
                "        ClassWriter cw = new ClassWriter(0);\n" +
                "        //创建类头部信息：jdk版本，修饰符，类全名，签名信息，父类，接口集\n" +
                "        cw.visit(Opcodes.V1_8, ACC_PUBLIC, \"asm/Student\", null, \"java/lang/Object\", null);\n" +
                "        //创建字段arg：修饰符，变量名，类型，签名信息，初始值（不一定会起作用后面会说明）\n" +
                "        cw.visitField(ACC_PUBLIC, \"arg\", \"I\", null, new Integer(11))\n" +
                "                .visitEnd();\n" +
                "        //创建方法：修饰符，方法名，类型，描述（输入输出类型），签名信息，抛出异常集合\n" +
                "        // 方法的逻辑全部使用jvm指令来书写的比较晦涩，门槛较高，后面会介绍简单的方法\n" +
                "        MethodVisitor mv = cw.visitMethod(ACC_PUBLIC, \"getAge\", \"()I\", null, null);\n" +
                "        // 创建方法第一步\n" +
                "        mv.visitCode();\n" +
                "        // 将索引为 #0 的本地变量列表加到操作数栈下。#0 索引的本地变量列表永远是 this ，当前类实例的引用。\n" +
                "        mv.visitVarInsn(ALOAD, 0);\n" +
                "        // 获取变量的值，\n" +
                "        mv.visitFieldInsn(GETFIELD, \"asm/Student\", \"age\", \"I\");\n" +
                "        // 返回age\n" +
                "        mv.visitInsn(IRETURN);\n" +
                "        // 设置操作数栈和本地变量表的大小\n" +
                "        mv.visitMaxs(1, 1);\n" +
                "        //结束方法生成\n" +
                "        mv.visitEnd();\n" +
                "        //结束类生成\n" +
                "        cw.visitEnd();\n" +
                "        //返回class的byte[]数组\n" +
                "        return cw.toByteArray();\n" +
                "    }\n" +
                "}\n";
        String charset ="GBK"; //假定编码格式
        String str1 = "返回";
//        try {
//
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
        System.out.println(str);


    }
}

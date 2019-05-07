package asm;

public class MyClassLoader extends ClassLoader{
    public Class getClassByBytes(byte[] bytes) {
        return defineClass(null, bytes, 0, bytes.length);
    }
}

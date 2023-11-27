package org.example.embedding;
import java.nio.ByteBuffer;

public class MyClass {
    public int               id;
    public String            text;
    public int[]             arr;
    public ByteBuffer buffer;
    public byte[] bytes;
    // public Callable<Integer> ret42 = () -> 42;

    public String banana() {
        return "buhhhhdeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee";
    }

    public AnotherClass anotherClass() {
        return new AnotherClass();
    }

    MyClass() {
        id = 4;
        text = "6";
        arr = new int[]{104,101,108,108,111};
        buffer = ByteBuffer.allocate(10);
        bytes = "hello".getBytes();

        buffer.put((byte) 1);
        buffer.put((byte) 2);
        buffer.put((byte) 3);
    }
}
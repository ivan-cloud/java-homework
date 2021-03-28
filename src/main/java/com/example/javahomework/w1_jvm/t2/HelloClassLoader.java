package com.example.javahomework.w1_jvm.t2;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

// 2.(必做)自定义一个 Classloader，加载一个 Hello.xlass 文件，执行 hello 方法，此 文件内容是一个 Hello.class 文件所有字节(x=255-x)处理后的文件。文件在视频下方 下载。
public class HelloClassLoader extends ClassLoader {
    public static void main(String[] args) {
        try {
            Object instance = new HelloClassLoader().findClass("Hello").newInstance();
            Method method = instance.getClass().getDeclaredMethod("hello");
            method.invoke(instance);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String filename = HelloClassLoader.class.getClassLoader().getResource("Hello.xlass").getPath();
        File file = new File(filename);
        if (file.exists()) {
            try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream((int) file.length());
                 BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file))) {
                int bufferSize = 1024;
                byte[] buffer = new byte[bufferSize];
                int len = 0;
                while (-1 != (len = bufferedInputStream.read(buffer, 0, bufferSize))) {
                    // 还原原值
                    for (int i = 0; i < buffer.length; i++) {
                        buffer[i] = (byte) (255 - buffer[i]);
                    }
                    byteArrayOutputStream.write(buffer, 0, len);
                }
                byte[] bytes = byteArrayOutputStream.toByteArray();
                return defineClass(name, bytes, 0, bytes.length);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return super.findClass(name);
    }
}
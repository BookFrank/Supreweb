package com.tazine.supreweb.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;

/**
 * 类加载器
 *
 * @author Frank
 * @since 1.0.0
 */
public class MyClassLoader {

    private static final Logger logger = LoggerFactory.getLogger(MyClassLoader.class);

    /**
     * 获取类加载器
     * @return
     */
    public static ClassLoader getClassLoader(){
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * 加载类
     * @param className
     * @param isInit
     * @return
     */
    public static Class<?> loadClass(String className,boolean isInit){
        Class<?> clz = null;
        try {
            clz = Class.forName(className, isInit, getClassLoader());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return clz;
    }

    /**
     * 获取指定包名下的所有类
     * @param packName
     * @return
     */
    public static Set<Class<?>> getClassesSet(String packName){
        return null;
    }

    private static Set<Class<?>> getClassesByPath(Set<Class<?>> set, String path){
        return null;
    }

    public static void main(String[] args) {

        System.out.println(getClassLoader().getResource("com.tazine.supreweb".replace(".","/")));

//        try {
//            Enumeration<URL> enumUrls = getClassLoader().getResources("com.tazine.supreweb".replace(".","/"));
//            while (enumUrls.hasMoreElements()){
//                //System.out.println(enumUrls.nextElement().toString());
//                System.out.println(enumUrls.nextElement().getPath());
//
//
//            }
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        File[] files = new File("/Users/lina/codeplay/github/supreweb/target/classes/com/tazine/supreweb").listFiles();
//        for (File file : files){
//            if (null != file && file.isFile()){
//                System.out.println(file.getName());
//            }
//        }

        String a = "/Users/lina/codeplay/github/supreweb/src/main/java/com/tazine/supreweb";
        getAllFiles(a);

    }

    /**
     * 递归获取文件夹下所有文件
     * @param path
     * @return
     */
    private static List<File> getAllFiles(String path){
        List<File> fileList = new ArrayList<File>();
        File[] files = new File(path).listFiles();
        for (File file : files){
            if (null != file && file.isFile()){
                fileList.add(file);
            }else if (file.isDirectory()){
                getAllFiles(file.getPath());
            }
        }
        return fileList;
    }

}

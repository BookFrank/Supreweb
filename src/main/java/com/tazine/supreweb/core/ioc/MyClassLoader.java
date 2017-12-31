package com.tazine.supreweb.core.ioc;

import com.tazine.supreweb.core.env.ConfigConstant;
import com.tazine.supreweb.core.env.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 类加载器
 *
 * @author frank
 * @since 1.0.0
 */
public class MyClassLoader {

    private static final Logger logger = LoggerFactory.getLogger(MyClassLoader.class);

    /**
     * 获取类加载器
     *
     * @return
     */
    public static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * 加载类
     *
     * @param className
     * @param isInit
     * @return
     */
    public static Class<?> loadClass(String className, boolean isInit) {
        Class<?> clz = null;
        try {
            clz = Class.forName(className, isInit, getClassLoader());
        } catch (ClassNotFoundException e) {
            logger.error("找不到该Class");
            e.printStackTrace();
        }
        return clz;
    }

    /**
     * 获取指定包名下的所有类
     *
     * @param packName
     * @return
     */
    public static Set<Class<?>> getClassesSet(String packName) {
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        URL url = getClassLoader().getResource(packName.replace(".", "/"));
        if (null != url) {
            String basePackagePath = url.getPath();
            classSet = getClassesByPath(classSet, basePackagePath, packName);
        }
        return classSet;
    }

    /**
     * 获取指定路径下的所有类
     *
     * @param set
     * @param path
     * @return
     */
    private static Set<Class<?>> getClassesByPath(Set<Class<?>> set, String path, String basePackageName) {
        List<String> names = getClassNames(path, basePackageName);
        for (String className : names) {
            // System.out.println(className);
            set.add(loadClass(className, false));
        }
        return set;
    }

    public static void main(String[] args) {

        System.out.println(getClassLoader());
        URL url = getClassLoader().getResource("com.tazine.supreweb");
        Set<Class<?>> set = getClassesSet(Environment.getProperty(ConfigConstant.APP_BASE_PACKAGE));
//
        for (Class<?> clz : set) {
            System.out.println(clz.getSimpleName());
        }

//        File[] files = new File("/Users/lina/codeplay/github/supreweb/target/classes/com/tazine/supreweb").listFiles();
//        for (File file : files){
//            if (null != file && file.isFile()){
//                System.out.println(file.getName());
//            }
//        }

        String a = "/Users/lina/codeplay/github/supreweb/src/main/java/com/tazine/supreweb";
        //getAllFiles(a);

    }

    /**
     * 递归获取文件夹下所有文件
     *
     * @param path
     * @return
     */
    private static List<File> getAllFiles(String path) {
        List<File> fileList = new ArrayList<File>();
        File[] files = new File(path).listFiles();
        for (File file : files) {
            if (null != file && file.isFile()) {
                fileList.add(file);
            } else if (file.isDirectory()) {
                getAllFiles(file.getPath());
            }
        }
        return fileList;
    }

    /**
     * 获取指定路径下的所有限定性类名
     *
     * @param basePath
     * @param basePackageName
     * @return
     */
    private static List<String> getClassNames(String basePath, String basePackageName) {
        List<String> fileList = new ArrayList<String>();
        File[] files = new File(basePath).listFiles();
        for (File file : files) {
            if (null != file && file.isFile() && file.getName().endsWith(".class")) {
                String clzName = basePackageName.concat(".").concat(file.getName());
                fileList.add(clzName.substring(0, clzName.length() - 6));
            } else if (file.isDirectory()) {
                fileList.addAll(getClassNames(file.getPath(), basePackageName.concat(".").concat(file.getName())));
            }
        }
        return fileList;
    }

}

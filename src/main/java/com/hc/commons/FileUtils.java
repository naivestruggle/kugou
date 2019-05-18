package com.hc.commons;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Author:
 * @Date:2019/5/17
 * @Description:com.hc.commons
 * @Version:1.0
 */
public class FileUtils {
    private FileUtils(){}

    /**
     * 上传文件
     * @param file 数据数组
     * @param filePath  文件路径（不带文件名）
     * @param fileName  文件名
     */
    public static void uploadFile(byte[] file, String filePath, String fileName) throws IOException {
        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(filePath+fileName);
        fileOutputStream.write(file);
        fileOutputStream.flush();
        fileOutputStream.close();
    }
}

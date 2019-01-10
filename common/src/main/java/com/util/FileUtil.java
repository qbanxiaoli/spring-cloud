package com.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * @author qbanxiaoli
 * @description
 * @create 2018/10/19 9:32 PM
 */
@Slf4j
public class FileUtil {

    private FileUtil() {
        throw new AssertionError("不能实例化FileUtil");
    }

    /**
     * @param multipartFile 待上传的图片
     * @return true or false
     * @author qbanxiaoli
     * @description 判断上传的文件类型是否图片
     */
    public static boolean isImage(MultipartFile multipartFile) {
        if (multipartFile.getContentType() != null) {
            return multipartFile.getContentType().startsWith("image");
        }
        return false;
    }

    /**
     * @param multipartFile 待上传的文件
     * @return 文件后缀名
     * @author qbanxiaoli
     * @description 获取文件的后缀名
     */
    public static String getFileExtension(MultipartFile multipartFile) {
        return FilenameUtils.getExtension(multipartFile.getOriginalFilename());
    }

    /**
     * @param is 文件输入流
     * @param os response的输出流
     * @author qbanxiaoli
     * @description 将文件输入流写入response的输出流中
     */
    public static void fileUpload(InputStream is, OutputStream os) {
        try {
            byte[] b = new byte[1024 * 1024 * 10];
            int length;
            while (true) {
                length = is.read(b);
                if (length < 0)
                    break;
                os.write(b, 0, length);
            }
            is.close();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param fileUrl 文件路径
     * @return byte数组
     * @author qbanxiaoli
     * @description 将文件转换成吧byte数组
     */
    public static byte[] getBytesByFile(String fileUrl) {
        File file = new File(fileUrl);
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            byte[] data = bos.toByteArray();
            bos.close();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param bytes    待转化成文件的byte数组
     * @param filePath 文件路径
     * @param fileName 文件名
     * @return true or false
     * @author qbanxiaoli
     * @description 将byte数组转换成文件
     */
    public static boolean getFileByBytes(byte[] bytes, String filePath, String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file;
        try {
            File dir = new File(filePath);
            if (!dir.exists() && dir.isDirectory()) {// 判断文件目录是否存在
                boolean b = dir.mkdirs();
                if (b) {
                    log.info("文件目录创建成功");
                }
                log.info("文件目录创建失败");
            }
            file = new File(filePath + '/' + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

}

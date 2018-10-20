package com.qbanxiaoli.common.util;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

/**
 * @author qbanxiaoli
 * @description
 * @create 2018/10/19 9:32 PM
 */
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
        return Objects.requireNonNull(multipartFile.getContentType()).startsWith("image");
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

}

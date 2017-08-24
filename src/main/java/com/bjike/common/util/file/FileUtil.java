package com.bjike.common.util.file;

import com.bjike.common.exception.SerException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.Servlet;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload.isMultipartContent;

/**
 * @Author: [liguiqin]
 * @Date: [2017-06-28 16:34]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class FileUtil {
    private static final String ROOT_PATH = "/root/storage";


    /**
     * 输出文件
     *
     * @param response
     * @param bytes
     * @param fileName
     * @throws IOException
     */
    public static void writeOutFile(HttpServletResponse response, byte[] bytes, String fileName) throws IOException {
        fileName = fileName.replaceAll(" ", "");
        response.reset();
        response.addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("utf-8"), "ISO8859-1"));
        response.addHeader("Content-Length", "" + bytes.length);
        OutputStream os = new BufferedOutputStream(response.getOutputStream());
        response.setContentType("application/octet-stream");
        os.write(bytes);// 输出文件
        os.flush();
        os.close();
    }



    /**
     * 文件保存
     *
     * @param request
     * @param path
     * @return
     * @throws SerException
     */
    public static List<File> save(HttpServletRequest request, String path) throws SerException {
        if (StringUtils.isNotBlank(path)) {
            path = getRealPath(path);
            if (isMultipartContent(request)) {
                List<MultipartFile> multipartFiles = getMultipartFile(request);
                if (null != multipartFiles && multipartFiles.size() > 0) {
                    List<File> files = new ArrayList<>(multipartFiles.size());
                    for (MultipartFile mfile : multipartFiles) {
                        File folder = new File(path);
                        if (!folder.exists()) {// 文件夹不存在则创建
                            folder.mkdirs();
                        }
                        File file = new File(path + "/" + mfile.getOriginalFilename());
                        try {
                            mfile.transferTo(file);
                        } catch (IOException e) {
                        }
                        files.add(file);
                    }
                    return files;
                }
            }

        } else {
            throw new SerException("path存储路径不能为空");
        }

        return new ArrayList<>(0);
    }


    /**
     * 字节转文件
     *
     * @param buffer   字节
     * @param path     文件路径
     * @param fileName 文件名
     * @return
     * @throws SerException
     */
    private static File byteToFile(byte[] buffer, String path, String fileName) throws SerException {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(path);
            if (!dir.exists()) { //&& dir.isDirectory() 不自动创建文件夹
                dir.mkdirs();
            }
            file = new File(path + "/" + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(buffer);
        } catch (Exception e) {
            throw new SerException(e.getMessage());
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    throw new SerException(e.getMessage());
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    throw new SerException(e.getMessage());
                }
            }
        }
        return file;
    }


    /**
     * 文件转字节
     *
     * @param filePath 文件路径
     * @return
     * @throws SerException
     */
    public static byte[] FileToByte(String filePath) throws SerException {
        byte[] buffer = null;
        try {
            File file = new File(filePath);
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                byte[] b = new byte[1024];
                int n;
                while ((n = fis.read(b)) != -1) {
                    bos.write(b, 0, n);
                }
                fis.close();
                bos.close();
                buffer = bos.toByteArray();
            } else {
                throw new SerException("文件不存在！");
            }

        } catch (FileNotFoundException e) {
            throw new SerException(e.getMessage());
        } catch (IOException e) {
            throw new SerException(e.getMessage());
        }
        return buffer;
    }

    /**
     * 通过request获取上传文件
     *
     * @param request
     * @return
     * @throws SerException
     */
    private static List<MultipartFile> getMultipartFile(HttpServletRequest request) throws SerException {

        if (null != request && !isMultipartContent(request)) {
            throw new SerException("上传表单不是multipart/form-data类型");
        }
        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request; // 转换成多部分request
        return multiRequest.getFiles("files");

    }

    public static String getRealPath(String path) throws SerException {
        if (!path.startsWith("/")) {
            throw new SerException("存储路径从[ / ]开始");
        }
        if (StringUtils.isNotBlank(path)) {
            return ROOT_PATH + path;
        } else {
            throw new SerException("存储路径不能为空");
        }
    }

}

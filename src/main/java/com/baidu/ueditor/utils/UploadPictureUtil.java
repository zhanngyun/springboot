package com.baidu.ueditor.utils;

import com.baidu.ueditor.define.AppInfo;
import com.baidu.ueditor.define.BaseState;
import com.baidu.ueditor.define.FileType;
import com.baidu.ueditor.define.State;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Author: yzhang
 * @Date: 2018/2/1 18:35
 */

public class UploadPictureUtil {


    @Value("${sftp.userName}")
    private static String sftpUserName;
    @Value("${sftp.password}")
    private static String sftpPassword;
    @Value("${sftp.host}")
    private static String sftpHost;
    @Value("${sftp.port}")
    private static Integer sftpPort;
    @Value("${fileserver.url}")
    private static String fsUrl;


    public static  State save(HttpServletRequest request,
                                   Map<String, Object> conf) {

        if (!ServletFileUpload.isMultipartContent(request)) {
            return new BaseState(false, AppInfo.NOT_MULTIPART_CONTENT);
        }

        try{
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            MultipartFile multipartFile = multipartRequest.getFile(conf.get("fieldName").toString());
            if(multipartFile==null){
                return new BaseState(false, AppInfo.NOTFOUND_UPLOAD_DATA);
            }
            String originFileName = multipartFile.getOriginalFilename();
            String suffix = FileType.getSuffixByFilename(originFileName);

            originFileName = originFileName.substring(0,
                    originFileName.length() - suffix.length());

            if (!validType(suffix, (String[]) conf.get("allowFiles"))) {
                return new BaseState(false, AppInfo.NOT_ALLOW_FILE_TYPE);
            }

            String url = SFTPUtil.upload(sftpUserName, sftpPassword, sftpHost, sftpPort,
                    MaterialConstant.IMG_DIR, 100001L, multipartFile);
            State storageState = new BaseState();
            if(StringUtils.isEmpty(url)){//上传失败
                return new BaseState(false, AppInfo.NOT_ALLOW_FILE_TYPE);

            }
            storageState.isSuccess();
            url = fsUrl+url;
            if (storageState.isSuccess()) {
                storageState.putInfo("url", url);
                storageState.putInfo("type", suffix);
                storageState.putInfo("original", originFileName + suffix);
            }

            return storageState;
        }catch (Exception e){
        }

        return new BaseState(false, AppInfo.IO_ERROR);

    }
    private static boolean validType(String type, String[] allowTypes) {
        List<String> list = Arrays.asList(allowTypes);

        return list.contains(type);
    }


}

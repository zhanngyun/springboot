package com.baidu.ueditor.utils;

import com.baidu.ueditor.define.AppInfo;
import com.baidu.ueditor.define.BaseState;
import com.baidu.ueditor.define.FileType;
import com.baidu.ueditor.define.State;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.boot.context.properties.ConfigurationProperties;
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

@ConfigurationProperties(locations = "classpath:application-dev.yml",prefix = "sftp")
public class UploadPictureUtil {


    private  String userName;

    private  String password;


    private  String host;
    private  Integer port;
    private  String fsUrl;



    public   State save(HttpServletRequest request,
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

            String url = SFTPUtil.upload(userName, password, host, port,
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
            e.printStackTrace();
        }

        return new BaseState(false, AppInfo.IO_ERROR);

    }
    private static boolean validType(String type, String[] allowTypes) {
        List<String> list = Arrays.asList(allowTypes);

        return list.contains(type);
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getFsUrl() {
        return fsUrl;
    }

    public void setFsUrl(String fsUrl) {
        this.fsUrl = fsUrl;
    }
}

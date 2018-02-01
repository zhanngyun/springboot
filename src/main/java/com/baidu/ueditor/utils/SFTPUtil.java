package com.baidu.ueditor.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Date;

/**
 * SFTP工具
 */
public class SFTPUtil {

    private static final Logger logger = LoggerFactory.getLogger(SFTPUtil.class);

    /**
     * 获取远程目录
     *
     * @param typeDir
     * @param companyId
     * @return
     */
    private static String getRemote(String typeDir, Long companyId) {
        return "/icmp/" + typeDir + "/" + companyId + "/" +
                DateUtil.format(new Date(), "yyyyMMdd");
    }

    /**
     * 上传
     *
     * @throws Exception
     */
    public static String upload(String userName, String password,
                                String host, int port, String typeDir, String suffix,
                                Long companyId, InputStream inputStream) throws Exception {
        String remote = getRemote(typeDir, companyId);
        SFTPHelper sftpHelper = new SFTPHelper(userName, password,
                host, port, remote);
        String fileName = DateUtil.format(new Date(), "yyyyMMddHHmmssSSS")
                + (int) (Math.random() * 100) % 10;
        if (sftpHelper.connectServer()) {
            sftpHelper.upLoadFile(fileName + "." + suffix, inputStream);
            sftpHelper.closeConnect();
        } else {
            throw new Exception("连接FTP服务器失败！");
        }
        fileName = fileName + "." + suffix;
        logger.info("上传路径：{}", remote + "/" + fileName);
        return remote + "/" + fileName;
    }

    /**
     * 通过微信地址，上传图片
     *
     * @throws Exception
     */
//    public static String uploadByWxUrl(String wxUrl, Integer materialType,
//            String userName, String password, String host, int port,
//            String typeDir, Long companyId) throws Exception {
//        String suffix = null;
//        if(materialType.intValue() == 4){
//            if(wxUrl.contains("?wx_fmt=")){
//                suffix = wxUrl.substring(wxUrl.lastIndexOf("=") + 1);
//            }else{
//                suffix = "png";
//            }
//        }else if(materialType.intValue() == 5){
//            String path = new URL(wxUrl).getPath();
//            suffix = path.substring(path.lastIndexOf(".")+1);
//        }else{
//            return "";
//        }
//
//        InputStream input = HttpUtil.get4Stream(wxUrl);
//        return SFTPUtil.upload(userName, password, host, port,
//                typeDir, suffix, companyId, input);
//    }

    /**
     * 上传
     * @throws Exception
     */
    public static String upload(String userName, String password,
                                String host, int port, String typeDir, Long companyId,
                                MultipartFile file) throws Exception {
        String remote = getRemote(typeDir, companyId);
        SFTPHelper sftpHelper = new SFTPHelper(userName, password,
                host, port, remote);
        String fileName = DateUtil.format(new Date(), "yyyyMMddHHmmssSSS")
                + (int)(Math.random()*100)%10;
        String name = file.getOriginalFilename();
        String suffix = name.substring(name.lastIndexOf("."));
        if(sftpHelper.connectServer()){
            sftpHelper.upLoadFile(fileName + suffix, file.getInputStream());
            sftpHelper.closeConnect();
        }else{
            throw new Exception("连接FTP服务器失败！");
        }
        return remote + "/" + fileName + suffix;
    }
}

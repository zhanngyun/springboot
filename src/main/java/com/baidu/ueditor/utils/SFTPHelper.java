package com.baidu.ueditor.utils;

import com.jcraft.jsch.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.Vector;

public class SFTPHelper {
    protected final Log log = LogFactory.getLog(this.getClass());
    private String userName;
    private String password;
    private String ip;
    private int port;
    private String remote;
    private ChannelSftp sftp = null;

    public SFTPHelper(String userName, String password,
                      String ip, int port, String remote) {
        this.userName = userName;
        this.password = password;
        this.ip = ip;
        this.port = port;
        this.remote = remote;
    }

    private String encoding(String fileName) {
        String newFileName = null;
        try {
            newFileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            newFileName = fileName;
        }
        return newFileName;
    }

    /**
     * 判断是否存在名字为name的文件（目录）
     *
     * @param name 判断的文件（目录）名
     * @return 是否存在名字为name的文件（目录）
     */
    public boolean isExistFileOrDir(String name) {
        try {
            Vector<?> vector = this.sftp.ls(name);
            if (vector != null && vector.size() > 0) {
                return true;
            }
        } catch (SftpException e) {
            return false;
        }
        return false;
    }

    /**
     * connect server via sftp
     */
    public boolean connectServer() {
        try {
            if (sftp != null) {
                log.info("sftp is not null");
            }
            JSch jsch = new JSch();
            jsch.getSession(this.userName, this.ip, this.port);
            Session sshSession = jsch.getSession(this.userName, this.ip, this.port);
            log.info("Session created.");
            sshSession.setPassword(this.password);
            Properties sshConfig = new Properties();
            sshConfig.put("StrictHostKeyChecking", "no");
            sshSession.setConfig(sshConfig);
            sshSession.connect();
            log.info("Session connected.");
            log.info("Opening Channel.");
            Channel channel = sshSession.openChannel("sftp");
            channel.connect();
            sftp = (ChannelSftp) channel;
            log.info("Connected to " + this.ip + ".");
            //设置当前工作目录

            String dirStr = this.remote;
            dirStr = dirStr.startsWith("/") ? dirStr.substring(1) : dirStr;
            String[] dirArray = dirStr.split("/");
            for (String dir : dirArray) {
                if ((dir.isEmpty() == false) && (this.isExistFileOrDir(dir) == false)) {
                    this.sftp.mkdir(this.encoding(dir));
                }
                this.sftp.cd(this.encoding(dir));
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info("Connected to " + ip + " failure!");
            this.closeConnect();
            return false;
        }
        return true;
    }

    /**
     * Disconnect with server
     */
    public void closeConnect() {
        if (this.sftp != null) {
            if (this.sftp.isConnected()) {
                this.sftp.disconnect();
            } else if (this.sftp.isClosed()) {
                log.info("sftp is closed already");
            }
        }

    }

    public void upLoadFile(String fileName, InputStream inputStream) throws Exception {
        try {
            this.sftp.put(inputStream, fileName);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("上传文件失败！");
        } finally {
            StreamUtil.close(inputStream);
        }
    }

//    public static void main(String[] args) {
//    	String userName ="icmpSftp";
//    	String password = "icmpSftp";//"5even@test";
//		String host = "10.253.102.157";//"192.168.70.60";
//		int port = 22;
//		String remote = "/images";
//    	SFTPHelper sftpHelper = new SFTPHelper(userName,
//    			password, host, port, remote);
//    	try{
//    		if(sftpHelper.connectServer()){
//            	sftpHelper.closeConnect();
//        		System.out.println("sftp连接成功!");
//        	}
//    	}catch(Exception e){
//    		e.printStackTrace();
//    	}
//        System.exit(0);
//    }

}

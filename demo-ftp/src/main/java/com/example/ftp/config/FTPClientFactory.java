package com.example.ftp.config;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * ftpClient连接工厂类
 *
 * @author Taxz
*/
public class FTPClientFactory {

    private static Logger logger = LoggerFactory.getLogger(FTPClientFactory.class);

    private FTPClientConfig config;

    public FTPClientFactory(FTPClientConfig config) {
        this.config = config;
    }

    /**
     * 创建连接
     * @return
     * @throws Exception
     */
    public FTPClient makeClient(){
        FTPClient ftpClient = new FTPClient();
        ftpClient.setConnectTimeout(config.getClientTimeout());
        try{
            ftpClient.connect(config.getHost(),config.getPort());
            int reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                logger.warn("FTPServer refused connection");
                return null;
            }
            boolean result = ftpClient.login(config.getUsername(), config.getPassword());
            if (!result) {
                logger.warn("ftpClient login failed... username is {}", config.getUsername());
            }
            ftpClient.setFileType(config.getTransferFileType());
            ftpClient.setBufferSize(1024);
            ftpClient.setControlEncoding(config.getEncoding());
            ftpClient.setKeepAlive(true);
            ftpClient.enterLocalPassiveMode();
            if (config.getPassiveMode().equals("true")) {
                ftpClient.enterLocalPassiveMode();
            }
        }catch (Exception e) {
                logger.error("create ftp connection failed...{}", e);
            throw new RuntimeException("创建FTP连接失败",e);
        }
        return ftpClient;
    }

    /**
     * 销毁连接
     * @param ftpClient
     * @throws Exception
     */
    public void destroyClient(FTPClient ftpClient) throws IOException {
        try {
            if(ftpClient != null && ftpClient.isConnected()) {
                ftpClient.logout();
            }
        } catch (Exception e) {
            logger.error("ftp client logout failed...{}", e);
            throw new RuntimeException("销毁连接失败");
        } finally {
            if(ftpClient != null) {
                ftpClient.disconnect();
            }
        }

    }

    /**
     * 验证连接
     * @param ftpClient
     * @return
     */
    public boolean validateClient(FTPClient ftpClient) {
        try {
            return ftpClient.sendNoOp();
        } catch (Exception e) {
            logger.error("Failed to validate client: {}", e);
            return false;
        }
    }
}

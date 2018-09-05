package com.example.ftp.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * ftpClient配置类
 *
 * @author Taxz
 */
@Data
@Configuration(value = "fTPClientConfig")
@NoArgsConstructor
@AllArgsConstructor
@PropertySource(value = "classpath:ftp-dev.properties")
public class FTPClientConfig {
    @Value("${ftp.host}")
    private String host;
    @Value("${ftp.port}")
    private int port;
    @Value("${ftp.userName}")
    private String username;
    @Value("${ftp.userPass}")
    private String password;
    @Value("${ftp.pool.size}")
    private int poolSize;
    @Value("${ftp.client.timeout}")
    private int clientTimeout;
    @Value("${ftp.transfer.file.type}")
    private int transferFileType;
    @Value("${ftp.encoding}")
    private String encoding;
    @Value("${ftp.passiveMode}")
    private String passiveMode;
    //@Value("${original.file.topDirectory}")
    //private String topDirectory;
    //@Value("${original.file.conversionDirectory}")
    //private String conversionDirectory;
    @Value("${ftp.sys.path}")
    private String ftpSysPath;


}

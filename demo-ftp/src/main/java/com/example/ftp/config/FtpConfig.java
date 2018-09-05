package com.example.ftp.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Taxz
 */

@Configuration
public class FtpConfig {

    @Bean(value = "ftpClientPool")
    public FTPClientPool ftpClientPool(@Qualifier("fTPClientConfig") FTPClientConfig ftpClientConfig ) throws Exception {
        FTPClientFactory factory = new FTPClientFactory(ftpClientConfig);
        return new FTPClientPool(ftpClientConfig.getPoolSize(), factory);
    }

}

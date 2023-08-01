package com.xudq.api.config;


import com.xudq.api.utils.OctoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * @author xudq
 * @version v1.0
 * @Title
 * @Description 为工具类从配置文件读取配置
 * @date
 */
@Configuration
public class PropertyConfig {

        @Autowired
        private Environment env;

        @Bean
        public int readConf() {
            OctoUtils.setOctoPrivilegeKey(env.getProperty("octo.key"));
            OctoUtils.setOctoPrivilegeSecretBase64(env.getProperty("octo.base64Secret"));
            return 1;
        }
}

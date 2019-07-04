package cn.gobaby.config;

import com.netflix.loadbalancer.IRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Base64;

@Configuration
public class RestConfig {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return  new RestTemplate();
    }

    @Bean
    public HttpHeaders getHeaders() { // 要进行一个Http头信息配置
        HttpHeaders headers = new HttpHeaders(); // 定义一个HTTP的头信息
        String auth = "admin:123456"; // 认证的原始信息
        byte[] encodedAuth = Base64.getEncoder()
                .encode(auth.getBytes(Charset.forName("US-ASCII"))); // 进行一个加密的处理
        String authHeader = "Basic " + new String(encodedAuth);
        headers.set("Authorization", authHeader);
        return headers;
    }

    /**
     * 设置全局的访问策略，其中IRule就是所有规则的标准
     * @return
     */
//    @Bean
//    public IRule ribbonRule() {
//        return new com.netflix.loadbalancer.RandomRule(); // 随机的访问策略
//    }

/**
 * 如需指定某个服务使用随机的访问策略，这个需要注释，并且配置RibbonConfig.class(在里面设置随机策略)
 * 启动项目的时候，在ConsumerApp配置@RibbonClient，指定某个服务访问策略为RibbonConfig.class
 */
}
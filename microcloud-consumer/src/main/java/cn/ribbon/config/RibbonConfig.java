package cn.ribbon.config;

import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;

public class RibbonConfig {

    @Bean
    public IRule ribbonRule() {
        return new com.netflix.loadbalancer.RandomRule(); // 随机的访问策略
    }

}

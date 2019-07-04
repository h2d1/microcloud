package cn.gobaby.controller;

import cn.gobaby.entity.UserBean;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/consumer")
public class ConsumerProductController {
    public static final String PRODUCT_HELLO_URL = "http://MICROCLOUD-PROVIDER-PRODUCT/product/hello/";
    public static final String PRODUCT_GET_URL = "http://MICROCLOUD-PROVIDER-PRODUCT/product/get/";
    public static final String PRODUCT_LIST_URL = "http://MICROCLOUD-PROVIDER-PRODUCT/product/list/";
    public static final String PRODUCT_ADD_URL = "http://MICROCLOUD-PROVIDER-PRODUCT/product/add/";

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private HttpHeaders httpHeaders;

    @Resource
    private LoadBalancerClient loadBalancerClient; // 在服务的消费方获取服务提供方的信息

    @RequestMapping("/product/hello/{name}")
    public String hello(@PathVariable("name") String name) {
        String str = restTemplate.exchange(PRODUCT_HELLO_URL + name, HttpMethod.GET, new HttpEntity<Object>(httpHeaders), String.class).getBody();

        ServiceInstance serviceInstance = this.loadBalancerClient.choose("MICROCLOUD-PROVIDER-PRODUCT") ;
        System.out.println(
                "【*** ServiceInstance ***】host = " + serviceInstance.getHost()
                        + "、port = " + serviceInstance.getPort()
                        + "、serviceId = " + serviceInstance.getServiceId());

        return str;
    }

    @RequestMapping("/product/get/{id}")
    public Object getProduct(@PathVariable("id") long id) {
        UserBean bean = restTemplate.exchange(PRODUCT_GET_URL + id, HttpMethod.GET, new HttpEntity<Object>(httpHeaders), UserBean.class).getBody();
        return bean;
    }

    @RequestMapping("/product/list")
    public Object listProduct() {
        List<UserBean> list = restTemplate.exchange(PRODUCT_LIST_URL, HttpMethod.GET,
                new HttpEntity<Object>(httpHeaders), List.class).getBody();
        return list;
    }

    @RequestMapping("/product/add")
    public Object addPorduct(UserBean bean) {
        Boolean result = restTemplate.exchange(PRODUCT_ADD_URL, HttpMethod.POST,
                new HttpEntity<Object>(bean, httpHeaders), Boolean.class).getBody();
        return result;
    }

}
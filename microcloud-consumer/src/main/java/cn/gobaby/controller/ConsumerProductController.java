package cn.gobaby.controller;

import cn.gobaby.entity.UserBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/consumer")
public class ConsumerProductController {
    public static final String PRODUCT_GET_URL = "http://localhost:8080/product/get/";
    public static final String PRODUCT_LIST_URL = "http://localhost:8080/product/list/";
    public static final String PRODUCT_ADD_URL = "http://localhost:8080/product/add/";
    @Resource
    private RestTemplate restTemplate;

    @Resource
    private HttpHeaders httpHeaders;

    @RequestMapping("/product/get")
    public Object getProduct(long id) {
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
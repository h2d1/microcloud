package cn.gobaby.controller;


import cn.gobaby.entity.UserBean;
import cn.gobaby.service.UserService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Resource
    private UserService userService;

    @RequestMapping(value="/hello/{name}")
    public String hello(@PathVariable("name") String name) {
        return "hello " + name;
    }

    @RequestMapping(value="/get/{id}")
    public String getuser(@PathVariable("id") long id) {
        UserBean bean = userService.get(id);
        return bean.toString();
    }

    @RequestMapping(value="/add")
    public Object add(@RequestBody UserBean bean) {
        return userService.addUser(bean) ;
    }

    @RequestMapping(value="/list")
    public Object list() {
        return userService.list() ;
    }
}

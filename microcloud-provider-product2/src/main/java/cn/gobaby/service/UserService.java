package cn.gobaby.service;

import cn.gobaby.entity.UserBean;

import java.util.List;

public interface UserService {

    int addUser(UserBean bean);

    UserBean get(long id);

    List<UserBean> list();
}

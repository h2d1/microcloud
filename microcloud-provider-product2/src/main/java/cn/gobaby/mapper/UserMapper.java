package cn.gobaby.mapper;
import cn.gobaby.entity.UserBean;

import java.util.List;

public interface UserMapper {
    int create(UserBean bean);

    UserBean findById(Long id);

    List<UserBean> findAll();
}


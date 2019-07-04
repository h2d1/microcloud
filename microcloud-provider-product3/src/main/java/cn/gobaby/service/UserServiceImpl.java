package cn.gobaby.service;

import cn.gobaby.entity.UserBean;
import cn.gobaby.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public int addUser(UserBean bean) {
        return userMapper.create(bean);
    }

    @Override
    public UserBean get(long id) {
        return userMapper.findById(id);
    }

    @Override
    public List<UserBean> list() {
        return userMapper.findAll();
    }
}

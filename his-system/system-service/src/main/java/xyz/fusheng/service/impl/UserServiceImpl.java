package xyz.fusheng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.fusheng.mapper.UserMapper;
import xyz.fusheng.domain.User;
import xyz.fusheng.service.UserService;

/**
 * @author code-fusheng
 */
@Service
public class UserServiceImpl implements UserService{

    @Resource
    private UserMapper userMapper;

    @Override
    public User queryUserByPhone(String phone) {
        // 查询条件构造器
        QueryWrapper<User> qw = new QueryWrapper<>();
        // 指定号码
        qw.eq(User.COL_PHONE, phone);
        User user = this.userMapper.selectOne(qw);
        return user;
    }

    @Override
    public User getOne(Long userId) {
        return this.userMapper.selectById(userId);
    }
}

package xyz.fusheng.service;

import xyz.fusheng.domain.User;

/**
 * @author code-fusheng
 */
public interface UserService{

    /**
     * 根据手机号查询用户
     * @param phone
     * @return
     */
    User queryUserByPhone(String phone);

    /**
     * 根据用户Id查询用户
     * @param userId
     * @return
     */
    User getOne(Long userId);

}

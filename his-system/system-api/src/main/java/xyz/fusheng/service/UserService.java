package xyz.fusheng.service;

import xyz.fusheng.domain.User;
import xyz.fusheng.dto.UserDto;
import xyz.fusheng.vo.DataGridView;

import java.util.List;

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

    /**
     * 分页查询用户
     * @param userDto
     * @return
     */
    DataGridView listUserForPage(UserDto userDto);

    /**
     * 添加用户
     * @param userDto
     * @return
     */
    int addUser(UserDto userDto);

    /**
     * 修改用户
     * @param userDto
     * @return
     */
    int updateUser(UserDto userDto);

    /**
     * 删除用户
     * @param userIds
     * @return
     */
    int deleteUserByIds(Long[] userIds);

    /**
     * 重置用户密码
     * @param userIds
     */
    void resetPassWord(Long[] userIds);

    /**
     * 查询所有可用的用户
     * @return
     */
    List<User> getAllUsers();

}

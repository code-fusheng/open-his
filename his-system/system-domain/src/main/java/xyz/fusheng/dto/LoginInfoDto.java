package xyz.fusheng.dto; /**
 * @author: code-fusheng
 * @Date: 2020/9/4 18:42
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @FileName: LoginInfoDto
 * @Author: code-fusheng
 * @Date: 2020/9/4 18:42
 * @version: 1.0
 * Description: 登录信息传输对象
 */

@ApiModel(value="xyz-fusheng-dto-LoginInfoDto")
@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
public class LoginInfoDto extends BaseDto {

    /**
     * 用户名称
     */
    @ApiModelProperty(value="用户名称")
    private String userName;

    /**
     * 登陆账号
     */
    @ApiModelProperty(value="登陆账号")
    private String loginAccount;

    /**
     * 登录IP地址
     */
    @ApiModelProperty(value="登录IP地址")
    private String ipAddr;

    /**
     * 登录状态（0成功 1失败）字典表
     */
    @ApiModelProperty(value="登录状态（0成功 1失败）字典表")
    private String loginStatus;

    /**
     * 登陆类型0系统用户1患者用户 字典表
     */
    @ApiModelProperty(value="登陆类型0系统用户1患者用户 字典表")
    private String loginType;

}

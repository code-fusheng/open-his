package xyz.fusheng.utils; /**
 * @author: code-fusheng
 * @Date: 2020/9/11 23:57
 */

import org.apache.shiro.crypto.hash.Md5Hash;

import java.util.UUID;

/**
 * @FileName: AppMd5Utils
 * @Author: code-fusheng
 * @Date: 2020/9/11 23:57
 * @version: 1.0
 * Description: MD5 加密算法工具类
 */

public class AppMd5Utils {
    /**
     * 生成盐
     */
    public static String createSalt(){
        return UUID.randomUUID().toString().replace("-","").toUpperCase();
    }

    /**
     * 生成加密字符串
     */
    public static String md5(String source,String salt,Integer hashIterations){
        return new Md5Hash(source,salt,hashIterations).toString();
    }

    public static void main(String[] args) {
        System.out.println(md5("907654","9ED13A792A6B438D9AC3FC1C90F2F06A",2));
    }

}

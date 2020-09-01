package xyz.fusheng.utils;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @FileName: IdGeneratorSnowflake
 * @Author: code-fusheng
 * @Date: 2020/9/1 15:26
 * @version: 1.0
 * Description: 雪花算法 - 分布式ID生成工具类
 */

public class IdGeneratorSnowflake {

    private static final Logger log = LoggerFactory.getLogger(HttpUtils.class);
    /**
     * 工作id
     */
    private static long workId = 0;
    /**
     * 数据中心id
     */
    private static long dataCenterId = 1;
    private static Snowflake snowflake;

    static {
        try {
            workId = NetUtil.ipv4ToLong(NetUtil.getLocalhostStr());
            log.info("当前机器的工作ID为:" + workId);
            snowflake= IdUtil.createSnowflake(workId, dataCenterId);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("当前机器的workId获取失败", e);
            workId = NetUtil.getLocalhostStr().hashCode();
        }
    }

    /**
     * 生成ID
     */
    public static synchronized Long snowflakeId() {
        return snowflake.nextId();
    }

    /**
     * 根据前缀前成ID
     */
    public static String generatorIdWithPrefix(String prefix) {
        return prefix + snowflakeId();
    }

}

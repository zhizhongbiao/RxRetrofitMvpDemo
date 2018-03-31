package cn.com.tianyudg.rxretrofitmvpdemo.basic.service;


import java.util.List;
import java.util.Map;

import cn.com.tianyudg.rxretrofitmvpdemo.basic.model.Entity;


/**
 * @author WaterFlower
 *         date 2016/7/24.
 *         请求数据转换成数据库查询条件的service
 */
public interface ConvertService<E extends Entity> {

    /**
     * 处理数据CRUD请求
     *
     * @param api    请求的类型
     * @param params 请求的参数
     * @return 处理结果
     */
    List<E> doAction(String api, Map<String, ?> params);

    /**
     * 执行同步服务器数据到本地操作
     *
     * @param syncData 同步下来的数据
     */
    void doSync(String api, List<? extends Entity> syncData);

    /**
     * @return 数据同步间隔时间
     */
    long getSyncPeriod();

}

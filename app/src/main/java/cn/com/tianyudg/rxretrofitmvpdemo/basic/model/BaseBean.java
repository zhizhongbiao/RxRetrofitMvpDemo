package cn.com.tianyudg.rxretrofitmvpdemo.basic.model;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;

import org.json.JSONException;

import java.io.Serializable;
import java.util.List;


public class BaseBean implements Serializable {
    private List<? extends Entity> queryData;
    private boolean                dataFromDB;

    public BaseBean() {

    }

    /**
     * 请求成功的回调方法，父类默认实现，使用反射方式获取每个field并赋值,field的名称必须和json数据中的名称一致才能有值
     *
     * @param jsonString
     */
    public static <T extends BaseBean> T parseDataVo(String jsonString, Class<T> dataClass) throws
                                                                                            JSONException
    {

        if (TextUtils.isEmpty(jsonString) || dataClass == null) {
            return null;
        }

        T baseVo = null;
        try {
//           TODO WaterFlower does this
//            baseVo = JSON.parseObject(jsonString, dataClass);

            if (jsonString.startsWith("[")) {
                baseVo = dataClass.newInstance();
                List<?> dataSet = JSON.parseArray(jsonString, baseVo.elementType());
                baseVo.setDataList(dataSet);
                baseVo.doMock();
            } else {
                baseVo = JSON.parseObject(jsonString, dataClass);
            }
        } catch (Exception ex) {
            throw new JSONException("数据解析失败! Exception: " + ex.getMessage());
        }
        return baseVo;
    }

    /**
     * 如果返回数据是集合，数据解析方法会调用次方法获取元素的类型
     *
     * @return
     */
    public Class<?> elementType() {
        return null;
    }


    /**
     * 数据解析成功后会调用此方法设置数据
     *
     * @param dataSet
     */
    public void setDataList(List<?> dataSet) {

    }


    /**
     * 执行数据模拟，在needMockData为true的情况下，框架层会调用此方法，
     * 子类将自己需要模拟的数据设置到当前对象。
     */
    public void doMock() {

    }

    public boolean isDataFromDB() {
        return dataFromDB;
    }

    public void setDataFromDB(boolean dataFromDB) {
        this.dataFromDB = dataFromDB;
    }

    public void formData(List<? extends Entity> queryData) {
        this.queryData = queryData;
    }

    public List<? extends Entity> getQueryData() {
        return queryData;
    }
}


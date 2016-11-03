package demo.yj.cn.feretrofitdemo.network;

import android.util.Log;
import java.lang.reflect.ParameterizedType;

import demo.yj.cn.feretrofitdemo.model.ResponseContent;

/**
 * Created by yj on 2016/10/27.
 */
public abstract class FECallback<T extends ResponseContent> {
    // 利用这个技术可以得到 泛型T的类型
    Class<T> clz = null;
    public FECallback() {
        clz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
    public Class<T> getClz() {
        return clz;
    }

    // 请求成功
    public abstract void onResponse(T t);
    // 解析Json出错
    public void onDecodeFail(String error){
        Log.d("MyTAG", error);
    }
    // 取消请求
    public void onCancel(Throwable throwable){};
    // 发生了其他错误
    public void onOtherFail(Throwable throwable){
//        Toast
    };

}

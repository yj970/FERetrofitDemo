package demo.yj.cn.feretrofitdemo.network.interfaces;

import demo.yj.cn.feretrofitdemo.network.FEClient;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by yj on 2016/10/27.
 */
public interface FEServes {
    @POST(FEClient.BASE_PATH)
    Call<String> post(@Query("json") String json);

    @GET(FEClient.BASE_PATH)
    Call<String> get(@Query("json") String json);
}

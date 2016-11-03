package demo.yj.cn.feretrofitdemo.network;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import demo.yj.cn.feretrofitdemo.model.LoginRequest;
import demo.yj.cn.feretrofitdemo.model.RequestContent;
import demo.yj.cn.feretrofitdemo.model.ResponseContent;
import demo.yj.cn.feretrofitdemo.network.interfaces.FEServes;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by yj on 2016/10/27.
 */
public class FEClient {
    private static String BASE_URL = "";
    public static final String BASE_PATH = "servlet/mobileServlet";
    public static final String UPLOAD_PATH = "servlet/uploadAttachmentServlet";

    private static FEClient mInstance;
    private static Retrofit retrofit;

    public static FEClient init(Context context){
        if (mInstance == null){
            synchronized (FEClient.class){
                if (mInstance == null)
                    mInstance = new FEClient(context);
            }
        }
        return mInstance;
    }

    public static void setBASE_URL(String url) {
        BASE_URL = url;
    }
    public FEClient(Context context){

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(7676, TimeUnit.MILLISECONDS)
                .connectTimeout(7676, TimeUnit.MILLISECONDS)
                // 绑定cookie
                .cookieJar(new CookieJar() {
                    private final HashMap<HttpUrl, List<Cookie>> cookieStore = new HashMap<>();

                    @Override
                    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                        HttpUrl httpUrl = HttpUrl.parse(BASE_URL);
                        cookieStore.put(httpUrl, cookies);
                    }

                    @Override
                    public List<Cookie> loadForRequest(HttpUrl url) {
                        HttpUrl httpUrl = HttpUrl.parse(BASE_URL);
                        List<Cookie> cookies = cookieStore.get(httpUrl);
                        return cookies != null ? cookies : new ArrayList<Cookie>();
                    }
                })
                .build();



        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    // 返回的Call可以用于取消请求
    public static <K extends RequestContent, T extends ResponseContent> Call<String> post(K reqContent, final FECallback<T> callback) {
        return request(reqContent, callback, "post");
    }

    public static <K extends RequestContent, T extends ResponseContent> Call<String> get(K reqContent, final FECallback<T> callback) {
        return request(reqContent, callback, "get");
    }

    // 请求服务
    private static <K extends RequestContent, T extends ResponseContent> Call<String> request(K reqContent, final FECallback<T> callback, String method) {
       // 将请求封装请json字符串
        final StringBuilder json = new StringBuilder("{\"iq\":");
        json.append("{\"namespace\":\"");
        json.append(reqContent.getNameSpace());
        // 如果是登入请求, 需要额外添加以下字段
        if(reqContent instanceof LoginRequest) {
            json.append("\",\"version\":\"6.5.5");
            json.append("\",\"model\":\"3");
            json.append("\",\"mobileVersion\":\"4.3");
            json.append("\",\"resolution\":\"720,1080");
        }
        json.append("\",\"query\":");
        json.append(new Gson().toJson(reqContent));
        json.append("}}");
        Log.d("MyTAG", "请求---> " + json.toString());

        // 发起请求
        Call<String> call = null;
        switch (method) {
            case "post" :
                 call = retrofit.create(FEServes.class).post(json.toString());
                break;
            case "get" :
                 call = retrofit.create(FEServes.class).get(json.toString());
                break;
            // 因为http请求一共有8种, 以后的事谁知道呢~
            default:
                call = retrofit.create(FEServes.class).post(json.toString());
                break;
        }
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d("MyTAG", "数据返回--> " + response.body());
                try {
                    JSONObject body = new JSONObject(response.body());
                    JSONObject iq = (JSONObject) body.get("iq");
                    T t = new Gson().fromJson(iq.get("query").toString(), callback.getClz());
                    callback.onResponse(t);
                } catch (JSONException e) {
                    callback.onDecodeFail(e.getMessage());
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable throwable) {
                if (call.isCanceled()) {
                    Log.d("MyTAG","取消请求--> " + throwable.getMessage());
                    callback.onCancel(throwable);
                } else {
                    Log.d("MyTAG","其他错误--> " + throwable.getMessage());
                    callback.onOtherFail(throwable);
                }
            }
        });
        return call;
    }

}

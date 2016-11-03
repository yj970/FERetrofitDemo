package demo.yj.cn.feretrofitdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.UUID;

import demo.yj.cn.feretrofitdemo.model.AgendaRequest;
import demo.yj.cn.feretrofitdemo.model.LoginRequest;
import demo.yj.cn.feretrofitdemo.model.LoginResponse;
import demo.yj.cn.feretrofitdemo.model.ResponseContent;
import demo.yj.cn.feretrofitdemo.network.FECallback;
import demo.yj.cn.feretrofitdemo.network.FEClient;
import retrofit2.Call;

public class MainActivity extends AppCompatActivity {

    private EditText edtServer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtServer = (EditText) findViewById(R.id.edt_server);

        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 测试
                StringBuilder url = new StringBuilder("http://");
                url.append(edtServer.getText().toString()).append("/");
                FEClient.setBASE_URL(url.toString());
                //  初始化
                FEClient.init(MainActivity.this);
                Call call = FEClient.post(new LoginRequest("钟日辉", "MTIz", newRandomUUID()), new MyCallback());
                // 取消任务
//                call.cancel();
            }
        });

    }

    class  MyCallback extends FECallback<LoginResponse> {
        @Override
        public void onResponse(LoginResponse responseContent) {
            Log.d("MyTAG", responseContent.getUserName());
            Call call2 = FEClient.post(new AgendaRequest("2016-10"), new MyCallback2());

        }
    }

    class  MyCallback2 extends FECallback<ResponseContent> {
        @Override
        public void onResponse(ResponseContent responseContent) {
        }
    }

    private String newRandomUUID() {
        final String uuidRaw = UUID.randomUUID().toString();
        return uuidRaw.replaceAll("-", "");
    }

}

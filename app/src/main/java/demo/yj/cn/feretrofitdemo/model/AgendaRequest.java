package demo.yj.cn.feretrofitdemo.model;



/**
 * Created by yj on 2016/7/13.
 */
public class AgendaRequest extends RequestContent {
    public static final String NAMESPACE = "AgendaRequest";

    private String date ;

    public AgendaRequest(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String getNameSpace() {
        return NAMESPACE;
    }
}

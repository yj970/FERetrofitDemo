package demo.yj.cn.feretrofitdemo.model;

/**
 * Created by yj on 2016/10/27.
 */
public class LoginRequest extends RequestContent {
    public static final String NAMESPACE = "LoginRequest";

    private String name;
    private String password;
    private String token;
    private String deviceId;
    private String languageType;
    private String mnc;
    private String brandID;

    public LoginRequest () {
    }

    public LoginRequest (String name, String password, String token) {
        this.name = name;
        this.password = password;
        this.token = token;
        this.deviceId = "88:E3:AB:6B:28:0A";
        this.languageType = "0";
        this.mnc = "**";
        this.brandID = "feep";
    }

    @Override
    public String getNameSpace () {
        return NAMESPACE;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getPassword () {
        return password;
    }

    public void setPassword (String password) {
        this.password = password;
    }

    public String getToken () {
        return token;
    }

    public void setToken (String token) {
        this.token = token;
    }

    public String getDeviceId () {
        return deviceId;
    }

    public void setDeviceId (String deviceId) {
        this.deviceId = deviceId;
    }

    public String getLanguageType () {
        return languageType;
    }

    public void setLanguageType (String languageType) {
        this.languageType = languageType;
    }

    public String getMnc () {
        return mnc;
    }

    public void setMnc (String mnc) {
        this.mnc = mnc;
    }

    public String getBrandID () {
        return brandID;
    }

    public void setBrandID (final String brandID) {
        this.brandID = brandID;
    }
}

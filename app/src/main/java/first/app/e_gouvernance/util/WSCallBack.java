package first.app.e_gouvernance.util;

public interface WSCallBack {
    void onSuccess(String response);

    void onFailure(int statusCode);

}

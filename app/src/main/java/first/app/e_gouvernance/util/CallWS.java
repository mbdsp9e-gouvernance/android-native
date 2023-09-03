package first.app.e_gouvernance.util;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

public class CallWS {

    private String urlServer = "https://5930-41-77-16-142.ngrok-free.app";
    private String authorizationHeader;

    public void setAuthorizationHeader(String token) {
        this.authorizationHeader = "Bearer " + token;
    }

    public void clearAuthorizationHeader() {
        this.authorizationHeader = null;
    }

    public void responseGet(String urlApi, RequestParams params, final WSCallBack callback) {
        String url = urlServer + urlApi;
        Log.d("Url", url);

        AsyncHttpClient client = new AsyncHttpClient();
        client.setResponseTimeout(120 * 1000); // 30 secondes

        if (authorizationHeader != null) {
            client.addHeader("Authorization", authorizationHeader);
        }
        client.get(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String str = new String(responseBody);
                callback.onSuccess(str);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                callback.onFailure(statusCode);
            }
        });
    }

    public void responsePost(String urlApi, RequestParams params, final WSCallBack callback) {
        String url = urlServer + urlApi;
        Log.d("Url", url);

        AsyncHttpClient client = new AsyncHttpClient();
        if (authorizationHeader != null) {
            client.addHeader("Authorization", authorizationHeader);
        }
        client.post(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String response = new String(responseBody);
                callback.onSuccess(response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                callback.onFailure(statusCode);
            }
        });

    }
}

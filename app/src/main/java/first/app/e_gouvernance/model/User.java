package first.app.e_gouvernance.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.loopj.android.http.RequestParams;

import java.io.FileOutputStream;
import java.io.IOException;

import first.app.e_gouvernance.sqLite.LocalAccess;
import first.app.e_gouvernance.sqLite.MySQLiteOpenHelper;
import first.app.e_gouvernance.util.CallWS;
import first.app.e_gouvernance.util.LoginCallBack;
import first.app.e_gouvernance.util.WSCallBack;

public class User {

    private String name;
    private String surname;
    private String userName;

    public User() {
    }

    public User(String name, String surname, String userName) {
        this.name = name;
        this.surname = surname;
        this.userName = userName;
    }

    public void login(String email, String password, Context context, LoginCallBack callback) {
        CallWS webServiceCall = new CallWS();
        String url = "/users/login";

        RequestParams params = new RequestParams();
        params.put("email", email);
        params.put("password", password);

        webServiceCall.responsePost(url, params, new WSCallBack() {
            @Override
            public void onSuccess(String response) {
                Gson gson = new Gson();
                JsonElement jsonElement = gson.fromJson(response, JsonElement.class);
                boolean success = false;
                if (jsonElement != null && jsonElement.isJsonObject()) {
                    JsonObject jsonObject = jsonElement.getAsJsonObject();
                    JsonObject userObject =jsonObject.getAsJsonObject("user");
                        try {
                            FileOutputStream fileOutputStream = context.openFileOutput("user_data.txt", Context.MODE_PRIVATE);
                            fileOutputStream.write(userObject.getAsByte());
                            fileOutputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Log.d("WebService", "Response: " + userObject);
                        success = true;

                }
                callback.onLoginResult(success);
            }

            @Override
            public void onFailure(int statusCode) {
                Log.e("WebService", "Request failed with status code: " + statusCode);
                callback.onLoginResult(false);
            }
        });
    }

    public void addUserToSQLite(Context context) {
        LocalAccess localAccess = new LocalAccess(context);
        MySQLiteOpenHelper dbAccess = localAccess.getDBAccess();
        SQLiteDatabase db= dbAccess.getWritableDatabase();
       String req = "insert into profil(username, name, surname) values";
        req += "(userTest,test,test)";
        db.execSQL(req);
    }
}

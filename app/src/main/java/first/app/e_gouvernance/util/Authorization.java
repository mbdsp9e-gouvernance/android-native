package first.app.e_gouvernance.util;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Authorization {
    public boolean verifyUser(Context context) {
        boolean userExists = false;
        try {
            FileInputStream fileInputStream = context.openFileInput("user_data.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String token = bufferedReader.readLine();
            fileInputStream.close();

            // Vérifiez si le token est non nul et non vide
            if (token != null && !token.isEmpty()) {
                userExists = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return userExists;
    }
}

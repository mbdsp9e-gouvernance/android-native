package first.app.e_gouvernance.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import first.app.e_gouvernance.R;
import first.app.e_gouvernance.controller.UserController;
import first.app.e_gouvernance.util.LoginCallBack;

public class MainActivity extends AppCompatActivity {

    // Propriety login
    private EditText txtEmail;
    private EditText txtPassword;

    private TextView errorLogin;
    //private UserController userController;
    private UserController userController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initLogin();
        this.userController = UserController.getInstance();
    }

    private void initLogin() {
        txtEmail = (EditText) findViewById(R.id.emailLogin);
        txtPassword = (EditText) findViewById(R.id.password);
        errorLogin = (TextView) findViewById(R.id.errorLogin);
        listenLoginButton();

    }

    private void setLoginButtonLoading(boolean isLoading) {
        Button loginButton = findViewById(R.id.btnLogin);
        if (isLoading) {
            loginButton.setText("Chargement...");
            loginButton.setEnabled(false);
        } else {
            loginButton.setText("Se connecter");
            loginButton.setEnabled(true);
        }
    }

    private void listenLoginButton() {
        ((Button) findViewById(R.id.btnLogin)).setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                setLoginButtonLoading(true);
                String emailValue = txtEmail.getText().toString();
                String passwordValue = txtPassword.getText().toString();
                loginResult(emailValue, passwordValue);
            }
        });
    }

    private void loginResult(String email, String password) {
        this.userController.verifyLogin(email, password, MainActivity.this, new LoginCallBack() {
            @Override
            public void onLoginResult(boolean success) {
                if (success) {
                    errorLogin.setText("");
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                    setLoginButtonLoading(false);
                } else {
                    errorLogin.setText("Email ou mot de passe incorrecte");
                    setLoginButtonLoading(false);
                }
            }
        });

    }
}
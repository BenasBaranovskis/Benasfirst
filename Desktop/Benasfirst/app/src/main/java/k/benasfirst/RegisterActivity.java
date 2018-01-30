package k.benasfirst;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    public Button but3;
    public void init() {
        but3 = (Button) findViewById(R.id.button2reg2);
        but3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent reg = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(reg);


            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        init();
        Button btnLogin = (Button) findViewById(R.id.button2reg2);
        final EditText etUsername = (EditText) findViewById(R.id.regusername);
        final EditText etPassword = (EditText) findViewById(R.id.regpassword);
        final EditText etEmail = (EditText) findViewById(R.id.regemail);

        etUsername.setError(null);
        etPassword.setError(null);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //cia vykdomas kodas, kai paspaudziamas mygtukas
                boolean cancel = false;
                if (!Validation.isValidCredentials(etUsername.getText().toString())) {
                    cancel = true;
                    etUsername.requestFocus();
                    etUsername.setError(getResources().getString(R.string.login_invalid_username));
                    //Toast.makeText(LoginActivity.this, getResources().getString(R.string.login_invalid_username),
                    //Toast.LENGTH_SHORT).show();
                } else if (!Validation.isValidCredentials(etPassword.getText().toString())) {
                    cancel = true;
                    etPassword.requestFocus();
                    etPassword.setError(getResources().getString(R.string.login_invalid_password));
                    // Toast.makeText(LoginActivity.this, getResources().getString(R.string.login_invalid_password),
                    // Toast.LENGTH_SHORT).show();
                }
                else if (!Validation.isValidEmail(etEmail.getText().toString())) {
                    cancel = true;
                    etEmail.requestFocus();
                    etEmail.setError(getResources().getString(R.string.register_invalid_Email));
                    // Toast.makeText(LoginActivity.this, getResources().getString(R.string.login_invalid_password),
                    // Toast.LENGTH_SHORT).show();
                }
                if (!cancel) {
                    Toast.makeText(RegisterActivity.this,
                            "Prisijungimo vardas: " + etUsername.getText().toString() + "\n" +
                                    "Slaptažodis: " + etPassword.getText().toString(), Toast.LENGTH_SHORT).show();
                    Intent goToSearchActivity = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(goToSearchActivity);
                }


            }
        });
    }
}



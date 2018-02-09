package k.benasfirst;


import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {
    public Button but2;

    public void init() {
        but2 = (Button) findViewById(R.id.buttonReg);
        but2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent reg = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(reg);


            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        init();
        Button btnLogin = (Button) findViewById(R.id.button2log);
        Button btnRegister = (Button) findViewById(R.id.buttonReg);
        final EditText etUsername = (EditText) findViewById(R.id.EditUsername);
        final EditText etPassword = (EditText) findViewById(R.id.EditPassword);
        final CheckBox cbRememberMe = (CheckBox) findViewById(R.id.cbrememberme);

        final User user = new User(LoginActivity.this);


        etUsername.setError(null);
        etPassword.setError(null);

        cbRememberMe.setChecked(user.isRemembered());

        if (user.isRemembered()) {
                        etUsername.setText(user.getUsernameForLogin(), TextView.BufferType.EDITABLE);
                        etPassword.setText(user.getPasswordForLogin(), TextView.BufferType.EDITABLE);
                   } else {
                        etUsername.setText("", TextView.BufferType.EDITABLE);
                        etPassword.setText("", TextView.BufferType.EDITABLE);
                    }


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                                        Intent goToSearchActivity = new Intent(LoginActivity.this, RegisterActivity.class);
                                        startActivity(goToSearchActivity);

                                    }
        });

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
                else { // praėjo validaciją, kreipiamės į duomenų bazę
                                        DatabaseSQLite databaseSQLite = new DatabaseSQLite(getApplicationContext());

                                                if (databaseSQLite.isValidUser(etUsername.getText().toString(),
                                                        etPassword.getText().toString())) { // rado vartotoją
                                                cancel = false;
                                            } else { // nerado vartotojo
                                                cancel = true;
                                                Toast.makeText(LoginActivity.this, "Tokio vartotojo duomenų bazėje nėra",
                                                                Toast.LENGTH_SHORT).show();
                                            }
                if (!cancel) {
                    Toast.makeText(LoginActivity.this,
                            "Prisijungimo vardas: " + etUsername.getText().toString() + "\n" +
                                    "Slaptažodis: " + etPassword.getText().toString(), Toast.LENGTH_SHORT).show();
                    if(cbRememberMe.isChecked()) {
                        user.setUsernameForLogin(etUsername.getText().toString());
                        user.setPasswordForLogin(etPassword.getText().toString());
                        user.setRemeberMeKeyForLogin(false);

                    }else{
                        user.setRemeberMeKeyForLogin(true);
                    }

                    Intent goToSearchActivity = new Intent(LoginActivity.this, SearchActivity.class);
                    startActivity(goToSearchActivity);
                }


            }
        };

}
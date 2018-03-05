package k.benasfirst;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.List;



        public class RegisterActivity extends AppCompatActivity {
        EditText username;
        EditText password;
        EditText repeatpassword;
        EditText email;
        Button register;

            @Override
    protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_register2);

                username            = (EditText)findViewById(R.id.regusername);
                password            = (EditText)findViewById(R.id.regpassword);
                repeatpassword      = (EditText)findViewById(R.id.repeatpassword);
                email               = (EditText)findViewById(R.id.regemail);

                register = (Button)findViewById(R.id.button2reg2);
                register.setOnClickListener(new View.OnClickListener() {

                    @Override
            public void onClick(View arg0) {

                                        if(!Validation.isValidCredentials(username.getText().toString())){
                                        Toast.makeText(getApplicationContext(),
                                                        "Netinkamas username arba password", Toast.LENGTH_LONG).show();
                                    }else if(!Validation.isValidCredentials(password.getText().toString())){
                                        Toast.makeText(getApplicationContext(),
                                                        "Netinkamas username arba password", Toast.LENGTH_LONG).show();
                                    }else if(!password.getText().toString().equals(repeatpassword.getText().toString())){
                                        Toast.makeText(getApplicationContext(),
                                                        "Nesutampa slaptažodžiai", Toast.LENGTH_LONG).show();
                                    }else if(!Validation.isValidEmail(email.getText().toString())){
                                        Toast.makeText(getApplicationContext(),
                                                        "Netinkamai ivestas email", Toast.LENGTH_LONG).show();
                                   }else{// validated

                                                DatabaseSQLite databaseSQLite = new DatabaseSQLite(getApplicationContext());

                                                User userToSQLite = new User("1",
                                                        username.getText().toString(),
                                                        password.getText().toString(),
                                                        email.getText().toString());

                                                databaseSQLite.addUser(userToSQLite);

                                                List<User> useriai = databaseSQLite.getAllUsers();

                                                for(User user : useriai){
                                                Toast.makeText(getApplicationContext(),
                                                                user.toString(),
                                                                Toast.LENGTH_LONG).show();
                                            }

                                                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                    }
                            }
        });
            }
}



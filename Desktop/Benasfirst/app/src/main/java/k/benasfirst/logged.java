package k.benasfirst;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class logged extends AppCompatActivity {
    public Button but2;

    public void init() {
        but2 = (Button) findViewById(R.id.btnNewEntry);
        but2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent reg = new Intent(logged.this,NewTargetActivity .class);
                startActivity(reg);


            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged);
        init();
    }
}

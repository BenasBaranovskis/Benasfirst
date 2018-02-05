package k.benasfirst;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;

public class NewTargetActivity extends AppCompatActivity {
    public Button but2;

    public void init() {
        but2 = (Button) findViewById(R.id.AddEntries);
        but2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent reg = new Intent(NewTargetActivity.this, SearchActivity.class);
                startActivity(reg);
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_target);
            init();
    }
}

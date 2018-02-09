package k.benasfirst;

import android.widget.Spinner;
import android.widget.Toast;
import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.CheckBox;
        import android.widget.EditText;
        import android.widget.RadioButton;
        import android.widget.RadioGroup;


        public class NewTargetActivity extends AppCompatActivity {
    Button btnAddTarget;
   EditText etId, etName, etWeight, etHeight;
   RadioGroup rbGroup;
  RadioButton rbNewTVery, rbNewTMedium, rbNewTNot;
            CheckBox cbkill, cbIntel, cbSabotage;
   Spinner spinner;
           Pokemonas pokemonas;

          String items[] = {"Aston Martin DB9", "Lamborghini Gallardo", "BMW M3", "Aston Martin DB7"};

           @Override
   protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_target);

                btnAddTarget = (Button) findViewById(R.id.AddEntries);
              etId = (EditText) findViewById(R.id.editTextId);
              etName = (EditText) findViewById(R.id.editTextName);
               etHeight = (EditText) findViewById(R.id.editTextHeight);
               etWeight = (EditText) findViewById(R.id.editTextWeight);

                rbGroup = (RadioGroup) findViewById(R.id.rbGroup);
                rbNewTVery = (RadioButton) findViewById(R.id.NewTRB1);
                rbNewTMedium = (RadioButton) findViewById(R.id.NewTRB2);
                rbNewTNot = (RadioButton) findViewById(R.id.NewTRB3);

                cbkill = (CheckBox) findViewById(R.id.cbKill);
               cbIntel = (CheckBox) findViewById(R.id.cbIntel);
              cbSabotage = (CheckBox) findViewById(R.id.cbSabotage);

                       spinner = (Spinner) findViewById(R.id.NewTSpinner);
                ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,items);
                adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
               spinner.setAdapter(adapter);



                                        btnAddTarget.setOnClickListener(new View.OnClickListener() {
       @Override
      public void onClick(View v) {
                                                         int id = Integer.parseInt(etId.getText().toString());
                                                            String name = etName.getText().toString();
                                                           double weight = Double.parseDouble(etWeight.getText().toString());
                                                            double height = Double.parseDouble(etHeight.getText().toString());
                                                            String rb = "";
                                                            String spinnerText = "";


                                                                    if(rbNewTVery.isChecked()){
                                                                   rb = rbNewTVery.getText().toString();
                                                               }else if(rbNewTMedium.isChecked()){
                                                                   rb = rbNewTMedium.getText().toString();}else{
                                                                  rb = rbNewTNot.getText().toString();}

                                                                    String checkboxText = "";

                                                                    if(cbkill.isChecked()){
                                                                    checkboxText = checkboxText + "KILL TANGO,";
                                                                }

                                                                    if(cbIntel.isChecked()){
                                                                    checkboxText = checkboxText + "GATHER INTEL,";
                                                                }

                                                                    if(cbSabotage.isChecked()){
                                                                    checkboxText = checkboxText + "SABOTAGE TANGO";
                                                                }

                                                                    spinnerText = spinner.getSelectedItem().toString();

                                                                    pokemonas = new Pokemonas();
                                                            pokemonas.setId(id);
                                                           pokemonas.setName(name);
                                                            pokemonas.setHeight(height);
                                                            pokemonas.setWeight(weight);
                                                            pokemonas.setToDo(checkboxText);
                                                            pokemonas.setHostility(rb);
                                                            pokemonas.setCar(spinnerText);

                                                                    toastMessage("ID: " + pokemonas.getId() + "\n" +
                                                                                    "Name: " + pokemonas.getName() + "\n" +
                                                                                    "Weight: " + pokemonas.getWeight() + "\n" +
                                                                                    "Height: " + pokemonas.getHeight() + "\n" +
                                                                                    "Hostility: " + pokemonas.getHostility() + "\n" +
                                                                                    "ToDo: " + pokemonas.getToDo() + "\n" +
                                                                                    "Car: " + pokemonas.getCar());

                                                                    Intent goToSearchActivity = new Intent(NewTargetActivity.this, SearchActivity.class);
                                                            startActivity(goToSearchActivity);
                                                        }
        });
            }

            public void toastMessage(String message){
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
}

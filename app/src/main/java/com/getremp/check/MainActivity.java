package com.getremp.check;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import static com.getremp.check.R.id.autoCompleteTextView1;
import static com.getremp.check.R.id.autoCompleteTextView2;




public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.check.MESSAGE";
    public static AutoCompleteTextView autocomplete2 = null;
    public static AutoCompleteTextView autocomplete = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        String[] arr = {"ראשון לציון-משה דיין", "תל אביב-השלום", "תחנה מרכזית באר שבע",
                "שער ראשון", "שער יפו"};

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        autocomplete = (AutoCompleteTextView)
                findViewById(autoCompleteTextView1);

        autocomplete2 = (AutoCompleteTextView)
                findViewById(autoCompleteTextView2);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item, arr);

        autocomplete.setThreshold(2);
        autocomplete.setAdapter(adapter);

        autocomplete2.setThreshold(2);
        autocomplete2.setAdapter(adapter);



    }


    public void sendMessage(View view) {

        Intent intent = new Intent(this, Main2Activity.class);
        intent.putExtra("Source", autocomplete.getText().toString());
        intent.putExtra("Dest", autocomplete2.getText().toString());

        startActivity(intent);

    }
    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

}


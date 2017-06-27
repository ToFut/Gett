package com.getremp.check;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;


public class DeafultPage extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.check.MESSAGE";
    public static AutoCompleteTextView autocomplete2 = null;
    public static AutoCompleteTextView autocomplete = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.defultpage);


        String[] arr = {"ראשון לציון-משה דיין", "תל אביב-השלום", "תחנה מרכזית באר שבע",
                "שער ראשון", "שער יפו"};

        autocomplete = (AutoCompleteTextView)
                findViewById(R.id.autoCompleteTextView1);

        autocomplete2 = (AutoCompleteTextView)
                findViewById(R.id.autoCompleteTextView2);


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


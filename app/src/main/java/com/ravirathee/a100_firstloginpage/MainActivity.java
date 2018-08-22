package com.ravirathee.a100_firstloginpage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemSelectedListener {


    Button button;
    int number_of_attempts;
    Spinner roll_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button = findViewById(R.id.button);
        button.setOnClickListener(this);

        number_of_attempts = 4;

        roll_number = findViewById(R.id.roll_number);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.role_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        roll_number.setAdapter(adapter);
        roll_number.setOnItemSelectedListener(this);



    }

    @Override
    public void onClick(View view) {

        EditText name = findViewById(R.id.name);
        String nameString = name.getText().toString();
        EditText password = findViewById(R.id.password);
        String passwordString = password.getText().toString();

        //EditText roll_number = findViewById(R.id.roll_number);
        //String roll_numberString = roll_number.getText().toString();


        String roll_numberString = roll_number.getSelectedItem().toString();


        switch (view.getId()) {
            case R.id.button: {


                Log.d("Hello", "onClick: button clicked! " + number_of_attempts);


                if (nameString.length() < 8) {
                    Toast.makeText(this, "Password length should be atleast 8", Toast.LENGTH_LONG).show();
                } else if (roll_numberString.equals(passwordString) == false) {
                    number_of_attempts -= 1;
                    Toast.makeText(this, "Roll Number is incorrect! " + number_of_attempts + "attempts left", Toast.LENGTH_SHORT).show();
                } else if (number_of_attempts > 0 && roll_numberString.equals(passwordString)) {
                    number_of_attempts = 4;
                    Toast.makeText(this, "Login Succesful !!", Toast.LENGTH_SHORT).show();
                }


                if (number_of_attempts <= 0) {
                    button.setEnabled(false);
                    Toast.makeText(this, "Cant Login Now", Toast.LENGTH_SHORT).show();
                }
            }
        }


    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
        String text = parent.getItemAtPosition(i).toString();
        Toast.makeText(parent.getContext(),text,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
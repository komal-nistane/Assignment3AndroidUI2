package com.example.uiassignment2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name, qual;
    TextView resName, respAge, respQual;
    Spinner spinnerAge;
    Button btnSave, btnCancel;
    ArrayAdapter<String> adapter ;
    RelativeLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponenets();

    }

    private void initComponenets() {
        qual = (EditText) findViewById(R.id.etQual);
        name = (EditText) findViewById(R.id.etName);
        spinnerAge = (Spinner) findViewById(R.id.spinnerAge);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnSave = (Button) findViewById(R.id.btnSave);
        resName = (TextView)findViewById(R.id.etResName);
        respAge = (TextView)findViewById(R.id.respAge);
        respQual = (TextView)findViewById(R.id.respQual);

       /* respQual.setEnabled(false);
        respAge.setEnabled(false);
        resName.setEnabled(false);*/

        String[] arrayAgeSpinner = new String[] {
                "Select","Between 20 to 25", "Between 25 to 30", "Between 30 to 35", "Between 35 to 40", "Between 40 to 45", "Between 45 to 50", "Above 50"};
        adapter= new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arrayAgeSpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAge.setAdapter(adapter);
        btnSave.setOnClickListener(listener);
        btnCancel.setOnClickListener(listener);
         mainLayout=(RelativeLayout)this.findViewById(R.id.relLayout);
        mainLayout.setVisibility(RelativeLayout.GONE);


    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.btnSave) {
                saveData();
            } else if (view.getId() == R.id.btnCancel) {
                clearFields();
            }
        }
    };

    private void saveData() {

        String nameRes = name.getText().toString().trim();
        String ageRes = spinnerAge.getSelectedItem().toString().trim();
        String qualRes = qual.getText().toString().trim();
        if(validateFields(nameRes , ageRes, qualRes)){
            resName.setText(nameRes);
            respAge.setText(ageRes);
            respQual.setText(qualRes);
            mainLayout.setVisibility(RelativeLayout.VISIBLE);
        }
    }

    private boolean validateFields(String nameRes, String ageRes, String qualRes) {
        if (TextUtils.isEmpty(nameRes)) {
            Toast.makeText(getApplicationContext(), "Please Enter Name", Toast.LENGTH_SHORT).show();
            name.requestFocus();
            return false;
        }
        if (spinnerAge.getSelectedItemPosition()==0) {
            Toast.makeText(getApplicationContext(), "Please Enter Age",  Toast.LENGTH_SHORT).show();
            spinnerAge.setFocusable(true);
            spinnerAge.setFocusableInTouchMode(true);
            spinnerAge.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(qualRes)) {
            Toast.makeText(getApplicationContext(), "Please Enter Qualification",  Toast.LENGTH_SHORT).show();
            qual.requestFocus();
            return false;
        }
        return true;
    }

    private void clearFields() {
        name.setText("");
        qual.setText("");
        resName.setText("");
        respAge.setText("");
        respQual.setText("");
        spinnerAge.setSelection(0);
        mainLayout.setVisibility(RelativeLayout.GONE);
    }
}

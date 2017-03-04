package com.example.administrator.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String amount;
    EditText totalAmount,persNbr;
    TextView display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        totalAmount=(EditText)findViewById(R.id.etAmount);
        persNbr=(EditText)findViewById(R.id.etNbr);
        display=(TextView)findViewById(R.id.tvAmountPerPerson);
    }

    public void btnCompute(View w){
        Double total=Double.parseDouble(totalAmount.getText().toString());
        Double persons=Double.parseDouble(persNbr.getText().toString());
        Double share=total/persons;
        share = Double.valueOf(Math.round(share+100));
        share=share/100;

        //Toast.makeText(getBaseContext(),"the amount to show is "+share,Toast.LENGTH_SHORT).show();
        display.setText(String.valueOf(share));
    }
}

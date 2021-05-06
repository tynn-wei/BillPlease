package com.example.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    EditText etAmountInput;
    EditText etNumPaxInput;
    ToggleButton tbNoSvs;
    ToggleButton tbGst;
    TextView tvTotalBill;
    TextView tvEachPays;
    Button btnSplit;
    Button btnReset;
    EditText etDiscInput;
    RadioGroup rgPaymentType;
    RadioButton rbCash;
    RadioButton rbPayNow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etAmountInput = findViewById(R.id.etAmountInput);
        etNumPaxInput = findViewById(R.id.etNumPaxInput);
        tbNoSvs = findViewById(R.id.tbNoSvs);
        tbGst = findViewById(R.id.tbGst);
        tvTotalBill = findViewById(R.id.tvTotalBill);
        tvEachPays = findViewById(R.id.tvEachPays);
        btnSplit = findViewById(R.id.btnSplit);
        btnReset = findViewById(R.id.btnRst);
        etDiscInput = findViewById(R.id.etDiscInput);
        rgPaymentType = findViewById(R.id.rgPaymentMethod);
        rbCash = findViewById(R.id.rbCash);
        rbPayNow = findViewById(R.id.rbPayNow);

        btnSplit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(etAmountInput.getText().toString().trim().length() != 0 && etNumPaxInput.getText().toString().trim().length() != 0) {

                    double initialAmount = Double.parseDouble(etAmountInput.getText().toString());
                    double discAmount = 0.0;

                    if(!tbNoSvs.isChecked() && !tbGst.isChecked()){
                        discAmount = initialAmount;

                    } else if(!tbNoSvs.isChecked() && tbGst.isChecked()) {
                        discAmount = initialAmount * 1.07;

                    } else if(tbNoSvs.isChecked() && !tbGst.isChecked()) {
                        discAmount = initialAmount * 1.10;

                    } else if(tbNoSvs.isChecked() && tbGst.isChecked()) {
                        discAmount = initialAmount * 1.17;
                    }

                    if (etDiscInput.getText().toString().trim().length() != 0) {
                        discAmount *= 1 - (Double.parseDouble(etDiscInput.getText().toString()) / 100);
                    }

                    String payment = " in cash";
                    if(rgPaymentType.getCheckedRadioButtonId() == R.id.rbPayNow) {
                        payment = " via PayNow to 91791596";
                    }



                    tvTotalBill.setText("Total Bill: $" + String.format("%.2f", discAmount/Double.parseDouble(etNumPaxInput.getText().toString()) + payment));

                    int people = Integer.parseInt(etNumPaxInput.getText().toString());

                    if(people > 1){
                        tvEachPays.setText("Each Pays: $" + String.format("%.2f", discAmount/people + payment));
                    } else if (people <= 1){
                        tvEachPays.setText("Each Pays: $" + String.format("%.2f", discAmount) + payment);
                    }

                }


            }
        });

    }








}
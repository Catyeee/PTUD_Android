package com.example.firstprogram;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
EditText txtX,txtY;
TextView txtResult;
Button btnPlus, btnMinus, btnMul, btnDiv, btnMod;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        innitControl();
    }
    private void innitControl()
    {
        txtX=findViewById(R.id.txtX);
        txtY=findViewById(R.id.txtY);
        txtResult=findViewById(R.id.txtResult);
        btnPlus=findViewById(R.id.btnPlus);
        btnMinus=findViewById(R.id.btnMinus);
        btnMul=findViewById(R.id.btnMul);
        btnDiv=findViewById(R.id.btnDiv);
        btnMod=findViewById(R.id.btnMod);
//        btnPlus.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                int x=Integer.parseInt(txtX.getText().toString());
//                int y=Integer.parseInt(txtY.getText().toString());
//                int result=x+y;
//                txtResult.setText(String.valueOf(result));
//            }
//        });
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtX.getText().toString().isEmpty() ||
                        txtY.getText().toString().isEmpty()) {
                    txtResult.setText("Nhập đủ X và Y");
                    return;
                }
                double x = Double.parseDouble(txtX.getText().toString());
                double y = Double.parseDouble(txtY.getText().toString());
                double result = 0;

                if (v == btnPlus) {
                    result = x + y;
                } else if (v == btnMinus) {
                    result = x - y;
                } else if (v == btnMul) {
                    result = x * y;
                } else if (v == btnDiv) {
                    if (y == 0) {
                        txtResult.setText("Không chia cho 0");
                        return;
                    }
                    result = x / y;
                } else if (v == btnMod) {
                    result = x % y;
                }

                txtResult.setText(String.valueOf(result));
            }
        };
        btnPlus.setOnClickListener(listener);
        btnMinus.setOnClickListener(listener);
        btnMul.setOnClickListener(listener);
        btnDiv.setOnClickListener(listener);
        btnMod.setOnClickListener(listener);
    }
}

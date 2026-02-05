package com.example.danhsachnv;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    private TextView tvId, tvName, tvAge;
    private Button btnDelete, btnBack;
    private SQLiteHelper dbHelper;
    private String employeeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvId = findViewById(R.id.tv_id);
        tvName = findViewById(R.id.tv_name);
        tvAge = findViewById(R.id.tv_age);
        btnDelete = findViewById(R.id.btn_delete);
        btnBack = findViewById(R.id.btn_back);

        dbHelper = new SQLiteHelper(this);

        // Lấy data từ Intent
        employeeId = getIntent().getStringExtra("id");
        String name = getIntent().getStringExtra("name");
        int age = getIntent().getIntExtra("age", 0);

        tvId.setText(getString(R.string.label_id) + " " + employeeId);
        tvName.setText(getString(R.string.label_name) + " " + name);
        tvAge.setText(getString(R.string.label_age) + " " + age);

        btnDelete.setOnClickListener(v -> {
            dbHelper.delete(employeeId);
            setResult(RESULT_OK); // Để MainActivity biết cần refresh
            finish();
        });

        btnBack.setOnClickListener(v -> finish());
    }
}
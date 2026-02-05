package com.example.danhsachnv;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView lvEmployees;
    private SQLiteHelper dbHelper;
    private List<Employee> employeeList;
    private ArrayAdapter<String> adapter;

    private final ActivityResultLauncher<Intent> detailLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    loadEmployees(); // Refresh ListView sau xóa
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvEmployees = findViewById(R.id.lv_employees);
        dbHelper = new SQLiteHelper(this);

        // Khởi tạo DB và thêm mẫu
        dbHelper.getWritableDatabase(); // Tạo DB nếu chưa có
        dbHelper.insertSampleData();

        loadEmployees();

        // Long click để mở chi tiết
        lvEmployees.setOnItemLongClickListener((parent, view, position, id) -> {
            Employee selected = employeeList.get(position);
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("id", selected.getId());
            intent.putExtra("name", selected.getName());
            intent.putExtra("age", selected.getAge());
            detailLauncher.launch(intent);
            return true;
        });
    }

    private void loadEmployees() {
        employeeList = dbHelper.getAll();
        List<String> names = new ArrayList<>();
        for (Employee emp : employeeList) {
            names.add(emp.getName());
        }
        adapter = new ArrayAdapter<>(this, R.layout.item_employee, R.id.tv_name, names);
        lvEmployees.setAdapter(adapter);
    }
}
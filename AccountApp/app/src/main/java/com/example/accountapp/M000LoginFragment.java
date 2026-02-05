package com.example.accountapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class M000LoginFragment extends Fragment {

    private EditText edtEmail, edtPass;
    private TextView tvLogin, tvRegister;
    private SQLiteHelper db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.m000_frg_login, container, false);

        edtEmail = v.findViewById(R.id.edt_email);
        edtPass = v.findViewById(R.id.edt_pass);
        tvLogin = v.findViewById(R.id.tv_login);
        tvRegister = v.findViewById(R.id.tv_register); // Ánh xạ nút đăng ký

        db = new SQLiteHelper(requireContext());

        tvLogin.setOnClickListener(view -> login());

        // Sự kiện khi bấm nút đăng ký (tv_register) để chuyển sang form đăng ký
        tvRegister.setOnClickListener(view -> gotoRegister());

        return v;
    }

    private void gotoRegister() {
        if (getActivity() != null) {
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.ln_main, new M001RegisterFragment())
                    .addToBackStack(null) // Để có thể bấm Back quay lại màn Login
                    .commit();
        }
    }

    private void login() {
        String email = edtEmail.getText().toString().trim();
        String pass = edtPass.getText().toString().trim();

        if (email.isEmpty() || pass.isEmpty()) {
            Toast.makeText(getActivity(), "Nhập đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        if (db.login(email, pass)) {
            Toast.makeText(getActivity(), "Login thành công", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "Sai email hoặc mật khẩu", Toast.LENGTH_SHORT).show();
        }
    }
}

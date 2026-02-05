package com.example.accountapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class M001RegisterFragment extends Fragment {

    private EditText edtEmail, edtPass, edtRePass;
    private TextView tvRegister;
    private ImageView ivBack;
    private SQLiteHelper db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.m001_frg_register, container, false);

        initViews(v);
        db = new SQLiteHelper(requireContext());

        return v;
    }

    private void initViews(View v) {
        edtEmail = v.findViewById(R.id.edt_email);
        edtPass = v.findViewById(R.id.edt_pass);
        edtRePass = v.findViewById(R.id.edt_re_pass);
        tvRegister = v.findViewById(R.id.tv_register);
        ivBack = v.findViewById(R.id.iv_back);

        tvRegister.setOnClickListener(view -> register());
        ivBack.setOnClickListener(view -> backToLogin());
    }

    private void register() {
        String email = edtEmail.getText().toString().trim();
        String pass = edtPass.getText().toString().trim();
        String re = edtRePass.getText().toString().trim();

        if (email.isEmpty() || pass.isEmpty() || re.isEmpty()) {
            Toast.makeText(getActivity(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!pass.equals(re)) {
            Toast.makeText(getActivity(), "Mật khẩu xác nhận không khớp", Toast.LENGTH_SHORT).show();
            return;
        }

        if (db.checkEmailExists(email)) {
            Toast.makeText(getActivity(), "Email này đã được đăng ký", Toast.LENGTH_SHORT).show();
            return;
        }

        Account acc = new Account(email, pass);
        if (db.insert(acc)) {
            Toast.makeText(getActivity(), "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
            backToLogin();
        } else {
            Toast.makeText(getActivity(), "Đăng ký thất bại, vui lòng thử lại", Toast.LENGTH_SHORT).show();
        }
    }

    private void backToLogin() {
        if (getFragmentManager() != null) {
            getFragmentManager().popBackStack();
        }
    }
}

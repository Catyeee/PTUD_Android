package com.example.mutillaguageapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.TextView;
import android.view.Menu;
import android.view.MenuItem;


import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final String PREF = "lang_pref";
    private static final String KEY_LANG = "lang";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // áp ngôn ngữ đã lưu trước khi setContentView
        applySavedLanguage();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvVi = findViewById(R.id.tvVi);
        TextView tvEn = findViewById(R.id.tvEn);
        TextView tvFr = findViewById(R.id.tvFr);

        tvVi.setOnClickListener(v -> changeLanguage("vi"));
        tvEn.setOnClickListener(v -> changeLanguage("en"));
        tvFr.setOnClickListener(v -> changeLanguage("fr"));
    }

    private void changeLanguage(String lang) {
        saveLanguage(lang);
        setLocale(lang);
        recreate(); // reload UI strings
    }

    private void saveLanguage(String lang) {
        SharedPreferences sp = getSharedPreferences(PREF, MODE_PRIVATE);
        sp.edit().putString(KEY_LANG, lang).apply();
    }

    private void applySavedLanguage() {
        SharedPreferences sp = getSharedPreferences(PREF, MODE_PRIVATE);
        String lang = sp.getString(KEY_LANG, null);
        if (lang != null) setLocale(lang);
    }

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);

        Configuration config = new Configuration(getResources().getConfiguration());
        config.setLocale(locale);

        Context ctx = createConfigurationContext(config);
        getResources().updateConfiguration(config, ctx.getResources().getDisplayMetrics());
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lang, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.mn_vi) {
            changeLanguage("vi");
            return true;
        } else if (id == R.id.mn_en) {
            changeLanguage("en");
            return true;
        } else if (id == R.id.mn_fr) {
            changeLanguage("fr");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}

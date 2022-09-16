package com.example.demolangauge.ui.login;

import android.app.Activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demolangauge.R;
import com.example.demolangauge.ui.login.LoginViewModel;
import com.example.demolangauge.ui.login.LoginViewModelFactory;
import com.example.demolangauge.databinding.ActivityLoginBinding;

import java.util.Locale;

public class LoginActivity extends AppCompatActivity {


    private ActivityLoginBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loadLocale();
        binding.btnchangeLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String languages []= {"English","Hindi","Urdu","Arabic"};


                AlertDialog.Builder mBuilder = new AlertDialog.Builder(LoginActivity.this);
                mBuilder.setTitle("Choose Language");
                mBuilder.setSingleChoiceItems(languages, -1, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (which == 0){
                            setLocale("");
                            recreate();

                        }
                       else if (which == 1){
                            setLocale("hi");

                            recreate();

                        }
                       else if (which == 2){
                            setLocale("ur");

                            recreate();

                        }
                        else if (which == 3){
                            setLocale("ar");

                            recreate();

                        }
                    }

                });
                mBuilder.create();
                mBuilder.show();

            }
        });
    }

    private void loadLocale() {
    }

    private void setLocale(String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Configuration configuration = new Configuration();
        configuration.locale =locale;
      getBaseContext().getResources().updateConfiguration(configuration ,
              getBaseContext().getResources().getDisplayMetrics());

        SharedPreferences.Editor editor = getSharedPreferences("Settings",MODE_PRIVATE).edit();
        editor.putString("app_lang", language);
        editor.apply();



    }
    private void LoadLocale(){
        SharedPreferences preferences = getSharedPreferences("Settings",MODE_PRIVATE);
        String language = preferences.getString("app_lan","");
        setLocale(language);
    }
}
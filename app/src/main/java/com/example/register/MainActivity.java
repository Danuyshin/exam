package com.example.register;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
EditText name,secondname,mail,password,password2;
Spinner country;
CheckBox news;
Button button;
    private final static String FILE_NAME = "content.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=findViewById(R.id.name);
        secondname=findViewById(R.id.secondname);
        mail=findViewById(R.id.mail);
        country=findViewById(R.id.country);
        password=findViewById(R.id.password);
        password2=findViewById(R.id.password2);
        news=findViewById(R.id.news);
        button=findViewById(R.id.button);
        String[] countries = { "Гамбия", "Германия", "Иран", "Намибия", "Науру","Панама","Словакия","Тувалу","Эсватини","Эфиопия"};
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, countries);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        country.setAdapter(adapter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.getText().toString().isEmpty() || secondname.getText().toString().isEmpty() || mail.getText().toString().isEmpty() || password.getText().toString().isEmpty() || password2.getText().toString().isEmpty() ) {
                    Toast.makeText(MainActivity.this, "Введите все данные", Toast.LENGTH_LONG).show();
                    return;
                }
                if (!password.getText().toString().equals(password2.getText().toString())) {
                    Toast.makeText(MainActivity.this, "Пароли не совпадают", Toast.LENGTH_LONG).show();
                    return;
                }
                FileOutputStream fos = null;


                String text = "Имя: "+name.getText().toString()+"\n"+
                        "Фамилия: "+secondname.getText().toString()+"\n"+
                        "Почта: "+mail.getText().toString()+"\n"+
                        "Пароль: "+password.getText().toString()+"\n"+
                        "Повторый пароль: "+password2.getText().toString()+"\n"+
                        "Страна: "+country.getSelectedItem().toString()+"\n"+
                        "Новости: "+news.isChecked();


                try {



                    fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
                    fos.write(text.getBytes());
                    Toast.makeText(MainActivity.this, "Файл сохранен", Toast.LENGTH_SHORT).show();
                }
                catch(IOException ex) {

                    Toast.makeText(MainActivity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
                finally{
                    try{
                        if(fos!=null)
                            fos.close();
                    }
                    catch(IOException ex){

                        Toast.makeText(MainActivity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }


            }
            });

    }
}
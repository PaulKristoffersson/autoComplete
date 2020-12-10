package com.projekt.autocomplete;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.edit_text);
        String[] data = new String[]{"Cool T-shirt", "Herr T-shirt 1", "Dam T-shirt 1", "Dam T-shirt 2","Hej"};
        AutoComplete searcher = new AutoComplete(this,editText,data);

        //Overridar metoden run från interfacet logic. Kan däremed förändra beteended då ett element klickas i listan från MainActivity
        /*searcher.setLogic(new Logic() {
            @Override
            public void run(String s) {

            }
        });*/
        searcher.start(0);
    }
}
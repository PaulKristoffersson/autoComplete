package com.projekt.autocomplete;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
public class AutoCompleteAdapter extends ArrayAdapter<String> {
    Context context;
    String [] array;
    int end;
    public AutoCompleteAdapter(Context context, String[] array, int end) {
        super(context, 0, array);
        this.array = array;
        this.context = context;
        this.end = end;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Skapar en ny textView för att rita ut elementen, elementen skickas ur arrayen steg för steg och färgen de ska ha
        AutoCompleteTextView result  = new AutoCompleteTextView(context, array[position], Color.BLACK);;
        //Ifall case 0 har valts så ska elementen som inte matchar ha röd färg på texten
        //Detta utförs genom att ändra färgen från svart till röd om element fortfarande ritas ut då position överstiger antalet matchingar
        if(position >= end)
            result.setColor(Color.RED);
        return result;
    }
    public String getName(int position){
        assert(position >= 0 && position < array.length);
        return array[position];
    }
}
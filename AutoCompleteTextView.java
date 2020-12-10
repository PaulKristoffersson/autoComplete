package com.projekt.autocomplete;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
public class AutoCompleteTextView extends View{
    Paint names = new Paint();
    String name;
    int color;
    public AutoCompleteTextView(Context context, String name, int color) {
        super(context);
        this.name = name;
        this.color = color;
    }
    //Metod att sätta färgen på texten som ska skrivas ut
    public void setColor(int color){
        this.color = color;
    }
    @Override
    //Bestämmer dimensionerna textens ruta ska ha
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(500,100);
    }
    //Sätter färg och textstorlek
    @Override
    protected void onDraw(Canvas canvas) {
        names.setColor(color);
        names.setTextSize(50);
        if(name != null)
            canvas.drawText(name, 100, 80, names);
        else
            canvas.drawText("", 40, 50, names);
    }
}
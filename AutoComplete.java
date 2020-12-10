package com.projekt.autocomplete;


import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListPopupWindow;

import java.util.ArrayList;

public class AutoComplete extends View implements Logic{
    ListPopupWindow listPopup;
    AutoCompleteAdapter adapter;
    EditText editText;
    Context context;
    String written;
    ArrayList<String> list;
    String[] data;
    int behaviour;
    private Logic logic;
    String lastMatch;

    public AutoComplete(Context context, EditText editText, String[] data) {
        super(context);
        this.context = context;
        this.editText = editText;
        this.data = data;
        this.list = new ArrayList<String>();
        //initialize();
    }

    public void initialize() {
        int behaviour = 0;
    }

    public void setBehaviour(int behaviour) {
        this.behaviour = behaviour;
    }

    public void start(final int behaviour) {
        listPopup = new ListPopupWindow(context);

        //OnClickListener för vår listPopUpWindow
        listPopup.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Beteendet av komponenten då ett elementen i listan klickas på kan förändras i run metoden i main.
                run(list.get(position));
            }
        });
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                list.clear();
                listPopup.setAnchorView(editText);
                //Kollar så något har skrivits in i textfältet och ifall det finns gör vi om det till en sträng
                if (s.length() > 0)
                    written = s.toString();
                else {
                    //Ifall inget finns låter vi den vara tom och stänger ner fönstret. Behövs då man tar bort alla bokstäver i fältet
                    written = "";
                    listPopup.dismiss();
                    return;
                }
                //Går igenom alla element i vår string array och kollar ifall t innehåller något av orden, alltså att de är potentiella matchningar sökaren vill få. Läggs till i listan isåfall
                for (String t : data) {
                    if (t.contains(written))
                        list.add(t);
                }

                int end = list.size();
                //Gör en switch beroende på vilket behaviour användar valt i Main.
                switch (behaviour) {
                    //Ifall användaren valt behaviour noll lägger vi till resterande element i listan
                    case 0:
                        for (String t : data) {
                            if (!list.contains(t))
                                list.add(t);
                        }
                        break;
                    //Ifall användaren valt behavoir ett skrivs första matchningen av ordet in automatiskt i textfältet
                    case 1:
                        System.out.println(s);
                        if(!s.toString().equals(lastMatch)) {
                            if (list.size() == 1) {
                                lastMatch = list.get(0);
                                editText.setText(lastMatch);

                            }
                        }
                        break;
                }
                int length = list.size();
                //Initierar en ny array med samma längd som listan
                String[] array = new String[length];
                //Lägger över alla element från listan på rätt positioner i arrayen
                for (int i = 0; i < length; i++)
                    array[i] = list.get(i);
                //Initierar adaptern och skickar in context, vår array med matchningar och dess längd.
                adapter = new AutoCompleteAdapter(context, array, end);
                adapter.notifyDataSetChanged();
                //Sätter adaptern på vår listPopup
                listPopup.setAdapter(adapter);
                //Visar listan
                listPopup.show();

            }
        });
    }

    public void matchFirst(){
        if(list.size()==1) {
            editText.setText(list.get(0));
        }
    }

    public void chooseBehaviour(int chosenBehaviour){
        behaviour = chosenBehaviour;
    }

    public void setLogic(Logic l){
        this.logic = l;
    }

    //Overridar run från interfacet Logic, bestämmer att elementet som klickas på i listan ska skrivas in i textfältet.
    @Override
    public void run(String s ) {
        editText.setText(s);
        list.clear();
    }
}

package com.projekt.autocomplete;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ListPopupWindow;
import java.util.ArrayList;

public class AutoComplete extends View {
    ListPopupWindow listPopup;
    AutoCompleteAdapter adapter;
    EditText editText;
    Context context;
    String written;

    String[] data = new String[]{"Cool T-shirt", "Herr T-shirt 1", "Dam T-shirt 1", "Dam T-shirt 2"};

    public AutoComplete(Context context, EditText editText, String[] data) {
        super(context);
        this.context = context;
        this.editText = editText;
        this.data = data;
    }

    public void Run() {
        listPopup = new ListPopupWindow(context);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(final CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(final Editable s) {
                ArrayList<String> list = new ArrayList<String>();
                listPopup.setAnchorView(editText);
                if(s.length()>0)
                    written = s.toString();
                else {
                    written = "";
                    listPopup.dismiss();
                    return;
                }
                for(String t: data){
                    if(t.contains(written))
                        list.add(t);
                }
                String[] array = (String[]) list.toArray();
                adapter = new AutoCompleteAdapter(context, array);
                adapter.notifyDataSetChanged();
                listPopup.setAdapter(adapter);
                listPopup.show();
            }
        });
    }
}

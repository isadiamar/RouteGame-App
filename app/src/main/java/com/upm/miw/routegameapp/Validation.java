package com.upm.miw.routegameapp;

import android.text.TextUtils;
import android.widget.EditText;

import com.upm.miw.routegameapp.R;

public class Validation {

    public static boolean validate(EditText et) {
        boolean valid = true;

        String input = et.getText().toString();
        if (TextUtils.isEmpty(input)) {
            et.setError(Integer.toString(R.string.field_required));
            et.requestFocus();
            valid = false;
        } else {
            et.setError(null);
        }
        return valid;
    }


}

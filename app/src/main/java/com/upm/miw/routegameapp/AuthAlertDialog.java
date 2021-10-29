package com.upm.miw.routegameapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

public class AuthAlertDialog extends DialogFragment {
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("An error occurred authenticating the user")
                .setPositiveButton(android.R.string.ok, null);
        return builder.create();
    }
}

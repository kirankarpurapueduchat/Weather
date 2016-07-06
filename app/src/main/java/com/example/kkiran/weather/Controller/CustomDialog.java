package com.example.kkiran.weather.Controller;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by kkiran on 01/07/16.
 */
public class CustomDialog extends android.app.DialogFragment{
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Oops! No Network")
                .setMessage("Please check your network connection")
                .setPositiveButton("OK", null);
        AlertDialog dialog = builder.create();
        return dialog;

    }
}


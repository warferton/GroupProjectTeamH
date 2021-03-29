package com.teamh.teamhfinalproject.ui.activities;

//this class shows message if user did not check the filters

import android.content.Context;
import android.widget.Toast;

public class MessageFilterNotChecked {
    public static void message(Context context, String msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}

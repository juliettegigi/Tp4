package com.softulp.app.tp4;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

public class WifiConectado extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getBooleanExtra("connected",false)){
             Intent intentLlamada=new Intent(Intent.ACTION_CALL);
             intentLlamada.setData(Uri.parse("tel:"+"444"));
             intentLlamada.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
             context.startActivity(intentLlamada);
        }
        else{
            Toast.makeText( context,"wifi desconectado.", Toast.LENGTH_LONG).show();
        }

    }
}
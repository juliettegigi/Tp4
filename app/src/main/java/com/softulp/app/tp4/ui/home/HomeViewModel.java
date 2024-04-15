package com.softulp.app.tp4.ui.home;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.math.BigInteger;

public class HomeViewModel extends AndroidViewModel {

    private  MutableLiveData<String> mutableTextoError;

    public HomeViewModel(@NonNull Application application) {
        super(application);
    }


    public LiveData<String> getMutableTextoError() {
        if(mutableTextoError==null)
            mutableTextoError=new MutableLiveData<>();
        return mutableTextoError;
    }

    public void llamarAlNumero(String numero){

        String regex = "\\d+";
        if (numero.matches(regex)) {
            Context context=getApplication().getApplicationContext();
            mutableTextoError.setValue("");
            Intent intentLlamar=new Intent(Intent.ACTION_CALL);
            intentLlamar.setData(Uri.parse("tel:"+numero));
            intentLlamar.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intentLlamar);
        } else {
            mutableTextoError.setValue("Error: Debe ingresar un número.");
        }


    }

}
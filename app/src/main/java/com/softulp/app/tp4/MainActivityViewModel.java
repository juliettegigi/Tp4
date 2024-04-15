package com.softulp.app.tp4;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class MainActivityViewModel extends AndroidViewModel{
    private MutableLiveData<String> mutableMsgError;
    public MainActivityViewModel(@NonNull Application application) {
        super(application);
    }
    public LiveData<String> getMutableMsgError(){
        if(mutableMsgError==null)
            mutableMsgError=new MutableLiveData<>();
        return mutableMsgError;
    }

    public void validarUsuario(String user,String pass){
        if(user.equalsIgnoreCase("julia") && pass.equals("123")){
            Context context =getApplication().getApplicationContext();

            Intent intent=new Intent(context, MenuNavegableActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
        else{
            mutableMsgError.setValue("Usuario o contraseña incorrecta, lo correcto es \"julia\" \"123\", respectivamente");
        }
    }

}

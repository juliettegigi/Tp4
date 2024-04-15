package com.softulp.app.tp4;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.softulp.app.tp4.databinding.ActivityMainBinding;
import static android.Manifest.permission.CALL_PHONE;
import android.net.wifi.WifiManager;

public class MainActivity extends AppCompatActivity {
   private ActivityMainBinding binding;
   private   WifiConectado wifi;

    MainActivityViewModel vm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        vm=new ViewModelProvider(this).get(MainActivityViewModel.class);
        setContentView(binding.getRoot());
        solicitarPermisos();
        registrarBroadcast();
        vm.getMutableMsgError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.tvError.setText(s);
            }
        });

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vm.validarUsuario(binding.etUser.getText().toString(),binding.etPass.getText().toString());
            }
        });
    }

    private void solicitarPermisos(){
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M
                && checkSelfPermission(CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{CALL_PHONE},1000);
        }
    }


    private void registrarBroadcast(){
         wifi=new WifiConectado();
          registerReceiver(wifi, new IntentFilter("android.net.wifi.supplicant.CONNECTION_CHANGE"));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(wifi);
    }
}
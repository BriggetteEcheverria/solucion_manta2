package com.uniscan.solucion_manta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    public static final int ACCESS_FINE_LOCATION_REQUEST = 1;
    public static final int BLUETOOTH_REQUEST = 2;
    BluetoothAdapter adapter;
    public static final String bUuid = "00001101-0000-1000-8000-00805F9B34FB";

    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            switch (action) {
                case BluetoothDevice.ACTION_ACL_CONNECTED:
                    break;
                case BluetoothDevice.ACTION_ACL_DISCONNECTED:
                    if (BluetoothConnection.getInstance() != null) {
                        BluetoothConnection.getInstance().closeSocket();
                    }
                    break;
            }
        }
    };

    private void initialize() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        adapter = BluetoothAdapter.getDefaultAdapter();

        BluetoothDevice device = adapter.getRemoteDevice("CC:78:AB:9E:50:FB");
        new BluetoothConnection(device, bUuid);
        if (BluetoothConnection.getInstance() != null) {
            BluetoothConnection.getInstance().start();
            prefs.edit().putString("mac", device.getAddress()).apply();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
        Button btn = (Button)findViewById(R.id.inventarioBtn);
        Button btnPicking = (Button)findViewById(R.id.pickingBtn);
        Button btnReEtiquetado = (Button)findViewById(R.id.reEtiquetadoBtn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Inventario.class));
            }
        });

        btnPicking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Picking.class));
            }
        });

        btnReEtiquetado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ReEtiquetado.class));
            }
        });

    }

}
package com.gameflyy.booster;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import com.gameflyy.booster.R;

public class MainActivity extends Activity {

    // Deklarasi Komponen UI
    private TextView tvStatus, tvRam, tvSuhu, tvNetwork, tvSelectedGame;
    private Button btnBoost, btnOpenWA;
    private Spinner spinnerGame;
    private LinearLayout panelFitur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Menghubungkan Java dengan Tampilan XML
        setContentView(R.layout.activity_main);

        // Inisialisasi ID Komponen
        tvStatus = findViewById(R.id.tvStatus);
        tvRam = findViewById(R.id.tvRam);
        tvSuhu = findViewById(R.id.tvSuhu);
        tvNetwork = findViewById(R.id.tvNetwork);
        tvSelectedGame = findViewById(R.id.tvSelectedGame);
        btnBoost = findViewById(R.id.btnBoost);
        btnOpenWA = findViewById(R.id.btnOpenWA);
        spinnerGame = findViewById(R.id.spinnerGame);
        panelFitur = findViewById(R.id.panelFitur);

        // Menjalankan pengecekan suhu baterai secara otomatis
        checkBatteryTemperature();
    }

    // Fungsi perbaikan Error ke-10 (BatteryManager)
    private void checkBatteryTemperature() {
        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent intentSuhu = registerReceiver(null, ifilter);
        
        if (intentSuhu != null) {
            int temp = intentSuhu.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0);
            float suhu = ((float) temp) / 10; 
            tvSuhu.setText("Suhu: " + suhu + "°C");
        } else {
            tvSuhu.setText("Suhu: Gagal memuat");
        }
    }
}

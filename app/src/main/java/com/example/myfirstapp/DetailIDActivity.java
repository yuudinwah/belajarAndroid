package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class DetailIDActivity extends AppCompatActivity {
    TextView nik, nama, ttl, alamat, jk, job, kawin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_id);
        getSupportActionBar().setTitle("Detail Page");
        Intent intent = getIntent();
        Uri locationUri = intent.getData();
        nik = findViewById(R.id.nik);
        nama = findViewById(R.id.nama);
        ttl = findViewById(R.id.ttl);
        alamat = findViewById(R.id.alamat);
        jk = findViewById(R.id.jk);
        job = findViewById(R.id.job);
        kawin = findViewById(R.id.kawin);
        nik.setText(": "+intent.getStringExtra("nik"));
        nama.setText(": "+intent.getStringExtra("nama"));
        ttl.setText(": "+intent.getStringExtra("tempatLahir")+", "+intent.getStringExtra("tanggalLahir"));
        alamat.setText(": "+intent.getStringExtra("alamat"));
        jk.setText(": "+intent.getStringExtra("jenisKelamin"));
        job.setText(": "+intent.getStringExtra("pekerjaan"));
        kawin.setText(": "+intent.getStringExtra("statusPerkawinan"));
    }
}


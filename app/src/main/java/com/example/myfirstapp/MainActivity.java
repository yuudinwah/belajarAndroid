package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private DatePickerDialog.OnDateSetListener dateSetListener;

    EditText nikController, namaController, tempatLahirController, alamatController, tanggalLahirController;
    String jenisKelamin, pekerjaan, statusPerkawinan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nikController = findViewById(R.id.nik_controller);
        namaController = findViewById(R.id.nama_controller);
        tempatLahirController = findViewById(R.id.tempatlahir_controller);
        alamatController = findViewById(R.id.alamat_controller);
        tanggalLahirController=findViewById(R.id.tanggallahir_controller);

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month++;
                tanggalLahirController.setText(year+"/"+month+"/"+day);
            }
        };
    }

    public void setJenisKelamin(View v){
        boolean checked = ((RadioButton) v).isChecked();
        switch(v.getId()) {
            case R.id.jk_male:
                if (checked)
                    jenisKelamin = "Laki-laki";
                    break;
            case R.id.jk_female:
                if (checked)
                    jenisKelamin = "Perempuan";
                    break;
        }
    }

    public void setPekerjaan(View v){
        boolean checked = ((RadioButton) v).isChecked();
        switch(v.getId()) {
            case R.id.job_pns:
                if (checked)
                    pekerjaan = "Pegawai Negeri Sipil";
                break;
            case R.id.job_swasta:
                if (checked)
                    pekerjaan = "PegawaiSwasta";
                break;
            case R.id.job_student:
                if (checked)
                    pekerjaan = "Siswa/Mahasiswa";
                break;
            case R.id.job_wiraswasta:
                if (checked)
                    pekerjaan = "Wiraswasta";
                break;
        }
    }

    public void setStatusPerkawinan(View v){
        boolean checked = ((RadioButton) v).isChecked();
        switch(v.getId()) {
            case R.id.kawin_false:
                if (checked)
                    statusPerkawinan = "Belum Kawin";
                break;
            case R.id.kawin_true:
                if (checked)
                    statusPerkawinan = "Sudah Kawin";
                break;
        }
    }

    public void submitForm(View view){
        Intent startIntent = new Intent(this, DetailIDActivity.class);
        Bundle extras = new Bundle();
        extras.putString("nik", String.valueOf(nikController.getText()));
        extras.putString("nama", String.valueOf(namaController.getText()));
        extras.putString("tempatLahir", String.valueOf(tempatLahirController.getText()));
        extras.putString("tanggalLahir", String.valueOf(tanggalLahirController.getText()));
        extras.putString("alamat", String.valueOf(alamatController.getText()));
        extras.putString("jenisKelamin", String.valueOf(jenisKelamin));
        extras.putString("pekerjaan", String.valueOf(pekerjaan));
        extras.putString("statusPerkawinan", String.valueOf(statusPerkawinan));
        nikController.setText("");
        namaController.setText("");
        alamatController.setText("");
        tempatLahirController.setText("");
        tanggalLahirController.setText("");
        RadioGroup radio = findViewById(R.id.jk);
        radio.clearCheck();
        radio = findViewById(R.id.job);
        radio.clearCheck();
        radio = findViewById(R.id.kawin);
        radio.clearCheck();

        startIntent.putExtras(extras);
        startActivity(startIntent);
    }

    public void showDatePickerDialog(View v) {
        Calendar cal = Calendar.getInstance();
        int year= cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(
                MainActivity.this, android.R.style.Theme_Holo_Dialog_MinWidth,
                dateSetListener,year, month, day
        );
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
}
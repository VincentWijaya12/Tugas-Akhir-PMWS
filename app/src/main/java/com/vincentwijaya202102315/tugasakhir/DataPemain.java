package com.vincentwijaya202102315.tugasakhir;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DataPemain extends AppCompatActivity {

    EditText namapemain, umurpemain, golonganpemain, alamat, kategori;
    Button simpan, tampil, edit, hapus;
    DBHelper db;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datapemain);
        namapemain = findViewById(R.id.edtnamapemain);
        umurpemain = findViewById(R.id.edtumurpemain);
        golonganpemain = findViewById(R.id.edtgolonganpemain);
        alamat = findViewById(R.id.edtalamatpemain);
        kategori = findViewById(R.id.edtkategori);
        simpan = findViewById(R.id.btntambah);
        tampil = findViewById(R.id.btntampil);
        hapus = findViewById(R.id.btnhapus);
        edit = findViewById(R.id.btnedit);
        db = new DBHelper(this);

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String isinamapemain = namapemain.getText().toString();
                String isiumurpemain = umurpemain.getText().toString();
                String isigolonganpemain = golonganpemain.getText().toString();
                String isialamat = alamat.getText().toString();
                String isikategori = kategori.getText().toString();

                if (TextUtils.isEmpty(isinamapemain) || TextUtils.isEmpty(isiumurpemain) || TextUtils.isEmpty(isigolonganpemain)
                        || TextUtils.isEmpty(isialamat) || TextUtils.isEmpty(isikategori)) {
                    Toast.makeText(DataPemain.this, "Semua Field Wajib diIsi", Toast.LENGTH_LONG).show();
                } else {
                    Boolean checkkodepemain = db.checkkodepemain(isinamapemain);
                    if (checkkodepemain == false) {
                        Boolean insert = db.insertDataPemain(isinamapemain, isiumurpemain, isigolonganpemain, isialamat, isikategori);
                        if (insert == true) {
                            Toast.makeText(DataPemain.this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), UserActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(DataPemain.this, "Data gagal disimpan", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(DataPemain.this, "Data Mahasiswa Sudah Ada", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        tampil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = db.tampilData();
                if (res.getCount() == 0) {
                    Toast.makeText(DataPemain.this, "Tidak ada Data", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("namapemain : " + res.getString(0) + "\n");
                    buffer.append("umurpemain : " + res.getString(1) + "\n");
                    buffer.append("golonganpemain : " + res.getString(2) + "\n");
                    buffer.append("alamat : " + res.getString(3) + "\n");
                    buffer.append("kategori : " + res.getString(4) + "\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(DataPemain.this);
                builder.setCancelable(true);
                builder.setTitle("Biodata Pemain Badminton");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

        hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String kb = namapemain.getText().toString();
                Boolean cekHapusData = db.hapusData(kb);
                if (cekHapusData == true)
                    Toast.makeText(DataPemain.this, "Data Terhapus", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(DataPemain.this, "Data Tidak Ada", Toast.LENGTH_SHORT).show();

            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String isinamapemain = namapemain.getText().toString();
                String isiumurpemain = umurpemain.getText().toString();
                String isigolonganpemain = golonganpemain.getText().toString();
                String isialamat = alamat.getText().toString();
                String isikategori = kategori.getText().toString();

                if (TextUtils.isEmpty(isinamapemain) || TextUtils.isEmpty(isiumurpemain) || TextUtils.isEmpty(isigolonganpemain)
                        || TextUtils.isEmpty(isialamat) || TextUtils.isEmpty(isikategori))
                    Toast.makeText(DataPemain.this, "Semua Field Wajib diIsi", Toast.LENGTH_LONG).show();
                else {
                    Boolean edit = db.editData(isinamapemain, isiumurpemain, isigolonganpemain, isialamat, isikategori);
                    if (edit == false) {
                        Toast.makeText(DataPemain.this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), UserActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(DataPemain.this, "Data gagal disimpan", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
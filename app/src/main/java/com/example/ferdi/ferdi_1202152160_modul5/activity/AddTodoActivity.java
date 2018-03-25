package com.example.ferdi.ferdi_1202152160_modul5.activity;

import android.content.ContentValues; //untuk import content value
import android.support.design.widget.TextInputEditText; //import sebuah textinputedittext
import android.support.v7.app.AppCompatActivity; //import AppCompatActivity
import android.os.Bundle; //import menyambungkan os bundle
import android.view.View; //import pada view
import android.widget.Button; //import widget button
import android.widget.Toast; //import untuk toast

import com.example.ferdi.ferdi_1202152160_modul5.R; //import dari R
import com.example.ferdi.ferdi_1202152160_modul5.data.OsasTodoContract; //import pada data OsasTodoContract

//extends untuk memanggil AppCompatActivity, class yang menghandle lifecycle aplikasi
//implements pada View.OnClickListener, kelas yang bertugas untuk interface suatu tampilan, memakai method-method yang ada dalam kelas tersebut
public class AddTodoActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputEditText tvName; //kode yang sesuai dengan tvName
    private TextInputEditText tvDescription; //kode yang sesuai dengan tvDescription
    private TextInputEditText tvPriority; //kode yang sesuai dengan tvPriority
    private Button btnAdd; //kode yang sesuai dengan btnAdd

    @Override
    protected void onCreate(Bundle savedInstanceState) { // kode akses yang membuat suatu method yang didefinisikan dengan tingkatan akses oleh kelas tersebut
        super.onCreate(savedInstanceState); //menginstansiasi OnCreate
        setContentView(R.layout.activity_add_todo); //terhubung pada activity_add_todo
        setUpView(); //menampilkannya
    }

    private void setUpView() { //kode bersifat privasi yang sesuai pada setUpView
        tvName = (TextInputEditText) findViewById(R.id.tvName); //menggunakan TextInputEditText pada tvName
        tvDescription = (TextInputEditText) findViewById(R.id.tvDescription); ////menggunakan TextInputEditText pada tvDescription
        tvPriority = (TextInputEditText) findViewById(R.id.tvPriority); ////menggunakan TextInputEditText pada tvPriority
        btnAdd = (Button) findViewById(R.id.btnAdd); //menggunakan Button pada btnAdd
        btnAdd.setOnClickListener(this); //penggunaan OnClickListener
    }

    @Override
    public void onClick(View view) { //method dalam suatu kodingan dapat diakses semua bagian di dalam program
        switch (view.getId()) { //pengambilan keputusan yang melibatkan sejumlah atau banyak alternatif penyelesaian
            case R.id.btnAdd: // yang akan dicocokkan dengan isi value.
                if(!validasiKosong()) { // pernyataan yang akan di kerjakan jika value benar
                    Toast.makeText(this, "Harap isi seluruh field!", Toast.LENGTH_SHORT).show(); //menampilkan toast, tampilan sebentar muncul
                    return; //melakukan pengulangan
                }

                insertData();
                Toast.makeText(this, "Data berhasil ditambahkan!", Toast.LENGTH_SHORT).show(); //menampilkan toast, tampilan sebentar muncul
                finish(); //penyelesaian
                break; //perintah untuk mengakhiri statement atau keluar dari switch
        }
    }

    private boolean validasiKosong() { //kode privasi boolean pada method validasikosong
        if(tvName.getText().toString().isEmpty() || tvDescription.getText().toString().isEmpty() ||
                tvPriority.getText().toString().isEmpty()) { //membaaca ketika salah atau benar
            return false; //dieksekusi jika value tidak cocok dengan salah satu constanta yang tersedia.
        } else { //apabila pada perintah selanjutnya
            return true; //dieksekusi jika value tidak cocok dengan salah satu constanta yang tersedia.
        }
    }

    private void insertData() { //method kode privasi pada insertdata
        ContentValues content = new ContentValues(); //pembuatan sebuah content
        content.put(OsasTodoContract.DaftarInput.COLUMN_NAME, tvName.getText().toString()); //membuat colom nama
        content.put(OsasTodoContract.DaftarInput.COLUMN_DESCRIPTION, tvDescription.getText().toString()); //membuat kolom deskripsi
        content.put(OsasTodoContract.DaftarInput.COLUMN_PRIORITY, tvPriority.getText().toString()); //membuat kolom priority
        getContentResolver().insert(OsasTodoContract.DaftarInput.CONTENT_URI, content); //mengambil content resolver
    }
}

package com.example.belajarsqlite;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
   ArrayList<mhsModel> mhslist;
  private mhsModel mm;
   DbHelper db;
    boolean isEdit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText ednama = (EditText) findViewById(R.id.ednama);
        EditText ednim = (EditText) findViewById(R.id.ednim);
        EditText edNoHp = (EditText) findViewById(R.id.edNoHp);
        Button simpanbtn = (Button) findViewById(R.id.simpanbtn);


        db = new DbHelper(getApplicationContext());
        mhslist = new ArrayList<>();
        isEdit = false;
        mm = new mhsModel();

        Intent intent_main = getIntent();
        if (intent_main.hasExtra("mhsData")){
            mm = intent_main.getExtras().getParcelable("mhsData");
            ednama.setText(mm.getNama());
            ednim.setText(mm.getNim());
            edNoHp.setText(mm.getNohp());

            isEdit = true;

            simpanbtn.setBackgroundColor(Color.GREEN);
            simpanbtn.setText("Edit");
        }


        boolean finalIsEdit = isEdit;
        simpanbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String isian_nama = ednama.getText().toString();
                String isian_nim = ednim.getText().toString();
                String isian_NoHp = edNoHp.getText().toString();
                if (isian_nama.isEmpty() || (isian_nim.isEmpty() || (isian_NoHp.isEmpty()))) {
                    Toast.makeText(MainActivity.this, "isian nama masih kosong", Toast.LENGTH_SHORT).show();
                } else {
                    //mhslist.add(new mhsModel (-1,isian_nama, isian_nim, isian_NoHp));


                boolean stts;
                   if (!isEdit){
                       mm = new mhsModel(-1,isian_nama, isian_nim, isian_NoHp);
                       stts = db.simpan(mm);
                       ednama.setText("");
                       ednim.setText("");
                       edNoHp.setText("");
                   }else{
                      mm = new mhsModel(mm.getId(),isian_nama, isian_nim, isian_NoHp);
                           stts = db.ubah(mm);
                       }

                   if(stts) {
                       ednama.setText("");
                       ednim.setText("");
                       edNoHp.setText("");


                       Toast.makeText(MainActivity.this, "data berhasil disimpan", Toast.LENGTH_SHORT).show();
                   }else {
                       Toast.makeText(MainActivity.this, "data gagal disimpan", Toast.LENGTH_SHORT).show();
                   }

                    //intent_list.putParcelableArrayListExtra("mhslist", mhslist);
                    //startActivity(intent_list);
                }
            }

        });
        Button lihatbtn = (Button) findViewById(R.id.lihatbtn);
        lihatbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mhslist = db.List();

                if (mhslist.isEmpty()) {
                    Toast.makeText(MainActivity.this, "belum ada data", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent_list = new Intent(MainActivity.this, ListMhsActivity.class
                    );
                    intent_list.putParcelableArrayListExtra("mhslist", mhslist);
                    startActivity(intent_list);

                }
            }

            ;
        });
    }}
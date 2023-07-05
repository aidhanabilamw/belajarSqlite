package com.example.belajarsqlite;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListMhsActivity extends AppCompatActivity {
    mhsAdapter mhsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listmhs);
        //ListView lvdata = (ListView) findViewById(R.id.lvdata);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        ArrayList<mhsModel> mhslist = getIntent().getExtras().getParcelableArrayList("mhslist");
        mhsAdapter = new mhsAdapter(mhslist, new mhsAdapter.onItemClickListener() {
            @Override
            public void onItemClick (ArrayList<mhsModel> mhslist, int position) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(ListMhsActivity.this);
                dialog.setTitle("pilihan");
                dialog.setItems(new CharSequence[]{"hapus", "edit"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int item) {
                        DbHelper db = new DbHelper(getApplicationContext());
                        mhsModel mm = mhslist.get(position);

                        switch (item) {

                            case 0:

                                boolean stts = db.hapus(mm.getId());
                                if (stts) {
                                    mhsAdapter.removeItem(position);
                                    Toast.makeText(getApplicationContext(), "data berhasil dihapus", Toast.LENGTH_SHORT).show();
                                }

                                break;
                            case 1:
                                Intent intent_main = new Intent(ListMhsActivity.this, MainActivity.class);
                                intent_main.putExtra("mhsData", mm);

                                startActivity(intent_main);
                                break;
                        }

                    }});
                dialog.create().show();
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListMhsActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mhsAdapter);

        FloatingActionButton fabTambah = findViewById(R.id.fabTambah);
        fabTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListMhsActivity.this, MainActivity.class));
            }
        });


       // ArrayAdapter<String> ArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mhslist);
        //lvdata.setAdapter(ArrayAdapter);
    }
}
package com.bai1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements IOnItemClickListener {
    public static final int REQUEST_ADD = 111;
    public static final int REQUEST_EDIT = 222;

    private ListView listView;
    private FloatingActionButton floatingActionBtn;

    private DatabaseHandler db;
    private ArrayList<Contact_TenSV> contacts;
    private HoTenSV_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHandler(this);

        initView();
    }

    private void initView() {
        listView = findViewById(R.id.listView);
        floatingActionBtn = findViewById(R.id.floatingActionButton);

        updateAdapter();

        floatingActionBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddContactActivity.class);
            startActivityForResult(intent, REQUEST_ADD);
        });

        registerForContextMenu(listView);
    }

    private void updateAdapter() {
        contacts = db.getAll();
        Collections.sort(contacts);
        adapter = new HoTenSV_Adapter(this, R.id.listView, contacts, this);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && (requestCode == REQUEST_ADD || requestCode == REQUEST_EDIT)) {
            updateAdapter();
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_context, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;

        if (item.getItemId() == R.id.itemDelete) {
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Confirm")
                    .setMessage("Họ tên sinh viên wants to delete?")
                    .setPositiveButton(android.R.string.ok, (dialog, whichButton) -> {
                        db.delete(contacts.get(position).getId());
                        contacts.remove(position);
                        updateAdapter();
                    })
                    .setNegativeButton(android.R.string.cancel, null).show();
        }
        else if (item.getItemId() == R.id.itemEdit) {
            Intent intent = new Intent(this, EditContactActivity.class);
            intent.putExtra("contact", contacts.get(position));
            startActivityForResult(intent, REQUEST_EDIT);
        }

        return super.onContextItemSelected(item);
    }

    @Override
    public void onClick(Contact_TenSV contact, int position) {

    }
}
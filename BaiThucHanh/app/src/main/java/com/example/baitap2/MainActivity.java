package com.example.baitap2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements IOnItemClickListener {
    public static final int RESULT_ADD = 0;

    private DatabaseHandler databaseHandler;

    private ArrayList<Device> deviceList;
    private DeviceAdapter deviceAdapter;
    private ListView listView;
    private Button buttonAdd;
    private Button buttonDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        databaseHandler = new DatabaseHandler(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        deviceList = databaseHandler.getAll();

        listView = findViewById(R.id.listView);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonDelete = findViewById(R.id.buttonDelete);

        updateAdapter();

        buttonAdd.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddDeviceActivity.class);
            startActivityForResult(intent, RESULT_ADD);
        });

        buttonDelete.setOnClickListener(view -> new AlertDialog.Builder(MainActivity.this)
                .setTitle("Thông báo")
                .setMessage("Bạn có chắc muốn xoá các mục đã chọn?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, (dialog, whichButton) -> {
                    for (Device i : deviceList) {
                        if (i.getStatus()) {
                            databaseHandler.delete(i.getId());
                        }
                    }
                    deviceList.removeIf(Device::getStatus);
                    updateAdapter();
                })
                .setNegativeButton(android.R.string.no, null).show());
    }

    private void updateAdapter() {
        deviceAdapter = new DeviceAdapter(this, R.id.listView, deviceList, this);
        listView.setAdapter(deviceAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_ADD) {
            Device device = ((Device) data.getSerializableExtra("device"));
            deviceList.add(device);
            databaseHandler.insert(device);
            updateAdapter();
        }
    }

    @Override
    public void onClick(Device device, int position) {
        deviceList.get(position).setStatus(!device.getStatus());
        databaseHandler.update(device);
        updateAdapter();
    }
}
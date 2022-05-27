package com.example.baitap2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import java.util.Date;

public class AddDeviceActivity extends AppCompatActivity {
    private EditText editTextName;
    private EditText editTextDescription;
    private SwitchCompat switchStatus;
    private Button buttonAddDevice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_device);

        editTextName = findViewById(R.id.editTextName);
        editTextDescription = findViewById(R.id.editTextDescription);
        switchStatus = findViewById(R.id.switchStatus);
        buttonAddDevice = findViewById(R.id.buttonAddDevice);

        buttonAddDevice.setOnClickListener(view -> {
            Date date = new Date();
            long id = date.getTime();

            Intent intent = getIntent();
            Device device = new Device(id, editTextName.getText().toString(), editTextDescription.getText().toString(), "", switchStatus.isChecked());
            intent.putExtra("device", device);
            setResult(MainActivity.RESULT_ADD, intent);
            finish();
        });
    }
}
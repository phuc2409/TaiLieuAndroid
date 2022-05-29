package com.bai1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddContactActivity extends AppCompatActivity {
    private DatabaseHandler db;

    private EditText etName;
    private EditText etPhoneNumber;
    private Button btnAdd;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        db = new DatabaseHandler(this);

        initView();
    }

    private void initView() {
        etName = findViewById(R.id.etName);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        btnAdd = findViewById(R.id.btnAdd);
        btnBack = findViewById(R.id.btnBack);

        btnAdd.setOnClickListener(view -> {
            if (!etName.getText().toString().isEmpty() && !etPhoneNumber.getText().toString().isEmpty()) {
                Contact_TenSV contact = new Contact_TenSV(0, etName.getText().toString(), etPhoneNumber.getText().toString());

                long res = db.insert(contact);
                if (res == -1) {
                    Toast.makeText(this, "Insert error", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = getIntent();

                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });

        btnBack.setOnClickListener(view -> finish());
    }
}
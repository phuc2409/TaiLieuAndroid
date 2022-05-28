package com.bai1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditContactActivity extends AppCompatActivity {
    private DatabaseHandler db;
    private Contact_TenSV contact;

    private EditText etName;
    private EditText etPhoneNumber;
    private Button btnEdit;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        db = new DatabaseHandler(this);
        contact = (Contact_TenSV) getIntent().getSerializableExtra("contact");

        initView();
    }

    private void initView() {
        etName = findViewById(R.id.etName);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        btnEdit = findViewById(R.id.btnEdit);
        btnBack = findViewById(R.id.btnBack);

        etName.setText(contact.getName());
        etPhoneNumber.setText(contact.getPhoneNumber());

        btnEdit.setOnClickListener(view -> {
            if (!etName.getText().toString().isEmpty() && !etPhoneNumber.getText().toString().isEmpty()) {
                contact.setName(etName.getText().toString());
                contact.setPhoneNumber(etPhoneNumber.getText().toString());

                long res = db.update(contact);
                if (res == -1) {
                    Toast.makeText(this, "Update error", Toast.LENGTH_SHORT).show();
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
package com.bai1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class AddContactActivity extends AppCompatActivity {
    private EditText etId;
    private EditText etName;
    private EditText etPhoneNumber;
    private Button btnAdd;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        initView();
    }

    private void initView() {
        etId = findViewById(R.id.etId);
        etName = findViewById(R.id.etName);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        btnAdd = findViewById(R.id.btnAdd);
        btnBack = findViewById(R.id.btnBack);

        btnAdd.setOnClickListener(view -> {
            if (!etId.getText().toString().isEmpty() && !etName.getText().toString().isEmpty() && !etPhoneNumber.getText().toString().isEmpty()) {
                Intent intent = getIntent();
                Contact_TenSV contact = new Contact_TenSV(Integer.parseInt(etId.getText().toString()), etName.getText().toString(), etPhoneNumber.getText().toString());
                intent.putExtra("contact", contact);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        btnBack.setOnClickListener(view -> finish());
    }
}
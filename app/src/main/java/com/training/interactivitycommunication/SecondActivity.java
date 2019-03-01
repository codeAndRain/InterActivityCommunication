package com.training.interactivitycommunication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String PERSON_EXTRA = "PERSON_EXTRA";

    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private Button updateButton;
    private Button clearButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        initViews();

        Intent intent = getIntent();

        if (intent.getExtras() != null) {
            Person person = intent.getExtras().getParcelable(PERSON_EXTRA);
            if (person != null) {
                firstNameEditText.setText(person.getFirstName());
                lastNameEditText.setText(person.getLastName());
            }
        }
    }

    private void initViews() {
        // setup toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        firstNameEditText = findViewById(R.id.firstname_edit_text);
        lastNameEditText = findViewById(R.id.last_name_edit_text);
        updateButton = findViewById(R.id.update_button);
        clearButton = findViewById(R.id.clear_button);
        updateButton.setOnClickListener(this);
        clearButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.update_button:
                String firstName = !firstNameEditText.getText().toString().isEmpty() ? firstNameEditText.getText().toString() : "";
                String lastName = !lastNameEditText.getText().toString().isEmpty() ? lastNameEditText.getText().toString() : "";

                if (!firstName.isEmpty() && !lastName.isEmpty()) {
                    Person person = new Person(firstName, lastName, 44);
                    Intent intent = new Intent();
                    intent.putExtra(MainActivity.PERSON_RESULT_EXTRA, person);
                    setResult(RESULT_OK, intent);
                    finish();
                }
                break;
            case R.id.clear_button:
                firstNameEditText.setText("");
                lastNameEditText.setText("");
                break;
        }
    }
}

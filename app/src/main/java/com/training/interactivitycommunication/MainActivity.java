package com.training.interactivitycommunication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final int PERSON_REQUEST_CODE = 1000;
    public static final String PERSON_RESULT_EXTRA = "PERSON_RESULT_EXTRA";

    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

    }

    private void initViews() {
        // setup toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        firstNameEditText = findViewById(R.id.firstname_edit_text);
        lastNameEditText = findViewById(R.id.last_name_edit_text);
        saveButton = findViewById(R.id.save_button);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = !firstNameEditText.getText().toString().isEmpty() ? firstNameEditText.getText().toString() : "";
                String lastName = !lastNameEditText.getText().toString().isEmpty() ? lastNameEditText.getText().toString() : "";

                Person person = new Person(firstName, lastName, 22);

                startSecondActivity(person);
            }
        });

    }

    private void startSecondActivity(Person person) {
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra(SecondActivity.PERSON_EXTRA, person);
        startActivityForResult(intent, PERSON_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == PERSON_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    Person person = data.getExtras().getParcelable(PERSON_RESULT_EXTRA);
                    if (person != null) {
                        firstNameEditText.setText(person.getFirstName());
                        lastNameEditText.setText(person.getLastName());
                    }
                }
            }
        }
    }
}

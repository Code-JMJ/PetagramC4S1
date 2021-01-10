package com.example.petagram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.textfield.TextInputLayout;

import com.example.petagram.javamail.JavaMailAPI;

public class Contact extends AppCompatActivity {

    private TextInputLayout textInputLayoutFullName;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        Toolbar toolbar = (Toolbar) findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        textInputLayoutFullName = (TextInputLayout) findViewById(R.id.tilFullName);
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.tilEmail);
        textInputLayoutComment = (TextInputLayout) findViewById(R.id.tilComment);
    }

    public boolean validateFullName(){
        String fullNameInput = textInputLayoutFullName.getEditText().getText().toString().trim();

        if (fullNameInput.isEmpty()){
            textInputLayoutFullName.setError("El campo esta vacio");
            return false;
        }else{
            textInputLayoutFullName.setError(null);
            return true;
        }
    }

    public boolean validateEmail(){
        String emailInput = textInputLayoutEmail.getEditText().getText().toString().trim();

        if (emailInput.isEmpty()){
            textInputLayoutEmail.setError("El campo esta vacio");
            return false;
        }else{
            textInputLayoutEmail.setError(null);
            return true;
        }
    }

    public boolean validateComment(){
        String commentInput = textInputLayoutComment.getEditText().getText().toString().trim();

        if (commentInput.isEmpty()){
            textInputLayoutComment.setError("El campo esta vacio");
            return false;
        }else{
            textInputLayoutComment.setError(null);
            return true;
        }
    }

    public void sendEmail(View view){
        if (!validateFullName() | !validateEmail() | !validateComment()){
            return;
        }
        //Toast.makeText(this, "Almost there!", Toast.LENGTH_SHORT).show();
        String email = textInputLayoutEmail.getEditText().getText().toString();
        String fullName = textInputLayoutFullName.getEditText().getText().toString();
        String message = textInputLayoutComment.getEditText().getText().toString();

        JavaMailAPI javaMailAPI = new JavaMailAPI(this, email, fullName, message);

        javaMailAPI.execute();
    }
}
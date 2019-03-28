package com.example.abc.privchat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class PasswordResetActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private TextInputLayout mPasswordResetEmail;
    private Button mResetBtn;
    private ProgressDialog mResetProgress;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset);

        mToolbar = findViewById(R.id.password_reset_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Reset Password");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mPasswordResetEmail = findViewById(R.id.password_reset_email);
        mResetBtn = findViewById(R.id.password_rest_submit_btn);

        mResetProgress = new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();

        mResetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mPasswordResetEmail.getEditText().getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Email-ID Is Required.", Toast.LENGTH_SHORT).show();
                }
                else {
                    mResetProgress.setTitle("Resetting Password");
                    mResetProgress.setMessage("Please wait while we check your Email-ID.");
                    mResetProgress.setCanceledOnTouchOutside(false);
                    mResetProgress.show();
                    resetPassword(mPasswordResetEmail.getEditText().getText().toString());
                }
            }
        });
    }

    private void resetPassword(String email) {
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()) {
                    mResetProgress.dismiss();

                    Toast.makeText(PasswordResetActivity.this, "Please Check Your Email.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(PasswordResetActivity.this, LoginActivity.class));
                }
                else {
                    String error = task.getException().getMessage();
                    Toast.makeText(PasswordResetActivity.this, error, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

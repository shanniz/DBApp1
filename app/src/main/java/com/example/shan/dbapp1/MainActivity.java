package com.example.shan.dbapp1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText mEditTextUsername, mEditTextEmail;
    TextView mTextViewUsername, mTextViewEmail;
    private final DBHelper mydb = DBHelper.getDBHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditTextUsername = (EditText)findViewById(R.id.editTextUsername);
        mEditTextEmail = (EditText)findViewById(R.id.editTextEmail);

        mTextViewUsername = (TextView) findViewById(R.id.textViewUsername);
        mTextViewEmail = (TextView) findViewById(R.id.textViewEmail);

    }

    public void adduser(View view) {
        mydb.insertUser(mEditTextUsername.getText().toString(),
                mEditTextEmail.getText().toString());

        mEditTextUsername.setText("");
        mEditTextEmail.setText("");
        Toast.makeText(this,"User Added!", Toast.LENGTH_SHORT)
                .show();
    }


    public void getUser(View view) {
        List<User> users = mydb.getAllManufacturers();
        int size = users.size();
        User u = users.get(size-1);
        mTextViewUsername.setText(u.getUsername());
        mTextViewEmail.setText(u.getEmail());
    }
}

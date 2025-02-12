package edu.snhu.kylerose.fishinggame;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class CreateAccountActivity extends AppCompatActivity {

    // Variables for account creation and database connection
    private MyDatabaseHelper open_helper;
    private SQLiteDatabase db;
    private EditText user_name, user_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_account);

        // Initializing variable to new instance of MyDatabaseHelper
        open_helper = new MyDatabaseHelper(this);

        // Initializing variables, linking with layout
        Button create_account_button = findViewById(R.id.createAccountButton2);
        user_name = findViewById(R.id.createUsernameText);
        user_password = findViewById(R.id.createPasswordText);

        // if user clicks create account
        create_account_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // open database
                db = open_helper.getWritableDatabase();
                // store username and password input
                String userName = user_name.getText().toString().trim();
                String userPassword = user_password.getText().toString().trim();
                // if username or password is empty, prompt user to fill in all the details
                if (userName.isEmpty() || userPassword.isEmpty()) {
                    Toast.makeText(CreateAccountActivity.this, "Please fill all the details.", Toast.LENGTH_SHORT).show();
                // if username and password are not empty
                } else {
                    // add account to database, display registration success message
                    open_helper.insertAccount(userName, userPassword);
                    Toast.makeText(CreateAccountActivity.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

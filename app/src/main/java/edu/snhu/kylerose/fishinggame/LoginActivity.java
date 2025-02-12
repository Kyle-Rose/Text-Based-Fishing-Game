package edu.snhu.kylerose.fishinggame;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    // Variables for login and database connection
    private MyDatabaseHelper open_helper;
    private SQLiteDatabase db;
    private EditText user_name, user_password;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        // Initializing variable to new instance of MyDatabaseHelper
        open_helper = new MyDatabaseHelper(this);

        // Initializing variables, linking with layout
        Button login_button = findViewById(R.id.loginButton);
        Button createAccount_button = findViewById(R.id.createAccountButton);
        user_name = findViewById(R.id.usernameText);
        user_password = findViewById(R.id.passwordText);

        // if user clicks login
        login_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // open database
                db = open_helper.getWritableDatabase();
                // get user input
                String userName = user_name.getText().toString().trim();
                String password = user_password.getText().toString().trim();
                // if username or password is empty prompt user to input information
                if (userName.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Enter your username and password to login.", Toast.LENGTH_SHORT).show();
                // else check user input against database
                } else {
                    cursor = db.rawQuery("SELECT * FROM " + MyDatabaseHelper.TABLE_NAME + " WHERE " + MyDatabaseHelper.COLUMN_USERNAME + "=? AND " + MyDatabaseHelper.COLUMN_PASSWORD + "=?", new String[]{userName, password});
                    // if match is found
                    if (cursor != null) {
                        if (cursor.getCount() > 0) {
                            // take user to main screen with a login success message
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            Toast.makeText(getApplicationContext(), "Login success!", Toast.LENGTH_SHORT).show();
                            finish();

                        // if match is not found, display incorrect information message to user
                        } else {
                            Toast.makeText(getApplicationContext(), "Incorrect username or password.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        // if user clicks create account
        createAccount_button.setOnClickListener(new View.OnClickListener() {
            // take user to create account screen
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, CreateAccountActivity.class));
            }
        });
    }
}

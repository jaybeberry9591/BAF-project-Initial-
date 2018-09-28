package com.example.user.janina;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    Button save,viewall;
    EditText name,level,credit,user,pass,id;
    String  sname,sid,slevel,scredit,suser,spass;
    private DatabaseReference refDatabase;
    private FirebaseAuth mAuth;
    private StudentInfo Student;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        save=(Button) findViewById(R.id.btnsave);
        viewall=(Button) findViewById(R.id.btnviewall);
        name=(EditText) findViewById(R.id.edname);
        level=(EditText) findViewById(R.id.edlevel);
        credit=(EditText) findViewById(R.id.edcredit);
        id=(EditText) findViewById(R.id.edid);
        user=(EditText) findViewById(R.id.edusername);
        pass=(EditText) findViewById(R.id.edpassword);
        mAuth=FirebaseAuth.getInstance();
        refDatabase= FirebaseDatabase.getInstance().getReference();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAllInputData();
                createStudent();
                CreateAccountAndSaveInfo();
              }
        });
        viewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toViewallActivity= new Intent(MainActivity.this,ViewAllActivity.class);
                startActivity(toViewallActivity);
            }
        });
    }

    void getAllInputData(){
        sname=name.getText().toString();
        slevel=level.getText().toString();
        scredit=credit.getText().toString();
        sid=id.getText().toString();
        suser=user.getText().toString();
        spass=pass.getText().toString();
    }
    void createStudent(){
        Student= new StudentInfo(sname,sid,slevel,scredit);
    }
    void CreateAccountAndSaveInfo(){
        mAuth.signInWithEmailAndPassword(suser, spass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            // Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            refDatabase= FirebaseDatabase.getInstance().getReference();
                            refDatabase.child(user.getUid()).setValue(Student);
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            //Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });

    }

}

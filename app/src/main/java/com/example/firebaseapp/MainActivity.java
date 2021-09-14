package com.example.firebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;

public class MainActivity extends AppCompatActivity {

    EditText et_ID,et_Name,et_address,et_contactNO;
    DatabaseReference dbRef;
    Student std;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_ID = findViewById(R.id.et_ID);
        et_Name = findViewById(R.id.et_Name);
        et_address = findViewById(R.id.et_address);
        et_contactNO = findViewById(R.id.et_contactNO);

        std = new Student();    // object for the student class

    }


    private void clearControls(){    // to clear all the values after operation done
        et_ID.setText("");
        et_Name.setText("");
        et_address.setText("");
        et_contactNO.setText("");
    }

    //to save all the details
    public void saveData(View view){
        dbRef = FirebaseDatabase.getInstance().getReference().child("Student");
        try{     //try catch block used to identify the inputed type of the data is wrong then print the error messages

            //see whether the fields are empty or not
            if(TextUtils.isEmpty(et_ID.getText().toString()))
                Toast.makeText(this, "Please Enter the ID !", Toast.LENGTH_SHORT).show();
            else if(TextUtils.isEmpty(et_Name.getText().toString()))
                Toast.makeText(this, "Please Enter the Name !", Toast.LENGTH_SHORT).show();
            else if(TextUtils.isEmpty(et_address.getText().toString()))
                Toast.makeText(this, "Please Enter the Address !", Toast.LENGTH_SHORT).show();
            if(TextUtils.isEmpty(et_contactNO.getText().toString()))
                Toast.makeText(this, "Please Enter the ContactNO !", Toast.LENGTH_SHORT).show();
            else{
                std.setID(et_ID.getText().toString().trim());
                std.setName(et_Name.getText().toString().trim());
                std.setAddress(et_address.getText().toString().trim());
                std.setContactNO(Integer.parseInt(et_contactNO.getText().toString().trim()));    //parseINt because input type is Integer

                dbRef.child("std1").setValue(std);

                Toast.makeText(this, "DAta SAved Successfully !", Toast.LENGTH_SHORT).show();

            }
        }catch(NumberFormatException e){
            Toast.makeText(this, "Invalid Contact Number !", Toast.LENGTH_SHORT).show();
        }


    }


    public void showData(View view){
        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Student/std1");
        readRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()) {
                    et_ID.setText(snapshot.child("id").getValue().toString());
                    et_Name.setText(snapshot.child("name").getValue().toString());
                    et_address.setText(snapshot.child("Address").getValue().toString());
                    et_contactNO.setText(snapshot.child("contactNO").getValue().toString());
                } else
                    Toast.makeText(MainActivity.this, "No data", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
    }


    public void updateData(View view){
        dbRef = FirebaseDatabase.getInstance().getReference();
        dbRef.child("Student/std1/name").setValue(et_Name.getText().toString());
        dbRef.child("Student/std1/address").setValue(et_address.getText().toString());
        Toast.makeText(this, "Successfully Updated !", Toast.LENGTH_SHORT).show();
        clearControls();

    }

    public void deleteData(View view){
        dbRef = FirebaseDatabase.getInstance().getReference().child("Student").child("std1");
        dbRef.removeValue();
        Toast.makeText(this, "Data deleted successfully !", Toast.LENGTH_SHORT).show();
        clearControls();
    }

}
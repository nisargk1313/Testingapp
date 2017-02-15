package com.example.rathin.testing;

/**
 * Created by Rathin on 10-Feb-17.
 */

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.example.rathin.testing.R.id.etPasswordConfirm;


public class fragment_tabbed_registration extends Fragment implements View.OnClickListener{

    private EditText etPassword,etName,etEmail,etMobile,etPasswordConfirm;
    private Button btRegister;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private ProgressBar progressBar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tabbed_registration, container, false);
        firebaseAuth =FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        etPassword = (EditText) rootView.findViewById(R.id.etPassword);
        etName = (EditText) rootView.findViewById(R.id.etName);
        etMobile = (EditText) rootView.findViewById(R.id.etMobile);
        etEmail = (EditText) rootView.findViewById(R.id.etEmail);
        etPasswordConfirm = (EditText) rootView.findViewById(R.id.etPasswordConfirm);
        btRegister = (Button) rootView.findViewById(R.id.btRegister);
        progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
        btRegister.setOnClickListener(this);

        return rootView;
    }



    private void SaveInformation(){
        String email= etEmail.getText().toString().trim();
        String password =etPassword.getText().toString().trim();
        String name= etName.getText().toString().trim();
        String number=etMobile.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getContext(), "Enter password!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < 6) {
            Toast.makeText(getContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(getContext(), "Enter Name!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(number)) {
            Toast.makeText(getContext(), "Enter Number!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (number.length() != 10) {
            Toast.makeText(getContext(), "Enter Valid Mobile Number", Toast.LENGTH_SHORT).show();
            return;
        }
      /*  if(etPassword !=etPasswordConfirm)
        {
            Toast.makeText(getContext(), "Password and Confirm Password should be same", Toast.LENGTH_SHORT).show();
            return;
        }*/
        progressBar.setVisibility(View.VISIBLE);
        firebaseAuth
                .createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        progressBar.setVisibility(View.GONE);
                        String name= etName.getText().toString().trim();
                        String email= etEmail.getText().toString().trim();
                        String password =etPassword.getText().toString().trim();
                        String number=etMobile.getText().toString().trim();
                        UserInformation userInformation= new UserInformation(name,email,password,number);
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        databaseReference.child(user.getUid()).setValue(userInformation);
                        Toast.makeText(getActivity(), "Welcome to DDB Mudra", Toast.LENGTH_SHORT).show();
                    }
                });



    }

    @Override
    public void onClick(View v) {
        if (v==btRegister)
        {
            SaveInformation();


        }
    }


}

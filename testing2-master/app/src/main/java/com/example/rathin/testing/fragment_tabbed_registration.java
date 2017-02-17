package com.example.rathin.testing;

/**
 * Created by Rathin on 10-Feb-17.
 */

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
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

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import static com.example.rathin.testing.R.id.etPasswordConfirm;


public class fragment_tabbed_registration extends Fragment implements View.OnClickListener{

    private EditText etPassword,etName,etEmail,etMobile,etPasswordConfirm;
    private Button btRegister;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;


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
        //progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
        progressDialog = new ProgressDialog(getActivity());
        btRegister.setOnClickListener(this);

        return rootView;
    }



    private void SaveInformation(){
        String email= etEmail.getText().toString().trim();
        String password =etPassword.getText().toString().trim();
        String confirm =etPasswordConfirm.getText().toString().trim();
        String name= etName.getText().toString().trim();
        String number=etMobile.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(getContext(), "Enter Name!", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(getContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            Toast.makeText(getContext(), "Enter Valid email address!", Toast.LENGTH_SHORT).show();
            return;
        }

        else if (TextUtils.isEmpty(password)) {
            Toast.makeText(getContext(), "Enter password!", Toast.LENGTH_SHORT).show();
            return;
        }

        else if (password.length() < 8) {
            Toast.makeText(getContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
            return;
        }
         if (!(confirm.equals(password))) {
            etPasswordConfirm.setError("Must be same as the password given above");

        }
        else if (TextUtils.isEmpty(number)) {
            Toast.makeText(getContext(), "Enter Number!", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (number.length() != 10) {
            Toast.makeText(getContext(), "Enter Valid Mobile Number", Toast.LENGTH_SHORT).show();
            return;
        }
        if ((confirm.equals(password))) {
            progressDialog.setMessage("Registering.");
            progressDialog.show();
            firebaseAuth
                    .createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Toast.makeText(getActivity(), "Welcome to DDB Mudra", Toast.LENGTH_SHORT).show();


                            if (!task.isSuccessful()) {
                                etName.setText("");
                                etEmail.setText("");
                                etPassword.setText("");
                                etPasswordConfirm.setText("");
                                etMobile.setText("");
                                progressDialog.dismiss();
                                Toast.makeText(getActivity(), "SignUp failed Do again", Toast.LENGTH_SHORT).show();

                            } else {
                                String name = etName.getText().toString().trim();
                                String email = etEmail.getText().toString().trim();
                                String password = etPassword.getText().toString().trim();
                                String number = etMobile.getText().toString().trim();
                                UserInformation userInformation = new UserInformation(name, email, password, number);
                                FirebaseUser user = firebaseAuth.getCurrentUser();
                                databaseReference.child(user.getUid()).setValue(userInformation);
                                etName.setText("");
                                etEmail.setText("");
                                etPasswordConfirm.setText("");
                                etMobile.setText("");
                                progressDialog.dismiss();
                            }
                        }
                    });
        }
        else
        {
            etPasswordConfirm.setError("Must be same as the password given above");

        }

    }

    @Override
    public void onClick(View v) {
        if (v==btRegister)
        {
            SaveInformation();


        }
    }


}

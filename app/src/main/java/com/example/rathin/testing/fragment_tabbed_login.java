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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;


import static com.example.rathin.testing.R.id.btSignIn;

public class fragment_tabbed_login extends Fragment implements View.OnClickListener{

    private AutoCompleteTextView actvEmail;
    private EditText etPassword;
    private Button btSignIn;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private TextView tvForgot;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tabbed_login, container, false);
        //TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
        firebaseAuth = FirebaseAuth.getInstance();
        actvEmail = (AutoCompleteTextView) rootView.findViewById(R.id.actvEmail);
        progressDialog = new ProgressDialog(getActivity());
        etPassword = (EditText) rootView.findViewById(R.id.etPassword);
        tvForgot=(TextView)rootView.findViewById(R.id.tvForgot);
        tvForgot.setOnClickListener(this);
        btSignIn = (Button) rootView.findViewById(R.id.btSignIn);
        btSignIn.setOnClickListener(this);
        return rootView;
    }







    private void SignIn( ) {

        String email = actvEmail.getText().toString().trim();
        String Password = etPassword.getText().toString().trim();
        if (!TextUtils.isEmpty(email)) {
            if (TextUtils.isEmpty(Password)) {
                Toast.makeText(getActivity(), "Enter your Password", Toast.LENGTH_SHORT).show();

            } else {
                firebaseAuth
                        .signInWithEmailAndPassword(email, Password)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(getActivity(), "You are Successfully Logged", Toast.LENGTH_SHORT).show();
                                Intent gotocam= new Intent(getActivity(),landing_page.class);
                                startActivity(gotocam);
                            }
                        });            }
        }
    }


    @Override
    public void onClick(View v) {
        if(v== btSignIn) {
            SignIn();
        }
        if (v==tvForgot)
        {
            Intent forpwd = new Intent(getActivity().getApplication(), ResetPasswordActivity.class);
            startActivity(forpwd);

        }

    }
}

package faculdadedelta.firebaseapp.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import faculdadedelta.firebaseapp.R;

public class Login_form extends AppCompatActivity {

    EditText emailId, password;
    Button btnSignIn;
    TextView tvSignUp;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);

        /////////////

        mFirebaseAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);
        btnSignIn = findViewById(R.id.btnSignIn);

        tvSignUp = findViewById(R.id.tvSignUp);

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                if (mFirebaseUser != null) {
                    Toast.makeText(Login_form.this, "Você está logado!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Login_form.this, MainActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(Login_form.this, "Por favor, Logue-se.", Toast.LENGTH_SHORT).show();
                }

            }
        };


        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailId.getText().toString();
                String senha = password.getText().toString();
                if (email.isEmpty()) {
                    emailId.setError("Insira um e-mail!");
                    emailId.requestFocus();
                }else if(senha.length() < 6 ){
                    password.setError("As senhas devem ter mais de 6 caracteres!");
                    password.requestFocus();
                }
                else if (senha.isEmpty()) {
                    password.setError("Insira uma senha!");
                    password.requestFocus();
                } else if (email.isEmpty() && senha.isEmpty()) {
                    Toast.makeText(Login_form.this, "Os campos estão vazios!", Toast.LENGTH_SHORT).show();

                } else if (!(email.isEmpty() && senha.isEmpty())) {
                    mFirebaseAuth.signInWithEmailAndPassword(email, senha).addOnCompleteListener(Login_form.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(Login_form.this, "Dados inválidos, tente novamente!", Toast.LENGTH_SHORT).show();
                            } else {
                                startActivity(new Intent(Login_form.this, MainActivity.class));
                            }
                        }
                    });
                } else {
                    Toast.makeText(Login_form.this, "Ocorreu um Erro!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intSignup = new Intent(Login_form.this, Signup_Form.class);
                startActivity(intSignup);
            }
        });


    }


    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }
}

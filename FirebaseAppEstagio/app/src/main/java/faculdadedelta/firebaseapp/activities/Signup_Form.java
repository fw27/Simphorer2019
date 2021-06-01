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

import faculdadedelta.firebaseapp.R;

public class Signup_Form extends AppCompatActivity {

    EditText emailId, password;
    Button btnSignUp;
    TextView tvSignIn, tvSignUp;
    FirebaseAuth mFirebaseAuth;

    private FirebaseAuth.AuthStateListener mAuthStateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup__form);


        mFirebaseAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);
        btnSignUp = findViewById(R.id.btnSignUp);



        tvSignIn = findViewById(R.id.tvSignIn);
        tvSignUp = findViewById(R.id.tvSignUp);



        btnSignUp.setOnClickListener(new View.OnClickListener() {
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
                } else if (senha.isEmpty()) {
                    password.setError("Insira uma senha!");
                    password.requestFocus();
                } else if (email.isEmpty() && senha.isEmpty()) {
                    Toast.makeText(Signup_Form.this, "Os campos estão vazios!", Toast.LENGTH_SHORT).show();

                } else if (!(email.isEmpty() && senha.isEmpty())) {
                    mFirebaseAuth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener(Signup_Form.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(Signup_Form.this, "Senha ou E-mail inválidos, tente novamente!", Toast.LENGTH_SHORT).show();
                            } else {
                                startActivity(new Intent(Signup_Form.this, MainActivity.class));
                            }
                        }
                    });
                } else {
                    Toast.makeText(Signup_Form.this, "Ocorreu um Erro!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Signup_Form.this, Login_form.class);
                startActivity(i);
            }
        });


    }
}

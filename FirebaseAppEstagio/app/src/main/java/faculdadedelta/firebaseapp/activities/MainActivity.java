package faculdadedelta.firebaseapp.activities;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import faculdadedelta.firebaseapp.R;
import faculdadedelta.firebaseapp.adapters.BookGalleryAdapter;
import faculdadedelta.firebaseapp.helpers.BookDatabaseHelper;
import faculdadedelta.firebaseapp.models.Book;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private GridView gallery;
    private BookGalleryAdapter bookGalleryAdapter;
    private ArrayList<Book> books;
    private FloatingActionButton addBook;
    private BookDatabaseHelper bookDatabaseHelper;
    private AVLoadingIndicatorView loader;
    private TextView check_availibity, checkInternet;


    ////

//    EditText emailId, password;
  //  Button btnSignUp;
        Button btnDeslogar;
//    TextView tvSignIn, tvSignUp;
    FirebaseAuth mFirebaseAuth;

    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        loader = (AVLoadingIndicatorView) findViewById(R.id.loader);
        gallery = (GridView) findViewById(R.id.book_gallery);
        books = new ArrayList<>();
        bookDatabaseHelper = new BookDatabaseHelper(this);
        bookGalleryAdapter = new BookGalleryAdapter(books, this);
        addBook = (FloatingActionButton) findViewById(R.id.add_book);
        check_availibity = (TextView) findViewById(R.id.book_check_availability);
        checkInternet = (TextView) findViewById(R.id.check_connectivity);
        gallery.setAdapter(bookGalleryAdapter);
        bookDatabaseHelper.all(checkInternet, check_availibity, loader, books, this, gallery);
        addBook.setOnClickListener(this);


        ///////

        mFirebaseAuth = FirebaseAuth.getInstance();
//        emailId = findViewById(R.id.etEmail);
//        password = findViewById(R.id.etPassword);
//        btnSignUp = findViewById(R.id.btnSignUp);

        btnDeslogar = findViewById(R.id.deslogar);

//        tvSignIn = findViewById(R.id.tvSignIn);
//        tvSignUp = findViewById(R.id.tvSignUp);


        btnDeslogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   FirebaseAuth.getInstance().signOut();
                   Intent intLogin = new Intent(MainActivity.this, Login_form.class);
                   startActivity(intLogin);
            }
        });


   /*     btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emailId.getText().toString();
                String senha = password.getText().toString();
                if (email.isEmpty()) {
                    emailId.setError("Insira um e-mail!");
                    emailId.requestFocus();
                } else if (senha.isEmpty()) {
                    password.setError("Insira uma senha!");
                    password.requestFocus();
                } else if (email.isEmpty() && senha.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Os campos estão vazios!", Toast.LENGTH_SHORT).show();

                } else if (!(email.isEmpty() && senha.isEmpty())) {
                    mFirebaseAuth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Cadastro falhou!", Toast.LENGTH_SHORT).show();
                            } else {
                                startActivity(new Intent(MainActivity.this, MainActivity.class));
                            }
                        }
                    });
                } else {
                    Toast.makeText(MainActivity.this, "Ocorreu um Erro!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Login_form.class);
                startActivity(i);
            }
        });

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Signup_Form.class);
                startActivity(i);
            }
        });*/
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_book:
                startActivity(new Intent(MainActivity.this, AddBook.class));
                break;
        }
    }
}

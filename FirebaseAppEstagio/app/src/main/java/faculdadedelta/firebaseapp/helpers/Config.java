package faculdadedelta.firebaseapp.helpers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import faculdadedelta.firebaseapp.R;

public class Config {
    public static final String BOOK_ID = "book_id";
    public static final String BOOK_TITLE = "book_title";
    public static final String BOOK_AUTHOR = "book_author";
    public static final String BOOK_RATING = "book_rating";
    public static final String BOOK_COVER_PHOTO_URL = "book_cover_photo_url";

    public final static String TITLE_EMPTY_MESSAGE = "É necessário um título!";
    public final static String AUTHOR_EMPTY_MESSAGE = "Inserir uma descrição é obrigatório!";
    public final static String RATING_ZERO_MESSAGE = "Dê alguma nota!";
    public final static String IMAGE_URL_NULL_MESSAGE = "Escolha uma imagem!";
    public final static String BOOK_ADD_SUCCESS_MSG = "Imagem Adicionada com sucesso!";
    public final static String BOOK_UPDATE_SUCCESS_MSG = "Imagem atualizada com sucesso!";
    public final static String BOOK_REMOVE_SUCCESS_MSG = "Imagem removida com sucesso!";
    public final static String BOOK_REMOVE_DIALOG_TITLE = "Remoção de Imagem";
    public final static String BOOK_REMOVE_DIALOG_MSG = "Tem certeza?";
    public final static String BOOK_REMOVE_TEXT = "Remover";
    public final static String BOOK_REMOVE_CANCEL_TEXT = "Cancelar";
    public static final int COVER_PHOTO_REQUEST_CODE = 1000;


    public final static String DATABASE_REFERENCE = "books";
    public final static String STORAGE_PATH = "cover_photo/";
    public final static String BOOK_ADDING_MESSAGE = "Adicionando Imagem...";
    public final static String BOOK_UPDATING_MESSAGE = "Atualizando Imagem...";

    //mostrar a toast personalizada
    public static void showToast(String message, Context context) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_toast_layout, null);
        Toast toast = new Toast(context);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        ((TextView) view.findViewById(R.id.message)).setText(message);
        toast.show();
    }

    //checar se a internet esta disponível
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected()) ? true : false;
    }
}

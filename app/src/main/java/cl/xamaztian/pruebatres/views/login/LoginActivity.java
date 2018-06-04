package cl.xamaztian.pruebatres.views.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.firebase.ui.auth.AuthUI;

import java.util.Arrays;

import cl.xamaztian.pruebatres.R;
import cl.xamaztian.pruebatres.data.Nodes;
import cl.xamaztian.pruebatres.views.main.MainActivity;
import cl.xamaztian.pruebatres.data.CurrentUser;

public class LoginActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        if (new CurrentUser().getCurrentUser() != null)
            logged();
        else
            signUp();
    }

    private void signUp() {
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(Arrays.asList(
                                new AuthUI.IdpConfig.EmailBuilder().build()/*,
                                new AuthUI.IdpConfig.GoogleBuilder().build(),
                                new AuthUI.IdpConfig.FacebookBuilder().build(),
                                new AuthUI.IdpConfig.TwitterBuilder().build()*/))
                        .build(),
                RC_SIGN_IN);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (RC_SIGN_IN == requestCode){
            if (RESULT_OK == resultCode){
                logged();
            }
        }
    }

    private void logged(){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}

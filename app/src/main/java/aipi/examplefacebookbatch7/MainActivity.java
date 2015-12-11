package aipi.examplefacebookbatch7;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class MainActivity extends AppCompatActivity implements FacebookCallback<LoginResult> {


    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private TextView textViewProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);

        callbackManager = CallbackManager.Factory.create();

        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("user_friends");
        loginButton.registerCallback(callbackManager, this);

        textViewProfile = (TextView) findViewById(R.id.textViewProfile);

    }

    @Override
    public void onSuccess(LoginResult loginResult) {

        Profile profile = Profile.getCurrentProfile();
        if (profile != null) {
            Toast.makeText(this, "Estas logueado", Toast.LENGTH_LONG).show();
            textViewProfile.setText("Name: " + profile.getName());


        } else {
            Toast.makeText(this, "No estas logueado", Toast.LENGTH_LONG).show();
            textViewProfile.setText("");
        }


    }

    @Override
    public void onCancel() {

    }

    @Override
    public void onError(FacebookException error) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}

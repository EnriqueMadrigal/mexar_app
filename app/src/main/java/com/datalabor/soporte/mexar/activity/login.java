package com.datalabor.soporte.mexar.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.datalabor.soporte.mexar.Common;
import com.datalabor.soporte.mexar.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.facebook.login.widget.LoginButton;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONObject;

public class login extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {


private static final String TAG = "SignInActivityA";
private static final int RC_SIGN_IN = 9001;

private GoogleApiClient mGoogleApiClient;

private ProgressDialog mProgressDialog;
private LoginButton loginButton;

    CallbackManager callbackManager;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;

        // Configure sign-in to request the user's ID, email address, and basic
// profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();


        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();


        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("email");
        loginButton.setText("Ingresar con Facebook");
        loginButton.setHeight(40);
        // If using in a fragment

        callbackManager = CallbackManager.Factory.create();
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                GraphRequest request = GraphRequest.newMeRequest(
                loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                Log.v("LoginActivity", response.toString());

                                // Application code
                                try {
                                    String email = object.getString("email");
                                    //String birthday = object.getString("birthday"); // 01/31/1980 format
                                    saveEmail(email);
                                    setLoginType("facebook");

                                }


                                catch (Exception e)
                                {
                                    Log.d(TAG,"Can not read json facebook");
                                    //return null;

                                }

                            }
                        });

            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });





    }

    public void sig_in(View view) {


        signIn();
        /*
        Log.d("Sig_in Pressed","1");
        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        finish();
        startActivity(intent);
        */
    }


    public void signTest(View view)
    {
        Log.d("Sig_in Pressed","1");
        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        finish();
        startActivity(intent);

    }

    ///////// Google sig in
    @Override
    public void onStart() {
        super.onStart();

        // Revisar si ya se ha firmado anteriormente
        if (checkEmail())
        {
            Intent intent = new Intent();
            intent.setClass(this, MainActivity.class);
            finish();
            startActivity(intent);

        }


        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (opr.isDone()) {
            // If the user's cached credentials are valid, the OptionalPendingResult will be "done"
            // and the GoogleSignInResult will be available instantly.
            Log.d(TAG, "Got cached sign-in");
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        } else {
            // If the user has not previously signed in on this device or the sign-in has expired,
            // this asynchronous branch will attempt to sign in the user silently.  Cross-device
            // single sign-on will occur in this branch.
            /*
            showProgressDialog();
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(GoogleSignInResult googleSignInResult) {
                    hideProgressDialog();
                    handleSignInResult(googleSignInResult);
                }
            });
            */
        }
    }
////////////

    @Override
    protected void onResume() {
        super.onResume();
        hideProgressDialog();
    }

    //////////////
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }

        // Si es de Facebook
        callbackManager.onActivityResult(requestCode, resultCode, data);


    }


    /////////////
    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
     //       mStatusTextView.setText(getString(R.string.signed_in_fmt, acct.getEmail()));
          //  updateUI(true);
            saveEmail(acct.getEmail());
            setLoginType("google");
            Intent intent = new Intent();
            intent.setClass(this, MainActivity.class);
            finish();
            startActivity(intent);

        } else {
            // Signed out, show unauthenticated UI.
           // updateUI(false);
            showInternetErrorDialog();
        }
    }
    // [END handleSignInResult]

    // [START signIn]
    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    ////////
    private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        // [START_EXCLUDE]
                       // updateUI(false);
                        // [END_EXCLUDE]
                    }
                });
    }

    ////////

    private void revokeAccess() {
        Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        // [START_EXCLUDE]
                       // updateUI(false);
                        // [END_EXCLUDE]
                    }
                });
    }
// [END revokeAccess]

    ///////

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
        showInternetErrorDialog();
    }


//////////

    @Override
    protected void onStop() {
        super.onStop();
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    ////////////////
    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }
    }

    ///////////


    protected void showInternetErrorDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Error: \nSin acceso a Internet. \nComprueba la conexión de red y  vuelve a Intentarlo")
                .setCancelable(false)
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }


    protected void showWarningDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Advertencia:\n" +  message)
                .setCancelable(false)
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }


    public void showemail(View view)
    {
        final Dialog alertDialog = new Dialog(this);
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        LayoutInflater factory = LayoutInflater.from(context);
        final View view2 = factory.inflate(R.layout.email_layout, null);

        final Button emailAceptar = (Button) view2.findViewById(R.id.emailAceptar);
        final Button emailCancelar = (Button) view2.findViewById(R.id.emailCancelar);
        final EditText emailText = (EditText) view2.findViewById(R.id.emailCorreo);

        alertDialog.setContentView(view2);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        alertDialog.show();

        emailAceptar.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {

                String curEmail = emailText.getText().toString();

                if(isValidMail(curEmail))
                {


                   saveEmail(curEmail);
                    setLoginType("email");
                    alertDialog.dismiss();
                    Intent intent = new Intent();
                    intent.setClass(context, MainActivity.class);
                    finish();
                    startActivity(intent);
                }

                else
                {

                    showWarningDialog("El correo no es valido..");
                }


            }
        });


        emailCancelar.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {

                alertDialog.dismiss();
            }
        });


    }



private void saveEmail(String newEmail)
{

    SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences( this );
    SharedPreferences.Editor editor = sharedPref.edit();

    editor.putString( Common.VAR_USER_NAME, newEmail );
    editor.commit();
}

    private void setLoginType(String newloginType)
    {

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences( this );
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString( Common.VAR_LOGIN_TYPE, newloginType );
        editor.commit();
    }




private boolean checkEmail()
{
    SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences( this );

    String user_uid = sharedPref.getString( Common.VAR_USER_NAME, "" );

if (user_uid.length()>0) return true;
    else return  false;
}


    private boolean isValidMail(String email) {
        boolean check;
        Pattern p;
        Matcher m;

        String EMAIL_STRING = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        p = Pattern.compile(EMAIL_STRING);

        m = p.matcher(email);
        check = m.matches();

        if(!check) {
     //       txtEmail.setError("Not Valid Email");
            Log.d(TAG,"Not Valid Email");
        }
        return check;
    }


}

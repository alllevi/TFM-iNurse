package tfm.muuinf.viciano.lledo.alejandro.inurse.gui.comun;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.commons.lang3.StringUtils;

import tfm.muuinf.viciano.lledo.alejandro.inurse.R;
import tfm.muuinf.viciano.lledo.alejandro.inurse.dal.ServiciosDAL;
import tfm.muuinf.viciano.lledo.alejandro.inurse.dto.UsuarioDTO;
import tfm.muuinf.viciano.lledo.alejandro.inurse.gui.pacientes.MenuPacientesActivity;
import tfm.muuinf.viciano.lledo.alejandro.inurse.gui.personal.MenuPersonalActivity;


public class LoginActivity extends InurseActivity {

    private UserLoginTask mAuthTask = null;
    private SharedPreferences sharedpreferences;

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onStart() {
        super.onStart();

        sharedpreferences = getSharedPreferences(ConstantesComun.SHARED_PREFS_FILE, ConstantesComun.CONTEXT_MODE_PRIVATE);

        /*Comprobamos si existe una sesión abierta por usuario
             ->Si existe entramos en el menu correspondiente
         */
        final String usuarioKey = sharedpreferences.getString(ConstantesComun.USUARIO_KEY, "");
        final String pacienteKey = sharedpreferences.getString(ConstantesComun.PACIENTE_KEY, "");

        if (StringUtils.isNotBlank(usuarioKey)) {
            final Intent intent;
            if (StringUtils.isNotBlank(pacienteKey)) {
                intent = new Intent(this, MenuPacientesActivity.class);
            } else {
                intent = new Intent(this, MenuPersonalActivity.class);
            }
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);

        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(final TextView textView, final int id, final KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        final Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View view) {
                attemptLogin();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */

    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        final String email = mEmailView.getText().toString();
        final String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isUserValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            if (checkInternet()) {
                mAuthTask = new UserLoginTask(email, password);
                mAuthTask.execute((Void) null);
            }
        }
    }

    private boolean isUserValid(final String user) {
        return user.length() > 4;
    }

    private boolean isPasswordValid(final String password) {
        return password.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            final int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(final Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(final Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;
        private String tipoUsuario;
        private UsuarioDTO usuarioDTO;

        UserLoginTask(final String email, final String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(final Void... params) {

            try {
                final ServiciosDAL dal = new ServiciosDAL();
                usuarioDTO = dal.getPacienteDAO().autenticarUsuario(mEmail, mPassword);

                if (usuarioDTO == null) {
                    return false;
                } else {
                    tipoUsuario = usuarioDTO.getTipo();
                    return true;
                }
            } catch (final Exception e) {
                return false;
            }
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);

            if (success) {
                final SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString(ConstantesComun.USUARIO_KEY, usuarioDTO.getKey().toString());
                if (tipoUsuario.equals("PERSONAL")) {
                    final Intent intent = new Intent(getBaseContext(), MenuPersonalActivity.class);
                    startActivity(intent);
                } else if (tipoUsuario.equals("PACIENTE")) {
                    editor.putString(ConstantesComun.PACIENTE_KEY, usuarioDTO.getPacienteKey().toString());
                    final Intent intent = new Intent(getBaseContext(), MenuPacientesActivity.class);
                    startActivity(intent);
                }
                editor.apply();
                finish();
            } else {
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }
}


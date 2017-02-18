package tfm.muuinf.viciano.lledo.alejandro.inurse.gui.pacientes;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import tfm.muuinf.viciano.lledo.alejandro.inurse.R;

public class InfoHospitalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_hospital);
        WebView myWebView = (WebView) findViewById(R.id.info_hosp_webview);
        myWebView.setInitialScale(1);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setSupportZoom(true);
        webSettings.setUseWideViewPort(true);
        if (checkInternet()) {
            myWebView.loadUrl("http://www.hospital-lafe.com/");
        }
    }

    private boolean checkInternet() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        } else {
            Toast.makeText(getApplicationContext(), "Compruebe su conexión a internet", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}

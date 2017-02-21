package tfm.muuinf.viciano.lledo.alejandro.inurse.gui.pacientes;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import tfm.muuinf.viciano.lledo.alejandro.inurse.R;
import tfm.muuinf.viciano.lledo.alejandro.inurse.gui.comun.InurseActivity;

public class InfoHospitalActivity extends InurseActivity {

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
}

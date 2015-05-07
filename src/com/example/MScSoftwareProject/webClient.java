package com.example.MScSoftwareProject;

import android.webkit.WebView;
import android.webkit.WebViewClient;
/**
 * Created by Johnathan McCartney
 * User:    Johnathan McCartney
 * Date:    26/10/2014.
 * This programme allows the web browser to opened within the application rather than launching a new browser
 */
public class webClient extends WebViewClient {

    //constructor loads url
    public boolean uniqueBrowser(WebView web, String url) {
        web.loadUrl(url);
        return true;
   }
}

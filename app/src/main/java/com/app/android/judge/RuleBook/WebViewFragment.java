package com.app.android.judge.RuleBook;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.app.android.judge.R;

/**
 * Created by jaren on 5/20/2017.
 */

public class WebViewFragment extends Fragment {

    private String pdfUrl;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_rulebook_webview, container, false);
        setHasOptionsMenu(true);
        WebView rulebookWebView = (WebView) rootView.findViewById(R.id.rulebook_webview);
        rulebookWebView.getSettings().setJavaScriptEnabled(true);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            pdfUrl = bundle.getString("rulePdf");
        }
        String drive = "http://drive.google.com/viewerng/viewer?embedded=true&url=";
        rulebookWebView.loadUrl(drive + pdfUrl);
        return rootView;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_search).setVisible(false);
        menu.findItem(R.id.action_reset).setVisible(false);
        menu.findItem(R.id.action_settings).setVisible(false);
        super.onPrepareOptionsMenu(menu);
    }
}

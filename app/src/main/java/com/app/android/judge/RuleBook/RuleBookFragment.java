package com.app.android.judge.RuleBook;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.app.android.judge.MainActivity;
import com.app.android.judge.R;


public class RuleBookFragment extends Fragment {

    private final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_rulebook, container, false);
        setHasOptionsMenu(true);

        Button basicButton = (Button) rootView.findViewById(R.id.basic_button);
        Button comprehensiveButton = (Button) rootView.findViewById(R.id.comprehensive_button);

        String basicRuleFile = "MTG Basic Rules.pdf";
        String comprehensiveRuleFile = "MTG Comprehensive Rules.pdf";

        final String basicStorageRef = "https://firebasestorage.googleapis.com/v0/b/judge-1b409.appspot.com/o/MTG%20Basic%20Rules.pdf?alt=media&token=e7d989b3-8d51-4f6d-88a0-26b3eed0f37d";
        final String comprehensiveStorageRef = "https://firebasestorage.googleapis.com/v0/b/judge-1b409.appspot.com/o/MTG%20Comprehensive%20Rules.pdf?alt=media&token=ae2fc455-24c8-4652-bd42-17eb89a812c0";
        final String url = "https://www.google.com";


        basicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebViewFragment webViewFragment = new WebViewFragment();
                Bundle bundle = new Bundle();
                bundle.putString("rulePdf", basicStorageRef);
                webViewFragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_container, webViewFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        comprehensiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebViewFragment webViewFragment = new WebViewFragment();
                Bundle bundle = new Bundle();
                bundle.putString("rulePdf", comprehensiveStorageRef);
                webViewFragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_container, webViewFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        return rootView;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_search).setVisible(false);
        menu.findItem(R.id.action_reset).setVisible(false);
        menu.findItem(R.id.action_settings).setVisible(false);
        super.onPrepareOptionsMenu(menu);
    }

//    private boolean checksFileExistence(String fileName) {
//        File checkFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + "/" + fileName);
//        return checkFile.exists();
//    }
//
//    private void openPdfFile(String file) {
//        File pdfFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + File.separator +
//                file);
//        Uri path = Uri.fromFile(pdfFile);
//        Intent pdfOpenintent = new Intent(Intent.ACTION_VIEW);
//        pdfOpenintent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//        pdfOpenintent.setDataAndType(path, getResources().getString(R.string.open_path));
//        Intent openWithIntent = Intent.createChooser(pdfOpenintent, getResources().getString(R.string.open_with));
//        try {
//            startActivity(openWithIntent);
//        } catch (ActivityNotFoundException e) {
//            Log.i(LOG_TAG, pdfFile.toString() + getResources().getString(R.string.start_failed));
//        }
//    }
//
//    private void downloadPdfFile(Uri uri) {
//        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri.toString()));
//        try {
//            startActivity(browserIntent);
//        } catch (ActivityNotFoundException e) {
//            Toast.makeText(getContext(), R.string.pdf_broke, Toast.LENGTH_SHORT).show();
//        }
//
//    }
}
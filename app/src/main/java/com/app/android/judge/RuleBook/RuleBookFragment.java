package com.app.android.judge.RuleBook;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.app.android.judge.MainActivity;
import com.app.android.judge.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;


public class RuleBookFragment extends Fragment {

    private StorageReference basicStorageRef;
    private StorageReference comprehensiveStorageRef;
    private String basicRuleFile;
    private String comprehensiveRuleFile;
    private final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_rule_book, container, false);
        setHasOptionsMenu(true);

        Button basicButton = (Button) rootView.findViewById(R.id.basic_button);
        Button comprehensiveButton = (Button) rootView.findViewById(R.id.comprehensive_button);

        basicRuleFile = "MTG Basic Rules.pdf";
        comprehensiveRuleFile = "MTG Comprehensive Rules.pdf";

        FirebaseStorage storage = FirebaseStorage.getInstance();
        basicStorageRef = storage.getReferenceFromUrl("gs://judge-1b409.appspot.com/").child(basicRuleFile);
        comprehensiveStorageRef = storage.getReferenceFromUrl("gs://judge-1b409.appspot.com/").child(comprehensiveRuleFile);

        basicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checksFileExistence(basicRuleFile)) {
                    openPdfFile(basicRuleFile);
                } else {
                    basicStorageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            downloadPdfFile(uri);
                        }
                    });
                }
            }
        });

        comprehensiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checksFileExistence(comprehensiveRuleFile)) {
                    openPdfFile(comprehensiveRuleFile);
                } else {
                    comprehensiveStorageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            downloadPdfFile(uri);
                        }
                    });
                }
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

    private boolean checksFileExistence(String fileName) {
        File checkFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + "/" + fileName);
        return checkFile.exists();
    }

    private void openPdfFile(String file) {
        File pdfFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + File.separator +
                file);
        Uri path = Uri.fromFile(pdfFile);
        Intent pdfOpenintent = new Intent(Intent.ACTION_VIEW);
        pdfOpenintent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        pdfOpenintent.setDataAndType(path, getResources().getString(R.string.open_path));
        Intent openWithIntent = Intent.createChooser(pdfOpenintent, getResources().getString(R.string.open_with));
        try {
            startActivity(openWithIntent);
        } catch (ActivityNotFoundException e) {
            Log.i(LOG_TAG, pdfFile.toString() + getResources().getString(R.string.start_failed));
        }
    }

    private void downloadPdfFile(Uri uri) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri.toString()));
        try {
            startActivity(browserIntent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(getContext(), R.string.pdf_broke, Toast.LENGTH_SHORT).show();
        }

    }
}
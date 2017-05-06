package com.example.android.judge.RuleBook;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.android.judge.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


public class RuleBookFragment extends Fragment {

    Button basicButton;
    Button comprehensiveButton;
    View rootView;
    StorageReference basicStorageRef;
    StorageReference comprehensiveStorageRef;
    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_rule_book, container, false);
        setHasOptionsMenu(true);

        progressBar = (ProgressBar) rootView.findViewById(R.id.rule_progress_bar);

        basicButton = (Button) rootView.findViewById(R.id.basic_button);
        comprehensiveButton = (Button) rootView.findViewById(R.id.comprehensive_button);

        FirebaseStorage storage = FirebaseStorage.getInstance();
        basicStorageRef = storage.getReferenceFromUrl("gs://judge-1b409.appspot.com/").child("MTG Basic Rules.pdf");
        comprehensiveStorageRef = storage.getReferenceFromUrl("gs://judge-1b409.appspot.com/").child("MTG Comprehensive Rules.pdf");

        basicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                basicStorageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getContext(), uri.toString(), Toast.LENGTH_SHORT).show();
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri.toString()));
                        try {
                            startActivity(browserIntent);
                        } catch (ActivityNotFoundException e) {
                            // Instruct the user to install a PDF reader here, or something
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), "FAIL", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

        comprehensiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comprehensiveStorageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getContext(), uri.toString(), Toast.LENGTH_SHORT).show();
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri.toString()));
                        try {
                            startActivity(browserIntent);
                        } catch (ActivityNotFoundException e) {
                            // Instruct the user to install a PDF reader here, or something
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), "FAIL", Toast.LENGTH_SHORT).show();

                    }
                });
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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
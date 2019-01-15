package com.divakrishna.museumpos.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.divakrishna.museumpos.MainActivity;
import com.divakrishna.museumpos.R;
import com.divakrishna.museumpos.ViewDataActivity;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScannerFragment extends Fragment implements ZXingScannerView.ResultHandler{

    private ZXingScannerView mScannerView;

    public static ScannerFragment newInstance() {
        ScannerFragment fragment = new ScannerFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ((MainActivity) getActivity()).setActionBarTitle("Pindai");

        mScannerView = new ZXingScannerView(getActivity());
        //return inflater.inflate(R.layout.fragment_scanner, container, false);
        return mScannerView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void handleResult(Result rawResult) {
        Intent i = new Intent(getActivity().getBaseContext(),
                ViewDataActivity.class);
        i.putExtra("no_inventaris", rawResult.getText().toString());

        startActivity(i);
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }
}

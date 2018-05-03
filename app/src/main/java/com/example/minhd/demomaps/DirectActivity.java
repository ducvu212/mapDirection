package com.example.minhd.demomaps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.example.minhd.demomaps.api.ApiConnector;
import com.example.minhd.demomaps.api.SearchAutocompletePlaceReponse;

import io.reactivex.disposables.Disposable;

public class DirectActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView rcDestination, rcOrigin;
    public static EditText edtOrigin;
    public static EditText edtDestination;
    private AddressAdapter addressOrigin;
    private  AddressDestinationAdapter addressDestination;
    private SearchAutocompletePlaceReponse responseOrigin;
    private SearchAutocompletePlaceReponse responseDestination;
    private Disposable disposableOrigin;
    private Disposable disposableDestination;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direct);
        initViews();
    }

    private void initViews() {
        rcDestination = (RecyclerView) findViewById(R.id.rc_address_destination);
        rcOrigin = (RecyclerView) findViewById(R.id.rc_address_origin);

        findViewById(R.id.btn_open_map).setOnClickListener(this);

        addressOrigin = new AddressAdapter(new AddressAdapter.IAddressAdapter() {
            @Override
            public int getCount() {
                if (responseOrigin == null || responseOrigin.getPredictionses() == null) {
                    return 0;
                }
                return responseOrigin.getPredictionses().size();
            }

            @Override
            public SearchAutocompletePlaceReponse.Prediction getData(int position) {
                return responseOrigin.getPredictionses().get(position);
            }
        });

        addressDestination = new AddressDestinationAdapter(new AddressDestinationAdapter.IAddressAdapter() {
            @Override
            public int getCount() {
                if (responseDestination == null || responseDestination.getPredictionses() == null) {
                    return 0;
                }
                return responseDestination.getPredictionses().size();
            }

            @Override
            public SearchAutocompletePlaceReponse.Prediction getData(int position) {
                return responseDestination.getPredictionses().get(position);
            }
        });
        LinearLayoutManager managerOrigin = new LinearLayoutManager(this);
        LinearLayoutManager managerDestination = new LinearLayoutManager(this);
        rcOrigin.setLayoutManager(managerOrigin);
        rcDestination.setLayoutManager(managerDestination);

        rcOrigin.setAdapter(addressOrigin);
        rcDestination.setAdapter(addressDestination);

        edtOrigin = (EditText) findViewById(R.id.edt_origin);
        edtDestination = (EditText) findViewById(R.id.edt_destination);


        edtOrigin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (disposableOrigin != null && !disposableOrigin.isDisposed()) {
                    disposableOrigin.dispose();
                }
                String content = s.toString().trim();
                disposableOrigin = ApiConnector.getInstance().searchPlace(content)
                        .subscribe(response -> {
                            responseOrigin = response;
                            addressOrigin.notifyDataSetChanged();
                        });
            }
        });

        edtDestination.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (disposableDestination != null && !disposableDestination.isDisposed()) {
                    disposableDestination.dispose();
                }
                String content = s.toString().trim();
                disposableDestination = ApiConnector.getInstance().searchPlace(content)
                        .subscribe(response -> {
                            responseDestination = response;
                            addressDestination.notifyDataSetChanged();
                        });
            }
        });

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(DirectActivity.this, MapsActivity.class);
        Bundle bundle = new Bundle();
        String origin = edtOrigin.getText().toString() ;
        String destination = edtDestination.getText().toString() ;
        bundle.putString("origin", origin) ;
        bundle.putString("destination", destination);
        intent.putExtra("Bundel", bundle) ;
        startActivity(intent);
    }
}

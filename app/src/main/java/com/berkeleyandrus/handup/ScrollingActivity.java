package com.berkeleyandrus.handup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class ScrollingActivity extends AppCompatActivity {

    private ArrayList<ShelterDTO> shelters = new ArrayList<>();
    private DataStore dataStore = new DataStore();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Button changeLocationButton = findViewById(R.id.change_location_button);

        String[] cities = {"Portland, Oregon", "Salt Lake City, Utah"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.select_dialog_item, cities);
        final AutoCompleteTextView autoTextEntry = findViewById(R.id.change_location_field);
        autoTextEntry.setThreshold(1);
        autoTextEntry.setAdapter(adapter);
        autoTextEntry.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    changeLocationButton.performClick();
                    return true;
                }
                return false;
            }
        });

        autoTextEntry.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                changeLocationButton.performClick();
            }
        });


        changeLocationButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (autoTextEntry.getVisibility() == View.VISIBLE) {
                    changeLocationButton.setText("Change Location");
                    changeLocation(autoTextEntry.getText().toString());
                    autoTextEntry.setText("");
                    autoTextEntry.setVisibility(View.GONE);
                    if (view != null) {
                        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }
                }
                else {
                    changeLocationButton.setText("Save");
                    autoTextEntry.setVisibility(View.VISIBLE);
                    autoTextEntry.setFocusableInTouchMode(true);
                    autoTextEntry.requestFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(autoTextEntry, InputMethodManager.SHOW_IMPLICIT);

                }
            }
        });

        initShelters();
        initRecyclerView();

    }

    public void showDetails() {
        Intent intent = new Intent(this, ShelterDetailView.class);
        startActivity(intent);
    }

    private void initShelters() {
        this.shelters = dataStore.getPortlandShelters();
    }

    private void changeLocation(String newLocation) {
        TextView locationLabel = findViewById(R.id.location_label);
        if (newLocation.toLowerCase().contains("salt lake")) {
            this.shelters = dataStore.getSaltLakeShelters();
            locationLabel.setText("Showing results for Salt Lake City, UT");
        }
        else if (newLocation.toLowerCase().contains("portland")) {
            this.shelters = dataStore.getPortlandShelters();
            locationLabel.setText("Showing results for Portland, OR");
        }
        else {
            this.shelters.removeAll(this.shelters);
            locationLabel.setText("No results for " + newLocation + "\nTry 'Portland' or 'Salt Lake City'");
        }
        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.test_list);
        ShelterViewAdapter adapter = new ShelterViewAdapter(shelters, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}

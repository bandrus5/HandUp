package com.berkeleyandrus.handup;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;


@RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
public class ShelterDetailView extends Activity {
    private ShelterDTO shelter;

    public ShelterDetailView() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shelter_detail_view);
        shelter = DataStore.selectedDTO;
        if (shelter == null) {
            finish();
        }

        TextView shelterName = findViewById(R.id.shelter_name);
        shelterName.setText(shelter.getName());
        TextView shelterAddress = findViewById(R.id.shelter_address);
        shelterAddress.setText(shelter.getAddress());
        TextView shelterPhone = findViewById(R.id.shelter_phone_number);
        shelterPhone.setText(shelter.getPhoneNumber());
        TextView shelterWebsite = findViewById(R.id.shelter_website);
        shelterWebsite.setText(shelter.getWebsite());



        int numServiceNeeds = shelter.getServices().size();
        int numFoodNeeds = shelter.getFoods().size();
        int numSupplyNeeds = shelter.getSupplies().size();
        int numClothingNeeds = shelter.getClothes().size();
        TextView serviceNeeds = findViewById(R.id.num_service_needs);
        serviceNeeds.setText(numServiceNeeds + " current needs");
        TextView foodNeeds = findViewById(R.id.num_food_needs);
        foodNeeds.setText(numFoodNeeds + " current needs");
        TextView supplyNeeds = findViewById(R.id.num_supply_needs);
        supplyNeeds.setText(numSupplyNeeds + " current needs");
        TextView clothingNeeds = findViewById(R.id.num_clothing_needs);
        clothingNeeds.setText(numClothingNeeds + " current needs");

        View serviceBar = findViewById(R.id.service_bar);
        ViewGroup.LayoutParams serviceParams = serviceBar.getLayoutParams();
        serviceParams.width = 3 + (numServiceNeeds * 8);
        serviceBar.setLayoutParams(serviceParams);
        View foodBar = findViewById(R.id.food_bar);
        ViewGroup.LayoutParams foodParams = foodBar.getLayoutParams();
        foodParams.width = 3 + (numFoodNeeds * 8);
        foodBar.setLayoutParams(foodParams);
        View supplyBar = findViewById(R.id.supplies_bar);
        ViewGroup.LayoutParams supplyParams = supplyBar.getLayoutParams();
        supplyParams.width = 3 + (numSupplyNeeds * 8);
        supplyBar.setLayoutParams(supplyParams);
        View clothingBar = findViewById(R.id.clothing_bar);
        ViewGroup.LayoutParams clothingParams = clothingBar.getLayoutParams();
        clothingParams.width = 3 + (numClothingNeeds * 8);
        clothingBar.setLayoutParams(clothingParams);

        ListView foodNeedDetails = findViewById(R.id.food_need_details);
        ArrayAdapter foodAdapter = new ArrayAdapter<String>(this,
                R.layout.my_list_item,
                shelter.getFoodDetails());
        foodNeedDetails.setAdapter(foodAdapter);
        setListViewHeightBasedOnChildren(foodNeedDetails, shelter.getFoodDetails().size());
        ListView supplyNeedDetails = findViewById(R.id.supply_need_details);
        ArrayAdapter supplyAdapter = new ArrayAdapter<String>(this,
                R.layout.my_list_item,
                shelter.getSupplyDetails());
        supplyNeedDetails.setAdapter(supplyAdapter);
        setListViewHeightBasedOnChildren(supplyNeedDetails, shelter.getSupplyDetails().size());
        ListView serviceNeedDetails = findViewById(R.id.service_need_details);
        ArrayAdapter serviceAdapter = new ArrayAdapter<String>(this,
                R.layout.my_list_item,
                shelter.getServiceDetails());
        serviceNeedDetails.setAdapter(serviceAdapter);
        setListViewHeightBasedOnChildren(serviceNeedDetails, shelter.getServiceDetails().size());
        ListView clothesNeedDetails = findViewById(R.id.clothing_need_details);
        ArrayAdapter clothesAdapter = new ArrayAdapter<String>(this,
                R.layout.my_list_item,
                shelter.getClothesDetails());
        clothesNeedDetails.setAdapter(clothesAdapter);
        setListViewHeightBasedOnChildren(clothesNeedDetails, shelter.getClothesDetails().size());
    }

    public static void setListViewHeightBasedOnChildren(ListView listView, int numItems) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, ListView.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        return;
//        final String TAG = "LIST_VIEW";
//
//        Log.v(TAG, "Num Items: " + numItems);
//        ListAdapter listAdapter = listView.getAdapter();
//        if (listAdapter == null) {
//            return;
//        }
//        int totalHeight = numItems * 75;
//        Log.v(TAG, "Size: " + totalHeight);
//        ViewGroup.LayoutParams params = listView.getLayoutParams();
//        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
//        listView.setLayoutParams(params);
//        listView.requestLayout();
    }
}

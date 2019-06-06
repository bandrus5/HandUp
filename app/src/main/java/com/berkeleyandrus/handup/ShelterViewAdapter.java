package com.berkeleyandrus.handup;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ShelterViewAdapter extends RecyclerView.Adapter<ShelterViewAdapter.ViewHolder>{

    private ArrayList<ShelterDTO> shelters = new ArrayList<>();
    private Context mContext;

    public ShelterViewAdapter(ArrayList<ShelterDTO> shelters, Context mContext) {
        this.shelters = shelters;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_shelter, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.shelterName.setText(shelters.get(position).getName());
//        holder.shelterPhone.setText(shelters.get(position).getPhoneNumber());
        holder.shelterAddress.setText(shelters.get(position).getAddress());
//        holder.shelterWebsite.setText(shelters.get(position).getWebsite());
        holder.numServiceNeeds = shelters.get(position).getServices().size();
        holder.numFoodNeeds = shelters.get(position).getFoods().size();
        holder.numSupplyNeeds = shelters.get(position).getSupplies().size();
        holder.numClothingNeeds = shelters.get(position).getClothes().size();
        holder.serviceNeeds.setText(holder.numServiceNeeds + " current needs");
        holder.foodNeeds.setText(holder.numFoodNeeds + " current needs");
        holder.supplyNeeds.setText(holder.numSupplyNeeds + " current needs");
        holder.clothingNeeds.setText(holder.numClothingNeeds + " current needs");
        ViewGroup.LayoutParams serviceParams = holder.serviceBar.getLayoutParams();
        serviceParams.width = 3 + (holder.numServiceNeeds * 8);
        if (serviceParams.width > 115) {
            serviceParams.width = 115;
        }
        holder.serviceBar.setLayoutParams(serviceParams);
        ViewGroup.LayoutParams foodParams = holder.foodBar.getLayoutParams();
        foodParams.width = 3 + (holder.numFoodNeeds * 8);
        if (foodParams.width > 115) {
            foodParams.width = 115;
        }
        holder.foodBar.setLayoutParams(foodParams);
        ViewGroup.LayoutParams supplyParams = holder.supplyBar.getLayoutParams();
        supplyParams.width = 3 + (holder.numSupplyNeeds * 8);
        if (supplyParams.width > 115) {
            supplyParams.width = 115;
        }
        holder.supplyBar.setLayoutParams(supplyParams);
        ViewGroup.LayoutParams clothingParams = holder.clothingBar.getLayoutParams();
        clothingParams.width = 3 + (holder.numClothingNeeds * 8);
        if (clothingParams.width > 115) {
            clothingParams.width = 115;
        }
        holder.clothingBar.setLayoutParams(clothingParams);

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataStore ds = new DataStore();
                ds.SelectDTO(shelters.get(position));
                Context context = view.getContext();
                ScrollingActivity activity = (ScrollingActivity) context;
                activity.showDetails();
            }
        });
    }

    @Override
    public int getItemCount() {
        return shelters.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView shelterName;
//        TextView shelterPhone;
        TextView shelterAddress;
//        TextView shelterWebsite;
        LinearLayout parentLayout;
        int numServiceNeeds;
        int numFoodNeeds;
        int numSupplyNeeds;
        int numClothingNeeds;
        TextView serviceNeeds;
        TextView foodNeeds;
        TextView supplyNeeds;
        TextView clothingNeeds;
        View serviceBar;
        View foodBar;
        View supplyBar;
        View clothingBar;

        public ViewHolder(View itemView) {
            super(itemView);
            parentLayout = itemView.findViewById(R.id.parent_layout);
            shelterName = itemView.findViewById(R.id.shelter_name);
//            shelterPhone = itemView.findViewById(R.id.shelter_phone_number);
            shelterAddress = itemView.findViewById(R.id.shelter_address);
//            shelterWebsite = itemView.findViewById(R.id.shelter_website);
            serviceNeeds = itemView.findViewById(R.id.num_service_needs);
            foodNeeds = itemView.findViewById(R.id.num_food_needs);
            supplyNeeds = itemView.findViewById(R.id.num_supply_needs);
            clothingNeeds = itemView.findViewById(R.id.num_clothing_needs);
            serviceBar = itemView.findViewById(R.id.service_bar);
            foodBar = itemView.findViewById(R.id.food_bar);
            supplyBar = itemView.findViewById(R.id.supplies_bar);
            clothingBar = itemView.findViewById(R.id.clothing_bar);
        }
    }
}

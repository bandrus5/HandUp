package com.berkeleyandrus.handup;

import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

public class ShelterDTO {
    private String name;
    private String phoneNumber;
    private String address;
    private String website;

    private ArrayList<String> services = new ArrayList<>();
    private ArrayList<String> foods = new ArrayList<>();
    private ArrayList<String> supplies = new ArrayList<>();
    private ArrayList<String> clothes = new ArrayList<>();

    static private String[] possibleServices = {"Counseling", "Prayer Group", "Bible Study",
                                                "Companionship", "Translating", "ARP Facilitators",
                                                "English Teachers", "Job Coaching"};

    static private String[] possibleFoods = {"Protein", "Fresh Produce", "Vitamins", "Water", "Carbs",
                                              "Canned Goods", "Starch"};

    static private String[] possibleSupplies = {"Can opener", "Band-Aids", "Pain killers", "Shampoo",
                                                "Soap", "Feminine hygiene products", "Toothbrushes",
                                                "Toothpaste", "Toilet paper", "Sanitary wipes",
                                                "Paper towels",  "Comb", "Brush",
                                                "Deodorant", "Lip balm"};

    static private String[] possibleClothes = {"Hat", "Gloves", "Scarf", "Underwear", "Socks", "Shoes",
                                                "Shirts", "Pants", "Coats"};



    public ShelterDTO(String name, String phoneNumber, String address, String website) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.website = website;
        Random rand = new Random();
        initList(services, possibleServices, rand, 4);
        initList(foods, possibleFoods, rand, 5);
        initList(supplies, possibleSupplies, rand, 3);
        initList(clothes, possibleClothes, rand, 5);
    }

    private void initList(ArrayList<String> list1, String[] list2, Random rand, int probability) {
        for (int i = 0; i < list2.length; i++) {
            if (rand.nextInt() % 3 < 2) {
                for (int j = 0; j <= rand.nextInt() % probability; j++) {
                    list1.add(list2[i]);
                }
            }
        }
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() { return phoneNumber; }

    public String getWebsite() { return website; }

    public String getAddress() {
        return address;
    }


    public ArrayList<String> getFoods() {
        return foods;
    }

    public ArrayList<String> getClothes() {
        return clothes;
    }

    public ArrayList<String> getServices() {
        return services;
    }

    public ArrayList<String> getSupplies() {
        return supplies;
    }

    public ArrayList<String> getFoodDetails() {
        return getNeedsDetails(foods);
    }

    public ArrayList<String> getClothesDetails() {
        return getNeedsDetails(clothes);
    }

    public ArrayList<String> getSupplyDetails() {
        return getNeedsDetails(supplies);
    }

    public ArrayList<String> getServiceDetails() {
        return getNeedsDetails(services);
    }

    private ArrayList<String> getNeedsDetails(ArrayList<String> needs) {
        ArrayList<String> details = new ArrayList<>();
        String TAG = "DETAIL_GENERATOR";
        if (needs.size() == 1) {
            details.add(needs.get(0) + " (1)");
            return details;
        }
        if (needs.size() == 0) {
            return details;
        }
        int counter = 1;
        for (int i = 1; i < needs.size(); i++) {
            if (needs.get(i).equals(needs.get(i-1))) {
                counter++;
            }
            else {
                String next = needs.get(i - 1) + " (" + counter + ")";
                details.add(next);
                counter = 1;
            }
            if (i == needs.size() - 1) {
                String next = needs.get(i) + " (" + counter + ")";
                details.add(next);
            }
        }
        return details;
    }
}

package com.example.foodplanner.models;

import java.util.HashMap;
import java.util.Map;

public class CountryData {
    private Map<String,String> imgLinks , countryNames;

    public CountryData() {
        imgLinks = new HashMap<>();
        countryNames = new HashMap<>();
        imgLinks.put("England","https://www.themealdb.com/images/icons/flags/big/64/gb.png");
        imgLinks.put("America","https://www.themealdb.com/images/icons/flags/big/64/us.png");
        imgLinks.put("France","https://www.themealdb.com/images/icons/flags/big/64/fr.png");
        imgLinks.put("Canada","https://www.themealdb.com/images/icons/flags/big/64/ca.png");
        imgLinks.put("Jamaica","https://www.themealdb.com/images/icons/flags/big/64/jm.png");
        imgLinks.put("China","https://www.themealdb.com/images/icons/flags/big/64/cn.png");
        imgLinks.put("Netherlands","https://www.themealdb.com/images/icons/flags/big/64/nl.png");
        imgLinks.put("Egypt","https://www.themealdb.com/images/icons/flags/big/64/eg.png");
        imgLinks.put("Greece","https://www.themealdb.com/images/icons/flags/big/64/gr.png");
        imgLinks.put("India","https://www.themealdb.com/images/icons/flags/big/64/in.png");
        imgLinks.put("Ireland","https://www.themealdb.com/images/icons/flags/big/64/ie.png");
        imgLinks.put("Italy","https://www.themealdb.com/images/icons/flags/big/64/it.png");
        imgLinks.put("Japan","https://www.themealdb.com/images/icons/flags/big/64/jp.png");
        imgLinks.put("Kenya","https://www.themealdb.com/images/icons/flags/big/64/kn.png");
        imgLinks.put("Malaysia","https://www.themealdb.com/images/icons/flags/big/64/my.png");
        imgLinks.put("Mexico","https://www.themealdb.com/images/icons/flags/big/64/mx.png");
        imgLinks.put("Morocco","https://www.themealdb.com/images/icons/flags/big/64/ma.png");
        imgLinks.put("Croatia ","https://www.themealdb.com/images/icons/flags/big/64/hr.png");
        imgLinks.put("Portugal","https://www.themealdb.com/images/icons/flags/big/64/pt.png");
        imgLinks.put("Russia","https://www.themealdb.com/images/icons/flags/big/64/ru.png");
        imgLinks.put("Spain","https://www.themealdb.com/images/icons/flags/big/64/es.png");
        imgLinks.put("Thailand","https://www.themealdb.com/images/icons/flags/big/64/th.png");
        imgLinks.put("Vietnam","https://www.themealdb.com/images/icons/flags/big/64/vn.png");
        imgLinks.put("Turkey","https://www.themealdb.com/images/icons/flags/big/64/tr.png");
        imgLinks.put("Tunisia","https://www.themealdb.com/images/icons/flags/big/64/tn.png");
        imgLinks.put("Poland","https://www.themealdb.com/images/icons/flags/big/64/pl.png");

        countryNames.put("American","America");
        countryNames.put("British","England");
        countryNames.put("Canadian","Canada");
        countryNames.put("Chinese","China");
        countryNames.put("Croatian","Croatia");
        countryNames.put("Dutch","Netherlands");
        countryNames.put("Egyptian","Egypt");
        countryNames.put("French","France");
        countryNames.put("Greek","Greece");
        countryNames.put("Indian","India");
        countryNames.put("Irish","Ireland");
        countryNames.put("Italian","Italy");
        countryNames.put("Jamaican","Jamaica");
        countryNames.put("Japanese","Japan");
        countryNames.put("Kenyan","Kenya");
        countryNames.put("Malaysian","Malaysia");
        countryNames.put("Mexican","Mexico");
        countryNames.put("Moroccan","Morocco");
        countryNames.put("Polish","Poland");
        countryNames.put("Portuguese","Portugal");
        countryNames.put("Russian","Russia");
        countryNames.put("Spanish","Spain");
        countryNames.put("Thai","Thailand");
        countryNames.put("Tunisian","Tunisia");
        countryNames.put("Turkish","Turkey");
        countryNames.put("Vietnamese","Vietnam");
    }

    public Map<String, String> getImgLinks() {
        return imgLinks;
    }

    public Map<String, String> getCountryNames() {
        return countryNames;
    }
}

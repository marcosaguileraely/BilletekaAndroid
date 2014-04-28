package com.cool4code.billeteka;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by marcosantonioaguilerely on 4/27/14.
 */
@ParseClassName("Country")
public class Country extends ParseObject{

    private String rank;
    private String country;
    private String population;

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public static ParseQuery<Country> getQuery() {
        return ParseQuery.getQuery(Country.class);
    }
}

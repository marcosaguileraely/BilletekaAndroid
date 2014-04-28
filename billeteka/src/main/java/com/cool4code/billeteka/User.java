package com.cool4code.billeteka;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by marcosantonioaguilerely on 4/27/14.
 */
@ParseClassName("User")
public class User extends ParseObject{
    public String getText() {
        return getString("text");
    }

    public void setText(String value) {
        put("text", value);
    }

    /*aqui van tantos campos del modelo como se necesiten*/

    public static ParseQuery<User> getQuery() {
        return ParseQuery.getQuery(User.class);
    }
}

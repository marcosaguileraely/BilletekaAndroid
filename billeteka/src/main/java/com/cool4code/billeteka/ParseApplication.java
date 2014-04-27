package com.cool4code.billeteka;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;
/**
 * Created by marcosantonioaguilerely on 4/26/14.
 */
public class ParseApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        // Add your initialization code here
        Parse.initialize(this, "XdWuoWJKZ4rL1hv2lGfgB9IP4fy5po65xayBwwNW", "Ky1GlA3n4jiEnTTLreBjDzo94J5tLshdnZ9sDR1e");
        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        // If you would like all objects to be private by default, remove this line.
        defaultACL.setPublicReadAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);
    }
}

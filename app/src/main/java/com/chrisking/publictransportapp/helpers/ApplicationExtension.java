package com.chrisking.publictransportapp.helpers;

import android.app.Application;
import android.content.Context;
import com.flurry.android.FlurryAgent;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import transportapisdk.models.Itinerary;


public class ApplicationExtension extends Application {
    private boolean mIsCommute = false;
    private boolean mIsCommuteHome = false;
    private Itinerary mItinerary;
    private ArrayList<Itinerary> mItineraries;
    private static Context mContext;

    public boolean getIsCommute() {return mIsCommute;}
    public boolean getIsCommuteHome() {return mIsCommuteHome;}
    public Itinerary getItinerary() {return mItinerary;}
    public ArrayList<Itinerary> getItineraries() {return mItineraries;}
    public static String ClientId(){ return Credentials.ClientId; }
    public static String ClientSecret(){
        return Credentials.ClientSecret;
    }

    public void setIsCommute(boolean isCommute, boolean isCommuteHome) {this.mIsCommute = isCommute; this.mIsCommuteHome = isCommuteHome;}
    public void setItinerary(Itinerary itinerary) {this.mItinerary = itinerary;}
    public void setItineraries(ArrayList<Itinerary> itineraries) {this.mItineraries = itineraries;}

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;

        if (!FirebaseApp.getApps(this).isEmpty())
        {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        }

        new FlurryAgent.Builder()
                .build(this, Credentials.FlurryKey);
    }

    public static Context getContext(){
        return mContext;
    }
}
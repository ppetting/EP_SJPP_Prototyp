package de.epprojekt.ep_sjpp_prototyp.Helferlein;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceHelferlein {

    private static final String MY_PREFERENCE_NAME = "de.epprojekt.ep_sjpp_prototyp";



    public static void saveTotalInPref(Context context, int total, String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, total);
        editor.apply();
    }

    public static int loadTotalFromPref (Context context, String key){
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getInt(key,0);
    }

    public static boolean firstAppStart(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        if (sharedPreferences.getBoolean(key, true)) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(key, false);
            editor.apply();
            return true;
        } else {
            return false;
        }
    }

}
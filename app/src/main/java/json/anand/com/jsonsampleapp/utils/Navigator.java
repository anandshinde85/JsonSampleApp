package json.anand.com.jsonsampleapp.utils;

import android.app.Activity;
import android.content.Intent;

import json.anand.com.jsonsampleapp.activities.JsonResultsActivity;

/**
 * Navigator class used for navigating from one activity to another
 *
 * @author Anand Shinde
 */
public class Navigator {
    public static void launchHome(Activity callingActivity) {
        Intent appHomeIntent = new Intent(callingActivity, JsonResultsActivity.class);
        callingActivity.startActivity(appHomeIntent);
    }
}

package wordlist.example.com.commons.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * This class provides functionality for network connectivity check
 *
 * @author Anand Shinde
 */
public class NetworkManager {

    /**
     * Method which will return if device is connected to internet or not
     *
     * @return true if connected otherwise false
     */
    public static boolean isConnected() {
        ConnectivityManager manager = (ConnectivityManager) WordListApplication.getInstance().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = manager.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected();
    }
}

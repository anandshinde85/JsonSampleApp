package wordlist.example.com.commons.utils;

import android.app.Application;

/**
 * Class holding application state
 *
 * @author Anand Shinde
 */
public class WordListApplication extends Application {
    private static WordListApplication instance;

    public WordListApplication() {
        super();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = (WordListApplication) getApplicationContext();
    }

    public static WordListApplication getInstance() {
        return instance;
    }
}

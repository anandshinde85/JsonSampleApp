package wordlist.example.com.commons.utils;


/**
 * Rest adapter for creating retrofit's rest adapter
 *
 * @author Anand Shinde
 */
public class RestAdapter {
    private static volatile RestAdapter instance = null;
    private static retrofit.RestAdapter restAdapter;

    private RestAdapter() {
    }

    private static RestAdapter getInstance() {
        if (instance == null) {
            synchronized (RestAdapter.class) {
                if (instance == null) {
                    instance = new RestAdapter();
                    createRestAdapter();
                }
            }
        }
        return instance;
    }

    public static retrofit.RestAdapter getRestAdapter() {
        return getInstance().restAdapter;
    }

    /**
     * Method to create rest adapter with base url
     */
    private static void createRestAdapter() {
        retrofit.RestAdapter.Builder builder = new retrofit.RestAdapter.Builder();
        restAdapter = builder.setEndpoint(Constants.BASE_URL).build();
    }
}

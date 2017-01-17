package wordlist.example.com.commons.utils;


import com.squareup.okhttp.Cache;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.IOException;

import retrofit.client.OkClient;

/**
 * Rest adapter for creating retrofit's rest adapter
 *
 * @author Anand Shinde
 */
public class RestAdapter {
  private static volatile RestAdapter instance = null;
  private static retrofit.RestAdapter restAdapter;
  private static OkHttpClient okHttpClient;

  private RestAdapter() {
  }

  private static RestAdapter getInstance() {
    if (instance == null) {
      synchronized (RestAdapter.class) {
        if (instance == null) {
          instance = new RestAdapter();
          initOkHttpClient();
          createRestAdapter();
        }
      }
    }
    return instance;
  }

  public static retrofit.RestAdapter getRestAdapter() {
    return getInstance().restAdapter;
  }

  private static void initOkHttpClient() {
    okHttpClient = new OkHttpClient();
    createCacheForOkHTTP();
  }

  /**
   * Method to create rest adapter with base url
   */
  private static void createRestAdapter() {
    retrofit.RestAdapter.Builder builder = new retrofit.RestAdapter.Builder();
    builder.setClient(new OkClient(okHttpClient));
    builder.setLogLevel(retrofit.RestAdapter.LogLevel.FULL);
    restAdapter = builder.setEndpoint(Constants.BASE_URL).build();
  }

  private static final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
    @Override
    public Response intercept(Chain chain) throws IOException {
      Response originalResponse = chain.proceed(chain.request());
      return originalResponse.newBuilder()
          .header("Cache-Control",
              String.format("max-age=%d, only-if-cached, max-stale=%d", 120, 0))
          .build();
    }
  };

  private static void createCacheForOkHTTP() {
    Cache cache = null;
    cache = new Cache(getDirectory(), 1024 * 1024 * 10);
    okHttpClient.setCache(cache);
    okHttpClient.networkInterceptors().add(REWRITE_CACHE_CONTROL_INTERCEPTOR);
  }

  // returns the file to store cached details
  private static File getDirectory() {
    return new File("location");
  }
}

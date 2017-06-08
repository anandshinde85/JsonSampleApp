package wordlist.example.com.commons.utils;


import android.util.Log;


import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Rest adapter for creating retrofit's rest adapter
 *
 * @author Anand Shinde
 */
public class RestAdapter {
  private static volatile RestAdapter instance = null;
  private static Retrofit restAdapter;
  private static OkHttpClient okHttpClient;
  private static OkHttpClient cacheClient;
  private static long SIZE_OF_CACHE = 10 * 1024 * 1024; // 10 MB

  private RestAdapter() {
  }

  private static RestAdapter getInstance() {
    if (instance == null) {
      synchronized (RestAdapter.class) {
        if (instance == null) {
          instance = new RestAdapter();
          //initOkHttpClient();
          initCacheController();
          createRestAdapter();
        }
      }
    }
    return instance;
  }

  public static Retrofit getRestAdapter() {
    return getInstance().restAdapter;
  }

  private static void initCacheController() {
    // Create Cache
    Cache cache = null;
    try {
      cache = new Cache(new File(WordListApplication.getInstance().getCacheDir(), "http"),
          SIZE_OF_CACHE);
    } catch (Exception e) {
      Log.e(RestAdapter.class.getSimpleName(), "Could not create Cache!", e);
    }

    // Create OkHttpBuilder
    OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
    okHttpBuilder.cache(cache);
    okHttpBuilder.connectTimeout(30, TimeUnit.SECONDS);
    okHttpBuilder.readTimeout(30, TimeUnit.SECONDS);

    // Create HTTPLogggingInterceptor
    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    okHttpBuilder.addInterceptor(loggingInterceptor);
    okHttpBuilder.addNetworkInterceptor(mCacheControlInterceptor);

    // Create OkHttpClient
    cacheClient = okHttpBuilder.build();
  }


//  /**
//   * Method to create rest adapter with base url
//   */
//  private static void createRestAdapter() {
//    Retrofit.Builder builder = new Retrofit.Builder();
//    builder.setClient(new OkClient(okHttpClient));
//    builder.setLogLevel(Retrofit.LogLevel.FULL);
//    restAdapter = builder.setEndpoint(Constants.BASE_URL).build();
//  }


  /**
   * Method to create rest adapter with base url
   */
  private static void createRestAdapter() {
    // Create Executor
    Executor executor = Executors.newCachedThreadPool();
    Retrofit.Builder builder = new Retrofit.Builder();
    builder.callbackExecutor(executor);
    builder.baseUrl(Constants.BASE_URL);
    builder.client(cacheClient);
    builder.addConverterFactory(GsonConverterFactory.create());
    restAdapter = builder.build();
  }

  private static final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
    @Override
    public Response intercept(Chain chain) throws IOException {
      Response originalResponse = chain.proceed(chain.request());
      return originalResponse.newBuilder()
          .header("Cache-Control",
              String.format("max-age=%d, only-if-cached, max-stale=%d", 120, 2419200))
          .build();
    }
  };

  private static final Interceptor mCacheControlInterceptor = new Interceptor() {
    @Override
    public Response intercept(Chain chain) throws IOException {
      Request request = chain.request();

      // Add Cache Control only for GET methods
      if (request.method().equals("GET")) {
        if (!NetworkManager.isConnected()) {
          // 1 day
          request.newBuilder()
              .header("Cache-Control", "only-if-cached")
              .build();
        } else {
          // 4 weeks stale
          request.newBuilder()
              .header("Cache-Control", "public, max-stale=2419200")
              .build();
        }
      }

      Response response = chain.proceed(request);

      // Re-write response CC header to force use of cache
      return response.newBuilder()
//          .header("Cache-Control", "public, max-age=86400") // 1 day
          .header("Cache-Control", "public, max-age=0") // 1 day
          .build();
    }
  };

  // returns the file to store cached details
  private static File getDirectory() {
    return new File("location");
  }
}

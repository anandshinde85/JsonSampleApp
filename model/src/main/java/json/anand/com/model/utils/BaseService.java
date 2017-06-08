package json.anand.com.model.utils;

import android.os.Handler;
import android.os.Looper;

import java.util.Map;

import okhttp3.Headers;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Base class for all the services.
 *
 * @author Anand Shinde
 */

public abstract class BaseService<RESPONSE> {
  private static final String TAG = "BaseService";
  /**
   * Tentative index of conversion id inside header list.
   */
  private static final String CONVERSATION_ID = "ConversationId";

  private final Callback<RESPONSE> callback = new Callback<RESPONSE>() {
    /**
     * Invoked for a received HTTP response.
     * <p>
     * Note: An HTTP response may still indicate an application-level failure such as a 404 or 500.
     * Call {@link Response#isSuccessful()} to determine if the response indicates success.
     *
     * @param call
     * @param response
     */
    @Override
    public void onResponse(Call<RESPONSE> call, retrofit2.Response<RESPONSE> response) {
      final String conversationId = getConversationId(response);
      if (response != null && response.isSuccessful() && response.body() != null) {
        storeInDBorCache(response.body(), response, conversationId);
        handleResponse(response.body(), response, conversationId);
      } else {
        handleError(response, conversationId);
      }
    }

    /**
     * Invoked when a network exception occurred talking to the server or when an unexpected
     * exception occurred creating the request or processing the response.
     *
     * @param call
     * @param t
     */
    @Override
    public void onFailure(Call<RESPONSE> call, Throwable t) {
//      final String conversationId = getConversationId(call.);
//      if (error.getCause() != null && error.getCause() instanceof ConversionException) {
//        handleJsonSyntaxError(conversationId);
//        return;
//      }
//      handleError(error, conversationId);
    }
  };

  /**
   * Method to retrieve conversation id from response
   *
   * @param response - retrofit response
   * @return - conversation id
   */
  private String getConversationId(retrofit2.Response<RESPONSE> response) {
    Headers headers = response.headers();
    return headers.get(CONVERSATION_ID);
  }

  /**
   * This method is used to execute new service with Retrofit's response using SXMTelematicsCallback
   *
   * @param conversationId
   */
  protected void handleJsonSyntaxError(final String conversationId) {
    // TODO: Implement handle json syntax error
  }

  /**
   * Method to handle the retrofit error and return appropriate BaseError
   *
   * @param response       - retrofit response
   * @param conversationId - unque conversation id
   */
  private void handleError(retrofit2.Response<RESPONSE> response, String conversationId) {
//    BaseError restBaseError = null;
//    String code = Constants.EMPTY_STRING;
//    String message = "Unexpected error occurred. Please try again later.";
//    ArrayList<String> faultInfo = new ArrayList<>();
//
//    //Worst case scenario where response itself null or its kind is null.
//    if (response == null || response.errorBody() == null) {
//      String description = StatusError.UNEXPECTED_ERROR.getName();
//      faultInfo.add(description);
//      handleError(
//          new BaseError(code, message, faultInfo, serviceRequestId, conversationId),
//          conversationId);
//      return;
//    }
//    try {
//      restBaseError = (BaseError) error.getBodyAs(BaseError.class);
//      if (null != restBaseError) {
//        restBaseError.setConversationId(conversationId);
//        restBaseError.setServiceRequestId(serviceRequestId);
//      }
//    } catch (Exception e) {
//      // Not handling error as this might be retrofit error, instead we will pass the message
//      Log.e(TAG, "handleError: ", e);
//    }
//    if (restBaseError != null) {
//      restBaseError.setRawResponse(error.getResponse());
//      faultInfo.add(error.getMessage());
//      restBaseError.setFaultInfo(faultInfo);
//      handleError(restBaseError, conversationId);
//    } else {
//      BaseError sxmError = new BaseError(error.getMessage());
//      faultInfo.add(error.getMessage());
//      sxmError.setFaultInfo(faultInfo);
//      sxmError.setRawResponse(error.getResponse());
//      sxmError.setServiceRequestId(serviceRequestId);
//      sxmError.setConversationId(conversationId);
//      handleError(sxmError, conversationId);
//    }
//    if (restBaseError != null && restBaseError.getStatus() ==
//        TOKEN_EXPIRED_STATUS) {
//      BusProvider.getUIBusInstance().post(new TokenExpired());
//    }
  }

  /**
   * Method to check cached/db response if available before executing service
   *
   * @param conversationId - conversation id
   */
  public final void request(final String conversationId) {
    final RESPONSE response = getFromDBorCache();
    if (response != null) {
      Handler handler = new Handler(Looper.getMainLooper());
      handler.post(new Runnable() {
        @Override
        public void run() {
          retrofit2.Response<RESPONSE> retrofitResponse = retrofit2.Response.success(response);
          handleResponse(response, retrofitResponse, conversationId);
        }
      });
      // Read from cache so no need to execute service, simply return
      return;
    }
    execute(callback);
  }

  /**
   * Override this method to get response stored in cache or db
   *
   * @return RESPONSE object
   */
  protected RESPONSE getFromDBorCache() {
    return null;
  }

  /**
   * Override this method for requests wherein we have to store the response in cache or db
   *
   * @param response         - response parsed object
   * @param retrofitResponse - retrofit response
   * @param conversationId   - conversation id
   */
  protected void storeInDBorCache(RESPONSE response, retrofit2.Response<RESPONSE> retrofitResponse,
                                  String conversationId) {
  }

  /**
   * Handle successful response returned by service
   *
   * @param responseEntity   - specified response object
   * @param retrofitResponse - retrofit response
   * @param conversationId   - conversation id
   */
  protected abstract void handleResponse(RESPONSE responseEntity, retrofit2.Response<RESPONSE>
      retrofitResponse, String conversationId);

  /**
   * Handle failure requests
   *
   * @param BaseError      - error object
   * @param conversationId - conversation id
   */
  protected abstract void handleError(BaseError BaseError, String conversationId);

  /**
   * Method to execute service calls
   *
   * @param callback - retrofit callback
   */
  protected abstract void execute(Callback<RESPONSE> callback);

  /**
   * For the successful response needs to be overridden by concrete class.
   *
   * @param jsonMappedResponse parsed MAP object.
   * @param rawResponse        Raw Response.
   */
  protected void handleResponse(Map<String, Object> jsonMappedResponse, Response rawResponse) {
  }
}
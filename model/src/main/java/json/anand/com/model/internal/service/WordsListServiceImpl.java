package json.anand.com.model.internal.service;

import android.util.Log;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import json.anand.com.model.internal.rest.WordsListAPI;
import json.anand.com.model.service.WordsListService;
import json.anand.com.model.utils.BaseError;
import json.anand.com.model.utils.BaseService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wordlist.example.com.commons.server.entities.Word;
import wordlist.example.com.commons.server.entities.WordsResponse;
import wordlist.example.com.commons.utils.RestAdapter;

/**
 * Implementation class for Word list service
 *
 * @author Anand Shinde
 */
public class WordsListServiceImpl extends BaseService<WordsResponse> implements WordsListService {
  private static final String TAG = "WordsListServiceImpl";
  private CallbackHandler handler;

  @Override
  public void getWordList(CallbackHandler handler) {
    this.handler = handler;
    request(UUID.randomUUID().toString());
  }

  /**
   * Handle successful response returned by service
   *
   * @param responseEntity   - specified response object
   * @param retrofitResponse - retrofit response
   * @param conversationId   - conversation id
   */
  @Override
  protected void handleResponse(WordsResponse responseEntity,
                                Response<WordsResponse> retrofitResponse, String conversationId) {
    handler.onSuccess(responseEntity.getWords(), conversationId);
  }

  /**
   * Handle failure requests
   *
   * @param BaseError      - error object
   * @param conversationId - conversation id
   */
  @Override
  protected void handleError(BaseError BaseError, String conversationId) {
    handler.onFailure(conversationId);
  }

  /**
   * Method to execute service calls
   *
   * @param callback - retrofit callback
   */
  @Override
  protected void execute(Callback<WordsResponse> callback) {
    // Synchronous request
    final Call<WordsResponse> call =
        RestAdapter.getRestAdapter().create(WordsListAPI.class).getWordsList();
    // Calling on separate thread as on main thread it will give network operation on main thread
    // error
    Thread thread = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          WordsResponse wordsResponse = call.execute().body();
          if (wordsResponse != null) {
            handler.onSuccess(wordsResponse.getWords(), "conversationId");
          } else {
            handler.onFailure("conversationId");
          }
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });
    thread.start();

    // asynchronous request
    RestAdapter.getRestAdapter().create(WordsListAPI.class).getWordsList().enqueue(callback);
  }
}
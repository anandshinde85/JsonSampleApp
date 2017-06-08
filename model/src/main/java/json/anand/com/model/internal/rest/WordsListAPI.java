package json.anand.com.model.internal.rest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import wordlist.example.com.commons.server.entities.WordsResponse;

/**
 * Retrofit interface for words list API
 *
 * @author Anand Shinde
 */
public interface WordsListAPI {
  /**
   * Method for retrieving word list using retrofit interface
   */
  @GET("/vocab/words.json")
  Call<WordsResponse> getWordsList();
}


package json.anand.com.model.internal.rest;

import retrofit.Callback;
import retrofit.http.GET;
import wordlist.example.com.commons.server.entities.WordsResponse;

/**
 * Retrofit interface for words list API
 *
 * @author Anand Shinde
 */
public interface WordsListAPI {
    /**
     * Method for retrieving word list using retrofit interface
     *
     * @param callback - retrofit callback for passing on the response
     */
    @GET("/vocab/words.json")
    void getWordsList(Callback<WordsResponse> callback);
}


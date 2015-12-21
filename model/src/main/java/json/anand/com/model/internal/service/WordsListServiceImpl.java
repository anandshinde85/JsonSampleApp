package json.anand.com.model.internal.service;

import java.util.List;

import json.anand.com.model.internal.rest.WordsListAPI;
import json.anand.com.model.service.WordsListService;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import wordlist.example.com.commons.server.entities.Word;
import wordlist.example.com.commons.server.entities.WordsResponse;
import wordlist.example.com.commons.utils.NetworkManager;
import wordlist.example.com.commons.utils.RestAdapter;

/**
 * Implementation class for Word list service
 *
 * @author Anand Shinde
 */
public class WordsListServiceImpl implements WordsListService {
    private CallbackHandler handler;
    private int uniqueId;
    private Callback<WordsResponse> callback = new Callback<WordsResponse>() {
        @Override
        public void success(WordsResponse words, Response response) {
            handler.onSuccess(words.getWords(), uniqueId);
        }

        @Override
        public void failure(RetrofitError error) {
            handler.onFailure(uniqueId);
        }
    };

    public WordsListServiceImpl(CallbackHandler handler, int uniqueId) {
        this.handler = handler;
        this.uniqueId = uniqueId;
    }

    @Override
    public void getWordList() {
        request();
    }

    /**
     * Request made for API call
     */
    private void request() {
        if (!NetworkManager.isConnected()) {
            handler.onFailure(uniqueId);
        }
        execute();
    }

    /**
     * Execute retrofit API
     */
    private void execute() {
        RestAdapter.getRestAdapter().create(WordsListAPI.class).getWordsList(callback);
    }

    /**
     * Callback handler for word list response
     *
     * @author Anand Shinde
     */
    public interface CallbackHandler {
        /**
         * Handle successful response in respective presenter
         *
         * @param response - response list
         * @param uniqueId - request id
         */
        void onSuccess(List<Word> response, int uniqueId);

        /**
         * Handle error in respective presenter
         *
         * @param uniqueId - request id
         */
        void onFailure(int uniqueId);
    }
}

package json.anand.com.model.service;

import java.util.List;

import wordlist.example.com.commons.server.entities.Word;

/**
 * Service for getting words list response
 *
 * @author Anand Shinde
 */
public interface WordsListService {
  /**
   * Method to retrieve word list
   *
   * @param callbackHandler - callback handler
   */
  void getWordList(CallbackHandler callbackHandler);

  /**
   * Callback handler for word list response
   *
   * @author Anand Shinde
   */
  interface CallbackHandler {
    /**
     * Handle successful response in respective presenter
     *
     * @param response - response list
     * @param uniqueId - request id
     */
    void onSuccess(List<Word> response, String uniqueId);

    /**
     * Handle error in respective presenter
     *
     * @param uniqueId - request id
     */
    void onFailure(String uniqueId);
  }
}